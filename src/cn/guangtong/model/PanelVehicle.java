package cn.guangtong.model;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.StringParse;
import cn.guangtong.utils.excel.ExportConfig;

/**
 * 信息面板车辆实体
 * 
 * @author SunTo
 * 
 */
public class PanelVehicle {
	// 车辆颜色
	@ExportConfig(value = "车辆颜色", width = 100)
	private String vmColor;
	// 车辆id
	private String vehicleId;
	// 终端编号
	@ExportConfig(value = "车载设备ID", width = 100)
	private String simNo;
	// 车牌号
	@ExportConfig(value = "车牌号", width = 100)
	private String num;
	// 车队名称
	private String tName;
	// 特别运输名称
	@ExportConfig(value = "车辆类型", width = 100)
	private String sName;
	// 企业名称
	private String cName;
	// 速度
	@ExportConfig(value = "速度", width = 100)
	private double velocity;
	// 方向
	@ExportConfig(value = "方向", width = 100)
	private String direction;
	// 纬度
	private double latitude;
	// 经度
	private double longitude;
	// 地理位置的文字描述,如省,市，县，路的详细描述
	@ExportConfig(value = "位置信息", width = 150)
	private String location;
	// 是否在线
	private int online;
	// 发送时间
	@ExportConfig(value = "发送时间", width = 150)
	private String sendTime;
	// 状态信息
	@ExportConfig(value = "状态", width = 200)
	private String status;
	// 报警信息
	@ExportConfig(value = "报警信息", width = 100)
	private String alarmState;
	// 驾驶员名称
	@ExportConfig(value = "驾驶员", width = 100)
	private String driverName;

	public String getVmColor() {
		return vmColor;
	}

	public void setVmColor(String vmColor) {
		this.vmColor = vmColor;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
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

	public String getLocation() {
		String temp = this.getLongitude() + "," + this.getLatitude();
		return AMAP.regeo(temp);
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getStatus() {
		return StringParse.pLocationInfo(status);
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAlarmState() {
		return StringParse.pAlarmStatus(alarmState);
	}

	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

}
