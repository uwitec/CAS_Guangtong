package cn.guangtong.utils;

import java.util.ArrayList;
import java.util.List;


import cn.guangtong.model.TerminalStatusInfo;

/**
 * 字符串解析状态信息和告警状态信息
 * 
 * @ClassName:StringParse
 * 
 */
public class StringParse {
	public static TerminalStatusInfo stringParse(int aState, int sTatus) {
		String s1 = "";
		String s2 = "";
		// 十进制转成二进制 //Gpsinfo表10进制数 status状态信息 alarmState告警状态
		String status = Integer.toBinaryString(sTatus);
		String alarmState = Integer.toBinaryString(aState);
		// 字符串拼接 不足32位 在前面用0填充
		for (int i = 0; i < (32 - alarmState.length()); i++) {
			s1 += "0";
		}
		for (int i = 0; i < (32 - status.length()); i++) {
			s2 += "0";
		}
		// 告警状态字符串
		alarmState = s1 + alarmState;
		// 状态信息字符串
		status = s2 + status;
		// 解析状态实体类
		TerminalStatusInfo statusInfo = new TerminalStatusInfo();
		// 车门加锁 车门解锁
		String cardoorStatus = status.substring(19, 20);
		// 电路断开 电路正常
		String circuitStatus = status.substring(20, 21);
		// 油路断开 油路正常
		String oilStatus = status.substring(21, 22);
		// 停运 营运状态
		String operating = status.substring(27, 28);
		// 西经 东经
		String longitude = status.substring(28, 29);
		// 南纬 北纬
		String latitude = status.substring(29, 30);
		// 定位 未定位
		String locationStatus = status.substring(30, 31);
		// ACC开丶关
		String accStatus = status.substring(31, 32);

		// 车辆非法位移
		String vDisplacement = alarmState.substring(3, 4);
		// 车辆非法点火
		String vIgnition = alarmState.substring(4, 5);
		// 车辆被盗
		String vTheft = alarmState.substring(5, 6);
		// 车辆油量异常
		String vAbnormaLoil = alarmState.substring(6, 7);
		// 车辆vss报警
		String vvssAlarm = alarmState.substring(7, 8);
		// 路线偏离报警
		String routeDevAlarm = alarmState.substring(8, 9);
		// 路段行驶时间不足/过长
		String travelTime = alarmState.substring(9, 10);
		// 进出路线
		String exitRoute = alarmState.substring(10, 11);
		// 进出区域
		String exitArea = alarmState.substring(11, 12);
		// 超时停车
		String overtimePark = alarmState.substring(12, 13);
		// 当天累计驾驶超时
		String driveTimeout = alarmState.substring(13, 14);
		// 计价器故障
		String meterfault = alarmState.substring(14, 15);
		// 摄像头故障
		String cameraFault = alarmState.substring(20, 21);
		// TTS模块故障
		String moduleFault = alarmState.substring(21, 22);
		// 终端LCD或显示器故障
		String monitorfailure = alarmState.substring(22, 23);
		// 终端主电源掉电
		String powerOff = alarmState.substring(23, 24);
		// 终端主电源欠压
		String undervoltage = alarmState.substring(24, 25);
		// GNSS模块天线短路
		String antennashort = alarmState.substring(25, 26);
		// GNSS模块天线未接
		String antennaFailure = alarmState.substring(26, 27);
		// GNSS模块发生故障
		String gnssFailure = alarmState.substring(27, 28);
		// 预警
		String earlyWarning = alarmState.substring(28, 29);
		// 疲劳驾驶
		String fatigueDriving = alarmState.substring(29, 30);
		// 超速告警
		String overspeedAlarm = alarmState.substring(30, 31);
		// 紧急告警
		String emergencyAlarm = alarmState.substring(31, 32);
		if ("1".equals(accStatus)) {
			accStatus = "ACC开";
			statusInfo.setAccStatus(accStatus);
		} else {
			accStatus = "ACC关";
			statusInfo.setAccStatus(accStatus);
		}
		if ("1".equals(locationStatus)) {
			locationStatus = "定位";
			statusInfo.setLocationStatus(locationStatus);
		} else {
			locationStatus = "未定位";
			statusInfo.setLocationStatus(locationStatus);
		}
		if ("1".equals(latitude)) {
			latitude = "南纬";
			statusInfo.setLatitude(latitude);
		} else {
			latitude = "北纬";
			statusInfo.setLatitude(latitude);
		}
		if ("1".equals(longitude)) {
			longitude = "西经";
			statusInfo.setLongitude(longitude);
		} else {
			longitude = "东经";
			statusInfo.setLongitude(longitude);
		}
		if ("1".equals(operating)) {
			operating = "停运";
			statusInfo.setOperating(operating);
		} else {
			operating = "营运状态";
			statusInfo.setOperating(operating);
		}
		if ("1".equals(oilStatus)) {
			oilStatus = "油路断开";
			statusInfo.setOilStatus(oilStatus);
		} else {
			oilStatus = "油路正常";
			statusInfo.setOilStatus(oilStatus);
		}
		if ("1".equals(circuitStatus)) {
			circuitStatus = "电路断开";
			statusInfo.setCircuitStatus(circuitStatus);
		} else {
			circuitStatus = "电路正常";
			statusInfo.setCircuitStatus(circuitStatus);
		}
		if ("1".equals(cardoorStatus)) {
			cardoorStatus = "车门加锁";
			statusInfo.setCardoorStatus(cardoorStatus);
		} else {
			cardoorStatus = "车门解锁";
			statusInfo.setCardoorStatus(cardoorStatus);
		}
		if ("1".equals(emergencyAlarm)) {
			emergencyAlarm = "紧急告警";
			statusInfo.setEmergencyAlarm(emergencyAlarm);
		}
		if ("1".equals(overspeedAlarm)) {
			overspeedAlarm = "超速告警";
			statusInfo.setOverspeedAlarm(overspeedAlarm);
		}
		if ("1".equals(fatigueDriving)) {
			fatigueDriving = "疲劳驾驶";
			statusInfo.setFatigueDriving(fatigueDriving);
		}
		if ("1".equals(earlyWarning)) {
			earlyWarning = "预警";
			statusInfo.setEarlyWarning(earlyWarning);
		}
		if ("1".equals(gnssFailure)) {
			gnssFailure = "GNSS模块发生故障";
			statusInfo.setGnssFailure(gnssFailure);
		}
		if ("1".equals(antennaFailure)) {
			antennaFailure = "GNSS模块天线未接";
			statusInfo.setAntennaFailure(antennaFailure);
		}
		if ("1".equals(antennashort)) {
			antennashort = "GNSS模块天线短路";
			statusInfo.setAntennashort(antennashort);
		}
		if ("1".equals(undervoltage)) {
			undervoltage = "终端主电源欠压";
			statusInfo.setUndervoltage(undervoltage);
		}
		if ("1".equals(powerOff)) {
			powerOff = "终端主电源掉电";
			statusInfo.setPowerOff(powerOff);
		}
		if ("1".equals(cameraFault)) {
			cameraFault = "摄像头故障";
			statusInfo.setCameraFault(cameraFault);
		}
		if ("1".equals(monitorfailure)) {
			monitorfailure = "终端LCD或显示器故障";
			statusInfo.setMonitorfailure(monitorfailure);
		}
		if ("1".equals(moduleFault)) {
			moduleFault = "TTS模块故障";
			statusInfo.setModuleFault(moduleFault);
		}
		if ("1".equals(meterfault)) {
			meterfault = "计价器故障";
			statusInfo.setModuleFault(meterfault);
		}
		if ("1".equals(driveTimeout)) {
			driveTimeout = "当天累计驾驶超时";
			statusInfo.setDriveTimeout(driveTimeout);
		}
		if ("1".equals(overtimePark)) {
			overtimePark = "超时停车";
			statusInfo.setOvertimePark(overtimePark);
		}
		if ("1".equals(exitArea)) {
			exitArea = "进出区域";
			statusInfo.setExitArea(exitArea);
		}
		if ("1".equals(exitRoute)) {
			exitRoute = "进出路线";
			statusInfo.setExitRoute(exitRoute);
		}
		if ("1".equals(travelTime)) {
			travelTime = "路段行驶时间不足/过长";
			statusInfo.setTravelTime(travelTime);
		}
		if ("1".equals(routeDevAlarm)) {
			routeDevAlarm = "路线偏离报警";
			statusInfo.setRouteDevAlarm(routeDevAlarm);
		}
		if ("1".equals(vvssAlarm)) {
			vvssAlarm = "车辆vss报警";
			statusInfo.setVvssAlarm(vvssAlarm);
		}
		if ("1".equals(vAbnormaLoil)) {
			vAbnormaLoil = "车辆油量异常";
			statusInfo.setvAbnormaLoil(vAbnormaLoil);
		}
		if ("1".equals(vTheft)) {
			vTheft = "车辆被盗";
			statusInfo.setvTheft(vTheft);
		}
		if ("1".equals(vIgnition)) {
			vIgnition = "车辆非法点火";
			statusInfo.setvIgnition(vIgnition);
		}
		if ("1".equals(vDisplacement)) {
			vDisplacement = "车辆非法位移";
			statusInfo.setvDisplacement(vDisplacement);
		}
		return statusInfo;
	}

