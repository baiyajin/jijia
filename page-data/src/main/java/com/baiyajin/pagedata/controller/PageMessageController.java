package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.entity.PageMessage;
import com.baiyajin.pagedata.service.PageMaterialInterface;
import com.baiyajin.pagedata.service.PageMessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/PageMessageController")
public class PageMessageController {

    @Autowired
    private PageMessageInterface pageMessageInterface;

    /**
     *
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/getMessage", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<PageMessage> getMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {


        return pageMessageInterface.selectByMap(map);



    }


    /**
     * 获取消息条数
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/getMessageCount", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public int getMessageCount(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return  pageMessageInterface.selectCount(map);
    }



}



