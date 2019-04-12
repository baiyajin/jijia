package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageMaterialClass;
import com.baiyajin.pagedata.entity.PageMessage;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface PageMessageInterface extends IService<PageMessage> {

    public Integer selectCount(String userId);

    public List<PageMessage> getMessage(Map<String,Object> map);


    public Integer deleteMessage(String ids);

}