	public static TerminalStatusInfo stringToParse(String alarmState, String status) {
		// 解析状态实体类
		TerminalStatusInfo statusInfo = new TerminalStatusInfo();
		// 车门加锁 车门解锁
		String cardoorStatus = status.substring(19, 20);
		// 电路断开 电路正常
		String circuitStatus = status.substring(20, 21);
		// 油路断开 油路正常
		String oilStatus = status.substring(21, 22);
		// 停运 营运状态
		String operating = status.substring(27, 28);
		// 西经 东经
		String longitude = status.substring(28, 29);
		// 南纬 北纬
		String latitude = status.substring(29, 30);
		// 定位 未定位
		String locationStatus = status.substring(30, 31);
		// ACC开丶关
		String accStatus = status.substring(31, 32);

		// 车辆非法位移
		String vDisplacement = alarmState.substring(3, 4);
		// 车辆非法点火
		String vIgnition = alarmState.substring(4, 5);
		// 车辆被盗
		String vTheft = alarmState.substring(5, 6);
		// 车辆油量异常
		String vAbnormaLoil = alarmState.substring(6, 7);
		// 车辆vss报警
		String vvssAlarm = alarmState.substring(7, 8);
		// 路线偏离报警
		String routeDevAlarm = alarmState.substring(8, 9);
		// 路段行驶时间不足/过长
		String travelTime = alarmState.substring(9, 10);
		// 进出路线
		String exitRoute = alarmState.substring(10, 11);
		// 进出区域
		String exitArea = alarmState.substring(11, 12);
		// 超时停车
		String overtimePark = alarmState.substring(12, 13);
		// 当天累计驾驶超时
		String driveTimeout = alarmState.substring(13, 14);
		// 当天累计驾驶超时
		String meterfault = alarmState.substring(14, 15);
		// 摄像头故障
		String cameraFault = alarmState.substring(20, 21);
		// TTS模块故障
		String moduleFault = alarmState.substring(21, 22);
		// 终端LCD或显示器故障
		String monitorfailure = alarmState.substring(22, 23);
		// 终端主电源掉电
		String powerOff = alarmState.substring(23, 24);
		// 终端主电源欠压
		String undervoltage = alarmState.substring(24, 25);
		// GNSS模块天线短路
		String antennashort = alarmState.substring(25, 26);
		// GNSS模块天线未接
		String antennaFailure = alarmState.substring(26, 27);
		// GNSS模块发生故障
		String gnssFailure = alarmState.substring(27, 28);
		// 预警
		String earlyWarning = alarmState.substring(28, 29);
		// 疲劳驾驶
		String fatigueDriving = alarmState.substring(29, 30);
		// 超速告警
		String overspeedAlarm = alarmState.substring(30, 31);
		// 紧急告警
		String emergencyAlarm = alarmState.substring(31, 32);
		if ("1".equals(accStatus)) {
			accStatus = "ACC开";
			statusInfo.setAccStatus(accStatus);
		} else {
			accStatus = "ACC关";
			statusInfo.setAccStatus(accStatus);
		}
		if ("1".equals(locationStatus)) {
			locationStatus = "定位";
			statusInfo.setLocationStatus(locationStatus);
		} else {
			locationStatus = "未定位";
			statusInfo.setLocationStatus(locationStatus);
		}
		if ("1".equals(latitude)) {
			latitude = "南纬";
			statusInfo.setLatitude(latitude);
		} else {
			latitude = "北纬";
			statusInfo.setLatitude(latitude);
		}
		if ("1".equals(longitude)) {
			longitude = "西经";
			statusInfo.setLongitude(longitude);
		} else {
			longitude = "东经";
			statusInfo.setLongitude(longitude);
		}
		if ("1".equals(operating)) {
			operating = "停运";
			statusInfo.setOperating(operating);
		} else {
			operating = "营运状态";
			statusInfo.setOperating(operating);
		}
		if ("1".equals(oilStatus)) {
			oilStatus = "油路断开";
			statusInfo.setOilStatus(oilStatus);
		} else {
			oilStatus = "油路正常";
			statusInfo.setOilStatus(oilStatus);
		}
		if ("1".equals(circuitStatus)) {
			circuitStatus = "电路断开";
			statusInfo.setCircuitStatus(circuitStatus);
		} else {
			circuitStatus = "电路正常";
			statusInfo.setCircuitStatus(circuitStatus);
		}
		if ("1".equals(cardoorStatus)) {
			cardoorStatus = "车门加锁";
			statusInfo.setCardoorStatus(cardoorStatus);
		} else {
			cardoorStatus = "车门解锁";
			statusInfo.setCardoorStatus(cardoorStatus);
		}

		if ("1".equals(emergencyAlarm)) {
			emergencyAlarm = "紧急告警";
			statusInfo.setEmergencyAlarm(emergencyAlarm);
		}
		if ("1".equals(overspeedAlarm)) {
			overspeedAlarm = "超速告警";
			statusInfo.setOverspeedAlarm(overspeedAlarm);
		}
		if ("1".equals(fatigueDriving)) {
			fatigueDriving = "疲劳驾驶";
			statusInfo.setFatigueDriving(fatigueDriving);
		}
		if ("1".equals(earlyWarning)) {
			earlyWarning = "预警";
			statusInfo.setEarlyWarning(earlyWarning);
		}
		if ("1".equals(gnssFailure)) {
			gnssFailure = "GNSS模块发生故障";
			statusInfo.setGnssFailure(gnssFailure);
		}
		if ("1".equals(antennaFailure)) {
			antennaFailure = "GNSS模块天线未接";
			statusInfo.setAntennaFailure(antennaFailure);
		}
		if ("1".equals(antennashort)) {
			antennashort = "GNSS模块天线短路";
			statusInfo.setAntennashort(antennashort);
		}
		if ("1".equals(undervoltage)) {
			undervoltage = "终端主电源欠压";
			statusInfo.setUndervoltage(undervoltage);
		}
		if ("1".equals(powerOff)) {
			powerOff = "终端主电源掉电";
			statusInfo.setPowerOff(powerOff);
		}
		if ("1".equals(cameraFault)) {
			cameraFault = "摄像头故障";
			statusInfo.setCameraFault(cameraFault);
		}
		if ("1".equals(monitorfailure)) {
			monitorfailure = "终端LCD或显示器故障";
			statusInfo.setMonitorfailure(monitorfailure);
		}
		if ("1".equals(moduleFault)) {
			moduleFault = "TTS模块故障";
			statusInfo.setModuleFault(moduleFault);
		}
		if ("1".equals(meterfault)) {
			meterfault = "计价器故障";
			statusInfo.setModuleFault(meterfault);
		}
		if ("1".equals(driveTimeout)) {
			driveTimeout = "当天累计驾驶超时";
			statusInfo.setDriveTimeout(driveTimeout);
		}
		if ("1".equals(overtimePark)) {
			overtimePark = "超时停车";
			statusInfo.setOvertimePark(overtimePark);
		}
		if ("1".equals(exitArea)) {
			exitArea = "进出区域";
			statusInfo.setExitArea(exitArea);
		}
		if ("1".equals(exitRoute)) {
			exitRoute = "进出路线";
			statusInfo.setExitRoute(exitRoute);
		}
		if ("1".equals(travelTime)) {
			travelTime = "路段行驶时间不足/过长";
			statusInfo.setTravelTime(travelTime);
		}
		if ("1".equals(routeDevAlarm)) {
			routeDevAlarm = "路线偏离报警";
			statusInfo.setRouteDevAlarm(routeDevAlarm);
		}
		if ("1".equals(vvssAlarm)) {
			vvssAlarm = "车辆vss报警";
			statusInfo.setVvssAlarm(vvssAlarm);
		}
		if ("1".equals(vAbnormaLoil)) {
			vAbnormaLoil = "车辆油量异常";
			statusInfo.setvAbnormaLoil(vAbnormaLoil);
		}
		if ("1".equals(vTheft)) {
			vTheft = "车辆被盗";
			statusInfo.setvTheft(vTheft);
		}
		if ("1".equals(vIgnition)) {
			vIgnition = "车辆非法点火";
			statusInfo.setvIgnition(vIgnition);
		}
		if ("1".equals(vDisplacement)) {
			vDisplacement = "车辆非法位移";
			statusInfo.setvDisplacement(vDisplacement);
		}
		return statusInfo;
	}

