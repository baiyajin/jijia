package com.baiyajin.systemuser.mapper;

import com.baiyajin.systemuser.entity.SystemUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface SystemUserMapper extends BaseMapper<SystemUser> {
    List<Map<String , Object>> loadSystemUserList(Map<String , Object> map);


}
