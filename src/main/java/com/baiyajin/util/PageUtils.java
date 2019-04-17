package com.baiyajin.util;

import org.apache.commons.lang.StringUtils;

public class PageUtils {

    /**
     *
     * @param pageCurrent 当前页
     * @param pageSize
     * @return 开始记录数
     */
    public static  int pageNoRecord(String pageCurrent,String pageSize){
        if (StringUtils.isBlank(pageCurrent) || "0".equals(pageCurrent)){
            pageCurrent = "1";
        }
        return Integer.valueOf(pageSize)*(Integer.valueOf(pageCurrent)-1);
    };
}
