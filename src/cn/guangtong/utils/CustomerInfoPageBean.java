package cn.guangtong.utils;

import cn.guangtong.entity.customer.CustomerInfo;

public class CustomerInfoPageBean extends PageBean<CustomerInfo> {

	private String username; // 登录名
	private String loginTime; // 登录时间
	private Integer isValid; // 是否有效
	private String startTime; // 开始时间
	private String endTime; // 结束时间
	private String currTime; // 服务器当前时间
	private Integer atype; // 类型
	private Integer pid; // 所属管理
	private String id;// 客户编号
	private String cname;// 客户名称
	private String ctypename;// 客户类型
	private String contactname;// 联系人
	private String tel;// 联系电话
	private String addr;// 地址

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
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * @return the ctypename
	 */
	public String getCtypename() {
		return ctypename;
	}

	/**
	 * @param ctypename the ctypename to set
	 */
	public void setCtypename(String ctypename) {
		this.ctypename = ctypename;
	}

	/**
	 * @return the contactname
	 */
	public String getContactname() {
		return contactname;
	}

	/**
	 * @param contactname the contactname to set
	 */
	public void setContactname(String contactname) {
		this.contactname = contactname;
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
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
