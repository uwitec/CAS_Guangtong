package cn.guangtong.entity.driver;

import cn.guangtong.utils.excel.ExportConfig;

public class DriverRecord {
	@ExportConfig(value = "驾驶员编号", width = 150)
	private String id;					//驾驶员编号
	@ExportConfig(value = "驾驶员名称", width = 100)
	private String dName;				//驾驶员名称
	@ExportConfig(value = "从业资格证号", width = 150)
	private String professionNum;		//从业资格证号
	@ExportConfig(value = "证发机构名称", width = 150)
	private String institutionName;		//证发机构名称
	@ExportConfig(value = "证件有效期", width = 200)
	private String professionValidDate;	//从业资格证有效期
	@ExportConfig(value = "车牌号", width = 100)
	private String defaultVehicleNum;	//车牌号
	@ExportConfig(value = "车辆颜色", width = 80)
	private String plateColor;			//车辆颜色
	@ExportConfig(value = "上线时间", width = 200)
	private String onlineTime;			//上线时间
	@ExportConfig(value = "下线时间", width = 200)
	private String offlineTime;			//下线时间
	private String count;				//记录数
	
	public String getProfessionValidDate() {
		return professionValidDate;
	}
	public void setProfessionValidDate(String professionValidDate) {
		this.professionValidDate = professionValidDate;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getProfessionNum() {
		return professionNum;
	}
	public void setProfessionNum(String professionNum) {
		this.professionNum = professionNum;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getDefaultVehicleNum() {
		return defaultVehicleNum;
	}
	public void setDefaultVehicleNum(String defaultVehicleNum) {
		this.defaultVehicleNum = defaultVehicleNum;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public String getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	public String getOfflineTime() {
		return offlineTime;
	}
	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}
	
}
