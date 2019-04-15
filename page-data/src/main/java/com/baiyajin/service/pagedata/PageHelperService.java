package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageHelper;
import com.baiyajin.mapper.pagedata.PageHelperMapper;
import com.baiyajin.vo.pagedata.HelperVo;
import com.baiyajin.vo.pagedata.Page;
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
