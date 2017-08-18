package cn.guangtong.entity.customer;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class CustomerInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成方式
    private String id;
	
    private String cname;
    private String tel;
    private String contactname;
    private String password;
    private String face;
    private String addr;
    private String email;
    private String wechataccount;
    private String qqaccount;
    private int isdel;
    private String androidcid;
    private String ioscid;
    private int gender;
    private String createtime;
    private int ctype;
    private double balance;
    private String ctypename;
    
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWechataccount() {
		return wechataccount;
	}
	public void setWechataccount(String wechataccount) {
		this.wechataccount = wechataccount;
	}
	public String getQqaccount() {
		return qqaccount;
	}
	public void setQqaccount(String qqaccount) {
		this.qqaccount = qqaccount;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getAndroidcid() {
		return androidcid;
	}
	public void setAndroidcid(String androidcid) {
		this.androidcid = androidcid;
	}
	public String getIoscid() {
		return ioscid;
	}
	public void setIoscid(String ioscid) {
		this.ioscid = ioscid;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public CustomerInfo(String id, String cname, String tel,
			String contactname, String password, String face, String addr,
			String email, String wechataccount, String qqaccount, int isdel,
			String androidcid, String ioscid, int gender, String createtime,
			int ctype, double balance) {
		super();
		this.id = id;
		this.cname = cname;
		this.tel = tel;
		this.contactname = contactname;
		this.password = password;
		this.face = face;
		this.addr = addr;
		this.email = email;
		this.wechataccount = wechataccount;
		this.qqaccount = qqaccount;
		this.isdel = isdel;
		this.androidcid = androidcid;
		this.ioscid = ioscid;
		this.gender = gender;
		this.createtime = createtime;
		this.ctype = ctype;
		this.balance = balance;
	}
	public CustomerInfo() {
		super();
	}
	public String getCtypename() {
		return ctypename;
	}
	public void setCtypename(String ctypename) {
		this.ctypename = ctypename;
	}
}