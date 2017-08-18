package cn.guangtong.excel;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * Exscel车辆实体类 赵发志
 */
public class Vehicle {
	// @ExportConfig(value = "车辆编号", width = 150)
	private String id;

	@ExportConfig(value = "编号", width = 150)
	private String nickname;// 别名

	@ExportConfig(value = "车牌号", width = 150)
	private String num;

	@ExportConfig(value = "类型id", width = 150)
	private String modelId;

	@ExportConfig(value = "企业id", width = 150)
	private String cooperationId;
	// @ExportConfig(value = "车架号", width = 150)
	private String VIN;

	// @ExportConfig(value = "发动机号", width = 150)
	private String engineNum;

	@ExportConfig(value = "车辆类型", width = 150)
	private String desc;

	// @ExportConfig(value = "车载设备号", width = 150)
	private String simNo;

	// @ExportConfig(value = "车辆颜色 ", width = 150)
	private String VMColor;

	// @ExportConfig(value = "所属业务", width = 150)
	private String tName;

	//@ExportConfig(value = "默认驾驶员", width = 150)
	private String defaultDriverName;

	// @ExportConfig(value = "车辆管理费有效期", width = 150)
	private String VMAvalidDate;

	// @ExportConfig(value = "行驶证年检", width = 150)
	private String rankCheckDate;

	// @ExportConfig(value = "运营证有效期", width = 150)
	private String operationLicenseDate;

	// @ExportConfig(value = "运营证年检", width = 150)
	private String operationYearCheck;

	// @ExportConfig(value = "车辆二级维护月份", width = 150)
	private String secondMaintainDate;

	// @ExportConfig(value = "交强险承保公司", width = 150)
	private String TCICompany;

	// @ExportConfig(value = "车辆强制保险单号", width = 150)
	private String TCINum;

	// @ExportConfig(value = "交强险有效期", width = 150)
	private String TCIValidDate;

	// @ExportConfig(value = "承保公司服务电话", width = 150)
	private String TCITel;

	// @ExportConfig(value = "商业险承保公司名称", width = 150)
	private String VCICompany;

	// @ExportConfig(value = "商业险保险单号", width = 150)
	private String VCINum;

	// @ExportConfig(value = "商业险联系电话", width = 150)
	private String VCITel;

	// @ExportConfig(value = "运营类型", width = 150)
	private String operationType;

	// @ExportConfig(value = "审核时间", width = 150)
	private String reviewTime;

	// @ExportConfig(value = "审核状态", width = 150)
	private String reviewStatus;

	// @ExportConfig(value = "审核信息备注", width = 150)
	private String reviewNote;

	// @ExportConfig(value = "车辆状态", width = 150)
	private String available;

	// @ExportConfig(value = "接受自动派单", width = 150)
	private String receiveAutoSend;

	// @ExportConfig(value = "企业可用状态", width = 150)
	private String cooperationAvailable;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

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

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getVMColor() {
		return VMColor;
	}

	public void setVMColor(String vMColor) {
		VMColor = vMColor;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getDefaultDriverName() {
		return defaultDriverName;
	}

	public void setDefaultDriverName(String defaultDriverName) {
		this.defaultDriverName = defaultDriverName;
	}

	public String getVMAvalidDate() {
		return VMAvalidDate;
	}

	public void setVMAvalidDate(String vMAvalidDate) {
		VMAvalidDate = vMAvalidDate;
	}

	public String getRankCheckDate() {
		return rankCheckDate;
	}

	public void setRankCheckDate(String rankCheckDate) {
		this.rankCheckDate = rankCheckDate;
	}

	public String getOperationLicenseDate() {
		return operationLicenseDate;
	}

	public void setOperationLicenseDate(String operationLicenseDate) {
		this.operationLicenseDate = operationLicenseDate;
	}

	public String getOperationYearCheck() {
		return operationYearCheck;
	}

	public void setOperationYearCheck(String operationYearCheck) {
		this.operationYearCheck = operationYearCheck;
	}

	public String getSecondMaintainDate() {
		return secondMaintainDate;
	}

	public void setSecondMaintainDate(String secondMaintainDate) {
		this.secondMaintainDate = secondMaintainDate;
	}

	public String getTCICompany() {
		return TCICompany;
	}

	public void setTCICompany(String tCICompany) {
		TCICompany = tCICompany;
	}

	public String getTCINum() {
		return TCINum;
	}

	public void setTCINum(String tCINum) {
		TCINum = tCINum;
	}

	public String getTCIValidDate() {
		return TCIValidDate;
	}

	public void setTCIValidDate(String tCIValidDate) {
		TCIValidDate = tCIValidDate;
	}

	public String getTCITel() {
		return TCITel;
	}

	public void setTCITel(String tCITel) {
		TCITel = tCITel;
	}

	public String getVCICompany() {
		return VCICompany;
	}

	public void setVCICompany(String vCICompany) {
		VCICompany = vCICompany;
	}

	public String getVCINum() {
		return VCINum;
	}

	public void setVCINum(String vCINum) {
		VCINum = vCINum;
	}

	public String getVCITel() {
		return VCITel;
	}

	public void setVCITel(String vCITel) {
		VCITel = vCITel;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReviewNote() {
		return reviewNote;
	}

	public void setReviewNote(String reviewNote) {
		this.reviewNote = reviewNote;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getReceiveAutoSend() {
		return receiveAutoSend;
	}

	public void setReceiveAutoSend(String receiveAutoSend) {
		this.receiveAutoSend = receiveAutoSend;
	}

	public String getCooperationAvailable() {
		return cooperationAvailable;
	}

	public void setCooperationAvailable(String cooperationAvailable) {
		this.cooperationAvailable = cooperationAvailable;
	}

	/**
	 * @return the modelId
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * @param modelId
	 *            the modelId to set
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the cooperationId
	 */
	public String getCooperationId() {
		return cooperationId;
	}

	/**
	 * @param cooperationId
	 *            the cooperationId to set
	 */
	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

}
