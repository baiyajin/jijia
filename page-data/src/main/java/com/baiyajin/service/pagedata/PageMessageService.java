package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageMessage;
import com.baiyajin.mapper.pagedata.PageMessageMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
