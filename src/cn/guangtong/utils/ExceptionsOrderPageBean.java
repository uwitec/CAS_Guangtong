package cn.guangtong.utils;

import java.util.Map;

public class ExceptionsOrderPageBean extends PageBean<Map<String, Object>>{
	
	private Integer exceptionReasonId; // 异常编号
    private String title; // 异常原因 
    private String takersTime; // 提交时间
    private String driverId; // 驾驶员
    private String ordernum; // 订单编号
    private String orderFrom; // 订单来源
    
    private String num;//车牌号
    private String dName;//驾驶员名称
    private Integer orderStatus;//订单状态
    
    
    
	public Integer getExceptionReasonId() {
		return exceptionReasonId;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void setExceptionReasonId(Integer exceptionReasonId) {
		this.exceptionReasonId = exceptionReasonId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTakersTime() {
		return takersTime;
	}
	public void setTakersTime(String takersTime) {
		this.takersTime = takersTime;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getOrderFrom() {
		return orderFrom;
	}
	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}
    
}
