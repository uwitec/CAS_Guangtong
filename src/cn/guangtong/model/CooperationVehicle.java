package cn.guangtong.model;

import java.util.List;
import java.util.Map;

/**
 * 企业所有车辆Model
 * 
 * @author SunTo
 * 
 */
public class CooperationVehicle {
	
	// 企业ID
	private String cId;
	// 企业名称
	private String cName;
	// 车辆集合
	private List<Map<String, Object>> vehicles;
	
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public List<Map<String, Object>> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Map<String, Object>> vehicles) {
		this.vehicles = vehicles;
	}
	@Override
	public String toString() {
		return "CooperationVehicle [cId=" + cId + ", cName=" + cName
				+ ", vehicles=" + vehicles + "]";
	}
}
