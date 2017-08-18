package cn.guangtong.entity.order;

/**
 * 钱包流水实体类
 * @author sutong
 *
 */
public class WalletCalendar {
    private Long id;
    private Long expenseid;
    private int rdtype; //收支(0:支出;1:收入)
    private String customerid;// 客户ID
    private double money;
    private int paytime;
    private int payment;//支付方式（1:支付宝;2:微信;3:银联）
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getExpenseid() {
		return expenseid;
	}
	public void setExpenseid(Long expenseid) {
		this.expenseid = expenseid;
	}
	public int getRdtype() {
		return rdtype;
	}
	public void setRdtype(int rdtype) {
		this.rdtype = rdtype;
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
	public int getPaytime() {
		return paytime;
	}
	public void setPaytime(int paytime) {
		this.paytime = paytime;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public WalletCalendar(Long id, Long expenseid, int rdtype,
			String customerid, double money, int paytime, int payment) {
		super();
		this.id = id;
		this.expenseid = expenseid;
		this.rdtype = rdtype;
		this.customerid = customerid;
		this.money = money;
		this.paytime = paytime;
		this.payment = payment;
	}
	public WalletCalendar() {
		super();
	}
}