package cn.guangtong.entity.cms;

public class Department {
    private Integer depid;
    private String createdate;
    private Boolean deleted; // bit类型
    private String owner;
    private String remark;
    private int tenantid;
    private String assoman;
    private String assotel;
    private String businessscope;
    private String memno;
    private String name;
    private int parentid;
    private String region;
    private String roadpermitword;
    private String type;
    private String updatetime;
	public Integer getDepid() {
		return depid;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
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
	public String getAssoman() {
		return assoman;
	}
	public void setAssoman(String assoman) {
		this.assoman = assoman;
	}
	public String getAssotel() {
		return assotel;
	}
	public void setAssotel(String assotel) {
		this.assotel = assotel;
	}
	public String getBusinessscope() {
		return businessscope;
	}
	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRoadpermitword() {
		return roadpermitword;
	}
	public void setRoadpermitword(String roadpermitword) {
		this.roadpermitword = roadpermitword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Department(Integer depid, String createdate, Boolean deleted,
			String owner, String remark, int tenantid, String assoman,
			String assotel, String businessscope, String memno, String name,
			int parentid, String region, String roadpermitword, String type,
			String updatetime) {
		super();
		this.depid = depid;
		this.createdate = createdate;
		this.deleted = deleted;
		this.owner = owner;
		this.remark = remark;
		this.tenantid = tenantid;
		this.assoman = assoman;
		this.assotel = assotel;
		this.businessscope = businessscope;
		this.memno = memno;
		this.name = name;
		this.parentid = parentid;
		this.region = region;
		this.roadpermitword = roadpermitword;
		this.type = type;
		this.updatetime = updatetime;
	}
	public Department() {
		super();
	}
}