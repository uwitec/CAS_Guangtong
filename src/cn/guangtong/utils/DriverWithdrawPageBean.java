package cn.guangtong.utils;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DriverWithdrawPageBean extends PageBean{

	@DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date begintime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date endtime;
	private int ispay;//0,未处理，1提现成功，2拒绝
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getIspay() {
		return ispay;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	
	
}
