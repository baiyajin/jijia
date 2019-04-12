package com.baiyajin.pagedata.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
@Data
public class MaterialVo implements Serializable {

    private String type;        //传入参数数据类型
    private String areaID;        //传入参数区域编码
    private String cateId;      // 传入参数材料分类ID
    private String startTime;     //传入参数起始时间
    private String endTime;       //传入参数结束时间
    private String season;      //传入参数季度
    private String year;          //传入参数年份

    private String time;          //时间
    private BigDecimal price;   //价格
    private BigDecimal tb;      //同比
    private BigDecimal hb;      //环比
    private BigDecimal zs;      //指数
    private String add;         //地址

}
