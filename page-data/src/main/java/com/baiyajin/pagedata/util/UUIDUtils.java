package com.baiyajin.pagedata.util;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID(){
       return UUID.randomUUID().toString().replaceAll("-","");
    }

}
