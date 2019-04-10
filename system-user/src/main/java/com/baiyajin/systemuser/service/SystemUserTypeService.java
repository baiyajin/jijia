package com.baiyajin.systemuser.service;


import com.baiyajin.systemuser.entity.SystemUserType;
import com.baiyajin.systemuser.mapper.SystemUserTypeMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SystemUserTypeService extends ServiceImpl<SystemUserTypeMapper, SystemUserType> implements SystemUserTypeInterface{
}
