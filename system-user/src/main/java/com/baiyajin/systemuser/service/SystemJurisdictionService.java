package com.baiyajin.systemuser.service;

import com.baiyajin.systemuser.entity.SystemJurisdiction;
import com.baiyajin.systemuser.mapper.SystemJurisdictionMapper;
import com.baiyajin.systemuser.mapper.SystemUserMapper;
import com.baiyajin.systemuser.util.ReturnModel;
import com.baiyajin.systemuser.util.UUIDUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class SystemJurisdictionService extends ServiceImpl<SystemJurisdictionMapper,SystemJurisdiction> implements SystemJurisdictionInterface {


    public int addJurisdiction(SystemJurisdiction systemJurisdiction){
        Map<String,Object> reMap = new HashMap<String,Object>();
        systemJurisdiction.setId(UUIDUtils.getUUID());
        return baseMapper.insert(systemJurisdiction);
    }


    public ReturnModel updateJurisdiction(Map<String,Object> map){

        SystemJurisdiction systemJurisdiction = new SystemJurisdiction(map);
        ReturnModel returnModel = new ReturnModel(0);
        if(this.updateById(systemJurisdiction)){
            returnModel = new ReturnModel(1);
        }
        return returnModel;
    }

}
