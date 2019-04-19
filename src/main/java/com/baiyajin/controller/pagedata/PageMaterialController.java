package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.MaterialAndClass;
import com.baiyajin.entity.pagedata.PageMaterial;
import com.baiyajin.service.pagedata.PageMaterialInterface;
import com.baiyajin.util.IdGenerate;
import com.baiyajin.util.Results;
import com.baiyajin.vo.pagedata.MaterialVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("材料")
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

    /**
     * 添加材料
     * @param pageMaterial
     * @return
     */
    @RequestMapping(value = "/addMaterial",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 根据时间查询数据
     * @param materialVo
     * @return
     */
    @RequestMapping(value = "/findByTime",method = RequestMethod.POST)
    @ResponseBody
    public Object findByTime(MaterialVo materialVo){
        List<MaterialVo> materialVoList = pageMaterialInterface.findByTime(materialVo);
        if (materialVoList == null || materialVoList.size() == 0){
            return new Results(1,"暂时无数据");
        }
                return materialVoList;
    }

    @RequestMapping(value = "/getMaterials", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object getMaterials(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("id",map.get("id"));
        List<PageMaterial> pageMaterialList = pageMaterialInterface.selectByMap(pMap);
        if (pageMaterialList == null || pageMaterialList.size() == 0){
            return new Results(1,"暂时无数据");
        }
        return pageMaterialList;

    }

   /* @RequestMapping(value = "/getMaterialsAndClass", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<MaterialAndClass> getMaterialsAndClass(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return pageMaterialInterface.getMaterialsAndClass(map);
    }*/


    @ApiOperation(value = "获取材料分类",notes = "获取材料分类信息（1,2级材料），请求类型json")
    @ApiImplicitParams({@ApiImplicitParam(name = "无需参数",value =  "如：\t {}",dataType = "String")})
    @RequestMapping(value = "/getMaterialsClass", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<MaterialAndClass> getMaterialsClass(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return pageMaterialInterface.getMaterialsClass(map);
    }

    //	"type":"1","pid":"0",, "area":"530112000000", "stratDate":"2019-01-01", "endDate":"2019-04-02"

    @ApiOperation(value = "获取材料价格信息",notes = "获取材料价格及统计数据，请求类型json")
    @ApiImplicitParams({@ApiImplicitParam(name = "pid,area,id,type,stratDate,endDate，number,level",value =  "分类id默认（查询一级分类）" +
            "区域地址id（默认查询云南地区），材料id指定查询该材料的信息,查询方式type=1查询月份（默认）type=2查询季度type=3查询年，" +
            "stratDate，stratDate查询的开始时间和结束时间，以当前时间为参照查询最近的数量,查询材料的等级 如：\t {\"pid\":\"0\",\"area\":\"530102000000\",\"id\":\"\"}",dataType = "String")})
    @RequestMapping(value = "/getMaterialsInfo", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<Map<String,Object>> getMaterialsInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) throws ParseException {
        return pageMaterialInterface.getMaterialsInfo(map);
    }

    @ApiOperation(value = "",notes = "json")
    @ApiImplicitParams({@ApiImplicitParam(name = "",value =  "",dataType = "String")})
    @RequestMapping(value = "/getMaterialsInfoByArea", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<Map<String,Object>> getMaterialsInfoByArea(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) throws ParseException {

            return pageMaterialInterface.getMaterialsInfoByArea(map);


    }



 /*   @RequestMapping(value = "/getMaterialsInfoByYear", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public List<Map<String,Object>> getMaterialsInfoByYear(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {

        return pageMaterialInterface.getMaterialsInfoByYear(map);

    }*/



}



