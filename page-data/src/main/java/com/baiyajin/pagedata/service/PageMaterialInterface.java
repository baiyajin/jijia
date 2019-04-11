package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface PageMaterialInterface extends IService<PageMaterial> {

    public List<MaterialAndClass> getMaterialsAndClass(Map<String,Object> map);
}
