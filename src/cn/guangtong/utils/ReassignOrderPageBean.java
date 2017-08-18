package cn.guangtong.utils;

import java.util.Map;

public class ReassignOrderPageBean extends PageBean<Map<String, Object>>{
	
    private String ordernum; // 订单编号
    private String title; // 异常信息
    private Double totalMoney; // 更改的金额
    private String vehicleId;  // 车辆
    private String num; //车牌号
    private String deliverAddr; // 取货地址
    private String receiptAddr; // 改派收货地址
    private String reassignTime; // 改派时间
    private String orderFrom; //订单来源
	
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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
	public String getReassignTime() {
		return reassignTime;
	}
	public void setReassignTime(String reassignTime) {
		this.reassignTime = reassignTime;
	}
	public String getOrderFrom() {
		return orderFrom;
	}
	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
    
}
