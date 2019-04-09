package com.baiyajin.systemuser.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@TableName("sys_jurisdiction")
public class SystemJurisdiction {
  private String id;
  private String name;


  private Timestamp createTime;
  private Timestamp updateTime;




}
