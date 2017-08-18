package cn.guangtong.controller.vehicle;


/**
 * 字符串解析位置信息和告警状态信息
 * 
 * @ClassName:StringParse
 * 
 */

public class StringParse {

	/**
	 * 字符串解析位置信息
	 * 
	 * @param status
	 * @return
	 */
	public static String pLocationInfo(String status) {
		// 位置信息
		String locationInfo = "";
		// 解析位置字符串
		String cardoorStatus = status.substring(19, 20);
		String circuitStatus = status.substring(20, 21);
		String oilStatus = status.substring(21, 22);
		String operating = status.substring(27, 28);
		String longitude = status.substring(28, 29);
		String latitude = status.substring(29, 30);
		String locationStatus = status.substring(30, 31);
		String accStatus = status.substring(31, 32);
		if ("1".equals(accStatus)) {
			accStatus = "ACC开--";
			locationInfo += accStatus;
		} else {
			accStatus = "ACC关--";
			locationInfo += accStatus;
		}
		if ("1".equals(locationStatus)) {
			locationStatus = "定位--";
			locationInfo += locationStatus;
		} else {
			locationStatus = "未定位--";
			locationInfo += locationStatus;
		}
		if ("1".equals(latitude)) {
			latitude = "南纬--";
			locationInfo += latitude;
		} else {
			latitude = "北纬--";
			locationInfo += latitude;
		}
		if ("1".equals(longitude)) {
			longitude = "西经--";
			locationInfo += longitude;
		} else {
			longitude = "东经--";
			locationInfo += longitude;
		}
		if ("1".equals(operating)) {
			operating = "停运--";
			locationInfo += operating;
		} else {
			operating = "营运状态--";
			locationInfo += operating;
		}
		if ("1".equals(oilStatus)) {
			oilStatus = "油路断开--";
			locationInfo += oilStatus;
		} else {
			oilStatus = "油路正常--";
			locationInfo += oilStatus;
		}
		if ("1".equals(circuitStatus)) {
			circuitStatus = "电路断开--";
			locationInfo += circuitStatus;
		} else {
			circuitStatus = "电路正常--";
			locationInfo += circuitStatus;
		}
		if ("1".equals(cardoorStatus)) {
			cardoorStatus = "车门加锁--";
			locationInfo += cardoorStatus;
		} else {
			cardoorStatus = "车门解锁--";
			locationInfo += cardoorStatus;
		}
		return locationInfo;
	}

	/**
	 * 字符串解析告警状态信息
	 * 
	 * @param alarmState
	 * @return
	 */
	public static String pAlarmStatus(String alarmState) {
		// 告警状态信息
		String alarmStatus = "";
		// 解析报警状态字符串
		String vDisplacement = alarmState.substring(3, 4);
		String vIgnition = alarmState.substring(4, 5);
		String vTheft = alarmState.substring(5, 6);
		String vAbnormaLoil = alarmState.substring(6, 7);
		String vvssAlarm = alarmState.substring(7, 8);
		String routeDevAlarm = alarmState.substring(8, 9);
		String travelTime = alarmState.substring(9, 10);
		String exitRoute = alarmState.substring(10, 11);
		String exitArea = alarmState.substring(11, 12);
		String overtimePark = alarmState.substring(12, 13);
		String driveTimeout = alarmState.substring(13, 14);
		String cameraFault = alarmState.substring(20, 21);
		String moduleFault = alarmState.substring(21, 22);
		String monitorfailure = alarmState.substring(22, 23);
		String powerOff = alarmState.substring(23, 24);
		String undervoltage = alarmState.substring(24, 25);
		String antennashort = alarmState.substring(25, 26);
		String antennaFailure = alarmState.substring(26, 27);
		String gnssFailure = alarmState.substring(27, 28);
		String earlyWarning = alarmState.substring(28, 29);
		String fatigueDriving = alarmState.substring(29, 30);
		String overspeedAlarm = alarmState.substring(30, 31);
		String emergencyAlarm = alarmState.substring(31, 32);

		if ("1".equals(emergencyAlarm)) {
			emergencyAlarm = "紧急告警--";
			alarmStatus += emergencyAlarm;
		}
		if ("1".equals(overspeedAlarm)) {
			overspeedAlarm = "超速告警--";
			alarmStatus += overspeedAlarm;
		}
		if ("1".equals(fatigueDriving)) {
			fatigueDriving = "疲劳驾驶--";
			alarmStatus += fatigueDriving;
		}
		if ("1".equals(earlyWarning)) {
			earlyWarning = "预警--";
			alarmStatus += earlyWarning;
		}
		if ("1".equals(gnssFailure)) {
			gnssFailure = "GNSS模块发生故障--";
			alarmStatus += gnssFailure;
		}
		if ("1".equals(antennaFailure)) {
			antennaFailure = "GNSS模块天线未接--";
			alarmStatus += antennaFailure;
		}
		if ("1".equals(antennashort)) {
			antennashort = "GNSS模块天线短路--";
			alarmStatus += antennashort;
		}
		if ("1".equals(undervoltage)) {
			undervoltage = "终端主电源欠压--";
			alarmStatus += undervoltage;
		}
		if ("1".equals(powerOff)) {
			powerOff = "终端主电源掉电--";
			alarmStatus += powerOff;
		}
		if ("1".equals(cameraFault)) {
			cameraFault = "摄像头故障--";
			alarmStatus += cameraFault;
		}
		if ("1".equals(monitorfailure)) {
			monitorfailure = "终端LCD或显示器故障--";
			alarmStatus += monitorfailure;
		}
		if ("1".equals(moduleFault)) {
			moduleFault = "TTS模块故障--";
			alarmStatus += moduleFault;
		}
		if ("1".equals(driveTimeout)) {
			driveTimeout = "当天累计驾驶超时--";
			alarmStatus += driveTimeout;
		}
		if ("1".equals(overtimePark)) {
			overtimePark = "超时停车--";
			alarmStatus += overtimePark;
		}
		if ("1".equals(exitArea)) {
			exitArea = "进出区域--";
			alarmStatus += exitArea;
		}
		if ("1".equals(exitRoute)) {
			exitRoute = "进出路线--";
			alarmStatus += exitRoute;
		}
		if ("1".equals(travelTime)) {
			travelTime = "路段行驶时间不足/过长--";
			alarmStatus += travelTime;
		}
		if ("1".equals(routeDevAlarm)) {
			routeDevAlarm = "路线偏离报警--";
			alarmStatus += routeDevAlarm;
		}
		if ("1".equals(vvssAlarm)) {
			vvssAlarm = "车辆vss报警--";
			alarmStatus += vvssAlarm;
		}
		if ("1".equals(vAbnormaLoil)) {
			vAbnormaLoil = "车辆油量异常--";
			alarmStatus += vAbnormaLoil;
		}
		if ("1".equals(vTheft)) {
			vTheft = "车辆被盗--";
			alarmStatus += vTheft;
		}
		if ("1".equals(vIgnition)) {
			vIgnition = "车辆非法点火--";
			alarmStatus += vIgnition;
		}
		if ("1".equals(vDisplacement)) {
			vDisplacement = "车辆非法位移--";
			alarmStatus += vDisplacement;
		}
		return alarmStatus;
	}

}
