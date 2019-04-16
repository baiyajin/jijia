package com.baiyajin.service.systemuser;

import com.baiyajin.entity.systemuser.SystemUser;
import com.baiyajin.mapper.systemuser.SystemUserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SystemUserService extends ServiceImpl<SystemUserMapper,SystemUser> implements SystemUserInterface {

    @Override
    public List<Map<String, Object>> loadSystemUserList(Map<String, Object> map) {
        return baseMapper.loadSystemUserList(map);
    }
}
