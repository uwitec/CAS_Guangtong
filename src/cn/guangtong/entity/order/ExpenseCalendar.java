package cn.guangtong.entity.order;

/**
 * 交易流水实体类
 * @author sutong
 *
 */
public class ExpenseCalendar {
    private Long id;
    private int origin; //来源（1：物流单;2：充值单）
    private String customerid;
    private double money;
    private String ordernum;
    private int payment; //支付方式（1:支付宝;2:微信;3:银联）
    private int paytime;
    private String tradeno;
    private int rdtype; //收支(0:支出;1:收入)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getPaytime() {
		return paytime;
	}
	public void setPaytime(int paytime) {
		this.paytime = paytime;
	}
	public String getTradeno() {
		return tradeno;
	}
	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}
	public int getRdtype() {
		return rdtype;
	}
	public void setRdtype(int rdtype) {
		this.rdtype = rdtype;
	}
	public ExpenseCalendar(Long id, int origin, String customerid,
			double money, String ordernum, int payment, int paytime,
			String tradeno, int rdtype) {
		super();
		this.id = id;
		this.origin = origin;
		this.customerid = customerid;
		this.money = money;
		this.ordernum = ordernum;
		this.payment = payment;
		this.paytime = paytime;
		this.tradeno = tradeno;
		this.rdtype = rdtype;
	}
	public ExpenseCalendar() {
		super();
	}
}