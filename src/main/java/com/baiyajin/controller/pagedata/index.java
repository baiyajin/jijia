package com.baiyajin.controller.pagedata;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/index")
public class index {


    @ApiOperation(value = "后台登录主页地址:{/templates/test/index.html}",notes = "test后台登录主页地址:{/templates/test/index.html}")
    @ApiImplicitParams({@ApiImplicitParam(name = "lineId（必填） ,timerId,",value =  "lineId ,timerId,token如：\t{\"lineId\":\"123456\",\"timerId\":\"123456\",\"token\":\"123456\"}",dataType = "String")})
    @RequestMapping(value = "/test1", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/test/index.html";
    }

    @ApiOperation(value = "大屏主页地址:{/templates/distD/index.html}",notes = "大屏主页地址:{/templates/distD/index.html}")
    @RequestMapping(value = "/test2", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String distD(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/distD/index.html";
    }

    @ApiOperation(value = "小屏主页地址:{/templates/distX/index.html}",notes = "小屏主页地址:{/templates/distX/index.html}")
    @RequestMapping(value = "/test3", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String distX(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return "/templates/distX/index.html";
    }









}



