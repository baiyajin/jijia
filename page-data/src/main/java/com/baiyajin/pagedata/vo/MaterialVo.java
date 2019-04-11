package com.baiyajin.pagedata.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

public class MaterialVo implements Serializable {

    private String type;        //传入参数数据类型
    private String area;        //传入参数区域编码
    private String cateId;      // 传入参数材料分类ID
    private Date startTime;     //传入参数起始时间
    private Date endTime;       //传入参数结束时间
    private String seacon;      //传入参数季度
    private Date year;          //传入参数年份

    private Date time;          //时间
    private BigDecimal price;   //价格
    private BigDecimal tb;      //同比
    private BigDecimal hb;      //环比
    private BigDecimal zs;      //指数
    private String add;         //地址
}
