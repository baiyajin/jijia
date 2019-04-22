package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageReport;
import com.baiyajin.service.pagedata.PageReportInterface;
import com.baiyajin.util.*;
import com.baiyajin.vo.pagedata.Page;
import com.baiyajin.vo.pagedata.ReportVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api("报告")
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
    @ApiOperation(value = "新增报告" ,notes = "新增报告默认状态ID为启用(qy)，type（1 平台发布,2 我的,3 全部），logo为图片上传，状态默认为qy，若不默认可传入statusID：jy")
    @ApiImplicitParams({@ApiImplicitParam(name = "name(必填),logo（必填），content(必填),publishState(非必填)，" +
            "startTimeStr(报告中材料的开始时间，非必填),endTimeStr(报告中材料的结束时间，非必填)" +
            "mark(非必填)，token（必填）,timeInterval(时间区域，选择传入)，materialClassID(材料类型ID，选择传入)，contrastRegionID(对比地区，可多个，用逗号隔开)"
            ,value =  "name:123,logo:safdaf/sfsa.*,content:asfa,mark:sdaf，timeInterval：2019-04-19，materialClassID：12346，contrastRegionID：132,asdf,123",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/addReport",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object addReport (PageReport pageReport,@RequestParam("startTimeStr") String startTimeStr,@RequestParam("endTimeStr")String endTimeStr){
        String token = pageReport.getToken();
        Claims claims = JWT.parseJWT(token);
        if (claims == null){
            return new Results(1,"请重新登录");
        }else {
            pageReport.setUserID(claims.getId());
        }
        if (StringUtils.isNotBlank(startTimeStr) && StringUtils.isNotBlank(endTimeStr)){
            pageReport.setStartTime(DateUtils.setDate(DateUtils.parseDate(startTimeStr,"yyyy-mm"),5,01));
            Date endDate =  DateUtils.parseDate(endTimeStr,"yyyy-MM");
            String lastDay = DateUtils.getDateLastDay(endDate);
            Date endTimeDate = DateUtils.parseDate(lastDay,"yyyy-MM-dd");
            pageReport.setEndTime(DateUtils.parseDate(lastDay,"yyyy-MM-dd"));
        }
        pageReport.setType("2");
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
    @ApiOperation(value = "删除报告" ,notes = "逻辑删除，statusId值为jy代表已删除，数据库依然存在，但是页面不显示")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/deleteReport",method = RequestMethod.POST)
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
    @ApiOperation(value = "修改报告" ,notes = "需要修改什么那些字段就传入那些字段，其中logo为图片上传，存放的是图片路径")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填),type(非必填),content(非必填),logo(必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/updateReport",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object updateReport(PageReport pageReport,@RequestParam(value = "startTimeStr",required = false) String startTimeStr,@RequestParam(value = "endTimeStr",required = false)String endTimeStr){
        String id = pageReport.getId();
        PageReport p = pageReportInterface.selectById(id);
        if(p == null){
            return new Results(1,"该报告不存在");
        }

        if (StringUtils.isNotBlank(startTimeStr) && StringUtils.isNotBlank(endTimeStr)){
            pageReport.setStartTime(DateUtils.setDate(DateUtils.parseDate(startTimeStr,"yyyy-mm"),5,01));
            Date endDate =  DateUtils.parseDate(endTimeStr,"yyyy-MM");
            String lastDay = DateUtils.getDateLastDay(endDate);
            Date endTimeDate = DateUtils.parseDate(lastDay,"yyyy-MM-dd");
            pageReport.setEndTime(endTimeDate);
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
    @ApiOperation(value = "分页产需拿报告" ,notes = "分页查询，未传入pageNum和pageSize默认从第1页查，每页十条数据,num为非必填，填入以后只查询该编号的文章，num为数字," +
            "type(报告类型,1 平台发布,2 我的),不传入参数时默认为查询全部，查询我的报告时，token必填")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum（非必填),pageSize(非必填)，token（非必填）,type(非必填)",value =  "pageNum:1,pageNum:5,token:15646saf",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/findListByPage",method = RequestMethod.POST)
    @ResponseBody
    public Object findListByPage(ReportVo reportVo,String pageNum,String pageSize){
        Page<ReportVo> p = new Page();
        PageReport pageReport = new PageReport();
        String token = reportVo.getToken();
        Claims claims = JWT.parseJWT(token);

        if ("2".equals(reportVo.getType())){
            if (claims == null){
                return new Results(1,"请重新登录");
            }else {
                pageReport.setUserID(claims.getId());
                reportVo.setUserID(claims.getId());
            }
        }

        if(StringUtils.isBlank(reportVo.getType())){
            if (claims == null){
                return new Results(1,"请重新登录");
            }else {
                pageReport.setUserID(claims.getId());
                reportVo.setUserID(claims.getId());
            }
        }

        if (StringUtils.isNotBlank(pageNum)&&StringUtils.isNotBlank(pageSize)){
            if ("0".equals(pageNum)){
                pageNum = "1";
            }
            reportVo.setPageCurrent(PageUtils.pageNoRecord(pageNum,pageSize));
            reportVo.setPSize(Integer.valueOf(pageSize));
            p.setPageSize(Integer.valueOf(pageSize));
            p.setPageNo(Integer.valueOf(pageNum));
        }else {
            reportVo.setPSize(10);
            reportVo.setPageCurrent(PageUtils.pageNoRecord("1","10"));
            p.setPageSize(10);
            p.setPageNo(1);
        }

        String type = reportVo.getType();
        if (StringUtils.isNotBlank(type)){
            pageReport.setType(type);
        }
        pageReport.setStatusID("qy");
        int count = pageReportInterface.getCount(reportVo);
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
    @ApiOperation(value = "获取报告详情" ,notes = "ID查询，只要ID能获取到就能查到文章，无论是否被删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填)",value =  "id:123465",dataType = "String",paramType = "body")})
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



