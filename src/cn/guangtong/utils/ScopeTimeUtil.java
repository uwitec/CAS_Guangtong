package cn.guangtong.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间格式处理
 * @author Administrator
 *
 */
public class ScopeTimeUtil {

	/**
	 * 处理时间查询时间段
	 * @param sTime
	 * @param eTime
	 * @param type
	 * @param tt
	 * @return
	 * @throws ParseException
	 */
	public static List<Object> getTime(String sTime,String eTime,String type) throws ParseException{
		List<Object> list = new ArrayList<Object>();
		Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date startDate = df.parse(sTime);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(eTime);
        endCalendar.setTime(endDate);
        while(true){
        	if(startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()){
        		list.add(df.format(startCalendar.getTime()));
        	}else{
        		break;
        	}
        	if(type.equals("yyyy-MM-dd")){
        		startCalendar.add(Calendar.DATE, 1);
			}else if(type.equals("yyyy-MM")){
				startCalendar.add(Calendar.DAY_OF_MONTH, 31);
			}else if(type.equals("yyyy")){
				startCalendar.add(Calendar.DAY_OF_YEAR, 366);
			}
        }
		return list;
	}
	
	
	public static List<String> getTimeToString(String sTime,String eTime,String type) throws ParseException{
		List<String> list = new ArrayList<String>();
		Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date startDate = df.parse(sTime);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(eTime);
        endCalendar.setTime(endDate);
        while(true){
        	if(startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()){
        		list.add(df.format(startCalendar.getTime()));
        	}else{
        		break;
        	}
        	if(type.equals("yyyy-MM-dd")){
        		startCalendar.add(Calendar.DATE, 1);
			}else if(type.equals("yyyy-MM")){
				startCalendar.add(Calendar.DAY_OF_MONTH, 31);
			}else if(type.equals("yyyy")){
				startCalendar.add(Calendar.DAY_OF_YEAR, 366);
			}
        }
		return list;
	}
	
	
	public static String dateFormat(String type,Object obj){
		String str = null;
		if(type.equals("day")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			str = sdf.format(obj);
		}else if(type.equals("month")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			str = sdf.format(obj);
		}else if(type.equals("year")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			str = sdf.format(obj);
		}
//		System.out.println(type.equals("month")+"lllllll");
//		System.out.println(obj+"nnnnnnnnnn");
		return str;
	}
	
	 /* 
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        Date dt = null;
		try {
			dt = sdf.parse(res);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return dt;
    }
}
