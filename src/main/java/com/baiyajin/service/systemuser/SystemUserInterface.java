package com.baiyajin.service.systemuser;

import com.baiyajin.entity.systemuser.SystemUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface SystemUserInterface extends IService<SystemUser> {
    List<Map<String , Object>> loadSystemUserList(Map<String, Object> map);
}
