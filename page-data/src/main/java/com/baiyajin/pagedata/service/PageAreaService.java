package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageArea;
import com.baiyajin.pagedata.mapper.PageAreaMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PageAreaService extends ServiceImpl<PageAreaMapper,PageArea> implements PageAreaInterface {

    public Integer save(Map<String,Object> map){
        Object pid = map.get("pid");
        PageArea newPageArea = new PageArea(map);
        if(pid!=null){
            PageArea pageArea = baseMapper.selectById(pid.toString());
            String path = pageArea.getPid()==null?"":pageArea.getPid();
            newPageArea.setPath(path+","+pageArea.getId());
        }
       return baseMapper.insert(newPageArea);
    }

}
