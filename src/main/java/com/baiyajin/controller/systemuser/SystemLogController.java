package com.baiyajin.controller.systemuser;

import com.baiyajin.entity.systemuser.SystemLog;
import com.baiyajin.service.systemuser.SystemLogInterface;
import com.baiyajin.util.IdGenerate;
import com.baiyajin.util.Results;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Api("日志")
@Controller
@RequestMapping(value = "/SystemLogController")
public class SystemLogController {
    @Autowired
    private SystemLogInterface systemLogInterface;

    /**
     * 插入日志
     * @param systemLog
     * @return
     */
    @RequestMapping(value = "/addSystemLog",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object addSystemLog(SystemLog systemLog){
        systemLog.setId(IdGenerate.uuid());
        systemLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        try {
            systemLogInterface.insert(systemLog);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }

    /**
     * 查询日志列表
     * @return
     */
    @RequestMapping(value = "/findSystemLogList",method = RequestMethod.GET)
    @ResponseBody
    public List<SystemLog> findSystemLogList(){
        List<SystemLog> systemLogList = systemLogInterface.selectList(new EntityWrapper<SystemLog>());
        return systemLogList;
    }

    /**
     * 根据ID查询日志
     * @param id
     * @return
     */
    @RequestMapping(value = "/findSystemLogById")
    @ResponseBody
    public SystemLog findSystemLogById(String id){
        SystemLog systemLog = systemLogInterface.selectById(id);
        return systemLog;
    }

}
