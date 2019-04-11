package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.MaterialAndClass;
import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.entity.PageMaterialClass;
import com.baiyajin.pagedata.service.PageMaterialClassInterface;
import com.baiyajin.pagedata.service.PageMaterialInterface;
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
@RequestMapping("/PageMaterialController")
public class PageMaterialController {

    @Autowired
    private PageMaterialInterface pageMaterialInterface;

    @Autowired
    private PageMaterialClassInterface pageMaterialClassInterface;


    @RequestMapping(value = "/getMaterials", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<PageMaterial> getMaterials(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {

        Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("id",map.get("id"));
        List<PageMaterial> PageMaterialList = pageMaterialInterface.selectByMap(pMap);
        return PageMaterialList;

    }


    @RequestMapping(value = "/getMaterialsAndClass", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public MaterialAndClass getMaterialsAndClass(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        String id = map.get("id").toString();
        PageMaterialClass pageMaterialClass = pageMaterialClassInterface.selectById(id);
        map.clear();
        map.put("materialClassID",id);
        List<PageMaterial> PageMaterialList = pageMaterialInterface.selectByMap(map);
        MaterialAndClass materialAndClass = new MaterialAndClass(pageMaterialClass,PageMaterialList);
        return materialAndClass;
    }




}



