package com.baiyajin.vo.pagedata;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SubscriptionVo implements Serializable {
    private String id;
    private String title;
    private String number;
    private Date createTime;
    private Page<SubscriptionVo> page;
    private String token;
    private String userID;

}
