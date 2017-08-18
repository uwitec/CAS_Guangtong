package cn.guangtong.model;

import cn.guangtong.utils.EvilTransform;

/**
 * 车辆最新状态
 * 
 * @author SunTo
 * 
 */
public class VehicleRealData extends VehicleMenu {

	// 海拔
	private double altitude;
	// 纬度
	private double latitude;
	// 经度
	private double longitude;
	// 方向
	private int direction;
	// 特别运输类型
	private String sName;

	// GPS设备在线状态, false代表不在线
	private int online;
	// 速度
	private double velocity;
	// 警报状态
	private String alarmState;

	// 报警转译
	private String alarm;
	// 状态标识
	private int state = 0;
	// 经纬度是否转换
	private int lonLat = 0;

	public int getLonLat() {
		return lonLat;
	}

	public void setLonLat(int lonLat) {
		this.lonLat = lonLat;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public String getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getLatitude() {
		if (this.lonLat == 0) {
			double[] temp = EvilTransform.transform(this.longitude, this.latitude);
			this.longitude = temp[0];
			this.latitude = temp[1];
			this.lonLat = 1;
		}
		return latitude;

	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		if (this.lonLat == 0) {
			double[] temp = EvilTransform.transform(this.longitude, this.latitude);
			this.longitude = temp[0];
			this.latitude = temp[1];
			this.lonLat = 1;
		}
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
