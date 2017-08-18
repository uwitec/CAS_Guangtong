package cn.guangtong.model;

/**
 * 终端报警状态信息
 */
public class TerminalStatusInfo {
	// ACC开丶关
	private String accStatus;
	// 定位 未定位
	private String locationStatus;
	// 南纬 北纬
	private String latitude;
	// 西经 东经
	private String longitude;
	// 停运 营运状态
	private String operating;
	// 油路断开 油路正常
	private String oilStatus;
	// 电路断开 电路正常
	private String circuitStatus;
	// 车门加锁 车门解锁
	private String cardoorStatus;
	// 紧急告警
	private String emergencyAlarm = "紧急告警";
	// 超速告警
	private String overspeedAlarm = "超速告警";
	// 疲劳驾驶
	private String fatigueDriving = "疲劳驾驶";
	// 预警
	private String earlyWarning = "预警";
	// GNSS模块发生故障
	private String gnssFailure = "GNSS模块发生故障";
	// GNSS模块天线未接
	private String antennaFailure = "GNSS模块天线未接";
	// GNSS模块天线短路
	private String antennashort = "GNSS模块天线短路";
	// 终端主电源欠压
	private String undervoltage = "终端主电源欠压";
	// 终端主电源掉电
	private String powerOff = "终端主电源掉电";
	// 终端LCD或显示器故障
	private String monitorfailure = "终端LCD或显示器故障";
	// TTS模块故障
	private String moduleFault = "TTS模块故障";
	// 摄像头故障
	private String cameraFault = "摄像头故障";
	// 计价器故障
	private String meterfault = "计价器故障";
	// 当天累计驾驶超时
	private String driveTimeout = "当天累计驾驶超时";
	// 超时停车
	private String overtimePark = "超时停车";
	// 进出区域
	private String exitArea = "进出区域";
	// 进出路线
	private String exitRoute = "进出路线";
	// 路段行驶时间不足/过长
	private String travelTime = "路段行驶时间不足/过长";
	// 路线偏离报警
	private String routeDevAlarm = "路线偏离报警";
	// 车辆vss报警
	private String vvssAlarm = "车辆VSS报警";
	// 车辆油量异常
	private String vAbnormaLoil = "车辆油量异常";
	// 车辆被盗
	private String vTheft = "车辆被盗";
	// 车辆非法点火
	private String vIgnition = "车辆非法点火";
	// 车辆非法位移
	private String vDisplacement = "车辆非法位移";

	/**
	 * @return the accStatus
	 */
	public String getAccStatus() {
		return accStatus;
	}

	/**
	 * @param accStatus
	 *            the accStatus to set
	 */
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	/**
	 * @return the locationStatus
	 */
	public String getLocationStatus() {
		return locationStatus;
	}

