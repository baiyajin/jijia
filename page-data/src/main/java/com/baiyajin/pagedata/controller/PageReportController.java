package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.PageReport;
import com.baiyajin.pagedata.service.PageReportInterface;
import com.baiyajin.pagedata.utils.IdGenerate;
import com.baiyajin.pagedata.utils.Results;
import com.baiyajin.pagedata.vo.Page;
import com.baiyajin.pagedata.vo.ReportVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/PageReportController")
public class PageReportController {

    @Autowired
    private PageReportInterface pageReportInterface;




    @RequestMapping(value = "/", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<Map<String,Object>> login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return null;
    }

    /**
     * 新增报告
     * @param pageReport
     * @return
     */
    @RequestMapping(value = "/addReport",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object addReport (PageReport pageReport){
        pageReport.setId(IdGenerate.uuid());
        pageReport.setStatusID("qy");
        pageReport.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pageReport.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            pageReportInterface.insert(pageReport);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }

    /**
     * 删除报告
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteReport",method = RequestMethod.PUT)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object deleteReort(String id){
        PageReport p = pageReportInterface.selectById(id);
        if(p == null){
            return new Results(1,"该报告不存在");
        }
        PageReport pageReport = new PageReport();
        pageReport.setId(id);
        pageReport.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        pageReport.setStatusID("jy");
        try {
            pageReportInterface.updateById(pageReport);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }
    /**
     * 修改报告
     * @param pageReport
     * @return
     */
    @RequestMapping(value = "/uppdateReport",method = RequestMethod.PUT)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object updateReport(PageReport pageReport){
        String id = pageReport.getId();
        PageReport p = pageReportInterface.selectById(id);
        if(p == null){
            return new Results(1,"该报告不存在");
        }
        pageReport.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            pageReportInterface.updateById(pageReport);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }

    /**
     * 分页查询报告
     * @param reportVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findListByPage",method = RequestMethod.POST)
    @ResponseBody
    public Object findListByPage(ReportVo reportVo,String pageNum,String pageSize){
        Page<ReportVo> p = new Page();
        if (StringUtils.isNotBlank(pageNum)&&StringUtils.isNotBlank(pageSize)){
            p.setPageNo(Integer.valueOf(pageNum));
            p.setPageSize(Integer.valueOf(pageSize));
        }else {
            p.setPageSize(10);
            p.setPageNo(0);
        }
//        Page<PageReport> page = pageReportInterface.selectPage(p);
        PageReport pageReport = new PageReport();
        String type = reportVo.getType();
        String userID = reportVo.getUserID();
        pageReport.setType(type);
        pageReport.setUserID(userID);
        int count = pageReportInterface.selectCount(new EntityWrapper<PageReport>(pageReport));
        Page<ReportVo> page = pageReportInterface.findList(p,reportVo);
        if (page == null || page.getList() == null ||page.getList().size() == 0){
            return new Results(1,"暂无数据");
        }
        page.setCount(count);
       return page;
    }

    /**
     * 获取报告详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getReportById",method = RequestMethod.POST)
    @ResponseBody
    public Object getReportById(String id){
        PageReport pageReport = pageReportInterface.selectById(id);
        if (pageReport != null){
            pageReport.setContent(StringEscapeUtils.escapeHtml(pageReport.getContent()));
        }else {
            return new Results(1,"该报告不存在");
        }
        return pageReport;
    }










}



