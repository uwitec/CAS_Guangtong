package cn.guangtong.utils;

import java.util.Map;

public class OrderPageBean extends PageBean<Map<String, Object>>{
	
	private String ordernum; // 订单编号
	private String callTime; // 预约取货时间
	private String deliverAddr; // 取货地点
	private String receiptAddr; // 送货地点
	private String vehicleId; //取货车辆
	private String title; //异常信息
	private Integer orderStatus; // 订单状态
	private String orderFrom; // 订单来源
	
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getDeliverAddr() {
		return deliverAddr;
	}
	public void setDeliverAddr(String deliverAddr) {
		this.deliverAddr = deliverAddr;
	}
	public String getReceiptAddr() {
		return receiptAddr;
	}
	public void setReceiptAddr(String receiptAddr) {
		this.receiptAddr = receiptAddr;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderFrom() {
		return orderFrom;
	}
	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}
}
