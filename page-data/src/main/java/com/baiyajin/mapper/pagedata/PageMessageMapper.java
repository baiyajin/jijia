package com.baiyajin.mapper.pagedata;

import com.baiyajin.entity.pagedata.PageMessage;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface PageMessageMapper extends BaseMapper<PageMessage> {

    public  Integer  selectCount(Map<String,Object> map);


    public  List<PageMessage>   getMessage(Map<String,Object> map);

    public Integer deleteMessage(Map<String,Object> map);
}
