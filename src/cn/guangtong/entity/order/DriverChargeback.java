package cn.guangtong.entity.order;

/**
 * 司机扣款实体类
 * @author sutong
 *
 */
public class DriverChargeback {
    private Long orderid;
    private String ordernum;
    private String driverid; //外键，对应实体类为Driver
    private double money;
    private String createtime;
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public DriverChargeback(Long orderid, String ordernum, String driverid,
			double money, String createtime) {
		super();
		this.orderid = orderid;
		this.ordernum = ordernum;
		this.driverid = driverid;
		this.money = money;
		this.createtime = createtime;
	}
	public DriverChargeback() {
		super();
	}
}