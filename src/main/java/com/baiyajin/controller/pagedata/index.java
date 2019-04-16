package com.baiyajin.controller.pagedata;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@RequestMapping("/templates")
public class index {


    @ApiOperation(value = "test后台登录主页",notes = "test后台登录主页")
    @RequestMapping(value = "/test/index.html", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/test/index.html";
    }

    @ApiOperation(value = "大屏主页",notes = "大屏主页")
    @RequestMapping(value = "/distD/index.html", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String distD(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/distD/index.html";
    }

    @ApiOperation(value = "小屏主页",notes = "小屏主页")
    @RequestMapping(value = "/distX/index.html", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String distX(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/distX/index.html";
    }









}



