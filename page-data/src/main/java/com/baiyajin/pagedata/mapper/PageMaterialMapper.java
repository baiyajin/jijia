package com.baiyajin.pagedata.mapper;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.vo.MaterialVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import java.util.List;
import java.util.Map;

public interface PageMaterialMapper extends BaseMapper<PageMaterial> {


    /**
     * 数据查询接口
     * @param materialVo
     * @return
     */
    List<MaterialVo> findByTime (MaterialVo materialVo);
    public List<MaterialAndClass> getMaterialsAndClass(Map<String,Object> map);

}
