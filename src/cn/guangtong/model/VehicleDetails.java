package cn.guangtong.model;

import java.util.List;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.EvilTransform;

/**
 * 根据simNO查询到的车辆详细信息
 * 
 * 
 */
public class VehicleDetails {
	// 车牌号
	private String plateNo;
	// 卡号
	private String simNo;

	// 当前油量
	private double gas;
	// 经度
	private double longitude;
	// 纬度
	private double latitude;
	// 地理位置
	private String location;
	// 方向
	private int direction;
	// 海拔
	private double altitude;
	// 行驶里程总数
	private double mileage;
	// 速度
	private double velocity;
	// 发送时间
	private String sendTime;
	// 更新时间
	private String updateDate;
	// 警报状态
	private String alarmState;
	// 设备终端状态
	private String status;
	// 驾驶证
	private String driverLicence;
	// 电话
	private String telephone;
	// 许可证单位
	private String licenseAgency;
	// 司机名称
	private String driverName;
	// 失效时间
	private String invalidDate;
	// 车辆颜色
	private String VMColor;
	// 车辆类型
	private String VehicleType;
	// 特别运输名称
	private String sName;
	// 企业名称
	private String cName;
	// 车队名称
	private String tName;
	// 终端报警状态信息
	private TerminalStatusInfo terminalStatusInfo;

	// 车架号
	private String VIN;
	// 司机1名称
	private String driverName1;
	// 1电话
	private String telephone1;
	// 所属平台
	private String platform;
	// 油耗量
	private double fuleStatistics;
	// 人工确认报警事件Id
	private String alarmProcessingId;

	// 限速区域类型
	private String aEnclosureType;
	// 限速区域id
	private String aEclosureId;

	// 进出区域类型
	private String bEnclosureType;
	// 进出区域id
	private String bEclosureId;
	// 进出报警方向
	private String bEclosureDirection;

	// 行驶路线报警Id
	private String cEclosureId;
	// 行驶路线报警时间
	private String cEnclosureTime;
	// 行驶路线报警结果
	private String cEnclosureResults;

	// 扩展车辆信号状态
	private String vehicleStatus;
	// IO状态
	private String IOStatus;

	// 模拟量
	private String Analog;
	// 无线通信网络信号强度
	private String SignalStrength;
	// GNSS定位卫星数
	private int GNSScounts;

	// 从业资格证编号
	private String professionNum;
	// 电子运单上报时间
	private String trackingDate;
	// 电子运单上报内容
	private String trackingContent;
	// 终端报警
	private List<String> TerminalAlerts;
	// 平台报警
	private List<String> PlatformAlert;

	// 经纬度是否转换
	private int lonLat = 0;

	public int getLonLat() {
		return lonLat;
	}

	public void setLonLat(int lonLat) {
		this.lonLat = lonLat;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public List<String> getTerminalAlerts() {
		return TerminalAlerts;
	}

	public void setTerminalAlerts(List<String> terminalAlerts) {
		TerminalAlerts = terminalAlerts;
	}

	public List<String> getPlatformAlert() {
		return PlatformAlert;
	}

	public void setPlatformAlert(List<String> platformAlert) {
		PlatformAlert = platformAlert;
	}

	public String getTrackingDate() {
		return trackingDate;
	}

	public void setTrackingDate(String trackingDate) {
		this.trackingDate = trackingDate;
	}

	public String getTrackingContent() {
		return trackingContent;
	}

	public void setTrackingContent(String trackingContent) {
		this.trackingContent = trackingContent;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public double getGas() {
		return gas;
	}

	public void setGas(double gas) {
		this.gas = gas;
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDriverLicence() {
		return driverLicence;
	}

	public void setDriverLicence(String driverLicence) {
		this.driverLicence = driverLicence;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLicenseAgency() {
		return licenseAgency;
	}

	public void setLicenseAgency(String licenseAgency) {
		this.licenseAgency = licenseAgency;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * @return the invalidDate
	 */
	public String getInvalidDate() {
		return invalidDate;
	}

	/**
	 * @param invalidDate
	 *            the invalidDate to set
	 */
	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getVMColor() {
		return VMColor;
	}

	public void setVMColor(String vMColor) {
		VMColor = vMColor;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public TerminalStatusInfo getTerminalStatusInfo() {
		return terminalStatusInfo;
	}

	public void setTerminalStatusInfo(TerminalStatusInfo terminalStatusInfo) {
		this.terminalStatusInfo = terminalStatusInfo;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public String getDriverName1() {
		return driverName1;
	}

	public void setDriverName1(String driverName1) {
		this.driverName1 = driverName1;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public double getFuleStatistics() {
		return fuleStatistics;
	}

	public void setFuleStatistics(double fuleStatistics) {
		this.fuleStatistics = fuleStatistics;
	}

	public String getAlarmProcessingId() {
		return alarmProcessingId;
	}

	public void setAlarmProcessingId(String alarmProcessingId) {
		this.alarmProcessingId = alarmProcessingId;
	}

	public String getaEnclosureType() {
		return aEnclosureType;
	}

	public void setaEnclosureType(String aEnclosureType) {
		this.aEnclosureType = aEnclosureType;
	}

	public String getaEclosureId() {
		return aEclosureId;
	}

	public void setaEclosureId(String aEclosureId) {
		this.aEclosureId = aEclosureId;
	}

	public String getbEnclosureType() {
		return bEnclosureType;
	}

	public void setbEnclosureType(String bEnclosureType) {
		this.bEnclosureType = bEnclosureType;
	}

	public String getbEclosureId() {
		return bEclosureId;
	}

	public void setbEclosureId(String bEclosureId) {
		this.bEclosureId = bEclosureId;
	}

	public String getbEclosureDirection() {
		return bEclosureDirection;
	}

	public void setbEclosureDirection(String bEclosureDirection) {
		this.bEclosureDirection = bEclosureDirection;
	}

	public String getcEclosureId() {
		return cEclosureId;
	}

	public void setcEclosureId(String cEclosureId) {
		this.cEclosureId = cEclosureId;
	}

	public String getcEnclosureTime() {
		return cEnclosureTime;
	}

	public void setcEnclosureTime(String cEnclosureTime) {
		this.cEnclosureTime = cEnclosureTime;
	}

	public String getcEnclosureResults() {
		return cEnclosureResults;
	}

	public void setcEnclosureResults(String cEnclosureResults) {
		this.cEnclosureResults = cEnclosureResults;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getIOStatus() {
		return IOStatus;
	}

	public void setIOStatus(String iOStatus) {
		IOStatus = iOStatus;
	}

	public String getAnalog() {
		return Analog;
	}

	public void setAnalog(String analog) {
		Analog = analog;
	}

	public String getSignalStrength() {
		return SignalStrength;
	}

	public void setSignalStrength(String signalStrength) {
		SignalStrength = signalStrength;
	}

	public int getGNSScounts() {
		return GNSScounts;
	}

	public void setGNSScounts(int gNSScounts) {
		GNSScounts = gNSScounts;
	}

	public String getProfessionNum() {
		return professionNum;
	}

	public void setProfessionNum(String professionNum) {
		this.professionNum = professionNum;
	}

	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return VehicleType;
	}

	/**
	 * @param vehicleType
	 *            the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}

}
