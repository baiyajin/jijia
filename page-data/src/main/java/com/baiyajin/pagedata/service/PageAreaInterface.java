package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageArea;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

public interface PageAreaInterface extends IService<PageArea> {
    public Integer save(Map<String,Object> map);
}