	/**
	 * @param locationStatus
	 *            the locationStatus to set
	 */
	public void setLocationStatus(String locationStatus) {
		this.locationStatus = locationStatus;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the operating
	 */
	public String getOperating() {
		return operating;
	}

	/**
	 * @param operating
	 *            the operating to set
	 */
	public void setOperating(String operating) {
		this.operating = operating;
	}

	/**
	 * @return the oilStatus
	 */
	public String getOilStatus() {
		return oilStatus;
	}

	/**
	 * @param oilStatus
	 *            the oilStatus to set
	 */
	public void setOilStatus(String oilStatus) {
		this.oilStatus = oilStatus;
	}

	/**
	 * @return the circuitStatus
	 */
	public String getCircuitStatus() {
		return circuitStatus;
	}

	/**
	 * @param circuitStatus
	 *            the circuitStatus to set
	 */
	public void setCircuitStatus(String circuitStatus) {
		this.circuitStatus = circuitStatus;
	}

	/**
	 * @return the cardoorStatus
	 */
	public String getCardoorStatus() {
		return cardoorStatus;
	}

	/**
	 * @param cardoorStatus
	 *            the cardoorStatus to set
	 */
	public void setCardoorStatus(String cardoorStatus) {
		this.cardoorStatus = cardoorStatus;
	}

	/**
	 * @return the emergencyAlarm
	 */
	public String getEmergencyAlarm() {
		return emergencyAlarm;
	}

	/**
	 * @param emergencyAlarm
	 *            the emergencyAlarm to set
	 */
	public void setEmergencyAlarm(String emergencyAlarm) {
		this.emergencyAlarm = emergencyAlarm;
	}

	/**
	 * @return the overspeedAlarm
	 */
	public String getOverspeedAlarm() {
		return overspeedAlarm;
	}

	/**
	 * @param overspeedAlarm
	 *            the overspeedAlarm to set
	 */
	public void setOverspeedAlarm(String overspeedAlarm) {
		this.overspeedAlarm = overspeedAlarm;
	}

	/**
	 * @return the fatigueDriving
	 */
	public String getFatigueDriving() {
		return fatigueDriving;
	}

	/**
	 * @param fatigueDriving
	 *            the fatigueDriving to set
	 */
	public void setFatigueDriving(String fatigueDriving) {
		this.fatigueDriving = fatigueDriving;
	}

	/**
	 * @return the earlyWarning
	 */
	public String getEarlyWarning() {
		return earlyWarning;
	}

	/**
	 * @param earlyWarning
	 *            the earlyWarning to set
	 */
	public void setEarlyWarning(String earlyWarning) {
		this.earlyWarning = earlyWarning;
	}

	/**
	 * @return the gnssFailure
	 */
	public String getGnssFailure() {
		return gnssFailure;
	}

	/**
	 * @param gnssFailure
	 *            the gnssFailure to set
	 */
	public void setGnssFailure(String gnssFailure) {
		this.gnssFailure = gnssFailure;
	}

	/**
	 * @return the antennaFailure
	 */
	public String getAntennaFailure() {
		return antennaFailure;
	}

	/**
	 * @param antennaFailure
	 *            the antennaFailure to set
	 */
	public void setAntennaFailure(String antennaFailure) {
		this.antennaFailure = antennaFailure;
	}

	/**
	 * @return the antennashort
	 */
	public String getAntennashort() {
		return antennashort;
	}

	/**
	 * @param antennashort
	 *            the antennashort to set
	 */
	public void setAntennashort(String antennashort) {
		this.antennashort = antennashort;
	}

	/**
	 * @return the undervoltage
	 */
	public String getUndervoltage() {
		return undervoltage;
	}

	/**
	 * @param undervoltage
	 *            the undervoltage to set
	 */
	public void setUndervoltage(String undervoltage) {
		this.undervoltage = undervoltage;
	}

	/**
	 * @return the powerOff
	 */
	public String getPowerOff() {
		return powerOff;
	}

	/**
	 * @param powerOff
	 *            the powerOff to set
	 */
	public void setPowerOff(String powerOff) {
		this.powerOff = powerOff;
	}

	/**
	 * @return the monitorfailure
	 */
	public String getMonitorfailure() {
		return monitorfailure;
	}

	/**
	 * @param monitorfailure
	 *            the monitorfailure to set
	 */
	public void setMonitorfailure(String monitorfailure) {
		this.monitorfailure = monitorfailure;
	}

	/**
	 * @return the moduleFault
	 */
	public String getModuleFault() {
		return moduleFault;
	}

	/**
	 * @param moduleFault
	 *            the moduleFault to set
	 */
	public void setModuleFault(String moduleFault) {
		this.moduleFault = moduleFault;
	}

	/**
	 * @return the cameraFault
	 */
	public String getCameraFault() {
		return cameraFault;
	}

	/**
	 * @param cameraFault
	 *            the cameraFault to set
	 */
	public void setCameraFault(String cameraFault) {
		this.cameraFault = cameraFault;
	}

	/**
	 * @return the driveTimeout
	 */
	public String getDriveTimeout() {
		return driveTimeout;
	}

	/**
	 * @param driveTimeout
	 *            the driveTimeout to set
	 */
	public void setDriveTimeout(String driveTimeout) {
		this.driveTimeout = driveTimeout;
	}

	/**
	 * @return the overtimePark
	 */
	public String getOvertimePark() {
		return overtimePark;
	}

	/**
	 * @param overtimePark
	 *            the overtimePark to set
	 */
	public void setOvertimePark(String overtimePark) {
		this.overtimePark = overtimePark;
	}

	/**
	 * @return the exitArea
	 */
	public String getExitArea() {
		return exitArea;
	}

	/**
	 * @param exitArea
	 *            the exitArea to set
	 */
	public void setExitArea(String exitArea) {
		this.exitArea = exitArea;
	}

	/**
	 * @return the exitRoute
	 */
	public String getExitRoute() {
		return exitRoute;
	}

	/**
	 * @param exitRoute
	 *            the exitRoute to set
	 */
	public void setExitRoute(String exitRoute) {
		this.exitRoute = exitRoute;
	}

	/**
	 * @return the travelTime
	 */
	public String getTravelTime() {
		return travelTime;
	}

	/**
	 * @param travelTime
	 *            the travelTime to set
	 */
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * @return the routeDevAlarm
	 */
	public String getRouteDevAlarm() {
		return routeDevAlarm;
	}

	/**
	 * @param routeDevAlarm
	 *            the routeDevAlarm to set
	 */
	public void setRouteDevAlarm(String routeDevAlarm) {
		this.routeDevAlarm = routeDevAlarm;
	}

	/**
	 * @return the vvssAlarm
	 */
	public String getVvssAlarm() {
		return vvssAlarm;
	}

	/**
	 * @param vvssAlarm
	 *            the vvssAlarm to set
	 */
	public void setVvssAlarm(String vvssAlarm) {
		this.vvssAlarm = vvssAlarm;
	}

	/**
	 * @return the vAbnormaLoil
	 */
	public String getvAbnormaLoil() {
		return vAbnormaLoil;
	}

	/**
	 * @param vAbnormaLoil
	 *            the vAbnormaLoil to set
	 */
	public void setvAbnormaLoil(String vAbnormaLoil) {
		this.vAbnormaLoil = vAbnormaLoil;
	}

	/**
	 * @return the vTheft
	 */
	public String getvTheft() {
		return vTheft;
	}

	/**
	 * @param vTheft
	 *            the vTheft to set
	 */
	public void setvTheft(String vTheft) {
		this.vTheft = vTheft;
	}

	/**
	 * @return the vIgnition
	 */
	public String getvIgnition() {
		return vIgnition;
	}

	/**
	 * @param vIgnition
	 *            the vIgnition to set
	 */
	public void setvIgnition(String vIgnition) {
		this.vIgnition = vIgnition;
	}

	/**
	 * @return the vDisplacement
	 */
	public String getvDisplacement() {
		return vDisplacement;
	}

	/**
	 * @param vDisplacement
	 *            the vDisplacement to set
	 */
	public void setvDisplacement(String vDisplacement) {
		this.vDisplacement = vDisplacement;
	}

	/**
	 * @return the meterfault
	 */
	public String getMeterfault() {
		return meterfault;
	}

	/**
	 * @param meterfault
	 *            the meterfault to set
	 */
	public void setMeterfault(String meterfault) {
		this.meterfault = meterfault;
	}

}
