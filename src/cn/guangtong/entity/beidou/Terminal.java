package cn.guangtong.entity.beidou;

/**
 * 终端
 * 
 * @author SunTo
 * 
 */
public class Terminal {
	private Integer termId;
	// 创建时间
	private String createDate;
	// 是否删除
	private int deleted = 0;
	// 物主
	private String owner;
	// 备注
	private String remark;
	private Short verHardware;
	// 版本协议
	private Short verProtocol;
	private Short verSoftware;
	// 承租者id
	private Integer tenantId = 0;
	// 是否已经绑定
	private int bind;
	// 出厂号
	private String devNo;
	// 安装地址
	private String instAlladdress;
	// 安装时间
	private String installTime;
	// 生产厂家
	private String makeFactory;
	// 生产批次
	private String makeNo;
	// 制造时间
	private String makeTime;
	// 流水号
	private String seqNo;
	// 车终端卡号
	private String simNo;
	// 状态
	private String state;
	// 设备编号
	private String termNo;
	// 终端型号
	private String termType;
	// 更新时间
	private String updateTime;
	// 安装工
	private String waitor;

	public Integer getTermId() {
		return termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Short getVerHardware() {
		return verHardware;
	}

	public void setVerHardware(Short verHardware) {
		this.verHardware = verHardware;
	}

	public Short getVerProtocol() {
		return verProtocol;
	}

	public void setVerProtocol(Short verProtocol) {
		this.verProtocol = verProtocol;
	}

	public Short getVerSoftware() {
		return verSoftware;
	}

	public void setVerSoftware(Short verSoftware) {
		this.verSoftware = verSoftware;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		this.bind = bind;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getInstAlladdress() {
		return instAlladdress;
	}

	public void setInstAlladdress(String instAlladdress) {
		this.instAlladdress = instAlladdress;
	}

	public String getInstallTime() {
		return installTime;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public String getMakeFactory() {
		return makeFactory;
	}

	public void setMakeFactory(String makeFactory) {
		this.makeFactory = makeFactory;
	}

	public String getMakeNo() {
		return makeNo;
	}

	public void setMakeNo(String makeNo) {
		this.makeNo = makeNo;
	}

	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getWaitor() {
		return waitor;
	}

	public void setWaitor(String waitor) {
		this.waitor = waitor;
	}

}