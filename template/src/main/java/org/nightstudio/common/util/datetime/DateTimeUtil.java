package org.nightstudio.common.util.datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	public static long MILLISECOND = 1;
	public static long SECOND = 1000;
	public static long MINUTE = SECOND * 60;
	public static long HOUR = MINUTE * 60;
	public static long DAY = HOUR * 24;
	
	public static String getDateTimeString(Calendar c) {
		return getDateTimeString(c.getTime());
	}
	
	public static String getDateTimeString(Date date) {
		return getDateTimeString(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getDateTimeString(Calendar c, String format) {
		return getDateTimeString(c.getTime(), format);
	}
	
	public static String getDateTimeString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String getDateString(Calendar c) {
		return getDateString(c.getTime());
	}
	
	public static String getDateString(Date date) {
		return getDateString(date, "yyyy-MM-dd");
	}
	
	public static String getDateString(Calendar c, String format) {
		return getDateString(c.getTime(), format);
	}
	
	public static String getDateString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static Calendar getDateTime(String str) throws ParseException{
		return getDateTime(str, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Calendar getDateTime(String str, String format) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat(format).parse(str));
		return cal;
	}
	
	public static Calendar getDate(String str) throws ParseException {
		return getDate(str, "yyyy-MM-dd");
	}

	public static Calendar getDate(String str, String format) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat(format).parse(str));
		return cal;
	}
	
	public static long getTimeInterval(Date before, Date later) {
		return getTimeInterval(before, later, MILLISECOND);
	}
	
	public static long getTimeInterval(Date before, Date later, long unit) {
		return (later.getTime() - before.getTime()) / unit;
	}
	
	
	/**
	 * 
	 * @Title: getNow
	 * @Description: 获取当前时间
	 * @param
	 * @return Calendar
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-10-23 下午12:24:18
	 */
	public static Calendar getNow() {
		return Calendar.getInstance();
	}
	
	/**
	 * 
	 * @Title: getNowDate
	 * @Description: 获取当前时间
	 * @param
	 * @return Calendar
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-10-23 下午12:36:16
	 */
	public static Date getNowDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 获取指定日期的 零点时间 即：00:00:00
	 */
	public static Date getFirstSecondOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal = getFirstSecondOfDay(cal);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期的 零点时间 即：00:00:00
	 */
	public static Calendar getFirstSecondOfDay(Calendar cal) {
		Calendar c = cal;
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}
	
	/**
	 * 获取指定日期的 最后一秒的时间  即：23:59:59
	 */
	public static Date getLastSecondOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal = getLastSecondOfDay(cal);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期的 最后一秒的时间  即：23:59:59
	 */
	public static Calendar getLastSecondOfDay(Calendar cal) {
		Calendar c = cal;
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c;
	}
	
	/**
	 * 获取上月第一天日期
	 */
	public static Calendar getPreviousMonthFirstDay() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		lastDate.set(Calendar.MILLISECOND, 0);
		return lastDate;
	}

	/**
	 * 获得上月最后一天的日期
	 */
	public static Calendar getPreviousMonthLastDay() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是上月最后一天
		lastDate.set(Calendar.HOUR_OF_DAY, 23);
		lastDate.set(Calendar.MINUTE, 59);
		lastDate.set(Calendar.SECOND, 59);
		lastDate.set(Calendar.MILLISECOND, 999);
		return lastDate;
	}
	
	/**
	 * 得到本周的第一天（认为周一是第一天）
	 */
	public static Calendar getCurrentWeekFirstDay() {
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar; 
	}
	
	/**
	 * 得到本周的最后一天（认为周六是最后一天）
	 */
	public static Calendar getCurrentWeekLastDay() {
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar; 
	}
	
	/**
	 * 
	 * @Title: getCurrentMonthFirstDay
	 * @Description: 获取当月第一天
	 * @param
	 * @return Calendar
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-10-23 下午12:23:21
	 */
	public static Calendar getCurrentMonthFirstDay() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		lastDate.set(Calendar.MILLISECOND, 0);
		return lastDate;
	}
	
	/**
	 * 
	 * @Title: getCurrentMonthFirstDay
	 * @Description: 获取当月最后一天
	 * @param
	 * @return Calendar
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-10-23 下午12:20:36
	 */
	public static Calendar getCurrentMonthLastDay() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.roll(Calendar.DATE, -1);
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		lastDate.set(Calendar.MILLISECOND, 0);
		return lastDate;
	}

	/**
	 * 
	 * @Title: getDateBefore
	 * @Description: 得到某日期几天前的时间
	 * @param
	 * @return Date
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-10-23 下午12:22:08
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 
	 * @Title: getDateAfter
	 * @Description: 得到某日期几天后的时间
	 * @param
	 * @return Date
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-10-23 下午12:22:38
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	/**
	 * 
	 * @Title: getTimestamp
	 * @Description: 获取指定日期的 Timestamp 格式日期
	 * @param
	 * @return Timestamp
	 * @throws
	 * @author CAOXZ
	 * @version 1.0
	 * @createtime 2010-10-23 下午12:23:00
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
