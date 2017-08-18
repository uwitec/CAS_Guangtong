package cn.guangtong.controller.vehicle;

import java.util.List;

import cn.guangtong.utils.PageBean;

@SuppressWarnings("rawtypes")
public class VehiclePageBean extends PageBean {
	private String id;// 编号
	private String num;// 车牌号
	private String nickname;// 别名
	private String cName;// 企业名
	private String tName;// 车队名
	private String desc;// 类型

	private String adminId;
	private String startTime;//
	private String endTime;//
	private String simNo;// 北斗设备号
	private String plateNo;// 车牌号
	private List<String> simNos;// 车牌号数组
	private String dName;// 模糊搜索司机姓名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

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

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public List<String> getSimNos() {
		return simNos;
	}

	public void setSimNos(List<String> simNos) {
		this.simNos = simNos;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

}
