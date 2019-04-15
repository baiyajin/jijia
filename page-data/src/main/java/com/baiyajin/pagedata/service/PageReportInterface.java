package com.baiyajin.pagedata.service;

import com.baiyajin.pagedata.entity.PageReport;
import com.baiyajin.pagedata.vo.Page;
import com.baiyajin.pagedata.vo.ReportVo;
import com.baomidou.mybatisplus.service.IService;

public interface PageReportInterface extends IService<PageReport> {

    Page<ReportVo> findList(Page<ReportVo> page, ReportVo reportVo);

}
