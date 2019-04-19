package com.baiyajin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public static String getDateLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        return getDateLastDay(year.toString(),month.toString());

    }

    public static String getDateLastDay(String year, String month) {
        //year="2018" month="2"
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month));

        // System.out.println(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(calendar.getTime());
    }

    /**
     * 设置日期时间
     *
     * @param date
     *            需要设置的时间
     * @param field
     *            设置的类型 （1：年，2月，5天，10小时，12分，13秒）
     * @param value
     *            设置的值
     * @return 设置好的日期
     */
    public static Date setDate(Date date, Integer field, Integer value) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(field, value);
        return gc.getTime();
    }



}
