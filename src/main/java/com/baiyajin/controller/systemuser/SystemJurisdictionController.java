package com.baiyajin.controller.systemuser;

import com.baiyajin.entity.systemuser.SystemJurisdiction;
import com.baiyajin.service.systemuser.SystemJurisdictionInterface;
import com.baiyajin.util.ReturnModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("权限")
@Controller
@RequestMapping("/SystemJurisdictionController")
public class SystemJurisdictionController {

    @Autowired
    private SystemJurisdictionInterface systemJurisdictionInterface;


    /*添加权限类型*/
    @ApiOperation(value = "feign远程服务调用测试",notes = "查询test")
    @RequestMapping(value = "/addJurisdiction", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  ReturnModel addJurisdiction(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        try{
            String name = map.get("name").toString();
            String urlAddress = map.get("urlAddress").toString();
            SystemJurisdiction systemJurisdiction = new SystemJurisdiction(name,urlAddress);
            int i  = systemJurisdictionInterface.addJurisdiction(systemJurisdiction);
            return new ReturnModel(i);
        }catch(Exception e){
            e.printStackTrace();
            return new ReturnModel(0);
        }
    }


    /*查询权限类型*/
    @ApiOperation(value = "feign远程服务调用测试",notes = "查询test")
    @RequestMapping(value = "/selectJurisdiction", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  ReturnModel selectJurisdiction(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {

        try{
//            String  id= map.get("id").toString();
//            String name = map.get("name").toString();
//            String urlAddress = map.get("urlAddress").toString();
            List<SystemJurisdiction> systemJurisdictionList = systemJurisdictionInterface.selectByMap(map);
            Map<String,Object> bodyMap = new HashMap<String,Object>();
            bodyMap.put("body",systemJurisdictionList);
            return new ReturnModel(1,bodyMap);
        }catch(Exception e){
            e.printStackTrace();
            return new ReturnModel(0);
        }

    }



    /*修改权限类型*/
    @ApiOperation(value = "feign远程服务调用测试",notes = "查询test")
    @RequestMapping(value = "/updateJurisdiction", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  ReturnModel updateJurisdiction(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {

        try{
//            String  id= map.get("id").toString();
//            String name = map.get("name").toString();
//            String urlAddress = map.get("urlAddress").toString();

            return  systemJurisdictionInterface.updateJurisdiction(map);
        }catch(Exception e){
            e.printStackTrace();
            return new ReturnModel(0);
        }

    }



}



