package com.baiyajin.controller.pagedata;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api("主页")
@Controller
@RequestMapping("/index")
public class index {


    @ApiOperation(value = "后台登录主页地址:{http://192.168.8.19:8018/templates/test/index.html}",notes = "test后台登录主页地址:{http://192.168.8.19:8018/templates/test/index.html}")
    @RequestMapping(value = "/test1", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/test/index.html";
    }

    @ApiOperation(value = "大屏主页地址:{http://192.168.8.19:8018/home/dist/index.html}",notes = "大屏主页地址:{http://192.168.8.19:8018/home/dist/index.html}")
    @RequestMapping(value = "/test2", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String distD(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "http://192.168.8.19:8018/home/dist/index.html#/";
    }

    @ApiOperation(value = "小屏主页地址:{/templates/distX/index.html}",notes = "小屏主页地址:{/templates/distX/index.html}")
    @RequestMapping(value = "/test3", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String distX(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/distX/index.html";
    }









}



