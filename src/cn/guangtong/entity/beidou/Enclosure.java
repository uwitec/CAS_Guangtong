package cn.guangtong.entity.beidou;

import java.util.Date;
import java.util.List;

/**
 * 区域实体类
 * 
 * @author SunTo
 * 
 */
public class Enclosure {

	// 多边形
	public static String POLYGON = "polygon";
	// 矩形
	public static String RECT = "rect";
	// 圆
	public static String CIRCLE = "circle";
	// 路线
	public static String ROUTE = "route";

	public static final String InDriver = "进区域报警给驾驶员";
	public static final String InPlatform = "进区域报警给平台";
	public static final String OutDriver = "出区域报警给驾驶员";
	public static final String OutPlatform = "出区域报警给平台";

	// 线路拐点集合
	private List<Linesegment> linesegments;

	private Integer enclosureId;
	// 创建时间
	private Date createDate = new Date();
	// 围栏名称
	private String name;
	// 是否删除
	private int deleted = 0;
	private String owner;
	private String remark;
	// 承租者Id
	private Integer tenantId = 0;
	// 报警类型，每个报警使用逗号隔开
	private String alarmType;
	// 是否根据时间
	private int byTime;
	// 围栏的中心经度
	private Double centerLat;
	// 纬度
	private Double centerLng;
	// 超速持续时间
	private Integer delay;
	// 围栏类型
	private String enclosureType;
	// 围栏有效期
	private String startDate;
	private String endDate;
	// 车基ID
	private String gpsId;
	// 关键点监控
	private Integer keyPoint = 0;
	// 是否限速
	private int limitSpeed;
	// 统一线宽
	private Double lineWidth = 1.0;
	// 最大速度限制
	private Double maxSpeed;
	// 偏移报警延迟时间
	private Integer offSetDelay = 0;
	// 车牌号
	private String plateNo;
	// 围栏坐标点,字符串格式是x1,y1;x2,y2,分别代表左上角和右下角的坐标点值
	private String points;
	// 半径，以地图的米为单位
	private Double radius;
	// 围栏号
	private Integer sn = 1;
	private String status;

	// 是否有行驶时间限制
	private int isTime;
	// 路段行驶时间限制
	private Integer maxtimelimit = 0;
	private Integer mintimelimit = 0;

	public int getIsTime() {
		return isTime;
	}

	public void setIsTime(int isTime) {
		this.isTime = isTime;
	}

	public Integer getMaxtimelimit() {
		return maxtimelimit;
	}

	public void setMaxtimelimit(Integer maxtimelimit) {
		this.maxtimelimit = maxtimelimit;
	}

	public Integer getMintimelimit() {
		return mintimelimit;
	}

	public void setMintimelimit(Integer mintimelimit) {
		this.mintimelimit = mintimelimit;
	}

	// 创建区域属性
	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
	// ORIGINAL LINE: public ushort CreateAreaAttr()
	public final short CreateAreaAttr() {
		byte[] bytes = new byte[16];
		bytes[0] = (byte) (getByTime() == 1 ? 1 : 0); // 1：根据时间
		bytes[1] = (byte) (getLimitSpeed() == 1 ? 1 : 0); // 限速
		bytes[2] = (byte) (getAlarmType().indexOf(InDriver) >= 0 ? 1 : 0);
		bytes[3] = (byte) (getAlarmType().indexOf(InPlatform) >= 0 ? 1 : 0);
		bytes[4] = (byte) (getAlarmType().indexOf(OutDriver) >= 0 ? 1 : 0);
		bytes[5] = (byte) (getAlarmType().indexOf(OutPlatform) >= 0 ? 1 : 0);
		bytes[6] = 0;
		bytes[7] = 0;
		bytes[8] = 0; // 0: 关闭 1：启动限速拍照

		bytes[15] = 0; // 15 0：无区域名称 1：有区域名称

		String byteStr = ""; // 二进制字符创
		for (int m = 0; m < 16; m++) {
			byteStr += bytes[15 - m];
			// byteStr += bytes[m];
		}

		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
		// ORIGINAL LINE: ushort t = Convert.ToUInt16(byteStr, 2);
		// short t = Short.parseShort(byteStr, 2); //将二进制字符转换成ushort
		short t = (short) Integer.parseInt(byteStr, 2);
		return t;
	}

	public List<Linesegment> getLinesegments() {
		return linesegments;
	}

	public void setLinesegments(List<Linesegment> linesegments) {
		this.linesegments = linesegments;
	}

	public static String getPOLYGON() {
		return POLYGON;
	}

	public static void setPOLYGON(String pOLYGON) {
		POLYGON = pOLYGON;
	}

	public static String getRECT() {
		return RECT;
	}

	public static void setRECT(String rECT) {
		RECT = rECT;
	}

	public static String getCIRCLE() {
		return CIRCLE;
	}

	public static void setCIRCLE(String cIRCLE) {
		CIRCLE = cIRCLE;
	}

	public static String getROUTE() {
		return ROUTE;
	}

	public static void setROUTE(String rOUTE) {
		ROUTE = rOUTE;
	}

	public Integer getEnclosureId() {
		return enclosureId;
	}

	public void setEnclosureId(Integer enclosureId) {
		this.enclosureId = enclosureId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
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

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public int getByTime() {
		return byTime;
	}

	public void setByTime(int byTime) {
		this.byTime = byTime;
	}

	public Double getCenterLat() {
		return centerLat;
	}

	public void setCenterLat(Double centerLat) {
		this.centerLat = centerLat;
	}

	public Double getCenterLng() {
		return centerLng;
	}

	public void setCenterLng(Double centerLng) {
		this.centerLng = centerLng;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public String getEnclosureType() {
		return enclosureType;
	}

	public void setEnclosureType(String enclosureType) {
		this.enclosureType = enclosureType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getGpsId() {
		return gpsId;
	}

	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}

	public Integer getKeyPoint() {
		return keyPoint;
	}

	public void setKeyPoint(Integer keyPoint) {
		this.keyPoint = keyPoint;
	}

	public int getLimitSpeed() {
		return limitSpeed;
	}

	public void setLimitSpeed(int limitSpeed) {
		this.limitSpeed = limitSpeed;
	}

	public Double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public Double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Integer getOffSetDelay() {
		return offSetDelay;
	}

	public void setOffSetDelay(Integer offSetDelay) {
		this.offSetDelay = offSetDelay;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static String getIndriver() {
		return InDriver;
	}

	public static String getInplatform() {
		return InPlatform;
	}

	public static String getOutdriver() {
		return OutDriver;
	}

	public static String getOutplatform() {
		return OutPlatform;
	}

}