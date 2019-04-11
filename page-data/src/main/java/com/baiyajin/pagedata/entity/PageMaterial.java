package com.baiyajin.pagedata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName("page_material")
public class PageMaterial {
  private String id;
  private String name;
  private BigDecimal price;
  private int exponent;
  private int tongbi;
  private int huanbi;
  private String materialClassID;
  private String statusID;




  private Timestamp createTime;
  private Timestamp updateTime;




}
