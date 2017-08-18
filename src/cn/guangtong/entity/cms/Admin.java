package cn.guangtong.entity.cms;

import java.io.Serializable;

import javax.persistence.Table;

/**
 * 管理员实体类
 * 
 * @author sutong
 * 
 */
@Table(name = "logistics_cms.admin")
public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String password;
	private String startTime;
	private String endTime;
	private String loginTime;
	private Integer atype=0;
	private Integer pid;
	// 管理员有效或无效判断
	private Integer isValid;
	// 服务器当前时间
	private String currTime;
	// 父级管理员名称
	private String parentName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
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

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getCurrTime() {
		return currTime;
	}

	public void setCurrTime(String currTime) {
		this.currTime = currTime;
	}

	public Admin() {
		super();
	}

	public Admin(Integer id) {
		super();
		this.id = id;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Admin(Integer id, String username, String password, String startTime, String endTime, String loginTime, Integer atype, Integer pid, Integer isValid, String currTime, String parentName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.startTime = startTime;
		this.endTime = endTime;
		this.loginTime = loginTime;
		this.atype = atype;
		this.pid = pid;
		this.isValid = isValid;
		this.currTime = currTime;
		this.parentName = parentName;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", startTime=" + startTime + ", endTime=" + endTime + ", loginTime=" + loginTime + ", atype=" + atype + ", pid=" + pid + ", isValid=" + isValid + ", currTime=" + currTime + ", parentName=" + parentName + "]";
	}
}