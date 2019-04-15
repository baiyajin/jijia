package com.baiyajin.controller.systemuser;

import com.baiyajin.entity.systemuser.SystemUserType;
import com.baiyajin.service.systemuser.SystemUserTypeInterface;
import com.baiyajin.util.IdGenerate;
import com.baiyajin.util.Results;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

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

    @RequestMapping(value = "/findUserTypeById")
    @ResponseBody
    public SystemUserType findUserTypeById(String id){
        SystemUserType systemUserType = systemUserTypeInterface.selectById(id);
        return systemUserType;
    };

}
