package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageMaterialClass;
import com.baiyajin.service.pagedata.PageMaterialClassInterface;
import io.swagger.annotations.Api;
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

@Api("材料类")
@Controller
@RequestMapping("/PageMaterialClassController")
public class PageMaterialClassController {

    @Autowired
    private PageMaterialClassInterface pageMaterialClassInterface;




    @RequestMapping(value = "/getMaterialClass", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<PageMaterialClass> getMaterialClass(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("id",map.get("id"));
        List<PageMaterialClass> PageMaterialClassList = pageMaterialClassInterface.selectByMap(pMap);
        return PageMaterialClassList;
    }










}



