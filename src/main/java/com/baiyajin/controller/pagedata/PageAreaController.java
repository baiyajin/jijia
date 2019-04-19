package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageArea;
import com.baiyajin.service.pagedata.PageAreaInterface;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Api("地区")
@Controller
@RequestMapping("/PageAreaController")
public class PageAreaController {

    @Autowired
    private PageAreaInterface pageAreaInterface;




    /**
     * 添加地址
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/addArea", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> addArea(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        Map<String,Object> reMap = new HashMap<String,Object>();
        try{
            int i = pageAreaInterface.save(map);
            if(i==0){
                reMap.put("stat",false);
                reMap.put("msg","操作失败");
            }else{
                reMap.put("stat",true);
                reMap.put("msg","操作成功");
            }
            return reMap;
        }catch (Exception e){
            e.printStackTrace();
            reMap.put("stat",false);
            reMap.put("msg","操作失败");
            return reMap;
        }
    }


    @RequestMapping(value = "/getAreaList", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<PageArea> getAreaList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
//        String pid = map.get("pid")==null?null:map.get("pid").toString();
        List<PageArea> areaList = pageAreaInterface.selectByMap(map);
        return areaList;
    }









}



