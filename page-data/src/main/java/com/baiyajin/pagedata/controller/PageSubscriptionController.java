package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.PageSubscription;
import com.baiyajin.pagedata.service.PageSubscriptionInterface;
import com.baiyajin.pagedata.utils.IdGenerate;
import com.baiyajin.pagedata.utils.Results;
import com.baiyajin.pagedata.vo.Page;
import com.baiyajin.pagedata.vo.SubscriptionVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import javax.swing.text.html.parser.Entity;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object add(PageSubscription pageSubscription){
        pageSubscription.setId(IdGenerate.uuid());
        pageSubscription.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pageSubscription.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        pageSubscription.setStatusID("qy");
        try {
            pageSubscriptionInterface.insert(pageSubscription);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fila");
        }
        return new Results(0,"success");
    }


    /**
     * 删除订阅
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
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
    @RequestMapping(value = "/findPage",method = RequestMethod.POST)
    @ResponseBody
    public Object findPage(SubscriptionVo subscriptionVo,String pageNum,String pageSize){
        Page<SubscriptionVo> p = new Page<>();
        if (StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)){
            p.setPageNo(Integer.valueOf(pageNum));
            p.setPageSize(Integer.valueOf(pageSize));
        }else {
            p.setPageSize(10);
            p.setPageNo(0);
        }
//        Page<PageSubscription> page = pageSubscriptionInterface.selectPage(p);
        PageSubscription pageSubscription = new PageSubscription();
        String number = subscriptionVo.getNumber();
        pageSubscription.setNumber(number);
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
    @RequestMapping(value = "/findPageById",method = RequestMethod.GET)
    @ResponseBody
    public Object findPageById(String id){
        PageSubscription pageSubscription = pageSubscriptionInterface.selectById(id);
        if (pageSubscription != null){
            return new Results(1,"没有该订阅消息");
        }
        return pageSubscription;
    }



}



