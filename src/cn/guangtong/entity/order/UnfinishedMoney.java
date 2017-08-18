package cn.guangtong.entity.order;

public class UnfinishedMoney {
    private String ordernum;
    private String driverid;
    private double insurancefee;
    private double floorfee;
    private double handlingfee;
    private double freightfee;
    private double drivermoney;
    private double totalmoney;
    private String paytime;
    private int settle; // 结算状态（0：未支付；1：已支付；2：部分支付,3:司机代收）
    private double settleamount;
    private int payment; //支付方式(1:支付宝;2:微信3:银联)
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
	public double getInsurancefee() {
		return insurancefee;
	}
	public void setInsurancefee(double insurancefee) {
		this.insurancefee = insurancefee;
	}
	public double getFloorfee() {
		return floorfee;
	}
	public void setFloorfee(double floorfee) {
		this.floorfee = floorfee;
	}
	public double getHandlingfee() {
		return handlingfee;
	}
	public void setHandlingfee(double handlingfee) {
		this.handlingfee = handlingfee;
	}
	public double getFreightfee() {
		return freightfee;
	}
	public void setFreightfee(double freightfee) {
		this.freightfee = freightfee;
	}
	public double getDrivermoney() {
		return drivermoney;
	}
	public void setDrivermoney(double drivermoney) {
		this.drivermoney = drivermoney;
	}
	public double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public int getSettle() {
		return settle;
	}
	public void setSettle(int settle) {
		this.settle = settle;
	}
	public double getSettleamount() {
		return settleamount;
	}
	public void setSettleamount(double settleamount) {
		this.settleamount = settleamount;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public UnfinishedMoney(String ordernum, String driverid,
			double insurancefee, double floorfee, double handlingfee,
			double freightfee, double drivermoney, double totalmoney,
			String paytime, int settle, double settleamount, int payment) {
		super();
		this.ordernum = ordernum;
		this.driverid = driverid;
		this.insurancefee = insurancefee;
		this.floorfee = floorfee;
		this.handlingfee = handlingfee;
		this.freightfee = freightfee;
		this.drivermoney = drivermoney;
		this.totalmoney = totalmoney;
		this.paytime = paytime;
		this.settle = settle;
		this.settleamount = settleamount;
		this.payment = payment;
	}
	
	public UnfinishedMoney(String ordernum, double insurancefee,
			double floorfee, double handlingfee, double freightfee,
			double drivermoney, double totalmoney, double settleamount) {
		super();
		this.ordernum = ordernum;
		this.insurancefee = insurancefee;
		this.floorfee = floorfee;
		this.handlingfee = handlingfee;
		this.freightfee = freightfee;
		this.drivermoney = drivermoney;
		this.totalmoney = totalmoney;
		this.settleamount = settleamount;
	}
	public UnfinishedMoney() {
		super();
	}
}