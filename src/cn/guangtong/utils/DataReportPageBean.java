package cn.guangtong.utils;

import cn.guangtong.model.DataReport;

public class DataReportPageBean extends PageBean<DataReport>{
	private String adminId;//用户登录ID
	private String startTime;	//开始时间
	private String endTime;		//结束时间
	private String simNo;		//SIM卡号
	
	/**
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
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
	public String getSimNo() {
		return simNo;
	}
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
}
