package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.mapper.PageMaterialMapper;
import com.baiyajin.pagedata.vo.MaterialVo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageMaterialService extends ServiceImpl<PageMaterialMapper,PageMaterial> implements PageMaterialInterface {


    @Override
    public List<MaterialVo> findByTime(MaterialVo materialVo) {
        return baseMapper.findByTime(materialVo);
    }
}
