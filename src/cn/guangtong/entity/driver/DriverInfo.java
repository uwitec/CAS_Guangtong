package cn.guangtong.entity.driver;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * 司机信息实体类 赵发志
 */
public class DriverInfo {
	@ExportConfig(value = "编号", width = 150)
	private String id;
	@ExportConfig(value = "姓名", width = 80)
	private String dName;
	@ExportConfig(value = "司机手机", width = 120)
	private String tel;
	@ExportConfig(value = "密码", width = 200)
	private String password;
	@ExportConfig(value = "头像", width = 150)
	private String face;
	@ExportConfig(value = "司机类型", width = 80)
	private int driverType; // 司机类型（0：个人；1：企业）
	@ExportConfig(value = "企业ID", width = 80)
	private String cooperationId; // 外键，对应实体类CooperationInfo
	@ExportConfig(value = "身份证号", width = 200)
	private String idCardNum; // 身份证号
	@ExportConfig(value = "身份证正面", width = 150)
	private String idCardFrontImg; // 身份证正面
	@ExportConfig(value = "身份证反面", width = 150)
	private String idCardBackImg; // 身份证反面
	@ExportConfig(value = "身份证有效期", width = 150)
	private String idCardValidDate; // 身份证有效期
	@ExportConfig(value = "现地址", width = 150)
	private String addr; // 现地址
	@ExportConfig(value = "准驾车型", width = 150)
	private String driverClass; // 准驾车型
	@ExportConfig(value = "驾驶证号", width = 150)
	private String DLNum; // 驾驶证号
	@ExportConfig(value = "驾驶证副本", width = 150)
	private String DLImg2;// 驾驶证副本
	@ExportConfig(value = "驾驶证图片", width = 150)
	private String DLImg; // 驾驶证图片
	@ExportConfig(value = "驾驶证有效期", width = 150)
	private String DLValidDate; // 驾驶证有效期
	@ExportConfig(value = "上岗证号", width = 150)
	private String CCBPNum; // 上岗证号
	@ExportConfig(value = "发证机构名称", width = 150)
	private String institutionName;// 发证机构名称
	@ExportConfig(value = "上岗证图片", width = 150)
	private String CCBPImg; // 上岗证图片
	@ExportConfig(value = "上岗证有效期", width = 150)
	private String CCBPValidDate; // 上岗证有效期
	@ExportConfig(value = "从业资格证编号", width = 150)
	private String professionNum; // 从业资格证编号
	@ExportConfig(value = "从业资格证", width = 150)
	private String professionImg; // 从业资格证
	@ExportConfig(value = "从业资格证副本", width = 150)
	private String professionImg2; // 从业资格证副本
	@ExportConfig(value = "从业资格继续教育次数", width = 150)
	private int professionCount; // 从业资格继续教育次数
	@ExportConfig(value = "从业资格证有效期", width = 150)
	private String professionValidDate; // 从业资格证有效期
	@ExportConfig(value = "从业资格定级年检", width = 150)
	private String professionYearCheck; // 从业资格定级年检
	@ExportConfig(value = "审核时间", width = 150)
	private String reviewTime; // 审核时间（不管通过不通过，都记录审核时的时间）
	@ExportConfig(value = "审核状态", width = 150)
	private int reviewStatus; // 审核状态。0：未审核，1：通过，-1：审核失败,2:待审核；3：重新提交审核
	@ExportConfig(value = "审核备注", width = 150)
	private String reviewNote; // 审核备注
	@ExportConfig(value = "状态", width = 150)
	private int available; // 状态（1：正常；0：冻结）
	@ExportConfig(value = "可用状态", width = 150)
	private int cooperationavAilable; // 可用状态。1，可用，0：不可用，与cooperation表保持一致

	private String registrationId;

	private String EcontactTel;

	private String Econtact;
	@ExportConfig(value = "IOS设备号", width = 150)
	private String iosCid;// IOS设备号
	@ExportConfig(value = "安卓设备号", width = 150)
	private String androidCid;// 安卓设备号
	@ExportConfig(value = "是否已删除", width = 150)
	private Boolean isdel; // 是否已删除(0:正常,1:已删除)
	@ExportConfig(value = "默认车牌号", width = 150)
	private String defaultVehicleNum; // 默认车牌号
	@ExportConfig(value = "图片来源", width = 150)
	private Boolean imageForm; // 图片来源(默认CMS1，APP0)
	@ExportConfig(value = "备注", width = 150)
	private String remarks; // 备注
	@ExportConfig(value = "最后修改人员", width = 150)
	private String lastEditor; // 最后修改人员
	private String updateTime; // 修改时间
	private String institutioName;// 发证机构名称

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getDriverType() {
		return driverType;
	}

	public void setDriverType(int driverType) {
		this.driverType = driverType;
	}

	public String getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getIdCardFrontImg() {
		return idCardFrontImg;
	}

	public void setIdCardFrontImg(String idCardFrontImg) {
		this.idCardFrontImg = idCardFrontImg;
	}

	public String getIdCardBackImg() {
		return idCardBackImg;
	}

	public void setIdCardBackImg(String idCardBackImg) {
		this.idCardBackImg = idCardBackImg;
	}

	public String getIdCardValidDate() {
		return idCardValidDate;
	}

