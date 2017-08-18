package cn.guangtong.entity.beidou;

import java.util.Date;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.EvilTransform;

/**
 * GPS实时数据
 * 
 * @author SunTo
 * 
 */
public class GpsRealData{
	// 唯一库表ID，没有实际意义
	private int id;
	// 警报状态
	private String alarmState;
	// 海拔
	private double altitude;
	// 部门
	private int depId;
	// 方向
	private int direction;
	// 视频在线状态
	private String dvrStatus;
	// 存储模块类型
	private int enclosureType;
	// 当前油量
	private double gas;
	// 纬度
	private double latitude;
	// 地理位置的文字描述,如省,市，县，路的详细描述
	private String location;
	// 经度
	private double longitude;
	// 行驶里程总量
	private double mileage;
	// GPS设备在线状态, false代表不在线
	private int online;
	// 车牌号
	private String plateNo;
	// 行驶记录仪速度
	private double recordVelocity;
	// 发送时间
	private String sendTime;
	// 车终端卡号
	private String simNo;
	// 设备终端状态
	private String status;
	// 更新时间
	private Date updateDate;
	// GPS的定位状态，false代表没有定位,被屏蔽或找不到卫星
	private int valid;
	// 车辆ID
	private String vehicleid;
	// 速度
	private double velocity;
	
	// 经纬度是否转换
	private int lonLat = 0;

	public int getLonLat() {
		return lonLat;
	}

	public void setLonLat(int lonLat) {
		this.lonLat = lonLat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getDvrStatus() {
		return dvrStatus;
	}

	public void setDvrStatus(String dvrStatus) {
		this.dvrStatus = dvrStatus;
	}

	public int getEnclosureType() {
		return enclosureType;
	}

	public void setEnclosureType(int enclosureType) {
		this.enclosureType = enclosureType;
	}

	public double getGas() {
		return gas;
	}

	public void setGas(double gas) {
		this.gas = gas;
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

	public String getLocation() {
		if (this.lonLat == 0) {
			double[] temp = EvilTransform.transform(this.longitude, this.latitude);
			this.longitude = temp[0];
			this.latitude = temp[1];
			this.lonLat = 1;
		}
		String temp = this.getLongitude() + "," + this.getLatitude();
		String location = AMAP.regeo(temp);
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public double getRecordVelocity() {
		return recordVelocity;
	}

	public void setRecordVelocity(double recordVelocity) {
		this.recordVelocity = recordVelocity;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public GpsRealData() {
		super();
	}
}