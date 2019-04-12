package com.baiyajin.pagedata.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date parseDate(String time,String style){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
