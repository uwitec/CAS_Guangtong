package cn.guangtong.model;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.StringParse;

/**
 * 地图轨迹信息实体类
 */
public class LocusInfo {
	private String plateNo;// 车牌号
	private String simNo;// 车载设备ID
	private String desc;// 车辆类型
	private String VMColor;// 车辆颜色
	private String velocity;// 速度
	private String direction;// 方向
	private String longitude;// 经度
	private String latitude;// 纬度
	private String location;// 位置信息
	private String createDate;// 接收时间
	private String STATUS;// 状态
	private String alarmState;// 报警状态

	/**
	 * @return the plateNo
	 */
	public String getPlateNo() {
		return plateNo;
	}

	/**
	 * @param plateNo
	 *            the plateNo to set
	 */
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	/**
	 * @return the simNo
	 */
	public String getSimNo() {
		return simNo;
	}

	/**
	 * @param simNo
	 *            the simNo to set
	 */
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the vMColor
	 */
	public String getVMColor() {
		return VMColor;
	}

	/**
	 * @param vMColor
	 *            the vMColor to set
	 */
	public void setVMColor(String vMColor) {
		VMColor = vMColor;
	}

	/**
	 * @return the velocity
	 */
	public String getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity
	 *            the velocity to set
	 */
	public void setVelocity(String velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		String temp = this.getLongitude() + "," + this.getLatitude();
		String location = AMAP.regeo(temp);
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the sTATUS
	 */
	public String getSTATUS() {
		return StringParse.pLocationInfoInt(Integer.parseInt(this.STATUS));
	}

	/**
	 * @param sTATUS
	 *            the sTATUS to set
	 */
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	/**
	 * @return the alarmState
	 */
	public String getAlarmState() {

		return StringParse.alarmStatusInfo(Integer.parseInt(this.alarmState));
	}

	/**
	 * @param alarmState
	 *            the alarmState to set
	 */
	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

}
