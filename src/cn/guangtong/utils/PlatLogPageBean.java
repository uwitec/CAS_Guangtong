package cn.guangtong.utils;

import cn.guangtong.entity.cms.PlatLog;

public class PlatLogPageBean extends PageBean<PlatLog>{
	
	private String username; //要搜索的管理员
	private String module; //要搜索的操作模块
	private String context; //要搜索的操作内容
	private String startTime; //要搜索的开始时间
	private String endTime; //要搜索的结束时间
	private String ip; //要搜索的ip
	private String adminId;//当前登录人ID	
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
