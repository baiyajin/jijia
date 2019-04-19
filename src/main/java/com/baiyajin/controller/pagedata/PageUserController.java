package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageUser;
import com.baiyajin.entity.systemuser.SystemUser;
import com.baiyajin.service.pagedata.PageUserInterface;
import com.baiyajin.util.HashSalt;
import com.baiyajin.util.JWT;
import com.baiyajin.util.PhoneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api("前端用户")
@Controller
@RequestMapping("/PageUserController")
public class PageUserController {

    @Autowired
    private PageUserInterface pageUserInterface;




    /**
     * 前台用户登录
     * @param
     * @return user
     */
    @ApiOperation(value = "前台登录",notes =
            "请求参数类型为:\t\nJSON\t\n"+
                    "请求参数说明:\t\nphone（必填） ,password（必填）\t\n" +
                    "请求参数列表为:\t\n{\"phone\":\"15288102051\",\"password\":\"123\"}")
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
                    m.put("message","登录成功");
                    systemUsers.get(0).setToken(JWT.createJWT(systemUsers.get(0).getId()));
                    m.put("user",systemUsers.get(0));
                    return m;
                }else if(systemUsers.size() == 0){
                    m.put("message","此手机号未注册");
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
            m.put("message","异常"+e.getMessage());
            return m;
        }
    }


    /**
     * 前台用户注册账号
     * @param
     * @return user
     */
    @ApiOperation(value = "前台用户注册账号",notes =
            "请求参数类型为:\t\nJSON\t\n"+
                    "请求参数说明:\t\nphone（必填） ,password（必填）,name（必填）\t\n" +
                    "请求参数列表为:\t\n{\"phone\":\"15288102051\",\"password\":\"123\",\"name\":\"baiyajin\"}")
    @RequestMapping(value = "/registerAccount", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> registerAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody PageUser user) {
        Map<String,Object> m = new HashMap<>();
        try {


            Map<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("phone",user.getPhone());

            List<PageUser> systemUsers = pageUserInterface.selectByMap(objectObjectHashMap);


            if (null == systemUsers || systemUsers.size() == 0) {
                user.setId(UUID.randomUUID().toString().replace("-", ""));
                user.setStatusID("qy");

                String salt = HashSalt.encode(Long.parseLong(user.getPhone()));
                String hashSalt = HashSalt.getMD5(salt);
                String ecPassWord = new SimpleHash("SHA-1", user.getPassword(), hashSalt).toString();
                user.setPassword(ecPassWord);

                user.setCreateTime(new Timestamp(System.currentTimeMillis()));
                pageUserInterface.insert(user);
                m.put("message","注册成功");
                return m;
            }
            m.put("message","此手机号已经被注册");
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            m.put("message","程序异常"+e.getMessage());
            return m;
        }
    }





}



