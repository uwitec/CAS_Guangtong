package cn.guangtong.utils;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 格式化时间字符串
 */
public class FormatDateUtils {

	private static DateFormat format;
	private static String myDate;

	/**
	 * index=1 yyyy-MM-dd index=2 yyyy年MM月dd日 index=3 yyyy年MM月dd日 index=0 默认
	 * 
	 * @param index
	 * @return
	 */
	public static String getDate(Integer index) {
		Date date = new Date();
		switch (index) {
		case 1:
			format = new SimpleDateFormat("yyyy-MM-dd");
			myDate = format.format(date);
			break;
		case 2:
			format = new SimpleDateFormat("yyyy年MM月dd日");
			myDate = format.format(date);
			break;
		case 3:
			format = new SimpleDateFormat("yyMMddHHmmss");
			myDate = format.format(date);
			break;
		case 4:
			format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			myDate = format.format(date);
			break;
		case 5:
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			myDate = format.format(date);
			break;
		}
		return myDate;
	}

	/**
	 * 字符串转时间
	 * 
	 * @param Date
	 * @return
	 */
	public static Date stringToDate(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(stringDate);
		} catch (ParseException e) {
			System.out.println("FormatDateUtils时间格式化错误");
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转时间
	 * 
	 * @param Date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringDate = null;
		stringDate = sdf.format(date);
		return stringDate;
	}
	
	/**
	 * 字符串转时间
	 * 
	 * @param Date
	 * @return
	 */
	public static String dateToString2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String stringDate = null;
		stringDate = sdf.format(date);
		return stringDate;
	}
	
	/**
	 * 获得本周一0点时间
	 * 
	 * @return
	 */
	public static Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获得本周日24点时间
	 * 
	 * @return
	 */
	public static Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}

	/**
	 * 获得上周一24点时间
	 * 
	 * @return
	 */
	public static Date PreviousWeekday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, -7);
		return cal.getTime();
	}

	public static void main(String[] args) {
		System.out.println(PreviousWeekday());
	}
}
