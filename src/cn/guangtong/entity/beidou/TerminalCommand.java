package cn.guangtong.entity.beidou;

import java.util.Date;

/**
 * 终端命令
 * 
 * @author SunTo
 * 
 */
public class TerminalCommand {
	// 命令主键
	private Integer cmdid;
	// 创建时间
	private String createdate ;
	// 是否删除
	private int deleted = 0;

	private String owner = "terminal";
	// 备注
	private String remark;
	// 租用者ID
	private Integer tenantid=0;
	// 通道id
	private Integer sn = 15;
	//命令数据中的命令字或标志位
	private String cmd="0";
	// 消息体
	private String cmddata;
	// 消息类型
	private Integer cmdtype;
	// 车牌号
	private String plateno;
	// 车辆sim卡号
	private String simno;
	// 命令状态
	private String status="New";
	// 更新时间
	private String updatedate;
	// id
	private Integer userid = 0;
	// 车辆id
	private Integer vehicleid;
	// 回复id
	private String replyId;
	// 回复参数
	private String replyParameter;
	//车辆颜色
	private String plateColor;
	public Integer getCmdid() {
		return cmdid;
	}

	public void setCmdid(Integer cmdid) {
		this.cmdid = cmdid;
	}

	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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
		this.owner = owner == null ? null : owner.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getTenantid() {
		return tenantid;
	}

	public void setTenantid(Integer tenantid) {
		this.tenantid = tenantid;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd == null ? null : cmd.trim();
	}

	public String getCmddata() {
		return cmddata;
	}

	public void setCmddata(String cmddata) {
		this.cmddata = cmddata == null ? null : cmddata.trim();
	}

	public Integer getCmdtype() {
		return cmdtype;
	}

	public void setCmdtype(Integer cmdtype) {
		this.cmdtype = cmdtype;
	}

	public String getPlateno() {
		return plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno == null ? null : plateno.trim();
	}

	public String getSimno() {
		return simno;
	}

	public void setSimno(String simno) {
		this.simno = simno == null ? null : simno.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	

	/**
	 * @return the updatedate
	 */
	public String getUpdatedate() {
		return updatedate;
	}

	/**
	 * @param updatedate the updatedate to set
	 */
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(Integer vehicleid) {
		this.vehicleid = vehicleid;
	}

	/**
	 * @return the replyId
	 */
	public String getReplyId() {
		return replyId;
	}

	/**
	 * @param replyId the replyId to set
	 */
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	/**
	 * @return the replyParameter
	 */
	public String getReplyParameter() {
		return replyParameter;
	}

	/**
	 * @param replyParameter the replyParameter to set
	 */
	public void setReplyParameter(String replyParameter) {
		this.replyParameter = replyParameter;
	}

	/**
	 * @return the plateColor
	 */
	public String getPlateColor() {
		return plateColor;
	}

	/**
	 * @param plateColor the plateColor to set
	 */
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	
}