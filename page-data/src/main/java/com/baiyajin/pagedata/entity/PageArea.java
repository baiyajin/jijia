package com.baiyajin.pagedata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Data
@TableName("page_area")
public class PageArea {
  private String id;
  private String name;
  private String pid;
  private String path;

  private Timestamp createTime;
  private Timestamp updateTime;


  public PageArea(){

  }

  public PageArea(Map<String,Object> map){
    this.id= UUID.randomUUID().toString().replaceAll("-","");
    this.name = map.get("name")==null?null:map.get("name").toString();
    this.pid = map.get("pid")==null?null:map.get("pid").toString();
    Long time = System.currentTimeMillis();
    this.createTime = new Timestamp(time);
    this.updateTime = new Timestamp(time);
  }


}
