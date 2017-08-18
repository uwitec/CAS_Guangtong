package cn.guangtong.utils;

import java.util.List;

/**
 * 终端分页查询pageBean
 * 
 * @author SunTo
 * 
 */
public class TerminalPageBean extends PageBean {
	
	// 处理时间
	private String dealTime;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	private String type; //报警类型
	private String tongjiType;//统计类型
	private List<String> simNos;//车牌号
	private String adminId;//用户登录ID
	private Integer termId;
	private String devNo;
	private String termType;
	private String instAlladdress;
	private String makeFactory;
	private String termNo;
	private String updateTime;
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the dealTime
	 */
	public String getDealTime() {
		return dealTime;
	}

	/**
	 * @param dealTime the dealTime to set
	 */
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	/**
	 * @return the simNos
	 */
	public List<String> getSimNos() {
		return simNos;
	}

	/**
	 * @param simNos the simNos to set
	 */
	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
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

	/**
	 * @return the tongjiType
	 */
	public String getTongjiType() {
		return tongjiType;
	}

	/**
	 * @param tongjiType the tongjiType to set
	 */
	public void setTongjiType(String tongjiType) {
		this.tongjiType = tongjiType;
	}

	/**
	 * @return the termId
	 */
	public Integer getTermId() {
		return termId;
	}

	/**
	 * @param termId the termId to set
	 */
	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	/**
	 * @return the devNo
	 */
	public String getDevNo() {
		return devNo;
	}

	/**
	 * @param devNo the devNo to set
	 */
	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	/**
	 * @return the instAlladdress
	 */
	public String getInstAlladdress() {
		return instAlladdress;
	}

	/**
	 * @param instAlladdress the instAlladdress to set
	 */
	public void setInstAlladdress(String instAlladdress) {
		this.instAlladdress = instAlladdress;
	}

	/**
	 * @return the termType
	 */
	public String getTermType() {
		return termType;
	}

	/**
	 * @param termType the termType to set
	 */
	public void setTermType(String termType) {
		this.termType = termType;
	}

	/**
	 * @return the termNo
	 */
	public String getTermNo() {
		return termNo;
	}

	/**
	 * @param termNo the termNo to set
	 */
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	/**
	 * @return the makeFactory
	 */
	public String getMakeFactory() {
		return makeFactory;
	}

	/**
	 * @param makeFactory the makeFactory to set
	 */
	public void setMakeFactory(String makeFactory) {
		this.makeFactory = makeFactory;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
