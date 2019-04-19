package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageReport;
import com.baiyajin.entity.pagedata.PageSubscription;
import com.baiyajin.service.pagedata.PageSubscriptionInterface;
import com.baiyajin.util.*;
import com.baiyajin.vo.pagedata.Page;
import com.baiyajin.vo.pagedata.ReportVo;
import com.baiyajin.vo.pagedata.SubscriptionVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api("订阅")
@Controller
@RequestMapping("/PageSubscriptionController")
public class PageSubscriptionController {

    @Autowired
    private PageSubscriptionInterface pageSubscriptionInterface;

    @RequestMapping(value = "/", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<Map<String,Object>> login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return null;
    }

    /**
     * 增加订阅消息
     * @param pageSubscription
     * @return
     */
    @ApiOperation(value = "新增订阅" ,notes = "新增订阅默认状态ID为启用(qy)，若不默认可传入statusID：jy，isPush代表是否推送(0代表已推送，1代表未推送)，bookPrice代表订阅是材料价格")
    @ApiImplicitParams({@ApiImplicitParam(name = "title（必填),materialID(必填),areaID（必填）token（必填），remark（非必填），isPush(必填),bookPrice(必填)，bookDate(订阅要关注的数据的时间)"
            ,value =  "title:jijkokie,materialID:1dsfs3,areaID:sa132546fdaf/sfsa.*,token:asasdffa,remark:s546daf,bookDate：20190-04-15",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object add(PageSubscription pageSubscription, @RequestParam("startTimeStr") String startTimeStr, @RequestParam("endTimeStr")String endTimeStr){
        String token = pageSubscription.getToken();
        Claims claims = JWT.parseJWT(token);
        if (claims == null){
            return new Results(1,"请重新登录");
        }else {
            pageSubscription.setUserID(claims.getId());
        }
        if (StringUtils.isNotBlank(startTimeStr) && StringUtils.isNotBlank(endTimeStr)){
            pageSubscription.setStartTime(DateUtils.setDate(DateUtils.parseDate(startTimeStr,"yyyy-mm"),5,01));
            Date endDate =  DateUtils.parseDate(endTimeStr,"yyyy-MM");
            String lastDay = DateUtils.getDateLastDay(endDate);
            Date endTimeDate = DateUtils.parseDate(lastDay,"yyyy-MM-dd");
            pageSubscription.setEndTime(DateUtils.parseDate(lastDay,"yyyy-MM-dd"));
        }
        pageSubscription.setId(IdGenerate.uuid());
        pageSubscription.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pageSubscription.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        pageSubscription.setStatusID("qy");
        try {
            pageSubscriptionInterface.insert(pageSubscription);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }


    /**
     * 删除订阅
     * @param id
     * @return
     */
    @ApiOperation(value = "删除订阅" ,notes = "逻辑删除，statusId值为jy代表已删除，数据库依然存在，但是页面不显示")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object delete(String id){
        PageSubscription pageSubscription = new PageSubscription();
        pageSubscription.setId(id);
        pageSubscription.setStatusID("jy");
        try {
            pageSubscriptionInterface.updateById(pageSubscription);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }

    /**
     * 分页查询订阅消息
     * @param subscriptionVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询订阅" ,notes = "分页查询，未传入pageNum和pageSize默认从第1页查，每页十条数据,num为非必填，填入以后只查询该编号的文章，num为数字")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum（非必填),pageSize(非必填)，token（必填）",value =  "token:sdfsadfsa,pageNum:1,pageNum:5",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/findPage",method = RequestMethod.POST)
    @ResponseBody
    public Object findPage(SubscriptionVo subscriptionVo,String pageNum,String pageSize){
        Page<SubscriptionVo> p = new Page();
        PageSubscription pageSubscription = new PageSubscription();
            String token = subscriptionVo.getToken();
            Claims claims = JWT.parseJWT(token);
            if (claims == null){
                return new Results(1,"请重新登录");
            }else {
                pageSubscription.setUserID(claims.getId());
                subscriptionVo.setUserID(claims.getId());
            }

        if (StringUtils.isNotBlank(pageNum)&&StringUtils.isNotBlank(pageSize)){
            if ("0".equals(pageNum)){
                pageNum = "1";
            }
            subscriptionVo.setPageCurrent(PageUtils.pageNoRecord(pageNum,pageSize));
            subscriptionVo.setPSize(Integer.valueOf(pageSize));
            p.setPageSize(Integer.valueOf(pageSize));
            p.setPageNo(Integer.valueOf(pageNum));
        }else {
            subscriptionVo.setPSize(10);
            subscriptionVo.setPageCurrent(PageUtils.pageNoRecord("1","10"));
            p.setPageSize(10);
            p.setPageNo(1);
        }
        String number = subscriptionVo.getNumber();
        if (StringUtils.isNotBlank(number)){
            pageSubscription.setNumber(number);
        }
        pageSubscription.setStatusID("qy");
        int count = pageSubscriptionInterface.selectCount(new EntityWrapper<>(pageSubscription));
        Page<SubscriptionVo> page = pageSubscriptionInterface.findList(p,subscriptionVo);
        if (page == null || page.getList() == null ||page.getList().size() == 0){
            return new Results(1,"暂无数据");
        }
        page.setCount(count);
        return page;
    }

    /**
     * 查询订阅详情
     * @param id
     * @return
     */
    @ApiOperation(value = "查询订阅详情" ,notes = "ID查询，只要ID能获取到就能查到文章，无论是否被删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填)",value =  "id:123465",dataType = "String",paramType = "body")})
    @RequestMapping(value = "/findPageById",method = RequestMethod.POST)
    @ResponseBody
    public Object findPageById(String id){
        PageSubscription pageSubscription = pageSubscriptionInterface.selectById(id);
        if (pageSubscription != null){
            return new Results(1,"没有该订阅消息");
        }
        return pageSubscription;
    }



}



