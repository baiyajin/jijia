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
     * 8.	获取消息列表
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/getMessage", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> getMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
//        String token =  map.get("token").toString();
        // TODO 验证token
        String userId =  map.get("userId").toString();
        Integer total = pageMessageInterface.selectCount(userId);

        String pageNumS = map.get("pageNum").toString();
        String pageSize =  map.get("pageSize").toString();
        Integer pageNum = (1-Integer.parseInt(pageNumS)) * Integer.parseInt(pageSize);
        map.put("pageNum",pageNum);
        map.put("pageSize",Integer.parseInt(pageSize));
        List<PageMessage> pageMessageList = pageMessageInterface.getMessage(map);

        Map<String,Object> reMap = new HashMap<String,Object>();
        reMap.put("data",pageMessageList);
        reMap.put("total",total);
        reMap.put("pagepageNum",pageNumS);
        return reMap;

    }


    /**
     * 8.	删除消息
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/delMessage", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> delMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        String ids = map.get("ids").toString();
        int i = pageMessageInterface.deleteMessage(ids);
        Map<String,Object> reMap = new HashMap<String,Object>();
        if(i>0){
            reMap.put("success",true);
            reMap.put("msg","操作成功");
        }else{
            reMap.put("success",false);
            reMap.put("msg","操作失败");
        }
        return reMap;
    }

//    /**
//     * 获取消息条数
//     * @param request
//     * @param response
//     * @param map
//     * @return
//     */
//    @RequestMapping(value = "/getMessageCount", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
//    @Transactional(rollbackFor = Exception.class)
//    @ResponseBody
//    public int getMessageCount(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
//        return  pageMessageInterface.selectCount(map);
//    }



}



