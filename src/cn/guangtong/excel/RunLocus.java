package cn.guangtong.excel;

import cn.guangtong.utils.AMAP;
import cn.guangtong.utils.excel.ExportConfig;

public class RunLocus {

	@ExportConfig(value = "车牌号", width = 100)
	private String plateNo;//车牌号
	@ExportConfig(value = "车辆颜色", width = 80)
	private String plateColor;//车辆颜色
	@ExportConfig(value = "经度", width = 120)
	private String longitude;//经度
	@ExportConfig(value = "纬度", width = 120)
	private String latitude;//纬度
	@ExportConfig(value = "位置信息", width = 150)
	private String location;//位置信息
	@ExportConfig(value = "时间", width = 200)
	private String sendTime;//时间
	@ExportConfig(value = "速度", width = 100)
	private String velocity;//速度
	@ExportConfig(value = "方向", width = 100)
	private String direction;//方向
	
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLocation() {
		String temp=this.getLongitude()+","+this.getLatitude();
		String location=AMAP.regeo(temp);
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getVelocity() {
		return velocity;
	}
	public void setVelocity(String velocity) {
		this.velocity = velocity;
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
	
	
}
