package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageHelper;
import com.baiyajin.vo.pagedata.HelperVo;
import com.baiyajin.vo.pagedata.Page;
import com.baomidou.mybatisplus.service.IService;

public interface PageHelperInterface extends IService<PageHelper> {
    Page<HelperVo> findList(Page<HelperVo>page,HelperVo helperVo);
}
