package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageReport;
import com.baiyajin.pagedata.mapper.PageReportMapper;
import com.baiyajin.pagedata.vo.Page;
import com.baiyajin.pagedata.vo.ReportVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageReportService extends ServiceImpl<PageReportMapper,PageReport> implements PageReportInterface {


    @Override
    public Page<ReportVo> findList(Page<ReportVo> page, ReportVo reportVo) {
        List<ReportVo> reportVoList = baseMapper.findList(reportVo);
        // 设置分页参数
        reportVo.setPage(page);
        // 执行分页查询
        page.setList(reportVoList);
        return page;
    }
}
