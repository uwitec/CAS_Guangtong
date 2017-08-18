package cn.guangtong.entity.order;

/**
 * 退款记录实体类
 * @author sutong
 *
 */
public class Refund {
    private Long id;
    private String refundnum;
    private String orderid;
    private double refundmoney;//退款金额
    private int refundtime;// 退款时间
    private int payment; //退款方式（1支付宝2微信3银联）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRefundnum() {
		return refundnum;
	}
	public void setRefundnum(String refundnum) {
		this.refundnum = refundnum;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public double getRefundmoney() {
		return refundmoney;
	}
	public void setRefundmoney(double refundmoney) {
		this.refundmoney = refundmoney;
	}
	public int getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(int refundtime) {
		this.refundtime = refundtime;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public Refund() {
		super();
	}
}