package com.baiyajin.vo.pagedata;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HelperVo implements Serializable {
    private String id;  //主键ID
    private Integer code; //编号
    private String title;   //标题
    private Date time;  //发布时间
    private String publishState;    //发布状态
    private String content; //内容

    private Integer pageCurrent;    //分页查询开始记录数
    private Integer pSize;

    private Page<HelperVo> page;//分页信息
}
