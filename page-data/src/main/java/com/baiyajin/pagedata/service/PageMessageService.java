package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageMessage;
import com.baiyajin.pagedata.mapper.PageMessageMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PageMessageService extends ServiceImpl<PageMessageMapper,PageMessage> implements PageMessageInterface {


}
