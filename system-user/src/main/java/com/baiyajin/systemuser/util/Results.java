package com.baiyajin.systemuser.util;

public class Results {
    private int result;
    private String msg;
    private Object data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Results() {
    }

    public Results(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }


    public Results(int result, String msg, Object data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }
}
