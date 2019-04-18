package com.baiyajin.entity.pagedata;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("page_helper")
public class PageHelper {
    private String id;
    private Integer artCode;
    private String title;
    private String content;
    private String statusID;
    private String publishState; //发布状态，0代表已发布，1代表未发布

    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp publishTime;
}
