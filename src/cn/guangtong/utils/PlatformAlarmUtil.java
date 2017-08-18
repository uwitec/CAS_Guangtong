package cn.guangtong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.total.PlafromAlarm;
import cn.guangtong.model.PlatformStatusInfo;
//平台报警信息插入工具类
public class PlatformAlarmUtil {
	public static PlafromAlarm addPlatformAlarm(Map<String, Object> map) {
		// 获取当前登录账户信息
		// Admin admin = (Admin) session.getAttribute("loginAdmin");
		// Hasmap<String, Object> map = gpsService.sVehicleConditionInformation(request.getParameter("simNo"));
		// 获取当前服务器时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		PlafromAlarm plAlarm = new PlafromAlarm();
		// 操作员
		plAlarm.setAdminId(110);
		// 处理时间
		plAlarm.setDealTime(simpleDateFormat.format((Date) map.get("sendTime")));
		// 处理方式
		plAlarm.setDealWay("手动");
		// 驾驶员
		plAlarm.setDriverName((String) map.get("driverName"));
		// 经度
		plAlarm.setLatitude((Double) map.get("latitude"));
		// 纬度
		plAlarm.setLongitude((Double) map.get("longitude"));
		// 位置信息
		plAlarm.setLocation((String) map.get("location"));
		// 车辆颜色
		plAlarm.setPlateColor("11");
		// 车牌号
		plAlarm.setPlateNo((String) map.get("plateNo"));
		plAlarm.setSimNo((String) map.get("simNo"));
		// 报警解除方式
		plAlarm.setSolution("设置区域指令");
		// 报警类型
		plAlarm.setType(new PlatformStatusInfo().getWing_Area_Alarm());
		// 速度
		plAlarm.setVelocity((Double) map.get("velocity"));
		return plAlarm;

	}
}
