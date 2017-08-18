package cn.guangtong.controller.driver;

import cn.guangtong.utils.PageBean;

public class DriverPageBean extends PageBean{
	
	private String startTime;// 开始
	private String endTime;//结束
	private String adminId;//用户登录ID
	private String simNo;//车牌号 
	private String id;//编号
	private String dName;//模糊搜索司机姓名
	private String tel;//电话号
	private String cName;//企业名
	private String defaultVehicleNum;//车牌号
	private String driverClass;//车型
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
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
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getSimNo() {
		return simNo;
	}
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the cName
	 */
	public String getcName() {
		return cName;
	}
	/**
	 * @param cName the cName to set
	 */
	public void setcName(String cName) {
		this.cName = cName;
	}
	/**
	 * @return the defaultVehicleNum
	 */
	public String getDefaultVehicleNum() {
		return defaultVehicleNum;
	}
	/**
	 * @param defaultVehicleNum the defaultVehicleNum to set
	 */
	public void setDefaultVehicleNum(String defaultVehicleNum) {
		this.defaultVehicleNum = defaultVehicleNum;
	}
	/**
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return driverClass;
	}
	/**
	 * @param driverClass the driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	
}
