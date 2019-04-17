package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.MaterialAndClass;
import com.baiyajin.entity.pagedata.PageMaterial;
import com.baiyajin.mapper.pagedata.PageMaterialMapper;
import com.baiyajin.vo.pagedata.MaterialVo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PageMaterialService extends ServiceImpl<PageMaterialMapper,PageMaterial> implements PageMaterialInterface {

    @Autowired
    private PageMaterialClassInterface pageMaterialClassInterface;

    public List<MaterialAndClass> getMaterialsAndClass(Map<String,Object> map){
//        String id = map.get("id")==null?null:map.get("id").toString();
//        map.clear();
//        map.put("id",id);
        List<MaterialAndClass> materialAndClassList = baseMapper.getMaterialsAndClass(map);
        return materialAndClassList;
    }

    @Override
    public List<MaterialAndClass> getMaterialsClass(Map<String, Object> map) {
        return baseMapper.getMaterialsClass(map);
    }

    @Override
    public List<MaterialVo> findByTime(MaterialVo materialVo) {
        return baseMapper.findByTime(materialVo);
    }

    @Override
    public List<Map<String,Object>> getMaterialsInfo(Map<String,Object> map){

        //默认查询一级分类
        if(map.get("pid")==null){
            map.put("pid",0);
        }
         //map.get("number").toString();
        if(map.get("stratDate")==null && map.get("endDate")==null){
            String number =  map.get("number").toString();
            //TODO 计算起始日期和结束日期，放入map
            //......
        }
        //默认云南地区
        if(map.get("area")==null || "".equals(map.get("area"))){
            map.put("area","53");
        }
        //type=1查询月份（默认），2查询季度，3查询年
        if(map.get("type")==null || map.get("type").toString().equals("1")  ){
            return baseMapper.getMaterialsInfo(map);
        }
        if(map.get("type").toString().equals("2")  ){
                map.put("type","quarter");
        }
        if(map.get("type").toString().equals("3")  ){
            map.put("type","year");
        }
         return this.getMaterialsInfoByYear(map);
    }


    private List<Map<String, Object>> getMaterialsInfoByYear(Map<String, Object> map) {

        return baseMapper.getMaterialsInfoByYear(map);

    }


}
