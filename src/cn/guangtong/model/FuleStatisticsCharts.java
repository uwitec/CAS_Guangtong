package cn.guangtong.model;

import java.util.Map;

/**
 * 油耗统计折线图
 * 
 * @author SunTo
 * 
 */
public class FuleStatisticsCharts {

	// 车牌号
	private String simNo;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 类型
	private String type;
	// 图形数据
	private Map<String, Object> data;
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

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
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
