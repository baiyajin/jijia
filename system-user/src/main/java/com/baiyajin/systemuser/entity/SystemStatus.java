package com.baiyajin.systemuser.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("sys_status")
public class SystemStatus {
  private String id;
  private String name;


  private Timestamp createTime;
  private Timestamp updateTime;




}
