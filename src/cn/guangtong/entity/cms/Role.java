package cn.guangtong.entity.cms;

public class Role {
    private int roleid;
    private String createdate;
    private Boolean deleted;
    private String owner;
    private String remark;
    private int tenantid;
    private String code;
    private String name;
    private int status;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getTenantid() {
		return tenantid;
	}
	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Role(int roleid, String createdate, Boolean deleted, String owner,
			String remark, int tenantid, String code, String name, int status) {
		super();
		this.roleid = roleid;
		this.createdate = createdate;
		this.deleted = deleted;
		this.owner = owner;
		this.remark = remark;
		this.tenantid = tenantid;
		this.code = code;
		this.name = name;
		this.status = status;
	}
	public Role() {
		super();
	}
}