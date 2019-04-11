package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.entity.PageMaterialClass;
import com.baiyajin.pagedata.mapper.PageMaterialMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PageMaterialService extends ServiceImpl<PageMaterialMapper,PageMaterial> implements PageMaterialInterface {

    @Autowired
    private PageMaterialClassInterface pageMaterialClassInterface;

    public List<MaterialAndClass> getMaterialsAndClass(Map<String,Object> map){
        String id = map.get("id")==null?null:map.get("id").toString();
        map.clear();
        map.put("id",id);
        List<MaterialAndClass> materialAndClassList = baseMapper.getMaterialsAndClass(map);
        return materialAndClassList;
    }

}
