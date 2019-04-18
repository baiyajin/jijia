package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.PageReport;
import com.baiyajin.vo.pagedata.Page;
import com.baiyajin.vo.pagedata.ReportVo;
import com.baomidou.mybatisplus.service.IService;

public interface PageReportInterface extends IService<PageReport> {

    Page<ReportVo> findList(Page<ReportVo> page, ReportVo reportVo);

    int getCount(ReportVo reportVo);

}
