package cn.guangtong.entity.total;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.excel.ExportConfig;

/**
 * 终端报警信息
 */
public class TerminalAlarm {

	
	private int id;// 终端id
	@ExportConfig(value = "车牌号", width = 100)
	private String plateNo;	// 车牌号

	private String simNo;	//simNo卡号
	@ExportConfig(value = "驾驶员", width = 100)
	private String driverName;	// 驾驶员
	@ExportConfig(value = "车辆颜色", width = 80)
	private String plateColor;	// 车辆颜色
	@ExportConfig(value = "经度", width = 100)
	private double longitude;	// 经度
	@ExportConfig(value = "纬度", width = 100)
	private double latitude;	// 纬度
	@ExportConfig(value = "位置信息", width = 100)
	private String location;	// 位置信息
	@ExportConfig(value = "速度", width = 100)
	private double velocity;	// 速度
	@ExportConfig(value = "报警解除方式", width = 100)
	private String solution;	//报警解除方式
	@ExportConfig(value = "报警类型", width = 100)
	private String type;	// 报警类型
	@ExportConfig(value = "操作员", width = 100)
	private int adminId;	// 操作员
	@ExportConfig(value = "处理方式", width = 100)
	private String dealWay;// 处理方式
	@ExportConfig(value = "处理时间", width = 100)
	private String dealTime;// 处理时间

	private int quantity;	// 数量
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the plateNo
	 */
	public String getPlateNo() {
		return plateNo;
	}
	/**
	 * @param plateNo the plateNo to set
	 */
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}
	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	/**
	 * @return the plateColor
	 */
	public String getPlateColor() {
		return plateColor;
	}
	/**
	 * @param plateColor the plateColor to set
	 */
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		String temp=this.getLongitude()+","+this.getLatitude();
		String location=AMAP.regeo(temp);
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the velocity
	 */
	public double getVelocity() {
		return velocity;
	}
	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the adminId
	 */
	public int getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return the dealWay
	 */
	public String getDealWay() {
		return dealWay;
	}
	/**
	 * @param dealWay the dealWay to set
	 */
	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}
	/**
	 * @return the dealTime
	 */
	public String getDealTime() {
		return dealTime;
	}
	/**
	 * @param dealTime the dealTime to set
	 */
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
	/**
	 * @return the simNo
	 */
	public String getSimNo() {
		return simNo;
	}
	/**
	 * @param simNo the simNo to set
	 */
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
