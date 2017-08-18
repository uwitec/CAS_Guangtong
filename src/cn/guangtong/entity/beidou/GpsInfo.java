package cn.guangtong.entity.beidou;

import java.util.Date;

import cn.guangtong.utils.EvilTransform;

/**
 * GPS历史数据,保存GPS上传的实时定位信息，状态信息等
 * 
 * @author sutong
 * 
 */
public class GpsInfo {
	private int gpsId;
	// 报警位状态
	private int alarmState;
	// 海拔高度
	private double altitude;
	// 更新时间
	private Date createDate;
	// 方向
	private int direction;
	// 油量
	private double gas;
	// 纬度
	private double latitude;
	// 对经纬度的地理位置解析
	private String location;
	// 经度
	private double longitude;
	// 里程
	private double mileage;
	// 车牌号
	private String plateNo;
	// 行驶记录仪速度
	private double recordVelocity;
	// 运行状态
	private String runStatus;
	// 发送时间
	private String sendTime;
	// 车终端卡号
	private String simNo;
	// 状态
	private int status;
	// GPS的定位状态，false代表没有定位,被屏蔽或找不到卫星
	private int valid;
	// 速度
	private double velocity;
	
	private String startTime;	//起始时间
	private String endTime;	//结束时间
	
	// 经纬度是否转换
	private int lonLat = 0;

	public int getLonLat() {
		return lonLat;
	}

	public void setLonLat(int lonLat) {
		this.lonLat = lonLat;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getGpsId() {
		return gpsId;
	}

	public void setGpsId(int gpsId) {
		this.gpsId = gpsId;
	}

	public int getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
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

	public String getPlateNo(){
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public double getRecordVeloVity() {
		return recordVelocity;
	}

	public void setRecordVelocity(double recordVelocity) {
		this.recordVelocity = recordVelocity;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

}