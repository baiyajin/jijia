package com.baiyajin.pagedata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("page_subscription")
public class PageSubscription {
  private String id;
  private String titel;
  private String materialID;
  private String areaID;
  private String time;
  private String userID;
  private String statusID;


  private Timestamp createTime;
  private Timestamp updateTime;



}
