package com.baiyajin.controller.systemuser;

import com.baiyajin.entity.systemuser.SystemUser;
import com.baiyajin.service.systemuser.SystemUserInterface;
import com.baiyajin.util.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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

@Api("后台用户")
@Controller
@RequestMapping("/SystemUserController")
public class SystemUserController {

    @Autowired
    private SystemUserInterface systemUserInterface;

    /**
     * 增加用户
     * @param systemUser
     * @return
     */
    @ApiOperation(value = "增加用户",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\n name（必填） ,phone（必填）,password(必填),userTypeId(必填)\t\n" +
                    "请求参数列表为:\t\n{\"name\":\"用户1\",\"phone\":\"15288102051\",\"password\":\"123\",\"userTypeId\":\"1\"}")
    @RequestMapping(value = "/addSysUser",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object addSysUser(SystemUser systemUser){
        systemUser.setId(IdGenerate.uuid());
        if (StringUtils.isNotBlank(systemUser.getPassword())){
            systemUser.setPassword(PasswordUtils.enPassword(systemUser.getPassword()));
        }
        systemUser.setStatusID("qy");
        systemUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
        systemUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            systemUserInterface.insert(systemUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }

    /**
     * 删除用户，逻辑删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\n id（必填）" +
                    "请求参数列表为:\t\n{\"id\":\"asdfasdfsadf\"}")
    @RequestMapping(value = "/deleteSysUser",method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object deleteSysUser(String id){
        SystemUser systemUser = systemUserInterface.selectById(id);
        if (null == systemUser){
            return new Results(0,"该用户不存在");
        }
        systemUser.setId(id);
        systemUser.setStatusID("jy");

        try {
            systemUserInterface.updateById(systemUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }

    /**
     * 修改用户
     * @param systemUser
     * @return
     */
    @ApiOperation(value = "修改用户",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\n id（必填） , name（非必填） ,phone（非必填）,password(非必填),userTypeId(必填)\t\n" +
                    "请求参数列表为:\t\n{\"id\":\"asdfsad\",\"name\":\"用户1\",\"phone\":\"15288102051\",\"password\":\"123\",\"userTypeId\":\"1\"}")
    @RequestMapping(value = "/updateSysUser",method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object updateSysUser(SystemUser systemUser){
        String id = systemUser.getId();
        SystemUser s = systemUserInterface.selectById(id);
        if (s == null) {
            return new Results(1,"该用户不存在");
        }
            String password = systemUser.getPassword();
            if (StringUtils.isNotBlank(password)){
                systemUser.setPassword(PasswordUtils.enPassword(password));
            }

        try {
            systemUserInterface.updateById(systemUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }


    /**
     * 后台用户登录
     * @param
     * @return
     */
    @ApiOperation(value = "后台用户登录",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\nphone（必填） ,password（必填）\t\n" +
                    "请求参数列表为:\t\n{\"phone\":\"15288102051\",\"password\":\"123\"}")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
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
                List<SystemUser> systemUsers = systemUserInterface.selectByMap(map);

                if(systemUsers.size() > 0 && systemUsers.get(0).getPassword().equals(ecPassWord)){
                    m.put("message","登录成功");
                    m.put("result",0);
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
     * 后台用户注册账号
     * @param
     * @return
     */
    @RequestMapping(value = "/registerAccount", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> registerAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemUser user) {
        Map<String,Object> m = new HashMap<>();
        try {


            Map<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("phone",user.getPhone());

            List<SystemUser> systemUsers = systemUserInterface.selectByMap(objectObjectHashMap);


            if (null == systemUsers || systemUsers.size() == 0) {
                user.setId(UUID.randomUUID().toString().replace("-", ""));
                user.setStatusID("jy");

                String salt = HashSalt.encode(Long.parseLong(user.getPhone()));
                String hashSalt = HashSalt.getMD5(salt);
                String ecPassWord = new SimpleHash("SHA-1", user.getPassword(), hashSalt).toString();
                user.setPassword(ecPassWord);

                user.setCreateTime(new Timestamp(System.currentTimeMillis()));
                systemUserInterface.insert(user);
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

    /**
     * 查询后台用户列表
     * @return
     */
    @RequestMapping(value = "/findSysUserList",method = RequestMethod.GET)
    @ResponseBody
    public List<SystemUser> findSysUserList(){
        SystemUser systemUser = new SystemUser();
        systemUser.setStatusID("qy");
        List<SystemUser> systemUserList = systemUserInterface.selectList(new EntityWrapper<SystemUser>(systemUser));
        return systemUserList;
    }

    /**
     * 根据用户ID查询用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/findSysUserById")
    @ResponseBody
    public SystemUser findSysUserById(String id){
        SystemUser systemUser = systemUserInterface.selectById(id);
        return  systemUser;
    }

}



