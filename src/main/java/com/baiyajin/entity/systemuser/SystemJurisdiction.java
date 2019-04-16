package com.baiyajin.entity.systemuser;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
@TableName("sys_jurisdiction")
public class SystemJurisdiction {
  private String id;
  private String name;
  private String urlAddress;

  private Timestamp createTime;
  private Timestamp updateTime;


  public SystemJurisdiction(String name,String urlAddress){
    this.name=name;
    this.urlAddress = urlAddress;
    long timestamp = System.currentTimeMillis();
    this.createTime = new Timestamp(timestamp);
    this.updateTime = new Timestamp(timestamp);
  }

  public SystemJurisdiction(Map<String,Object> map){
      this.id = map.get("id")==null?null:map.get("id").toString();
      this.name= map.get("name")==null?null:map.get("name").toString();
      this.urlAddress= map.get("urlAddress")==null?null:map.get("urlAddress").toString();
      this.updateTime = new Timestamp(System.currentTimeMillis());
  }

  public SystemJurisdiction(){

  }


}
