package com.baiyajin.entity.pagedata;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("page_subscription")
public class PageSubscription {
  private String id;
  private String title;
  private String materialID;
  private String areaID;
  private String time;
  private String userID;
  private String statusID;
  private String number;

  private Timestamp createTime;
  private Timestamp updateTime;



}