	/**
	 * 字符串解析告警状态信息
	 * 
	 * @param aState
	 * @return
	 */
	public static List<String> stringAlarm(int aState) {
		String s1 = "";
		String alarmState = Integer.toBinaryString(aState);
		// 字符串拼接 不足32位 在前面用0填充
		for (int i = 0; i < (32 - alarmState.length()); i++) {
			s1 += "0";
		}
		// 告警状态字符串
		alarmState = s1 + alarmState;
		List<String> list = new ArrayList<String>();

		// 车辆非法位移
		String vDisplacement = alarmState.substring(3, 4);
		// 车辆非法点火
		String vIgnition = alarmState.substring(4, 5);
		// 车辆被盗
		String vTheft = alarmState.substring(5, 6);
		// 车辆油量异常
		String vAbnormaLoil = alarmState.substring(6, 7);
		// 车辆vss报警
		String vvssAlarm = alarmState.substring(7, 8);
		// 路线偏离报警
		String routeDevAlarm = alarmState.substring(8, 9);
		// 路段行驶时间不足/过长
		String travelTime = alarmState.substring(9, 10);
		// 进出路线
		String exitRoute = alarmState.substring(10, 11);
		// 进出区域
		String exitArea = alarmState.substring(11, 12);
		// 超时停车
		String overtimePark = alarmState.substring(12, 13);
		// 当天累计驾驶超时
		String driveTimeout = alarmState.substring(13, 14);
		// 计价器故障
		String meterfault = alarmState.substring(14, 15);
		// 摄像头故障
		String cameraFault = alarmState.substring(20, 21);
		// TTS模块故障
		String moduleFault = alarmState.substring(21, 22);
		// 终端LCD或显示器故障
		String monitorfailure = alarmState.substring(22, 23);
		// 终端主电源掉电
		String powerOff = alarmState.substring(23, 24);
		// 终端主电源欠压
		String undervoltage = alarmState.substring(24, 25);
		// GNSS模块天线短路
		String antennashort = alarmState.substring(25, 26);
		// GNSS模块天线未接
		String antennaFailure = alarmState.substring(26, 27);
		// GNSS模块发生故障
		String gnssFailure = alarmState.substring(27, 28);
		// 预警
		String earlyWarning = alarmState.substring(28, 29);
		// 疲劳驾驶
		String fatigueDriving = alarmState.substring(29, 30);
		// 超速告警
		String overspeedAlarm = alarmState.substring(30, 31);
		// 紧急告警
		String emergencyAlarm = alarmState.substring(31, 32);

		if ("1".equals(emergencyAlarm)) {
			emergencyAlarm = "紧急告警";
			list.add(emergencyAlarm);
		}
		if ("1".equals(overspeedAlarm)) {
			overspeedAlarm = "超速告警";
			list.add(overspeedAlarm);
		}
		if ("1".equals(fatigueDriving)) {
			fatigueDriving = "疲劳驾驶";
			list.add(fatigueDriving);
		}
		if ("1".equals(earlyWarning)) {
			earlyWarning = "预警";
			list.add(earlyWarning);
		}
		if ("1".equals(gnssFailure)) {
			gnssFailure = "GNSS模块发生故障";
			list.add(gnssFailure);
		}
		if ("1".equals(antennaFailure)) {
			antennaFailure = "GNSS模块天线未接";
			list.add(antennaFailure);
		}
		if ("1".equals(antennashort)) {
			antennashort = "GNSS模块天线短路";
			list.add(antennashort);
		}
		if ("1".equals(undervoltage)) {
			undervoltage = "终端主电源欠压";
			list.add(undervoltage);
		}
		if ("1".equals(powerOff)) {
			powerOff = "终端主电源掉电";
			list.add(powerOff);
		}
		if ("1".equals(cameraFault)) {
			cameraFault = "摄像头故障";
			list.add(cameraFault);
		}
		if ("1".equals(monitorfailure)) {
			monitorfailure = "终端LCD或显示器故障";
			list.add(monitorfailure);
		}
		if ("1".equals(moduleFault)) {
			moduleFault = "TTS模块故障";
			list.add(moduleFault);
		}
		if ("1".equals(meterfault)) {
			meterfault = "计价器故障";
			list.add(meterfault);
		}
		if ("1".equals(driveTimeout)) {
			driveTimeout = "当天累计驾驶超时";
			list.add(driveTimeout);
		}
		if ("1".equals(overtimePark)) {
			overtimePark = "超时停车";
			list.add(overtimePark);
		}
		if ("1".equals(exitArea)) {
			exitArea = "进出区域";
			list.add(exitArea);
		}
		if ("1".equals(exitRoute)) {
			exitRoute = "进出路线";
			list.add(exitRoute);
		}
		if ("1".equals(travelTime)) {
			travelTime = "路段行驶时间不足/过长";
			list.add(travelTime);
		}
		if ("1".equals(routeDevAlarm)) {
			routeDevAlarm = "路线偏离报警";
			list.add(routeDevAlarm);
		}
		if ("1".equals(vvssAlarm)) {
			vvssAlarm = "车辆vss报警";
			list.add(vvssAlarm);
		}
		if ("1".equals(vAbnormaLoil)) {
			vAbnormaLoil = "车辆油量异常";
			list.add(vAbnormaLoil);
		}
		if ("1".equals(vTheft)) {
			vTheft = "车辆被盗";
			list.add(vTheft);
		}
		if ("1".equals(vIgnition)) {
			vIgnition = "车辆非法点火";
			list.add(vIgnition);
		}
		if ("1".equals(vDisplacement)) {
			vDisplacement = "车辆非法位移";
			list.add(vDisplacement);
		}
		return list;
	}

