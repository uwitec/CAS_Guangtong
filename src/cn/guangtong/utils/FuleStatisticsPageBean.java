package cn.guangtong.utils;

import cn.guangtong.model.FuleStatistics;

/**
 * 油耗统计分页查询
 * 
 * @author SunTo
 * 
 */
public class FuleStatisticsPageBean extends PageBean<FuleStatistics> {
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 统计单位
	private String type;
	// 查询的simNo卡号
	private String simNo;
	private String adminId;//用户登录ID
	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

}
