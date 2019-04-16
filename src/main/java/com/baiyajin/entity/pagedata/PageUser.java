package com.baiyajin.entity.pagedata;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("page_user")
public class PageUser {
  private String id;
  private String name;
  private String phone;
  private String password;
  private String statusID;
  private String userTypeID;
  @TableField(exist = false)
  private String token;

  private Timestamp createTime;
  private Timestamp updateTime;



}
