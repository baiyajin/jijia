package com.baiyajin.pagedata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("page_report")
public class PageReport {
  private String id;
  private String type;
  private String name;
  private String content;
  private String mark;
  private String statusID;


  private Timestamp createTime;
  private Timestamp updateTime;



}
