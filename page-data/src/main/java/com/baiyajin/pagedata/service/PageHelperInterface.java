package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageHelper;
import com.baiyajin.pagedata.vo.HelperVo;
import com.baiyajin.pagedata.vo.Page;
import com.baomidou.mybatisplus.service.IService;

public interface PageHelperInterface extends IService<PageHelper> {
    Page<HelperVo> findList(Page<HelperVo>page,HelperVo helperVo);
}
