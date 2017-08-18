package cn.guangtong.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 计算终端报警百分比工具类
 * 
 * @ClassName:PercentagePie
 */
public class PercentageUtil {
	public static int percent(String t, String s) {
		int count = 0;
		Pattern p = Pattern.compile(t);
		Matcher m = p.matcher(s);
		while (m.find()) {
			count++;
		}
		return count;
	}
}
