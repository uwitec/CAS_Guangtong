package cn.guangtong.model;

/**
 * 车辆级联菜单
 * 
 * @author SunTo
 * 
 */
public class VehicleMenu {

	// 车辆id
	private String id;
	// 车牌号
	private String num;
	// simNo卡号
	private String simNo;
	// 车队名称
	private String tName;
	// 企业名称
	private String cName;
	// 是否列入监控
	private int isRead;
	// adminid
	private Integer adminid;
	// 企业id
	private String cooperationId;
	// 车队id
	private String teamId;

	// 别名
	private String nickname;
	// 外派状态
	private Integer isforeignfaction;

	// 车辆颜色
	private String VMColor;

	public String getVMColor() {
		return VMColor;
	}

	public void setVMColor(String vMColor) {
		VMColor = vMColor;
	}

	public String getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getIsforeignfaction() {
		return isforeignfaction;
	}

	public void setIsforeignfaction(Integer isforeignfaction) {
		this.isforeignfaction = isforeignfaction;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
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

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

}
