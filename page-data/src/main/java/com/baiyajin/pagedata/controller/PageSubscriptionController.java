package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.PageSubscription;
import com.baiyajin.pagedata.service.PageSubscriptionInterface;
import com.baiyajin.pagedata.utils.IdGenerate;
import com.baiyajin.pagedata.utils.Results;
import com.baomidou.mybatisplus.plugins.Page;
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
     * 分页查询订阅消息
     * @param pageSubscription
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findPage",method = RequestMethod.GET)
    @ResponseBody
    public Object findPage(PageSubscription pageSubscription,String pageNum,String pageSize){
        Page<PageSubscription> p = new Page<>();
        if (StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)){
            p.setCurrent(Integer.valueOf(pageNum));
            p.setSize(Integer.valueOf(pageSize));
        }else {
            p.setSize(10);
            p.setCurrent(0);
        }
        Page<PageSubscription> page = pageSubscriptionInterface.selectPage(p);
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



