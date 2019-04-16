package com.baiyajin.entity.systemuser;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("sys_log")
public class SystemLog {
  private String id;      //主键ID
  private String logType; //日志类型
  private String content; //日志内容
  private String userID;  //操作者ID
  private Timestamp createTime; //生成时间

  public SystemLog() {
  }
}
