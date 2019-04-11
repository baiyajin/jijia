package com.baiyajin.pagedata.mapper;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface PageMaterialMapper extends BaseMapper<PageMaterial> {

    public List<MaterialAndClass> getMaterialsAndClass(Map<String,Object> map);

}
