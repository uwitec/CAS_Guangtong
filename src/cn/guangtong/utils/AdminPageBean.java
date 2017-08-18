package cn.guangtong.utils;

import cn.guangtong.entity.cms.Admin;

public class AdminPageBean extends PageBean<Admin> {

	private Integer adminId;
	private String username; // 登录名
	private String loginTime; // 登录时间
	private Integer isValid; // 是否有效
	private String startTime; // 开始时间
	private String endTime; // 结束时间
	private String currTime; // 服务器当前时间
	private Integer atype; // 类型
	private Integer pid; // 所属管理

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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

	public Integer getAtype() {
		return atype;
	}

	public void setAtype(Integer atype) {
		this.atype = atype;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCurrTime() {
		return currTime;
	}

	public void setCurrTime(String currTime) {
		this.currTime = currTime;
	}
}
