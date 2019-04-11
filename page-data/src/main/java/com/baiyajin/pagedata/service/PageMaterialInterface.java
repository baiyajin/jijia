package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.vo.MaterialVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import java.util.List;
import java.util.Map;

public interface PageMaterialInterface extends IService<PageMaterial> {
    /**
     *
     * @param materialVo
     * @return
     */
    List<MaterialVo> findByTime(MaterialVo materialVo);

    public List<MaterialAndClass> getMaterialsAndClass(Map<String,Object> map);
}
