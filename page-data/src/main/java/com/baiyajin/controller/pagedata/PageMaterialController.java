package com.baiyajin.controller.pagedata;

import com.baiyajin.entity.pagedata.MaterialAndClass;
import com.baiyajin.entity.pagedata.PageMaterial;
import com.baiyajin.service.pagedata.PageMaterialInterface;
import com.baiyajin.util.IdGenerate;
import com.baiyajin.util.Results;
import com.baiyajin.vo.pagedata.MaterialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
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
    public List<MaterialVo> findByTime(MaterialVo materialVo){
        List<MaterialVo> materialVoList = pageMaterialInterface.findByTime(materialVo);
                return materialVoList;
    }

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
    public List<MaterialAndClass> getMaterialsAndClass(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) {
        return pageMaterialInterface.getMaterialsAndClass(map);
    }


}


