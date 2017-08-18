package cn.guangtong.utils;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.guangtong.excel.OnlineRate;


public class GpsInfoFormPageBean extends PageBean<OnlineRate>{
	
	private String startTime;
	private String endTime;
	private String type;
	private List<String> simNos;
	private List<String> simNo;
	
	private String adminId;
	
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
	public List<String> getSimNos() {
		return simNos;
	}
	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
	}
	public List<String> getSimNo() {
		return simNo;
	}
	public void setSimNo(List<String> simNo) {
		this.simNo = simNo;
	}
}
