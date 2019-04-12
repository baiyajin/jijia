package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageHelper;
import com.baiyajin.pagedata.mapper.PageHelperMapper;
import com.baiyajin.pagedata.vo.HelperVo;
import com.baiyajin.pagedata.vo.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PageHelperService extends ServiceImpl<PageHelperMapper, PageHelper> implements PageHelperInterface{

    @Override
    public Page<HelperVo> findList(Page<HelperVo> page, HelperVo helperVo) {
        List<HelperVo> helperVoList = baseMapper.findList(helperVo);
        // 设置分页参数
        helperVo.setPage(page);
        // 执行分页查询
        page.setList(helperVoList);
        return page;
    }
}
