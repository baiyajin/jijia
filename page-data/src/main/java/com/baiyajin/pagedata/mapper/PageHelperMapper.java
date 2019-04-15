package com.baiyajin.pagedata.mapper;

import com.baiyajin.pagedata.entity.PageHelper;
import com.baiyajin.pagedata.vo.HelperVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PageHelperMapper extends BaseMapper<PageHelper> {
    List<HelperVo> findList(HelperVo helperVo);
}
