package cn.guangtong.excel;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.excel.ExportConfig;

/**
 * 最新状态实体类
 * 
 * @author SunTo
 * 
 */
public class LatestStatus {
	// 发送时间
	@ExportConfig(value = "最后上报时间", width = 150)
	private String sendTime;
	// 报警状态
	@ExportConfig(value = "报警状态", width = 150)
	private String alarmState;
	// 车牌号
	@ExportConfig(value = "车牌号", width = 150)
	private String plateNo;
	// 经度
	@ExportConfig(value = "经度", width = 150)
	private Double latitude;
	// 是否在线
	@ExportConfig(value = "是否在线", width = 150)
	private int online;
	// 地理位置的中文描述
	@ExportConfig(value = "地理位置", width = 150)
	private String location;
	// 速度
	@ExportConfig(value = "速度", width = 150)
	private Double velocity;
	// 颜色
	@ExportConfig(value = "颜色", width = 150)
	private String plateColor;
	// 纬度
	@ExportConfig(value = "纬度", width = 150)
	private Double longitude;
	// 方向
	@ExportConfig(value = "方向", width = 150)
	private String direction;
	// 状态
	@ExportConfig(value = "状态", width = 150)
	private String status;

	/**
	 * @return the sendTime
	 */
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime
	 *            the sendTime to set
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public String getLocation() {
		String temp = this.getLongitude() + "," + this.getLatitude();
		String location = AMAP.regeo(temp);
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getVelocity() {
		return velocity;
	}

	public void setVelocity(Double velocity) {
		this.velocity = velocity;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

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

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
