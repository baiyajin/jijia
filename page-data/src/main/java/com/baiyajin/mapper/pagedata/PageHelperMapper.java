package com.baiyajin.mapper.pagedata;

import com.baiyajin.entity.pagedata.PageHelper;
import com.baiyajin.vo.pagedata.HelperVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PageHelperMapper extends BaseMapper<PageHelper> {
    List<HelperVo> findList(HelperVo helperVo);
}
