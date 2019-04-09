
package com.baiyajin.systemuser.util;


import com.alibaba.fastjson.JSONObject;

public class ResultReturn {
    public ResultReturn() {
    }

    /*0是对的请求成功，1是错误请求失败*/
    public static String createMessage(Integer code,String message) {
        JSONObject mes = new JSONObject();
        mes.put("code", code);
        mes.put("message", message);
        return mes.toString();
    }


}
