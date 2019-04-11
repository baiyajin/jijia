package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.vo.MaterialVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface PageMaterialInterface extends IService<PageMaterial> {
    /**
     *
     * @param materialVo
     * @return
     */
    List<MaterialVo> findByTime(MaterialVo materialVo);
}
