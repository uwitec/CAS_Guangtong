package cn.guangtong.entity.customer;

public class CommonDeliver {
	
    private String company;
    private String customerid;
    private String dname;
    private String provance;
    private String addr;
    private String location;
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
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getProvance() {
		return provance;
	}
	public void setProvance(String provance) {
		this.provance = provance;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public CommonDeliver(String company, String customerid, String dname,
			String provance, String addr, String location, String tel, int isdel) {
		super();
		this.company = company;
		this.customerid = customerid;
		this.dname = dname;
		this.provance = provance;
		this.addr = addr;
		this.location = location;
		this.tel = tel;
		this.isdel = isdel;
	}
	
	public CommonDeliver(String company, String customerid, String dname,
			String addr, String location, String tel) {
		super();
		this.company = company;
		this.customerid = customerid;
		this.dname = dname;
		this.addr = addr;
		this.location = location;
		this.tel = tel;
	}
	public CommonDeliver() {
		super();
	}
}