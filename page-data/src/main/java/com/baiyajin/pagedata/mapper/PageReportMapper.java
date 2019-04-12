package com.baiyajin.pagedata.mapper;

import com.baiyajin.pagedata.entity.PageReport;
import com.baiyajin.pagedata.vo.ReportVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PageReportMapper extends BaseMapper<PageReport> {
    List<ReportVo> findList(ReportVo reportVo);
}
