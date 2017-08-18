package cn.guangtong.entity.vehicle;

public class VehicleInfo {
	private String nickname;//别名
	private Integer id;// 车辆编号ID
	private String num;// 车牌号
	private String modelId;// 车型id
	private String engineNum;// 发动机号
	private String VIN;// 车架号
	private String vehicleImg;// 车辆照片
	private String VMAvalidDate;// 车辆管理费有效期
	private String brandModel;// 厂牌型号
	private String vehicleLicenseImg;// 行驶证照片
	private String operationLicenseImg;// 营运证照片
	private String operationLicenseNum;// 营运证号
	private String operationLicenseDate;// 营运证有效期
	private String operationYearCheck;// 营运证定级年检
	private String rankCheckDate;// 车辆定级年检
	private String secondMaintainDate;// 车辆二级维护月份
	private String TCICompany;// 交强险承保公司
	private String TCINum;// 车辆强制保险单号
	private String TCIValidDate;// 交强险有效期
	private String TCITel;// 承保公司服务电话
	private String VCICompany;// 商业险承保公司名称
	private String VCIValidDate;// 商业保险有效期
	private String VCINum;// 商业险保险单号
	private Byte VCIType;// 商业投保类型???
	private String VCITel;// 商业险联系电话
	private int operationType;// 营运类型（企业?个体）:企业：1，个人：2
	private String cooperationId;// 所属企业/个体编号
	private String reviewTime;// 审核时间
	private Byte reviewStatus;// 审核状态,0:待审核，1：通过，-1：拒绝
	private String reviewNote;// 审核信息备注
	private String createTime;// 创建时间
	private int available;// 车辆状态：0：不可用（冻结），1：可用
	private int cooperationAvailable;// 企业可用状态，与企业表里保持一致
	private String vehicleLicenseImg2;// 行驶证副本
	private int isdel;// 是否已删除(0:正常,1:已删除)
	private int receiveAutoSend;// 是否接收自动派单(0:不接收;1:接收)
	private String simNo;// 北斗设备号
	private String defaultDriverName;// 默认驾驶员姓名
	private String lastEditor;// 最后修改人员
	private String updateTime;// 修改时间
	private String teamId;//车队id
	private String VMColor;// 车辆颜色
	private String specialId;// 特别运输类型Id
	private String subordinateBusinessId;//所属业务id
	private String fuelTank;// 油箱类型
    private String desc;//车辆类型
    
    private String cName;//企业名称
    private String tName;//车队名称
    
    private int isRead;//是否列入监控范围
	@Override
	public String toString() {
		return "VehicleInfo [id=" + id + ", num=" + num + ", modelId=" + modelId + ", engineNum=" + engineNum + ", VIN=" + VIN + ", vehicleImg=" + vehicleImg + ", VMAvalidDate=" + VMAvalidDate + ", brandModel=" + brandModel + ", vehicleLicenseImg=" + vehicleLicenseImg + ", operationLicenseImg=" + operationLicenseImg + ", operationLicenseNum=" + operationLicenseNum + ", operationLicenseDate=" + operationLicenseDate + ", operationYearCheck=" + operationYearCheck + ", rankCheckDate=" + rankCheckDate + ", secondMaintainDate=" + secondMaintainDate + ", TCICompany=" + TCICompany + ", TCINum=" + TCINum + ", TCIValidDate=" + TCIValidDate + ", TCITel=" + TCITel + ", VCICompany=" + VCICompany + ", VCIValidDate=" + VCIValidDate + ", VCINum=" + VCINum + ", VCIType=" + VCIType + ", VCITel=" + VCITel + ", operationType=" + operationType + ", cooperationId=" + cooperationId + ", reviewTime=" + reviewTime + ", reviewStatus=" + reviewStatus + ", reviewNote=" + reviewNote + ", createTime=" + createTime + ", available=" + available + ", cooperationAvailable=" + cooperationAvailable + ", vehicleLicenseImg2=" + vehicleLicenseImg2 + ", isdel=" + isdel + ", receiveAutoSend=" + receiveAutoSend + ", simNo=" + simNo + ", defaultDriverName=" + defaultDriverName + ", lastEditor=" + lastEditor + ", updateTime=" + updateTime + ", teamId=" + teamId + ", VMColor=" + VMColor + ", specialId=" + specialId + ", subordinateBusinessId=" + subordinateBusinessId + ", fuelTank=" + fuelTank + "]";
	}
	
	

