package cn.guangtong.entity.order;

/**
 * 已完成订单金额实体类
 * @author sutong
 *
 */
public class DoneMoney {
    private String ordernum;
    private String driverid;
    private double insurancefee; //报价费
    private double floorfee; //上楼费
    private double handlingfee; //装卸费
    private double freightfee; //运输费
    private double drivermoney; //司机金额
    private double totalmoney; //订单金额
    private int settle; //结算状态（0：未支付；1：已支付；2：部分支付,3:司机代收）
    private double settleamount; //待支付金额
    private int driversettle; //司机结算状态（0：未结算；1：已结算）
    private String paytime; //支付时间
    private Boolean dgrade; // 星级评分(0~5)
    private Boolean hasgrade; //是否已评分(0:未评分,1:客户已评分,2:超时自动评分)
    private String assessment; //评价详情
    private String assesstime; //评分时间(客户评分时间 或者 系统自动评分时间)
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
	public int getDriversettle() {
		return driversettle;
	}
	public void setDriversettle(int driversettle) {
		this.driversettle = driversettle;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public Boolean getDgrade() {
		return dgrade;
	}
	public void setDgrade(Boolean dgrade) {
		this.dgrade = dgrade;
	}
	public Boolean getHasgrade() {
		return hasgrade;
	}
	public void setHasgrade(Boolean hasgrade) {
		this.hasgrade = hasgrade;
	}
	public String getAssessment() {
		return assessment;
	}
	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	public String getAssesstime() {
		return assesstime;
	}
	public void setAssesstime(String assesstime) {
		this.assesstime = assesstime;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public DoneMoney(String ordernum, String driverid, double insurancefee,
			double floorfee, double handlingfee, double freightfee,
			double drivermoney, double totalmoney, int settle,
			double settleamount, int driversettle, String paytime,
			Boolean dgrade, Boolean hasgrade, String assessment,
			String assesstime, int payment) {
		super();
		this.ordernum = ordernum;
		this.driverid = driverid;
		this.insurancefee = insurancefee;
		this.floorfee = floorfee;
		this.handlingfee = handlingfee;
		this.freightfee = freightfee;
		this.drivermoney = drivermoney;
		this.totalmoney = totalmoney;
		this.settle = settle;
		this.settleamount = settleamount;
		this.driversettle = driversettle;
		this.paytime = paytime;
		this.dgrade = dgrade;
		this.hasgrade = hasgrade;
		this.assessment = assessment;
		this.assesstime = assesstime;
		this.payment = payment;
	}
	public DoneMoney() {
		super();
	}
}