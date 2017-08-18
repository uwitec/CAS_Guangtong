package cn.guangtong.entity.beidou;

import java.util.Date;

/**
 * 区域与车辆绑定关系
 * 
 * @ClassName:EnclosureBinding
 */
public class EnclosureBinding {
	// 绑定id
	private Integer bindid;
	// 创建时间
	private Date createdate=new Date();
	// 是否消除
	private int deleted = 0;
	// 物主
	private String owner;
	// 备注
	private String remark;
	// 承租者id
	private Integer tenantid=0;
	// 部门id
	private Integer depid=0;
	// 区域id
	private Integer enclosureid;
	// 车辆id
	private Integer vehicleid;

	public Integer getBindid() {
		return bindid;
	}

	public void setBindid(Integer bindid) {
		this.bindid = bindid;
	}

	/**
	 * @return the createdate
	 */
	public Date getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return the deleted
	 */
	public int getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner == null ? null : owner.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getTenantid() {
		return tenantid;
	}

	public void setTenantid(Integer tenantid) {
		this.tenantid = tenantid;
	}

	public Integer getDepid() {
		return depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
	}

	public Integer getEnclosureid() {
		return enclosureid;
	}

	public void setEnclosureid(Integer enclosureid) {
		this.enclosureid = enclosureid;
	}

	public Integer getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(Integer vehicleid) {
		this.vehicleid = vehicleid;
	}
}