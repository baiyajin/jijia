package com.baiyajin.pagedata.mapper;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.entity.PageMessage;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface PageMessageMapper extends BaseMapper<PageMessage> {

    public  Integer  selectCount(Map<String,Object> map);


    public  Map<String,Object>  getMessage(Map<String,Object> map);


}
