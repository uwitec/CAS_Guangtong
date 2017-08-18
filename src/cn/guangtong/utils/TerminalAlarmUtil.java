package cn.guangtong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.total.TerminalAlarm;
//终端报警信息插入工具类
public class TerminalAlarmUtil {
	public static TerminalAlarm addTerminalAlarm(Map<String, Object> map) {
		// 获取当前登录账户信息
		// Admin admin = (Admin) session.getAttribute("loginAdmin");
		// 获取当前服务器时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		TerminalAlarm tAlarm = new TerminalAlarm();
		// 操作员
		tAlarm.setAdminId(110);
		// 处理时间
		tAlarm.setDealTime(simpleDateFormat.format((Date) map.get("sendTime")));
		// 处理方式
		tAlarm.setDealWay("手动");
		// 驾驶员
		tAlarm.setDriverName((String) map.get("driverName"));
		// 经度
		tAlarm.setLatitude((Double) map.get("latitude"));
		// 纬度
		tAlarm.setLongitude((Double) map.get("longitude"));
		// 位置信息
		tAlarm.setLocation((String) map.get("location"));
		// 车辆颜色
		tAlarm.setPlateColor("11");
		// 报警解除方式
		tAlarm.setSolution("设置区域指令");
		// 车牌号
		tAlarm.setPlateNo((String) map.get("plateNo"));
		tAlarm.setSimNo((String) map.get("simNo"));
		// 报警类型
		tAlarm.setType("进出区域");
		// 速度
		tAlarm.setVelocity((Double) map.get("velocity"));
		return tAlarm;

	}
}
