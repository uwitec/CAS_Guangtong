package cn.guangtong.entity.cooperation;

/**
 * 加盟企业信息实体类
 * @author sutong
 *
 */
public class CooperationInfo {
    private String id;
    private String cname;
    private String contact;
    private String tel;
    private String email;
    private String addr;
    private int ctype; //1:企业，2：个人
    private String cnum;
    private String cimg;
    private int available; //冻结状态。0：冻结，1：可用（企业冻结，则下属车辆和司机都不可用）
    private int isdel; //是否已删除(0:正常,1:已删除)
    private String createtime;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getCimg() {
		return cimg;
	}
	public void setCimg(String cimg) {
		this.cimg = cimg;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public CooperationInfo(String id, String cname, String contact, String tel,
			String email, String addr, int ctype, String cnum, String cimg,
			int available, int isdel, String createtime) {
		super();
		this.id = id;
		this.cname = cname;
		this.contact = contact;
		this.tel = tel;
		this.email = email;
		this.addr = addr;
		this.ctype = ctype;
		this.cnum = cnum;
		this.cimg = cimg;
		this.available = available;
		this.isdel = isdel;
		this.createtime = createtime;
	}
	public CooperationInfo() {
		super();
	}
}