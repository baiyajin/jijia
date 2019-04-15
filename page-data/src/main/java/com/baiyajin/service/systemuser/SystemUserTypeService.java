package com.baiyajin.service.systemuser;


import com.baiyajin.entity.systemuser.SystemUserType;
import com.baiyajin.mapper.systemuser.SystemUserTypeMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SystemUserTypeService extends ServiceImpl<SystemUserTypeMapper, SystemUserType> implements SystemUserTypeInterface{
}
