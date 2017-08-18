package cn.guangtong.entity.order;

/**
 * 充值订单实体类
 * @author sutong
 *
 */
public class RechargeOrder {
    private Long id;
    private String ordernum;
    private String customerid;
    private double totalmoney;
    private int payment;//支付方式（1:支付宝；2：微信;3：银联）
    private int createtime; //创建时间
    private int paytime; //支付时间
    private int ispay; //是否支付（0：未成功；1:支付成功）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public int getPaytime() {
		return paytime;
	}
	public void setPaytime(int paytime) {
		this.paytime = paytime;
	}
	public int getIspay() {
		return ispay;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	public RechargeOrder(Long id, String ordernum, String customerid,
			double totalmoney, int payment, int createtime, int paytime,
			int ispay) {
		super();
		this.id = id;
		this.ordernum = ordernum;
		this.customerid = customerid;
		this.totalmoney = totalmoney;
		this.payment = payment;
		this.createtime = createtime;
		this.paytime = paytime;
		this.ispay = ispay;
	}
	public RechargeOrder() {
		super();
	}
}