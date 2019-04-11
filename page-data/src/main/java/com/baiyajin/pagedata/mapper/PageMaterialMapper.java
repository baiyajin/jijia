package com.baiyajin.pagedata.mapper;

import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.vo.MaterialVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PageMaterialMapper extends BaseMapper<PageMaterial> {


    /**
     * 数据查询接口
     * @param materialVo
     * @return
     */
    List<MaterialVo> findByTime (MaterialVo materialVo);
}
