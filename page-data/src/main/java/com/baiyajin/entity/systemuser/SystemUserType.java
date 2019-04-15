package com.baiyajin.entity.systemuser;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("sys_user_type")
public class SystemUserType {
  private String id;
  private String name;
  private String jurisdictionID;
  private String statusID;
  private Timestamp createTime;
  private Timestamp updateTime;


}
