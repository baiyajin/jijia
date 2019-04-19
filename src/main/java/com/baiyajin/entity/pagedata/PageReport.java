package com.baiyajin.entity.pagedata;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("page_report")
public class PageReport {
  private String id;
  private String type;
  private String name;
  private String logo;
  private String content;
  private String mark;
  private String statusID;
  private String userID;

  private String timeInterval;
  private String materialClassID;
  private String contrastRegionID;

  private Date startTime;
  private Date endTime;



  @TableField(exist = false)
  private String token;


  private Timestamp createTime;
  private Timestamp updateTime;



}
