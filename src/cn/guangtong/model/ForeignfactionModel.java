package cn.guangtong.model;

/**
 * 车辆外派管理分页实体
 * 
 * @author SunTo
 * 
 */
public class ForeignfactionModel {

	// 车辆id
	private String id;
	// 车辆别名
	private String nickname;
	// 车牌号
	private String num;
	// 接受企业名称
	private String cName;
	// 结束时间
	private String endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
