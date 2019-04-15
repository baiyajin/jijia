package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.entity.PageMessage;
import com.baiyajin.pagedata.mapper.PageMaterialMapper;
import com.baiyajin.pagedata.mapper.PageMessageMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PageMessageService extends ServiceImpl<PageMessageMapper,PageMessage> implements PageMessageInterface {

    public Integer selectCount(String userId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",userId);
        return baseMapper.selectCount(map);

    }



    public List<PageMessage> getMessage(Map<String,Object> map){

        return baseMapper.getMessage(map);
    }

    public Integer deleteMessage(String ids){
        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);
        return baseMapper.deleteMessage(map);
    }

}
