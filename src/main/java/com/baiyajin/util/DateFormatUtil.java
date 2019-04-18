package com.baiyajin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatUtil {

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

	/**
	 * 字符串转日期（默认字符串格式："yyyy-MM-dd HH:mm:ss"）
	 * 
	 * @param dateString
	 *            字符串
	 * @return
	 * @throws ParseException
	 *             参数字符串转换异常
	 */
	public static Date stringToDate(String dateString) throws ParseException {
		return stringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
	}




	/**
	 * 字符串转日期
	 * 
	 * @param dateString
	 *            字符串
	 * @param format
	 *            日期字符串格式
	 * @return
	 * @throws ParseException
	 *             参数字符串转换异常
	 */
	public static Date stringToDate(String dateString, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(dateString);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return （默认字符串格式："yyyy-MM-dd HH:mm:ss"）
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param formatString:日期字符串格式
	 * @return
	 */
	public static String dateToString(Date date, String formatString) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		try {

			return formatter.format(date);
		} catch (Exception e) {

			e.printStackTrace();
			return formatter.format(new Date());
		}
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param type（1：年，2月，3周，5天，10小时，12分，13秒）
	 * @param val
	 *            加减天数
	 * @return
	 */
	public static Date dateCompute(Date date, Integer type, Integer val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(type, val);
		return gc.getTime();
	}

	/**
	 * 日期计算并返回string
	 * 
	 * @param date
	 *            （日期格式默认为"yyyy-MM-dd HH:mm:ss"）
	 * @param type
	 *            1：年，2：月份，3：周，5天
	 * @param val
	 *            加减天数
	 * @return
	 */
	public static String dateComputeToString(Date date, Integer type, Integer val) {
		return dateToString(dateCompute(date, type, val));
	}

	/**
	 * 日期计算并返回string
	 * 
	 * @param date
	 * @param format:日期格式
	 * @param type
	 *            1：年，2：月份，3：周，5天
	 * @param val
	 *            加减天数
	 * @return
	 */
	public static String dateComputeToString(Date date, String format, Integer type, Integer val) {
		date = dateCompute(date, type, val);
		return dateToString(date, format);
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>e 返回1, s=e 返回0,s<e 返回-1)
	 * @param s
	 * @param e
	 * @return boolean
	 */
	public static int compareDate(Date s, Date e) {
		Long sLong = s.getTime();
		Long eLong = e.getTime();
		Long result = sLong - eLong;

		return (int) (result == 0 ? result : result / Math.abs(result));
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>e 返回1, s=e 返回0,s<e 返回-1)
	 * @param s
	 * @param e
	 * @param type
	 *            比较类型
	 * @return boolean
	 * @throws ParseException
	 */
	public static int compareDate(Date s, Date e, String type) throws ParseException {

		if ("day".equals(type)) {
			s = stringToDate(dateToString(s), "yyyy-MM-dd");
			e = stringToDate(dateToString(e), "yyyy-MM-dd");
		}

		if ("month".equals(type)) {
			s = stringToDate(dateToString(s), "yyyy-MM");
			e = stringToDate(dateToString(e), "yyyy-MM");
		}

		if ("hour".equals(type)) {
			s = stringToDate(dateToString(s), "yyyy-MM-dd HH");
			e = stringToDate(dateToString(e), "yyyy-MM-dd HH");
		}

		if ("half".equals(type)) {
			s = stringToDate(dateToString(s), "yyyy-MM-dd HH:mm");
			e = stringToDate(dateToString(e), "yyyy-MM-dd HH:mm");
		}

		if ("ten".equals(type)) {
			s = stringToDate(dateToString(s), "yyyy-MM-dd HH:mm");
			e = stringToDate(dateToString(e), "yyyy-MM-dd HH:mm");
		}
		return compareDate(s, e);
	}

	/**
	 * 获取当前季度
	 */
//	public static String getQuarter() {
//		return getQuarter( Calendar.getInstance());
//	}






	/**
	 * 获取季度
	 */
	public static Integer getQuarter(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return getQuarter( Calendar.getInstance());
	}


	/**
	 * 获取季度
	 */
	public static Integer getQuarter(Calendar c) {
		int month = c.get(c.MONTH) + 1;
		int quarter = 0;
		if (month >= 1 && month <= 3) {
			quarter = 1;
		} else if (month >= 4 && month <= 6) {
			quarter = 2;
		} else if (month >= 7 && month <= 9) {
			quarter = 3;
		} else {
			quarter = 4;
		}
		return quarter;
	}


	public static Date getQuarterFrsitDay(Calendar c) {

		int quarter = getQuarter(c);
		if(quarter==1){
//			stringToDate();
		}



		int s = c.get(c.MONTH)+1;

		System.out.println(s);

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




	public static void main(String[] args) throws ParseException {

	/*	Date stDate1 =  DateFormatUtil.stringToDate("2019-04","yyyy-mm");
		stDate1 =  DateFormatUtil.setDate(stDate1,5,1);

		System.out.println(DateFormatUtil.dateToString(stDate1));*/

		System.out.println(DateFormatUtil.getDateLastDay(new Date()));
	}

}
