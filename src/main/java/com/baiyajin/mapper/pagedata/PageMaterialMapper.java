package com.baiyajin.mapper.pagedata;

import com.baiyajin.entity.pagedata.MaterialAndClass;
import com.baiyajin.entity.pagedata.PageMaterial;
import com.baiyajin.vo.pagedata.MaterialVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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

    public List<MaterialAndClass> getMaterialsClass(Map<String,Object> map);

    public List<Map<String,Object>> getMaterialsInfo(Map<String,Object> map);

    public List<Map<String,Object>> getMaterialsInfoByYear(Map<String,Object> map);

    public List<Map<String,Object>> getMaterialsInfoByArea(Map<String,Object> map);


}
