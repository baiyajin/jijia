package com.baiyajin.systemuser.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("sys_jurisdiction")
public class SystemLog {
  private String id;
  private String logType;
  private String content;
  private String userID;


  private Timestamp createTime;




}
