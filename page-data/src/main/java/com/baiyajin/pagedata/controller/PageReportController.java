package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.PageReport;
import com.baiyajin.pagedata.service.PageReportInterface;
import com.baiyajin.pagedata.utils.IdGenerate;
import com.baiyajin.pagedata.utils.Results;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
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
     * @param pageReport
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findListByPage",method = RequestMethod.GET)
    @ResponseBody
    public Object findListByPage(PageReport pageReport,String pageNum,String pageSize){
        Page<PageReport> p = new Page();
        if (StringUtils.isNotBlank(pageNum)&&StringUtils.isNotBlank(pageSize)){
            p.setCurrent(Integer.valueOf(pageNum));
            p.setSize(Integer.valueOf(pageSize));
        }else {
            p.setSize(10);
            p.setCurrent(0);
        }
        Page<PageReport> page = pageReportInterface.selectPage(p);
       return page;
    }

    @RequestMapping(value = "/getReportById",method = RequestMethod.GET)
    @ResponseBody
    public Object getReportById(String id){
        PageReport pageReport = pageReportInterface.selectById(id);
        if (pageReport != null){
            pageReport.setContent(StringEscapeUtils.escapeHtml(pageReport.getContent()));
        }else {
            return new Results(1,"改报告不存在");
        }
        return pageReport;
    }










}



