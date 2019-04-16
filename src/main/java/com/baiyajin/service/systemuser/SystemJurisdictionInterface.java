package com.baiyajin.service.systemuser;

import com.baiyajin.entity.systemuser.SystemJurisdiction;
import com.baiyajin.util.ReturnModel;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

public interface SystemJurisdictionInterface extends IService<SystemJurisdiction> {

     public int addJurisdiction(SystemJurisdiction systemJurisdiction);

     public ReturnModel updateJurisdiction(Map<String, Object> map);


}
