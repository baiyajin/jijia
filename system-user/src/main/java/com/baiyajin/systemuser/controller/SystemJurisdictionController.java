package com.baiyajin.systemuser.controller;

import com.baiyajin.systemuser.entity.SystemJurisdiction;
import com.baiyajin.systemuser.entity.SystemUser;
import com.baiyajin.systemuser.service.SystemJurisdictionInterface;
import com.baiyajin.systemuser.service.SystemUserInterface;
import com.baiyajin.systemuser.util.ConversionBetweenObjectsAndMaps;
import com.baiyajin.systemuser.util.HashSalt;
import com.baiyajin.systemuser.util.PhoneUtils;
import com.baiyajin.systemuser.util.ReturnModel;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SystemJurisdictionController")
public class SystemJurisdictionController {

    @Autowired
    private SystemJurisdictionInterface systemJurisdictionInterface;


    /*添加权限类型*/
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