	public int getIsRead() {
		return isRead;
	}



	public void setIsRead(int isRead) {
		this.isRead = isRead;
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


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public String getVehicleImg() {
		return vehicleImg;
	}

	public void setVehicleImg(String vehicleImg) {
		this.vehicleImg = vehicleImg;
	}

	public String getVMAvalidDate() {
		return VMAvalidDate;
	}

	public void setVMAvalidDate(String vMAvalidDate) {
		VMAvalidDate = vMAvalidDate;
	}

	public String getBrandModel() {
		return brandModel;
	}

	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}

	public String getVehicleLicenseImg() {
		return vehicleLicenseImg;
	}

	public void setVehicleLicenseImg(String vehicleLicenseImg) {
		this.vehicleLicenseImg = vehicleLicenseImg;
	}

	public String getOperationLicenseImg() {
		return operationLicenseImg;
	}

	public void setOperationLicenseImg(String operationLicenseImg) {
		this.operationLicenseImg = operationLicenseImg;
	}

	public String getOperationLicenseNum() {
		return operationLicenseNum;
	}

	public void setOperationLicenseNum(String operationLicenseNum) {
		this.operationLicenseNum = operationLicenseNum;
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

	public String getRankCheckDate() {
		return rankCheckDate;
	}

	public void setRankCheckDate(String rankCheckDate) {
		this.rankCheckDate = rankCheckDate;
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

	public String getVCIValidDate() {
		return VCIValidDate;
	}

	public void setVCIValidDate(String vCIValidDate) {
		VCIValidDate = vCIValidDate;
	}

	public String getVCINum() {
		return VCINum;
	}

	public void setVCINum(String vCINum) {
		VCINum = vCINum;
	}

	public Byte getVCIType() {
		return VCIType;
	}

	public void setVCIType(Byte vCIType) {
		VCIType = vCIType;
	}

	public String getVCITel() {
		return VCITel;
	}

	public void setVCITel(String vCITel) {
		VCITel = vCITel;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public String getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Byte getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Byte reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReviewNote() {
		return reviewNote;
	}

	public void setReviewNote(String reviewNote) {
		this.reviewNote = reviewNote;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getCooperationAvailable() {
		return cooperationAvailable;
	}

	public void setCooperationAvailable(int cooperationAvailable) {
		this.cooperationAvailable = cooperationAvailable;
	}

	public String getVehicleLicenseImg2() {
		return vehicleLicenseImg2;
	}

	public void setVehicleLicenseImg2(String vehicleLicenseImg2) {
		this.vehicleLicenseImg2 = vehicleLicenseImg2;
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public int getReceiveAutoSend() {
		return receiveAutoSend;
	}

	public void setReceiveAutoSend(int receiveAutoSend) {
		this.receiveAutoSend = receiveAutoSend;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getDefaultDriverName() {
		return defaultDriverName;
	}

	public void setDefaultDriverName(String defaultDriverName) {
		this.defaultDriverName = defaultDriverName;
	}

	public String getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(String lastEditor) {
		this.lastEditor = lastEditor;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getVMColor() {
		return VMColor;
	}

	public void setVMColor(String vMColor) {
		VMColor = vMColor;
	}

	public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public String getSubordinateBusinessId() {
		return subordinateBusinessId;
	}

	public void setSubordinateBusinessId(String subordinateBusinessId) {
		this.subordinateBusinessId = subordinateBusinessId;
	}

	public String getFuelTank() {
		return fuelTank;
	}

	public void setFuelTank(String fuelTank) {
		this.fuelTank = fuelTank;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}