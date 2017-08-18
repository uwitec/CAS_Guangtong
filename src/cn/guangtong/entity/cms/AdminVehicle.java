package cn.guangtong.entity.cms;

/**
 * 账户——车辆关联实体类
 * 
 * @author SunTo
 * 
 */
public class AdminVehicle {
	private Integer id;

	private Integer adminid;

	private String vehicleid;

	private Integer isRead = 0;

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid == null ? null : vehicleid.trim();
	}
}