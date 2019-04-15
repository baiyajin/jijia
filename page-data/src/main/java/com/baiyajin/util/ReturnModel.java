package com.baiyajin.util;

import java.util.Map;

public class ReturnModel {

    /**
     * 返回页面实体封装
     * @param i 返回状态，0：失败，1：成功
     */
    public ReturnModel(int i){
        if(i==0){
            this.success = false;
            this.msg = "操作失败";
        }else{
            this.success = true;
            this.msg = "操作成功";
        }
    }

    /**
     * 返回页面实体封装
     * @param i 返回状态，0：失败，1：成功
     * @param  bodyMap 返回数据内容
     */
    public ReturnModel(int i, Map<String,Object> bodyMap){
        if(i==0){
            this.success = false;
            this.msg = "操作失败";
        }else{
            this.success = true;
            this.msg = "操作成功";
        }
        this.bodyMap = bodyMap;
    }

    private Boolean success;
    private String msg;
    private Map<String,Object> bodyMap;

    public Boolean getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
