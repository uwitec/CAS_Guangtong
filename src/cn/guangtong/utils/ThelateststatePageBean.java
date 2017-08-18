package cn.guangtong.utils;

import java.util.List;

import cn.guangtong.excel.LatestStatus;

/**
 * 查询车辆状态，分页
 * 
 * @author SunTo
 * 
 */
public class ThelateststatePageBean extends PageBean<LatestStatus> {
	// 获取到的simNo
	private List<String> simNos;// 车牌号
	private String adminId;//用户登录ID
	public List<String> getSimNos() {
		return simNos;
	}

	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
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
