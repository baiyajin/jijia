package com.baiyajin.systemuser.service;

import com.baiyajin.systemuser.entity.SystemJurisdiction;
import com.baiyajin.systemuser.entity.SystemUser;
import com.baiyajin.systemuser.util.ReturnModel;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface SystemJurisdictionInterface extends IService<SystemJurisdiction> {

     public int addJurisdiction(SystemJurisdiction systemJurisdiction);

     public ReturnModel updateJurisdiction(Map<String, Object> map);


}