	/**
	 * 字符串解析状态信息
	 * 
	 * @param status
	 * @return
	 */
	public static String pLocationInfo(String status) {
		// 状态信息
		String locationInfo = "";
		// 解析状态字符串
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
	 * 字符串解析状态信息
	 * 
	 * @param status
	 * @return
	 */
	public static String pLocationInfoInt(int pstatus) {
		String s2 = "";
		String status = Integer.toBinaryString(pstatus);
		for (int i = 0; i < (32 - status.length()); i++) {
			s2 += "0";
		}
		// 状态信息字符串
		status = s2 + status;
		// 状态信息
		String locationInfo = "";
		// 解析状态字符串
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
		String meterfault = alarmState.substring(14, 15);
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
		if ("1".equals(meterfault)) {
			meterfault = "计价器故障--";
			alarmStatus += meterfault;
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

	/**
	 * 字符串解析告警状态信息
	 * 
	 * @param alarmState
	 * @return
	 */
	public static String alarmStatusInfo(int aState) {
		String s1 = "";
		String alarmState = Integer.toBinaryString(aState);
		// 字符串拼接 不足32位 在前面用0填充
		for (int i = 0; i < (32 - alarmState.length()); i++) {
			s1 += "0";
		}
		// 告警状态字符串
		alarmState = s1 + alarmState;
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
		String meterfault = alarmState.substring(14, 15);
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
		if ("1".equals(meterfault)) {
			meterfault = "计价器故障--";
			alarmStatus += meterfault;
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

	/**
	 * 保存报警状态字符串
	 * 
	 * @param 转入字符串数组
	 * @return
	 */
	public static StringBuffer toParseString(String[] alarmState) {
		StringBuffer s1 = new StringBuffer();
		for (int i = 1; i < 25; i++) {
			if (i == 1) {
				s1.append("000");
			}
			if (i == 14) {
				s1.append("00000");
			}
			for (int j = 0; j < alarmState.length; j++) {
				if (i == Integer.valueOf(alarmState[j])) {
					s1.append("1");
					break;
				}
			}
			if (s1.length() - 3 != i && i < 14) {
				s1.append("0");
			} else if (s1.length() - 8 != i && i != 24 && i >= 14) {
				s1.append("0");
			}
		}
		return s1;
	}

	/**
	 * 保存报警状态字符串
	 * 
	 * @param 传入整型数组
	 * @return
	 */
	public static StringBuffer toParseInt(int[] par) {
		String[] b = new String[32];

		for (int i = 0; i < par.length; i++) {
			if (par[i] <= 12) {
				b[(par[i] + 2)] = "1";
			} else {
				b[(par[i] + 2 + 5)] = "1";
			}
		}
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			if (b[i] == null) {
				b[i] = "0";
			}
			s.append(b[i]);
		}

		return s;
	}
}