	public void setIdCardValidDate(String idCardValidDate) {
		this.idCardValidDate = idCardValidDate;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getDLNum() {
		return DLNum;
	}

	public void setDLNum(String dLNum) {
		DLNum = dLNum;
	}

	public String getDLImg2() {
		return DLImg2;
	}

	public void setDLImg2(String dLImg2) {
		DLImg2 = dLImg2;
	}

	public String getDLImg() {
		return DLImg;
	}

	public void setDLImg(String dLImg) {
		DLImg = dLImg;
	}

	public String getDLValidDate() {
		return DLValidDate;
	}

	public void setDLValidDate(String dLValidDate) {
		DLValidDate = dLValidDate;
	}

	public String getCCBPNum() {
		return CCBPNum;
	}

	public void setCCBPNum(String cCBPNum) {
		CCBPNum = cCBPNum;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getCCBPImg() {
		return CCBPImg;
	}

	public void setCCBPImg(String cCBPImg) {
		CCBPImg = cCBPImg;
	}

	public String getCCBPValidDate() {
		return CCBPValidDate;
	}

	public void setCCBPValidDate(String cCBPValidDate) {
		CCBPValidDate = cCBPValidDate;
	}

	public String getProfessionNum() {
		return professionNum;
	}

	public void setProfessionNum(String professionNum) {
		this.professionNum = professionNum;
	}

	public String getProfessionImg() {
		return professionImg;
	}

	public void setProfessionImg(String professionImg) {
		this.professionImg = professionImg;
	}

	public String getProfessionImg2() {
		return professionImg2;
	}

	public void setProfessionImg2(String professionImg2) {
		this.professionImg2 = professionImg2;
	}

	public int getProfessionCount() {
		return professionCount;
	}

	public void setProfessionCount(int professionCount) {
		this.professionCount = professionCount;
	}

	public String getProfessionValidDate() {
		return professionValidDate;
	}

	public void setProfessionValidDate(String professionValidDate) {
		this.professionValidDate = professionValidDate;
	}

	public String getProfessionYearCheck() {
		return professionYearCheck;
	}

	public void setProfessionYearCheck(String professionYearCheck) {
		this.professionYearCheck = professionYearCheck;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public int getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReviewNote() {
		return reviewNote;
	}

	public void setReviewNote(String reviewNote) {
		this.reviewNote = reviewNote;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getCooperationavAilable() {
		return cooperationavAilable;
	}

	public void setCooperationavAilable(int cooperationavAilable) {
		this.cooperationavAilable = cooperationavAilable;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getEcontactTel() {
		return EcontactTel;
	}

	public void setEcontactTel(String econtactTel) {
		EcontactTel = econtactTel;
	}

	public String getEcontact() {
		return Econtact;
	}

	public void setEcontact(String econtact) {
		Econtact = econtact;
	}

	public String getIosCid() {
		return iosCid;
	}

	public void setIosCid(String iosCid) {
		this.iosCid = iosCid;
	}

	public String getAndroidCid() {
		return androidCid;
	}

	public void setAndroidCid(String androidCid) {
		this.androidCid = androidCid;
	}

	public Boolean getIsdel() {
		return isdel;
	}

	public void setIsdel(Boolean isdel) {
		this.isdel = isdel;
	}

	public String getDefaultVehicleNum() {
		return defaultVehicleNum;
	}

	public void setDefaultVehicleNum(String defaultVehicleNum) {
		this.defaultVehicleNum = defaultVehicleNum;
	}

	public Boolean getImageForm() {
		return imageForm;
	}

	public void setImageForm(Boolean imageForm) {
		this.imageForm = imageForm;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getInstitutioName() {
		return institutioName;
	}

	public void setInstitutioName(String institutioName) {
		this.institutioName = institutioName;
	}

	public DriverInfo(String id, String dName, String tel, String password, String face, int driverType, String cooperationId, String idCardNum, String idCardFrontImg, String idCardBackImg, String idCardValidDate, String addr, String driverClass, String dLNum, String dLImg2, String dLImg, String dLValidDate, String cCBPNum, String institutionName, String cCBPImg, String cCBPValidDate, String professionNum, String professionImg, String professionImg2, int professionCount, String professionValidDate, String professionYearCheck, String reviewTime, int reviewStatus, String reviewNote, int available, int cooperationavAilable, String registrationId, String econtactTel, String econtact, String iosCid, String androidCid, Boolean isdel, String defaultVehicleNum, Boolean imageForm, String remarks, String lastEditor, String updateTime, String institutioName) {
		super();
		this.id = id;
		this.dName = dName;
		this.tel = tel;
		this.password = password;
		this.face = face;
		this.driverType = driverType;
		this.cooperationId = cooperationId;
		this.idCardNum = idCardNum;
		this.idCardFrontImg = idCardFrontImg;
		this.idCardBackImg = idCardBackImg;
		this.idCardValidDate = idCardValidDate;
		this.addr = addr;
		this.driverClass = driverClass;
		DLNum = dLNum;
		DLImg2 = dLImg2;
		DLImg = dLImg;
		DLValidDate = dLValidDate;
		CCBPNum = cCBPNum;
		this.institutionName = institutionName;
		CCBPImg = cCBPImg;
		CCBPValidDate = cCBPValidDate;
		this.professionNum = professionNum;
		this.professionImg = professionImg;
		this.professionImg2 = professionImg2;
		this.professionCount = professionCount;
		this.professionValidDate = professionValidDate;
		this.professionYearCheck = professionYearCheck;
		this.reviewTime = reviewTime;
		this.reviewStatus = reviewStatus;
		this.reviewNote = reviewNote;
		this.available = available;
		this.cooperationavAilable = cooperationavAilable;
		this.registrationId = registrationId;
		EcontactTel = econtactTel;
		Econtact = econtact;
		this.iosCid = iosCid;
		this.androidCid = androidCid;
		this.isdel = isdel;
		this.defaultVehicleNum = defaultVehicleNum;
		this.imageForm = imageForm;
		this.remarks = remarks;
		this.lastEditor = lastEditor;
		this.updateTime = updateTime;
		this.institutioName = institutioName;
	}

	public DriverInfo() {
		super();
	}
}