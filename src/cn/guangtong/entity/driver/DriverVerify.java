package cn.guangtong.entity.driver;
/**
 * 司机验证码实体类
 * @author sutong
 *
 */
public class DriverVerify {
    private String tel;
    private String verifynum;
    private int gentime; //时间戳
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
	public DriverVerify(String tel, String verifynum, int gentime) {
		super();
		this.tel = tel;
		this.verifynum = verifynum;
		this.gentime = gentime;
	}
	public DriverVerify() {
		super();
	}
}