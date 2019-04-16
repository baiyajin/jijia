package com.baiyajin.entity.pagedata;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.plugins.Page;
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
  private String userID;


  @TableField(exist = false)
  private String token;


  private Timestamp createTime;
  private Timestamp updateTime;



}
