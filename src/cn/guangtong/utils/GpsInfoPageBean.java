package cn.guangtong.utils;

import java.util.List;

import cn.guangtong.entity.beidou.GpsInfo;

public class GpsInfoPageBean extends PageBean<GpsInfo>{
	
	private String startTime;
	private String endTime;
	private String month;
	private String week;
	private String day;
	private String hour;
	private List<String> simNos;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public List<String> getSimNos() {
		return simNos;
	}
	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
	}
	
}
