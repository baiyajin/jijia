package com.baiyajin.entity.systemuser;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("sys_jurisdiction")
public class SystemUserTypeJurisdiction {
  private String id;
  private String name;
  private String urlAddress;

  private Timestamp createTime;
  private Timestamp updateTime;


  public SystemUserTypeJurisdiction(String name, String urlAddress){
    this.name=name;
    this.urlAddress = urlAddress;
    long timestamp = System.currentTimeMillis();
    this.createTime = new Timestamp(timestamp);
    this.updateTime = new Timestamp(timestamp);
  }





}
