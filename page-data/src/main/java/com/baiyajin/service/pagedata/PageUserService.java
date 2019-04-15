package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageUser;
import com.baiyajin.mapper.pagedata.PageUserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PageUserService extends ServiceImpl<PageUserMapper,PageUser> implements PageUserInterface {


}
