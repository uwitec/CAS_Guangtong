package cn.guangtong.model;

import cn.guangtong.utils.AMAP;

/**
 * 进出区域
 */
public class AccessArea {
	// 车牌号
	private String num;
	// 车辆颜色
	private String VMColor;
	// 别名
	private String nickname;
	// 车终端卡号
	private String simNo;
	// 经度
	private double longitude;
	// 纬度
	private double latitude;
	// 对经纬度的地理位置解析
	private String location;
	// 方向
	private String direction;
	// 速度
	private double velocity;
	// 报警位状态
	private int alarmState;
	// 状态
	private int status;
	//报警状态信息
	private String alarmStatusInfo;
	//位置状态信息
	private String statusInfo;
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * @return the vMColor
	 */
	public String getVMColor() {
		return VMColor;
	}
	/**
	 * @param vMColor the vMColor to set
	 */
	public void setVMColor(String vMColor) {
		VMColor = vMColor;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the simNo
	 */
	public String getSimNo() {
		return simNo;
	}
	/**
	 * @param simNo the simNo to set
	 */
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		String temp=this.getLongitude()+","+this.getLatitude();
		String location=AMAP.regeo(temp);
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return the direction
	 */
	public String getDirection() {
		String directionStr="";
		if(this.direction!=null && !this.direction.equals("")){
			int temp=Integer.parseInt(this.direction);
			if (temp == 0) {
				directionStr = "正北";
			} else if (temp > 0 & temp < 90) {
				directionStr = "东北";
			} else if (temp == 90) {
				directionStr = "正东";
			} else if (temp > 90 & temp < 180) {
				directionStr = "东南";
			} else if (temp == 180) {
				directionStr = "正南";
			} else if (temp > 180 & temp < 270) {
				directionStr = "西南";
			} else if (temp == 270) {
				directionStr = "正西";
			} else if (temp > 270 & temp < 360) {
				directionStr = "西北";
			}
		}	
		return directionStr;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the velocity
	 */
	public double getVelocity() {
		return velocity;
	}
	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	/**
	 * @return the alarmState
	 */
	public int getAlarmState() {
		return alarmState;
	}
	/**
	 * @param alarmState the alarmState to set
	 */
	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the alarmStatusInfo
	 */
	public String getAlarmStatusInfo() {
		return alarmStatusInfo;
	}
	/**
	 * @param alarmStatusInfo the alarmStatusInfo to set
	 */
	public void setAlarmStatusInfo(String alarmStatusInfo) {
		this.alarmStatusInfo = alarmStatusInfo;
	}
	/**
	 * @return the statusInfo
	 */
	public String getStatusInfo() {
		return statusInfo;
	}
	/**
	 * @param statusInfo the statusInfo to set
	 */
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
	
}
