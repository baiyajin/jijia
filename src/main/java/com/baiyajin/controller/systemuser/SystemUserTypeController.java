package com.baiyajin.controller.systemuser;

import com.baiyajin.entity.systemuser.SystemUserType;
import com.baiyajin.service.systemuser.SystemUserTypeInterface;
import com.baiyajin.util.IdGenerate;
import com.baiyajin.util.Results;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Api("用户类型")
@Controller
@RequestMapping(value = "/systemUserTypeController")
public class SystemUserTypeController {
    @Autowired
    private SystemUserTypeInterface systemUserTypeInterface;

    /**
     * 新增用户类型
     * @param systemUserType
     * @return
     */
    @ApiOperation(value = "新增用户类型",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\n name（必填） ,jurisdictionId（必填）\t\n" +
                    "请求参数列表为:\t\n{\"name\":\"普通员工\",\"jurisdictionId\":\"1\"}")
    @RequestMapping(value = "/addSystemUserType",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object addSystemUserType(SystemUserType systemUserType){
            systemUserType.setId(IdGenerate.uuid());
            systemUserType.setCreateTime(new Timestamp(System.currentTimeMillis()));
            systemUserType.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            systemUserType.setStatusID("qy");
        try {
            systemUserTypeInterface.insert(systemUserType);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fails");
        }
        return new Results(0,"success");
    }

    /**
     * 删除用户类型
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户类型",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\n id（必填）" +
                    "请求参数列表为:\t\n{\"id\":\"sfdsadf\"}")
    @RequestMapping(value = "/deleteSystemType",method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object deleteSystemType(String id){
        SystemUserType systemUserType = systemUserTypeInterface.selectById(id);
        if (systemUserType == null){
            return new Results(1,"该类别不存在");
        }
        systemUserType.setStatusID("jy");
        try {
            systemUserTypeInterface.updateById(systemUserType);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fails");
        }
        return new Results(0,"success");

    }

    /**
     * 修改用户类型
     * @param systemUserType
     * @return
     */
    @ApiOperation(value = "修改用户类型",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\n id（必填） ,\t\n name（非必填） ,jurisdictionId（非必填）\t\n" +
                    "请求参数列表为:\t\n{\"id\":\"asdfsadf\",\"name\":\"普通员工\",\"jurisdictionId\":\"1\"}")
    @RequestMapping(value = "/updateSystemType",method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object updateSystemType(SystemUserType systemUserType) {
        String id = systemUserType.getId();
        SystemUserType s = systemUserTypeInterface.selectById(id);
        if (s == null){
            return new Results(1,"该类别不存在");
        }
        try {
            systemUserTypeInterface.updateById(systemUserType);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1, "fails");
        }
        return new Results(0,"success");
    }

        /**
     * 查询用户类型列表
     * @return
     */
    @RequestMapping(value = "/findUserTypeList",method = RequestMethod.GET)
    @ResponseBody
    public List<SystemUserType> findUserTypeList(){
        SystemUserType systemUserType = new SystemUserType();
        systemUserType.setStatusID("qy");
        List<SystemUserType> systemUserTypeList = systemUserTypeInterface.selectList(new EntityWrapper<SystemUserType>(systemUserType));
        return systemUserTypeList;
    }

    @ApiOperation(value = "通过用户ID查询用户",notes =
            "请求参数类型为:\t\n body\t\n"+
                    "请求参数说明:\t\n id（必填） " +
                    "请求参数列表为:\t\n{\"id\":\"asdfsadf\"}")
    @RequestMapping(value = "/findUserTypeById")
    @ResponseBody
    public SystemUserType findUserTypeById(String id){
        SystemUserType systemUserType = systemUserTypeInterface.selectById(id);
        return systemUserType;
    };

}
