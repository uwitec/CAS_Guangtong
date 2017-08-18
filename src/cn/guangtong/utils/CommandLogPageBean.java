package cn.guangtong.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.guangtong.entity.beidou.CommandLog;

@Component
public class CommandLogPageBean extends PageBean<CommandLog>{
	
	private String startTime; //要搜索的开始时间
	private String endTime; //要搜索的结束时间
	private String adminId; //管理员ID
	
	private List<String> simNos;//车牌号
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
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
	public List<String> getSimNos() {
		return simNos;
	}
	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
	}
	public CommandLogPageBean(String startTime, String endTime,
			List<String> simNos) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.simNos = simNos;
	}
	public CommandLogPageBean() {
		super();
	}
	@Override
	public String toString() {
		return "CommandLogPageBean [startTime=" + startTime + ", endTime="
				+ endTime + ", simNos=" + simNos + "]";
	}
}
