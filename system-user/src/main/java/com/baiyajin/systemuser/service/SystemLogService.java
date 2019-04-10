package com.baiyajin.systemuser.service;

import com.baiyajin.systemuser.entity.SystemLog;
import com.baiyajin.systemuser.mapper.SystemLogMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogInterface{
}
