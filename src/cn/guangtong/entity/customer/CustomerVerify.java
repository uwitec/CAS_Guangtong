package cn.guangtong.entity.customer;

public class CustomerVerify {
    private String tel;
    private String verifynum;
    private int gentime;
    
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getVerifynum() {
		return verifynum;
	}
	public void setVerifynum(String verifynum) {
		this.verifynum = verifynum;
	}
	public int getGentime() {
		return gentime;
	}
	public void setGentime(int gentime) {
		this.gentime = gentime;
	}
	public CustomerVerify(String tel, String verifynum, int gentime) {
		super();
		this.tel = tel;
		this.verifynum = verifynum;
		this.gentime = gentime;
	}
	public CustomerVerify() {
		super();
	}
}