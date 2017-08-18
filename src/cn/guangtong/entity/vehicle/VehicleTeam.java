package cn.guangtong.entity.vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleTeam {

	private String id;
	// 车队名称
	private String tName;
	// 所属企业
	private String cooperationId;
	// 所属企业名称
	private String cName;
	// 所属车队
	private Map<String, Object> map = new HashMap<String, Object>();

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
