package cn.guangtong.entity.beidou;

import java.util.Date;

/**
 * 报警记录表
 * 
 * @author SunTo
 * 
 */
public class Alarmrecord {

	// public static final String TYPE_WARN = "Warn"; //报警记录
	public static final String TYPE_STATE = "State"; // 状态记录
	public static final String TYPE_PARKING = "Stop"; // 停车记录
	public static final String TYPE_ONLINE = "GpsOnline"; // 在线记录
	public static final String TYPE_OFFLINE = "GpsOffline"; // 离线记录
	public static final String TYPE_OFFSET_ROUTE = "OffsetRoute"; // 路线偏移
	public static final String TYPE_ON_ROUTE = "OnRoute"; // 路线偏移
	public static final String TYPE_OVER_SPEED_ON_ROUTE = "OverSpeedOnRoute"; // 分段限速
	public static final String TYPE_ARRIVE_NOT_ON_TIME = "ArriveNotOnTime"; // 规定时间到达
	public static final String TYPE_LEAVE_NOT_ON_TIME = "LeaveNotOnTime"; // 规定时间离开

	public static String TYPE_CROSS_BORDER = "CrossBorder"; // 围栏报警
	public static String TYPE_IN_AREA = "InArea"; // 进入区域
	public static final String STATUS_NEW = "New"; // 开始状态
	public static final String STATUS_OLD = "Old"; // 结束状态

	public static String ALARM_FROM_PLATFORM = "platform_alarm";// 平台报警;
	public static String ALARM_FROM_TERM = "terminal_alarm";// 终端报警;
	public static String ALARM_FROM_GOV = "gov_alarm";// 政府平台报警;
	public static String STATE_FROM_TERM = "terminal_state";// 终端状态变化报警;

	// 警告标识
	private Integer alarmId;
	// 创建时间
	private Date createDate;
	// 是否消除
	private Boolean deleted;
	// 物主
	private String owner;
	// 备注
	private String remark;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 承租者id
	private Integer tenantId;
	// 记录子类型
	private String childType;

	// 子类型的转译
	private String alarm;

	// 司机
	private String driver;
	// 处理标志，可填写处理原因等
	private String flag;
	// 起始油量
	private Double gas1;
	// 终止油量
	private Double gas2;
	// 纬度
	private Double latitude;
	private Double latitude1;
	// 经度
	private Double longitude;
	private Double longitude1;
	// 开始的地点
	private String location;
	// 最终的地点
	private String location1;
	// 起始里程
	private Double mileage1;
	// 终止里程
	private Double mileage2;
	// 车牌号
	private String plateNo;
	// > 0 代表此记录已经报过警，避免重复报警,默认是0
	private Integer processed;
	// 地点类型
	private Integer station;
	// 记录的状态
	private String status;
	// 时间间隔，以分钟为单位
	private Double timeSpan;
	// 记录类型
	private String type;
	// 行驶速度
	private Double velocity;
	// 视频文件名称
	private String videoFileName;

	public String getAlarm() {
		String temp = "未解析";
		if (childType != null) {
			if ("OffsetRoute".equals(childType) || "OnRoute".equals(childType)) {
				temp = "偏离路线报警";
			} else if ("CrossBorder".equals(childType)) {
				temp = "围栏报警";
			} else if ("InArea".equals(childType)) {
				temp = "进入区域";
			} else if ("0".equals(childType)) {
				temp = "紧急报警";
			} else if ("1".equals(childType)) {
				temp = "超速报警";
			} else if ("2".equals(childType)) {
				temp = "疲劳驾驶";
			}else if ("3".equals(childType)) {
				temp = "预警";
			}else if ("4".equals(childType)) {
				temp = "GNSS模板发生故障";
			}else if ("5".equals(childType)) {
				temp = "GNSS天线未接或被剪断";
			}else if ("6".equals(childType)) {
				temp = "GNSS天线短路";
			}else if ("7".equals(childType)) {
				temp = "终端主电源欠压";
			}else if ("8".equals(childType)) {
				temp = "终端主电源掉电";
			}else if ("9".equals(childType)) {
				temp = "终端lcd或显示器故障";
			}else if ("10".equals(childType)) {
				temp = "TTS模块故障";
			}else if ("11".equals(childType)) {
				temp = "摄像头故障";
			}else if ("18".equals(childType)) {
				temp = "当天累计驾驶超速";
			}else if ("19".equals(childType)) {
				temp = "超时停车";
			}else if ("20".equals(childType)) {
				temp = "超出区域";
			}else if ("21".equals(childType)) {
				temp = "进入路线";
			}else if ("22".equals(childType)) {
				temp = "路段行驶时间不足/过长";
			}else if ("23".equals(childType)) {
				temp = "路线偏离报警";
			}else if ("24".equals(childType)) {
				temp = "车辆VSS故障";
			}else if ("25".equals(childType)) {
				temp = "车辆油量异常";
			}else if ("26".equals(childType)) {
				temp = "车辆被盗";
			}else if ("27".equals(childType)) {
				temp = "车辆非法点火";
			}else if ("28".equals(childType)) {
				temp = "车辆非法位移";
			}else if ("29".equals(childType)) {
				temp = "碰撞侧翻报警";
			}
		}
		return temp;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	public Integer getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getChildType() {
		return childType;
	}

	public void setChildType(String childType) {
		this.childType = childType;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Double getGas1() {
		return gas1;
	}

	public void setGas1(Double gas1) {
		this.gas1 = gas1;
	}

	public Double getGas2() {
		return gas2;
	}

	public void setGas2(Double gas2) {
		this.gas2 = gas2;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(Double latitude1) {
		this.latitude1 = latitude1;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(Double longitude1) {
		this.longitude1 = longitude1;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public Double getMileage1() {
		return mileage1;
	}

	public void setMileage1(Double mileage1) {
		this.mileage1 = mileage1;
	}

	public Double getMileage2() {
		return mileage2;
	}

	public void setMileage2(Double mileage2) {
		this.mileage2 = mileage2;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Integer getProcessed() {
		return processed;
	}

	public void setProcessed(Integer processed) {
		this.processed = processed;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(Double timeSpan) {
		this.timeSpan = timeSpan;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getVelocity() {
		return velocity;
	}

	public void setVelocity(Double velocity) {
		this.velocity = velocity;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

}