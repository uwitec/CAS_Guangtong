package cn.guangtong.entity.customer;

public class CommonReceipt {
    private String company;
    private String customerid;
    private String rname;
    private String provance;
    private String location;
    private String addr;
    private String tel;
    private int isdel;
    
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getProvance() {
		return provance;
	}
	public void setProvance(String provance) {
		this.provance = provance;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public CommonReceipt(String company, String customerid, String rname,
			String provance, String location, String addr, String tel, int isdel) {
		super();
		this.company = company;
		this.customerid = customerid;
		this.rname = rname;
		this.provance = provance;
		this.location = location;
		this.addr = addr;
		this.tel = tel;
		this.isdel = isdel;
	}
	
	public CommonReceipt(String company, String customerid, String rname,
			String location, String addr, String tel) {
		super();
		this.company = company;
		this.customerid = customerid;
		this.rname = rname;
		this.location = location;
		this.addr = addr;
		this.tel = tel;
	}
	public CommonReceipt() {
		super();
	}
}