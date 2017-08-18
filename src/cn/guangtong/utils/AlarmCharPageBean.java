package cn.guangtong.utils;

import java.util.List;

import cn.guangtong.model.PlatAndTerAlarmChar;

public class AlarmCharPageBean extends PageBean<PlatAndTerAlarmChar> {
	
	private String startTime;		//开始搜索时间
	private String endTime;			//结束搜索时间
	private String type;			//查询警报类型{终端[terminal_state]or平台[platform_alarm]}
	private List<String> plateNos;	//车牌号数组
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getPlateNos() {
		return plateNos;
	}
	public void setPlateNos(List<String> plateNos) {
		this.plateNos = plateNos;
	}
	
	
}
