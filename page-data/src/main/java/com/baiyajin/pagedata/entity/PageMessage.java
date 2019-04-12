package com.baiyajin.pagedata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@TableName("page_message")

public class PageMessage {
    private String id;
    private Integer number;
    private String title;
    private String text;
    private String userId;
    //0：未读，1：已读
    private Integer is_read;
    //0：正常，1：删除
    private Integer is_del;
    private Timestamp createTime;
    private Timestamp updateTime;
}
