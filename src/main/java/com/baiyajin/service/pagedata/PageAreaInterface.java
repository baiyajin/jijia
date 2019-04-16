package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageArea;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

public interface PageAreaInterface extends IService<PageArea> {
    Integer save(Map<String,Object> map);
}
