package com.baiyajin.service.pagedata;

import com.baiyajin.entity.pagedata.MaterialAndClass;
import com.baiyajin.entity.pagedata.PageMaterial;
import com.baiyajin.mapper.pagedata.PageMaterialMapper;
import com.baiyajin.util.DateFormatUtil;
import com.baiyajin.vo.pagedata.MaterialVo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
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
    public List<Map<String,Object>> getMaterialsInfo(Map<String,Object> map) throws ParseException {






        //默认查询一级分类
        if(map.get("level")==null){
            map.put("level",1);
        }

        if(map.get("number")!=null){
            String number =  map.get("number").toString();
            Date nowDate = new Date();
            map.put("endDate",nowDate);

            //计算开始时间
            //计算单位为月份
            if(map.get("type")==null || map.get("type").toString().equals("1")){
                Date stDate = DateFormatUtil.dateCompute(nowDate,2,Integer.parseInt(number));
                map.put("stratDate",stDate);
            }
            //计算单位为年
            if(map.get("type")!=null && map.get("type").toString().equals("3")){
                Date stDate = DateFormatUtil.dateCompute(nowDate,1,Integer.parseInt(number));
                map.put("stratDate",stDate);
            }
        }

        if(map.get("stratDate")!=null){
            Date stDate1 =  DateFormatUtil.stringToDate(map.get("stratDate").toString(),"yyyy-MM");
            stDate1 =  DateFormatUtil.setDate(stDate1,5,1);
            map.put("stratDate",DateFormatUtil.dateToString(stDate1));
        }
        if(map.get("endDate")!=null){
            Date endDate =  DateFormatUtil.stringToDate(map.get("endDate").toString(),"yyyy-MM");
            String lastDay = DateFormatUtil.getDateLastDay(endDate);
            map.put("endDate",lastDay);
        }


        //默认云南地区
        if(map.get("area")==null || "".equals(map.get("area"))){
            map.put("area","53");
        }
        //type=0查询月份（默认），1查询季度，2查询年
        if(map.get("type")==null || map.get("type").toString().equals("0")  ){
            //直接查询返回结果(否则统计后返回)
            return baseMapper.getMaterialsInfo(map);
        }
       /* if(map.get("type").toString().equals("2")  ){
                map.put("type","quarter");
        }
        if(map.get("type").toString().equals("3")  ){
            map.put("type","year");
        }*/



         return this.getMaterialsInfoByYear(map);
    }


    private List<Map<String, Object>> getMaterialsInfoByYear(Map<String, Object> map) {

        return baseMapper.getMaterialsInfoByYear(map);

    }


}
