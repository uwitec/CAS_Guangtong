package cn.guangtong.entity.customer;

public class CustomerBankCard {
    private Long id;
    private String customerid;
    private String bandname;
    private String bandnum;
    private int createtime;
    private int usetime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getBandname() {
		return bandname;
	}
	public void setBandname(String bandname) {
		this.bandname = bandname;
	}
	public String getBandnum() {
		return bandnum;
	}
	public void setBandnum(String bandnum) {
		this.bandnum = bandnum;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public int getUsetime() {
		return usetime;
	}
	public void setUsetime(int usetime) {
		this.usetime = usetime;
	}
	public CustomerBankCard(Long id, String customerid, String bandname,
			String bandnum, int createtime, int usetime) {
		super();
		this.id = id;
		this.customerid = customerid;
		this.bandname = bandname;
		this.bandnum = bandnum;
		this.createtime = createtime;
		this.usetime = usetime;
	}
	public CustomerBankCard() {
		super();
	}
}