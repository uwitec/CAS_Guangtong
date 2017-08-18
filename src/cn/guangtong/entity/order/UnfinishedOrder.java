package cn.guangtong.entity.order;

/**
 * 进行中订单实体类
 * @author sutong
 *
 */
public class UnfinishedOrder {
	
    private Long id;
    private String ordernum;
    private int orderstatus;//订单状态（0:待分配；3：待取货；4：待送达；1：完成；2:改派;5:异常;6：已关闭7:取消订单）
    private String driverid;
    private String dName;
    private String num;
    private String vehicleid;
    private String customerid;
    private String deliver;
    private String delivertel;
    private String scompany;
    private String sprovance;
    private String deliveraddr;
    private String receipt;
    private String receipttel;
    private String rcompany;
    private String rprovance;
    private String receiptaddr;
    private Long rounteid;
    private double volume;
    private double weight;
    private double length;
    private double wide;
    private double tall;
    private int goodsnum;
    private String description;
    private int paymethod; //付费方式（1：现金支付；2：在线支付；3：协议付款）
    private String createtime;
    private String takerstime;
    private String calltime;
    private String pickuptime;
    private String updatetime;
    private String leadtime;
    private int exceptionreasonid;
    private String exceptionreason;
    private String addrequir;
    private String callimg;
    private String leadimg;
    private String exceptionimg;
    private String reassigntime;
    private int paytimetype; //付款时间类型(1发货时；2收货时)
    private String deliverlocation;
    private String receiptlocation;
    private int orderfrom;//订单来源(1:APP，2:CMS)
    private int ischarter;//是否包车(0:否,1:是)
    
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
	public int getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getDeliver() {
		return deliver;
	}
	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}
	public String getDelivertel() {
		return delivertel;
	}
	public void setDelivertel(String delivertel) {
		this.delivertel = delivertel;
	}
	public String getScompany() {
		return scompany;
	}
	public void setScompany(String scompany) {
		this.scompany = scompany;
	}
	public String getSprovance() {
		return sprovance;
	}
	public void setSprovance(String sprovance) {
		this.sprovance = sprovance;
	}
	public String getDeliveraddr() {
		return deliveraddr;
	}
	public void setDeliveraddr(String deliveraddr) {
		this.deliveraddr = deliveraddr;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getReceipttel() {
		return receipttel;
	}
	public void setReceipttel(String receipttel) {
		this.receipttel = receipttel;
	}
	public String getRcompany() {
		return rcompany;
	}
	public void setRcompany(String rcompany) {
		this.rcompany = rcompany;
	}
	public String getRprovance() {
		return rprovance;
	}
	public void setRprovance(String rprovance) {
		this.rprovance = rprovance;
	}
	public String getReceiptaddr() {
		return receiptaddr;
	}
	public void setReceiptaddr(String receiptaddr) {
		this.receiptaddr = receiptaddr;
	}
	public Long getRounteid() {
		return rounteid;
	}
	public void setRounteid(Long rounteid) {
		this.rounteid = rounteid;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWide() {
		return wide;
	}
	public void setWide(double wide) {
		this.wide = wide;
	}
	public double getTall() {
		return tall;
	}
	public void setTall(double tall) {
		this.tall = tall;
	}
	public int getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(int goodsnum) {
		this.goodsnum = goodsnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(int paymethod) {
		this.paymethod = paymethod;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getTakerstime() {
		return takerstime;
	}
	public void setTakerstime(String takerstime) {
		this.takerstime = takerstime;
	}
	public String getCalltime() {
		return calltime;
	}
	public void setCalltime(String calltime) {
		this.calltime = calltime;
	}
	public String getPickuptime() {
		return pickuptime;
	}
	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getLeadtime() {
		return leadtime;
	}
	public void setLeadtime(String leadtime) {
		this.leadtime = leadtime;
	}
	public int getExceptionreasonid() {
		return exceptionreasonid;
	}
	public void setExceptionreasonid(int exceptionreasonid) {
		this.exceptionreasonid = exceptionreasonid;
	}
	public String getExceptionreason() {
		return exceptionreason;
	}
	public void setExceptionreason(String exceptionreason) {
		this.exceptionreason = exceptionreason;
	}
	public String getAddrequir() {
		return addrequir;
	}
	public void setAddrequir(String addrequir) {
		this.addrequir = addrequir;
	}
	public String getCallimg() {
		return callimg;
	}
	public void setCallimg(String callimg) {
		this.callimg = callimg;
	}
	public String getLeadimg() {
		return leadimg;
	}
	public void setLeadimg(String leadimg) {
		this.leadimg = leadimg;
	}
	public String getExceptionimg() {
		return exceptionimg;
	}
	public void setExceptionimg(String exceptionimg) {
		this.exceptionimg = exceptionimg;
	}
	public String getReassigntime() {
		return reassigntime;
	}
	public void setReassigntime(String reassigntime) {
		this.reassigntime = reassigntime;
	}
	public int getPaytimetype() {
		return paytimetype;
	}
	public void setPaytimetype(int paytimetype) {
		this.paytimetype = paytimetype;
	}
	public String getDeliverlocation() {
		return deliverlocation;
	}
	public void setDeliverlocation(String deliverlocation) {
		this.deliverlocation = deliverlocation;
	}
	public String getReceiptlocation() {
		return receiptlocation;
	}
	public void setReceiptlocation(String receiptlocation) {
		this.receiptlocation = receiptlocation;
	}
	public int getOrderfrom() {
		return orderfrom;
	}
	public void setOrderfrom(int orderfrom) {
		this.orderfrom = orderfrom;
	}
	public int getIscharter() {
		return ischarter;
	}
	public void setIscharter(int ischarter) {
		this.ischarter = ischarter;
	}
	
	/**
	 * @return the dName
	 */
	public String getdName() {
		return dName;
	}
	/**
	 * @param dName the dName to set
	 */
	public void setdName(String dName) {
		this.dName = dName;
	}
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}
	public UnfinishedOrder() {
		super();
	}
	public UnfinishedOrder(String ordernum, String customerid, String deliver,
			String delivertel, String scompany,
			String deliveraddr, String receipt, String receipttel,
			String rcompany, String receiptaddr,
			double volume, double weight,double length,double wide,double tall, String description, int paymethod,
			String createtime, String calltime, String updatetime,
			String deliverlocation, String receiptlocation, int orderfrom) {
		super();
		this.ordernum = ordernum;
		this.customerid = customerid;
		this.deliver = deliver;
		this.delivertel = delivertel;
		this.scompany = scompany;
		this.deliveraddr = deliveraddr;
		this.receipt = receipt;
		this.receipttel = receipttel;
		this.rcompany = rcompany;
		this.receiptaddr = receiptaddr;
		this.volume = volume;
		this.weight = weight;
		this.length = length;
		this.wide = wide;
		this.tall = tall;
		this.description = description;
		this.paymethod = paymethod;
		this.createtime = createtime;
		this.calltime = calltime;
		this.updatetime = updatetime;
		this.deliverlocation = deliverlocation;
		this.receiptlocation = receiptlocation;
		this.orderfrom = orderfrom;
	}
	
}