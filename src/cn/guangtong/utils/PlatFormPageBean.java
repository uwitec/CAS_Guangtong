package cn.guangtong.utils;

import java.util.List;

public class PlatFormPageBean extends PageBean{
	private String adminId;//用户登录ID
	private String startTime;//开始时间
	private String endTime;// 结束时间
	private String type; //报警类型
	private String tongjiType;//统计类型
	private List<String> simNos;//车牌号
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getSimNos() {
		return simNos;
	}
	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
	}
	public String getTongjiType() {
		return tongjiType;
	}
	public void setTongjiType(String tongjiType) {
		this.tongjiType = tongjiType;
	}
	public PlatFormPageBean(String startTime, String endTime, String type,
			List<String> simNos) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.simNos = simNos;
	}
	public PlatFormPageBean() {
		super();
	}
}