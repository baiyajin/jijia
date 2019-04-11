package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.PageUser;
import com.baiyajin.pagedata.service.PageUserInterface;
import com.baiyajin.pagedata.util.HashSalt;
import com.baiyajin.pagedata.util.PhoneUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/PageUserController")
public class PageUserController {

    @Autowired
    private PageUserInterface pageUserInterface;




    @RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        Map<String,Object> m = new HashMap<>();


        try {

            if(null == map.get("password") && "" == map.get("password")){
                m.put("message","密码不能为空");
                return m;
            }

            if(null != map.get("phone") && "" != map.get("phone") && PhoneUtils.isPhone(map.get("phone").toString())){

                String salt = HashSalt.encode(Long.parseLong(map.get("phone").toString()));
                String hashSalt = HashSalt.getMD5(salt);
                String ecPassWord = new SimpleHash("SHA-1", map.get("password").toString(), hashSalt).toString();



                /*通过手机去数据库拿密码作比对*/
                map.remove("password");
                List<PageUser> systemUsers = pageUserInterface.selectByMap(map);

                if(systemUsers.size() > 0 && systemUsers.get(0).getPassword().equals(ecPassWord)){
                    m.put("message","密码不能为空");
                    m.put("user",systemUsers.get(0));
                    return m;
                }else if(systemUsers.size() == 0){
                    m.put("message","此手机号还没注册");
                    return m;
                }else if(!systemUsers.get(0).getPassword().equals(ecPassWord)){
                    m.put("message","密码错误");
                    return m;
                }
            }

            m.put("message","手机号格式不对");
            return m;

        } catch (Exception e) {
            e.printStackTrace();
            m.put("message","程序异常");
            m.put("obj",e.getMessage());
            return m;
        }
    }









}



