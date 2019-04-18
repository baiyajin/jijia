package com.baiyajin.mapper.pagedata;

import com.baiyajin.entity.pagedata.PageReport;
import com.baiyajin.vo.pagedata.ReportVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PageReportMapper extends BaseMapper<PageReport> {
    List<ReportVo> findList(ReportVo reportVo);

    int getCount(ReportVo reportVo);
}
