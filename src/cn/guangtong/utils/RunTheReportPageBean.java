package cn.guangtong.utils;

import java.util.List;
import java.util.Map;

/**
 * 车辆运行报表分页查询
 * 
 * @author SunTo
 * 
 */
public class RunTheReportPageBean extends PageBean<Map<String, Object>> {
	// 获取到的simNo
	private List<String> simNos;// 车牌号
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String velocity; // 速度

	public List<String> getSimNos() {
		return simNos;
	}

	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
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

	public String getVelocity() {
		return velocity;
	}

	public void setVelocity(String velocity) {
		this.velocity = velocity;
	}

}
