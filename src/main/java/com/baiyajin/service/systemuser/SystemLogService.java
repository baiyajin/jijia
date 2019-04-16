package com.baiyajin.service.systemuser;

import com.baiyajin.entity.systemuser.SystemLog;
import com.baiyajin.mapper.systemuser.SystemLogMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogInterface{
}
