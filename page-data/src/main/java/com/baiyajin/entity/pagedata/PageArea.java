package com.baiyajin.entity.pagedata;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@TableName("page_area")
public class PageArea {
  private String id;
  private String name;
  private String pid;


  private Timestamp createTime;
  private Timestamp updateTime;




}
