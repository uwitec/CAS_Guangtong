package cn.guangtong.model;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.StringParse;
import cn.guangtong.utils.excel.ExportConfig;

/**
 * 轨迹点实体
 * 
 * @author SunTo
 * 
 */
public class Trajectory {

	// 主键id
	private Integer gpsId;
	// 纬度
	private double latitude;
	// 经度
	private double longitude;
	// 点类型 0:运行中 1:停车点 2:报警点
	private Integer type = 0;
	// 时间点
	@ExportConfig(value = "时间点", width = 200)
	private String portTime;
	// 方向
	@ExportConfig(value = "方向", width = 100)
	private String direction;
	// 速度
	@ExportConfig(value = "速度", width = 100)
	private double velocity;
	// 对经纬度的地理位置解析
	@ExportConfig(value = "位置信息", width = 200)
	private String location;
	// 状态
	@ExportConfig(value = "状态", width = 200)
	private String status;
	// 报警位状态
	@ExportConfig(value = "报警状态", width = 200)
	private String alarmState;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	public String getPortTime() {
		return portTime;
	}

	public void setPortTime(String portTime) {
		this.portTime = portTime;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public String getLocation() {
//		String temp = this.getLongitude() + "," + this.getLatitude();
//		String location = AMAP.geocode(temp);
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDirection() {
		String directionStr = "";
		if (this.direction != null && !this.direction.equals("")) {
			int temp = Integer.parseInt(this.direction);
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

	public Integer getGpsId() {
		return gpsId;
	}

	public void setGpsId(Integer gpsId) {
		this.gpsId = gpsId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
