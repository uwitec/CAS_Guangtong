package cn.guangtong.model;

/**
 * 权限控制查询所有企业以及可管理企业
 * 
 * @author SunTo
 * 
 */
public class AdminCooperation {

	private String id;

	private String cname;
	// 管理员Id
	private Integer adminId;
	// 是否拥有权限
	private int isPermissions = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public int getIsPermissions() {
		return isPermissions;
	}

	public void setIsPermissions(int isPermissions) {
		this.isPermissions = isPermissions;
	}

}
