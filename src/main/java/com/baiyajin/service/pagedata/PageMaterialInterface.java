package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.MaterialAndClass;
import com.baiyajin.entity.pagedata.PageMaterial;
import com.baiyajin.vo.pagedata.MaterialVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import java.util.Map;

public interface PageMaterialInterface extends IService<PageMaterial> {
    /**
     *
     * @param materialVo
     * @return
     */
    List<MaterialVo> findByTime(MaterialVo materialVo);

    List<MaterialAndClass> getMaterialsAndClass(Map<String,Object> map);

    public List<MaterialAndClass> getMaterialsClass(Map<String,Object> map);

    public List<Map<String,Object>> getMaterialsInfo(Map<String,Object> map);



}
