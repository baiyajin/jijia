package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.PageMessage;
import com.baiyajin.service.pagedata.PageMessageInterface;
import com.baiyajin.util.CustomException;
import com.baiyajin.util.JWT;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api("消息")
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
    @ApiOperation(value = "获取消息列表",notes = "通过用户id获取该用户的消息数据，请求类型json")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId（必填）",value =  "如：\t{\"userId\":\"11\"}",dataType = "String")})
    @RequestMapping(value = "/getMessage", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> getMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        try{
            if( map.get("token")==null || JWT.parseJWT(map.get("token").toString())==null ){
                    throw new CustomException("登录已过期","-1");
            }
            String token =  map.get("token").toString();
            Claims claims = JWT.parseJWT(token);

            String userId = claims.getId();
           userId = "2";
            Integer total = pageMessageInterface.selectCount(userId);
            String pageNumS = "1";
            String pageSize =  "10";
            if(map.get("pageNum")==null || map.get("pageSize")==null){
                  pageNumS = "1";
                  pageSize =  "10";
            }



            Integer pageNum = (Integer.parseInt(pageNumS)-1) * Integer.parseInt(pageSize);
            map.put("pageNum",pageNum);
            map.put("pageSize",Integer.parseInt(pageSize));
            map.put("userId",userId);
            List<PageMessage> pageMessageList = pageMessageInterface.getMessage(map);

            Map<String,Object> reMap = new HashMap<String,Object>();
            reMap.put("data",pageMessageList);
            reMap.put("total",total);
            reMap.put("pagepageNum",pageNumS);
            reMap.put("msg","success");
            return reMap;
        }catch (CustomException ce){
            Map<String,Object> reMap = new HashMap<String,Object>();
            reMap.put("data",null);
            reMap.put("total",0);
            reMap.put("pagepageNum",0);
            reMap.put("msg",ce.getMessage());
            return reMap;
        }
    }


    /**
     * 8.	删除消息
     * @param request
     * @param response
     * @param map
     * @return
     */
    @ApiOperation(value = "删除消息",notes = "通过消息id删除该条消息（需要删除多条id用逗号隔开），请求类型json")
    @ApiImplicitParams({@ApiImplicitParam(name = "id（必填）",value =  "如：\t{\"userId\":\"11\"}或者{\"userId\":\"11,12\"}",dataType = "String")})
    @RequestMapping(value = "/delMessage", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Map<String,Object> delMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        try {
            if (map.get("ids")==null){
                throw new CustomException("参数错误","-1");
            }
            if( map.get("token")==null || JWT.parseJWT(map.get("token").toString())==null ){
                throw new CustomException("登录已过期","-1");
            }
            String token =  map.get("token").toString();
            Claims claims = JWT.parseJWT(token);

            String ids = map.get("ids").toString();
            int i = pageMessageInterface.deleteMessage(ids);
            Map<String, Object> reMap = new HashMap<String, Object>();
            if (i > 0) {
                reMap.put("success", true);
                reMap.put("msg", "操作成功");
            } else {
                reMap.put("success", false);
                reMap.put("msg", "操作失败");
            }
            return reMap;
        }catch (CustomException ce) {
            Map<String, Object> reMap = new HashMap<String, Object>();
            reMap.put("success", false);
            reMap.put("msg", ce.getMessage());
            return reMap;
        }
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



