package com.baiyajin.pagedata.controller;

import com.baiyajin.pagedata.entity.PageMaterial;
import com.baiyajin.pagedata.service.PageMaterialInterface;
import com.baiyajin.pagedata.utils.IdGenerate;
import com.baiyajin.pagedata.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;
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
@RequestMapping("/PageMaterialController")
public class PageMaterialController {

    @Autowired
    private PageMaterialInterface pageMaterialInterface;

    @RequestMapping(value = "/", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<Map<String,Object>> login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return null;
    }

    @RequestMapping(value = "addMaterial",method = RequestMethod.POST)
    @ResponseBody
    public Object addMaterial(PageMaterial pageMaterial){
        pageMaterial.setId(IdGenerate.uuid());
        pageMaterial.setStatusID("qy");
        pageMaterial.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pageMaterial.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            pageMaterialInterface.insert(pageMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(1,"fail");
        }
        return new Results(0,"success");
    }




}



