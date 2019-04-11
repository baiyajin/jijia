package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageUser;
import com.baiyajin.pagedata.mapper.PageUserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PageUserService extends ServiceImpl<PageUserMapper,PageUser> implements PageUserInterface {


}
