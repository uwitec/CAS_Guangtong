package cn.guangtong.entity.beidou;

import java.util.Date;

/**
 * 车辆基本静态信息
 * 
 * @author SunTo
 * 
 */
public class Vehicle {
	private int vehicleId;
	// 创建时间
	private Date createDate=new Date();
	// 是否删除
	private int deleted;
	// 物主
	private String owner;
	// 备注
	private String remark;
	// 承租者id
	private int tenantId;
	// 购买日期
	private String buyTime;
	// 所属部门ID
	private int depId;
	// 部门名称
	private String depName;
	// 司机
	private String driver;
	// 司机电话
	private String driverMobile;
	// GPS车机类型,使用不同终端设备的类型
	private String gpsTerminalType;
	// 所属行业，在基础数据里定义
	private String industry;
	// 添加时间
	private Date installDate;
	// 设备巡检时间
	private String lastCheckTime;
	// 所属业户Id
	private int memberId;
	// 押运员
	private String monitor;
	// 押运员电话
	private String monitorMobile;
	// 发动机号
	private String motorId;
	// 上次离线时间
	private String offlineTime;
	// 最新在线时间
	private String onlineTime;
	// 经营许可
	private String operPermit;
	// 车牌颜色
	private int plateColor;
	// 车牌号
	private String plateNo;
	// 车籍地
	private String region;
	// 绑定的路线ID，多条路线以,号分割
	private String routes;
	// 行驶状态，正常normal，报废scrape，维修maintaining，等等
	private String runStatus;
	// GPS手机卡号
	private String simNo;
	// GPS 在线状态
	private String status;
	// 终端Id
	private int termId;
	// 使用类型
	private String useType;
	// 车辆类型,在基础数据表中定义各个车型
	private String vehicleType;
	// 厂商型号
	private String vendor;
	// 视频设备的唯一ID
	private String videodeviceId;

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getGpsTerminalType() {
		return gpsTerminalType;
	}

	public void setGpsTerminalType(String gpsTerminalType) {
		this.gpsTerminalType = gpsTerminalType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public String getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(String lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getMonitorMobile() {
		return monitorMobile;
	}

	public void setMonitorMobile(String monitorMobile) {
		this.monitorMobile = monitorMobile;
	}

	public String getMotorId() {
		return motorId;
	}

	public void setMotorId(String motorId) {
		this.motorId = motorId;
	}

	public String getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getOperPermit() {
		return operPermit;
	}

	public void setOperPermit(String operPermit) {
		this.operPermit = operPermit;
	}

	public int getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
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

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVideodeviceId() {
		return videodeviceId;
	}

	public void setVideodeviceId(String videodeviceId) {
		this.videodeviceId = videodeviceId;
	}

	public Vehicle() {
		super();
	}
}