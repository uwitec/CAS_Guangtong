package cn.guangtong.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * 设置消息头
 * 
 * @author SunTo
 * 
 */
public class SettingMessageHeaders {

	public static void setHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
	}
	
}
