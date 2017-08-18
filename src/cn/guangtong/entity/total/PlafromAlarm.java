package cn.guangtong.entity.total;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.excel.ExportConfig;


/**
 * 平台报警信息
 */
public class PlafromAlarm {
	
	
	private int id;//平台id
	@ExportConfig(value = "车牌号", width = 100)
	private String plateNo;//车牌号
	
	private String simNo;//simNo卡号
	@ExportConfig(value = "驾驶员", width = 100)
	private String driverName;//驾驶员
	@ExportConfig(value = "车辆颜色", width = 80)
	private String plateColor;//车辆颜色
	@ExportConfig(value = "经度", width = 100)
	private double longitude;//经度
	@ExportConfig(value = "纬度", width = 100)
	private double latitude;//纬度
	@ExportConfig(value = "位置信息", width = 150)
	private String location;//位置信息
	@ExportConfig(value = "速度", width = 80)
	private double velocity;//速度
	@ExportConfig(value = "报警类型", width = 100)
	private String type;//报警类型
	@ExportConfig(value = "报警解除方式", width = 100)
	private String solution;//报警解除方式
	@ExportConfig(value = "操作员", width = 100)
	private int adminId;//操作员
	@ExportConfig(value = "处理方式", width = 100)
	private String dealWay;//处理方式
	@ExportConfig(value = "处理时间", width = 200)
	private String dealTime;//处理时间
	
	private int quantity;// 数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPlateNo() {
		return plateNo;
	}
	
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	
	public String getDriverName() {
		return driverName;
	}
	
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
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public String getLocation() {
		String temp=this.getLongitude()+","+this.getLatitude();
		String location=AMAP.regeo(temp);
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSolution() {
		return solution;
	}
	
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getDealWay() {
		return dealWay;
	}
	
	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getSimNo() {
		return simNo;
	}
	
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
