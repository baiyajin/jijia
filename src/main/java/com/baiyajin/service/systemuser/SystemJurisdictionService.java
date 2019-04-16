package com.baiyajin.service.systemuser;

import com.baiyajin.entity.systemuser.SystemJurisdiction;
import com.baiyajin.mapper.systemuser.SystemJurisdictionMapper;
import com.baiyajin.util.ReturnModel;
import com.baiyajin.util.UUIDUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


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
