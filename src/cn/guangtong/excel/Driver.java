package cn.guangtong.excel;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * 驾驶员实体类
 * 赵发志
 */
public class Driver{
	@ExportConfig(value = "司机编号", width = 150)
	private String id;

	@ExportConfig(value = "司机姓名", width = 150)
	private String dName;
	
	@ExportConfig(value = "企业名称", width = 150)
	private String cName;

	//@ExportConfig(value = "密码", width = 150)
	private String password;

	@ExportConfig(value = "企业ID", width = 150)
	private String cooperationId;

	@ExportConfig(value = "司机手机", width = 150)
	private String tel;

	//@ExportConfig(value = "现地址", width = 150)
	private String addr;

	//@ExportConfig(value = "身份证号", width = 150)
	private String idCardNum;

	//@ExportConfig(value = "身份证有效期", width = 150)
	private String idCardValidDate;

	@ExportConfig(value = "默认车牌号", width = 150)
	private String defaultVehicleNum;

	//@ExportConfig(value = "驾驶证号", width = 150)
	private String DLNum;

	//@ExportConfig(value = "驾驶证有效期", width = 150)
	private String DLValidDate;

	@ExportConfig(value = "准驾车型", width = 150)
	private String driverClass;

	//@ExportConfig(value = "从业资格证编号", width = 150)
	private String professionNum;

	//@ExportConfig(value = "最后更新时间", width = 150)
	private String institutionName;

	//@ExportConfig(value = "从业资格证有效期", width = 150)
	private String professionValidDate;

	//@ExportConfig(value = "从业资格定级年检", width = 150)
	private String professionYearCheck;

	//@ExportConfig(value = "状态（1：正常；0：冻结）", width = 150)
	private String available;

	//@ExportConfig(value = "从业资格继续教育次数", width = 150)
	private String professionCount;

	//@ExportConfig(value = "最后修改人员", width = 150)
	private String lastEditor;

	//@ExportConfig(value = "最后更新时间", width = 150)
	private String updateTime;

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

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getIdCardValidDate() {
		return idCardValidDate;
	}

	public void setIdCardValidDate(String idCardValidDate) {
		this.idCardValidDate = idCardValidDate;
	}

	public String getDefaultVehicleNum() {
		return defaultVehicleNum;
	}

	public void setDefaultVehicleNum(String defaultVehicleNum) {
		this.defaultVehicleNum = defaultVehicleNum;
	}

	public String getDLNum() {
		return DLNum;
	}

	public void setDLNum(String dLNum) {
		DLNum = dLNum;
	}

	public String getDLValidDate() {
		return DLValidDate;
	}

	public void setDLValidDate(String dLValidDate) {
		DLValidDate = dLValidDate;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
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

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getProfessionCount() {
		return professionCount;
	}

	public void setProfessionCount(String professionCount) {
		this.professionCount = professionCount;
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

	/**
	 * 
	 */
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
