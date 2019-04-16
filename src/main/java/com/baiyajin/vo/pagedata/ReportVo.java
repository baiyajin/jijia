package com.baiyajin.vo.pagedata;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReportVo implements Serializable {

    private String id;  //id
    private String type;    //报告类型
    private String title;   //报告标题
    private Date createTime;  //时间
    private String userID;

    private Page<ReportVo>  page;//分页信息
}
