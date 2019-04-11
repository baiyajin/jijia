package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.entity.PageMessage;
import com.baiyajin.pagedata.mapper.PageMaterialMapper;
import com.baiyajin.pagedata.mapper.PageMessageMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PageMessageService extends ServiceImpl<PageMessageMapper,PageMessage> implements PageMessageInterface {

    public Integer selectCount(Map<String,Object> map){
        return baseMapper.selectCount(map);
    }


}
