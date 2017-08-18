package cn.guangtong.entity.vehicle;

import java.util.HashMap;
import java.util.Map;

public class TotalOfVehicle {
	private String orderId;	//订单编号
	private String vNum;	//车牌号
	private String dName;	//驾驶员
	private String cName;	//司机所属企业
	private String cuName;	//客户名称
	private String totalMoney;//订单金额
	private String driverMoney;//司机所得金额
	private String money;	//扣款金额
	private String orderStatus;//订单状态
	private String endTime;	//订单完成时间
	private String tName;	//所属车队
	
	private String month;	//月份
	private String mon; 	//当月总金额
	private String times;	//时间段查询开始时间
	private String timee;	//时间段查询结束时间
	
	private Map<String, String> map = new HashMap<String, String>();
	
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getTimee() {
		return timee;
	}
	public void setTimee(String timee) {
		this.timee = timee;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getvNum() {
		return vNum;
	}
	public void setvNum(String vNum) {
		this.vNum = vNum;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getDriverMoney() {
		return driverMoney;
	}
	public void setDriverMoney(String driverMoney) {
		this.driverMoney = driverMoney;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	
}
