package com.baiyajin.pagedata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("page_message")
public class PageMessage {
  private String id;
  private String materialID;
  private String areaID;
  private String userID;
  private String statusID;


  private Timestamp createTime;
  private Timestamp updateTime;



}
