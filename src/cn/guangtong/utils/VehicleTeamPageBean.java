package cn.guangtong.utils;

import cn.guangtong.entity.vehicle.VehicleTeam;

/**
 * 车队管理分页Bean
 * 
 * @author SunTo
 * 
 */
public class VehicleTeamPageBean extends PageBean<VehicleTeam> {

	private Integer adminId;

	// 车队id
	private String id;
	// 企业名称
	private String cName;
	// 车队名称
	private String tName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
