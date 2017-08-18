package cn.guangtong.entity.cms;

public class Userinfo {
    private int userid;
    private String createdate;
    private Boolean deleted;
    private String owner;
    private String remark;
    private int tenantid;
    private String loginname;
    private double mapcenterlat;
    private double mapcenterlng;
    private int maplevel;
    private int maptype;
    private String name;
    private String password;
    private int userflag;
    private String userstate;
    
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public double getMapcenterlat() {
		return mapcenterlat;
	}
	public void setMapcenterlat(double mapcenterlat) {
		this.mapcenterlat = mapcenterlat;
	}
	public double getMapcenterlng() {
		return mapcenterlng;
	}
	public void setMapcenterlng(double mapcenterlng) {
		this.mapcenterlng = mapcenterlng;
	}
	public int getMaplevel() {
		return maplevel;
	}
	public void setMaplevel(int maplevel) {
		this.maplevel = maplevel;
	}
	public int getMaptype() {
		return maptype;
	}
	public void setMaptype(int maptype) {
		this.maptype = maptype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserflag() {
		return userflag;
	}
	public void setUserflag(int userflag) {
		this.userflag = userflag;
	}
	public String getUserstate() {
		return userstate;
	}
	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}
	public Userinfo(int userid, String createdate, Boolean deleted,
			String owner, String remark, int tenantid, String loginname,
			double mapcenterlat, double mapcenterlng, int maplevel,
			int maptype, String name, String password, int userflag,
			String userstate) {
		super();
		this.userid = userid;
		this.createdate = createdate;
		this.deleted = deleted;
		this.owner = owner;
		this.remark = remark;
		this.tenantid = tenantid;
		this.loginname = loginname;
		this.mapcenterlat = mapcenterlat;
		this.mapcenterlng = mapcenterlng;
		this.maplevel = maplevel;
		this.maptype = maptype;
		this.name = name;
		this.password = password;
		this.userflag = userflag;
		this.userstate = userstate;
	}
	public Userinfo() {
		super();
	}
}