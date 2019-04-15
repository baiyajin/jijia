package com.baiyajin.entity.pagedata;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@TableName("page_material")
public class PageMaterial {
  private String id;
  private String name;
  private BigDecimal price;
  private BigDecimal exponent;
  private BigDecimal tongbi;
  private BigDecimal huanbi;
  private String materialClassID;
  private String statusID;




  private Timestamp createTime;
  private Timestamp updateTime;




}
