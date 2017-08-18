package cn.guangtong.entity.vehicle;

public class AlarmOfVehicle {

	private String vehicleId;			//车牌号
	private String vehicleColor;		//车辆颜色
	private String alarmCount;			//警报数量
	private String longitude;			//经度
	private String latitude;			//纬度
	private String location;			//位置信息
	private String time;				//时间
	private String velocity;			//速度
	private String direction;			//方向
	private String date;				//日期
	private String oilType;				//油箱类型
	private String avgOilNow;			//当前平均油量
	private String avgOil;				//平均油耗量
	private String dataNum;				//数据上报条数
	
	
	public String getDataNum() {
		return dataNum;
	}
	public void setDataNum(String dataNum) {
		this.dataNum = dataNum;
	}
	public String getOilType(){
		return oilType;
	}
	public void setOilType(String oilType){
		this.oilType = oilType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAvgOilNow() {
		return avgOilNow;
	}
	public void setAvgOilNow(String avgOilNow) {
		this.avgOilNow = avgOilNow;
	}
	public String getAvgOil() {
		return avgOil;
	}
	public void setAvgOil(String avgOil) {
		this.avgOil = avgOil;
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
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVelocity() {
		return velocity;
	}
	public void setVelocity(String velocity) {
		this.velocity = velocity;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public String getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(String alarmCount) {
		this.alarmCount = alarmCount;
	}
	
}
