package cn.guangtong.utils;

import java.util.List;

import cn.guangtong.entity.total.TerminalAlarm;

// 终端报警信息分页查询pageBean
public class TerminalFormPageBean extends PageBean<TerminalAlarm> {
	private String startTime;//开始时间
	private String endTime;// 结束时间
	private String type; //报警类型
	private String tongjiType;//统计类型
	private List<String> simNos;//车牌号
	
	private String adminId;//当前管理员ID
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
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
	 * @param endTime the endTime to set
	 */
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
	/**
	 * @return the simNos
	 */
	public List<String> getSimNos() {
		return simNos;
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
	 * @param simNos the simNos to set
	 */
	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
	}
	public TerminalFormPageBean(String startTime, String endTime, String type,
			List<String> simNos) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.simNos = simNos;
	}
	public TerminalFormPageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TerminalFormPageBean [startTime=" + startTime + ", endTime=" + endTime + ", type=" + type + ", simNos=" + simNos + "]";
	}
	/**
	 * 
	 */
	

	

}
