package com.baiyajin.pagedata.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class MaterialVo implements Serializable {

    private String type;        //传入参数数据类型
    private String area;        //传入参数区域编码
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTb() {
        return tb;
    }

    public void setTb(BigDecimal tb) {
        this.tb = tb;
    }

    public BigDecimal getHb() {
        return hb;
    }

    public void setHb(BigDecimal hb) {
        this.hb = hb;
    }

    public BigDecimal getZs() {
        return zs;
    }

    public void setZs(BigDecimal zs) {
        this.zs = zs;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public MaterialVo() {
    }
}
