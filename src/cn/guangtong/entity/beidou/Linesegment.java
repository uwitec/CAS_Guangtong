package cn.guangtong.entity.beidou;

import java.util.Date;

public class Linesegment {

	private Integer segid;
	private Date createdate = new Date();
	private int deleted = 0;
	private String owner;
	// 备注
	private String remark;
	private Integer tenantid = 0;
	// 路段属性,各个选项使用;分割
	private String alarmtype;
	// 所属区域Id
	private Integer enclosureid;
	// 经度
	private Double latitude1 = 0.0;
	private Double latitude2 = 0.0;
	// 路段宽度
	private Integer linewidth=12;
	// 纬度
	private Double longitude1 = 0.0;
	private Double longitude2 = 0.0;
	// 超速限制
	private Integer maxspeed=0;
	// 路段行驶时间限制
	private Integer maxtimelimit=0;
	private Integer mintimelimit=0;
	private int bytime=0;
	private String name;
	// 超速持续时间
	private Integer overspeedtime=0;
	private int limitspeed;
	// 拐点ID
	private Integer pointid = 0;
	// 是否包站点
	private int station = 0;

	public Integer getSegid() {
		return segid;
	}

	public void setSegid(Integer segid) {
		this.segid = segid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
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

	public Integer getTenantid() {
		return tenantid;
	}

	public void setTenantid(Integer tenantid) {
		this.tenantid = tenantid;
	}

	public String getAlarmtype() {
		return alarmtype;
	}

	public void setAlarmtype(String alarmtype) {
		this.alarmtype = alarmtype;
	}

	public int getBytime() {
		return bytime;
	}

	public void setBytime(int bytime) {
		this.bytime = bytime;
	}

	public Integer getEnclosureid() {
		return enclosureid;
	}

	public void setEnclosureid(Integer enclosureid) {
		this.enclosureid = enclosureid;
	}

	public Double getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(Double latitude1) {
		this.latitude1 = latitude1;
	}

	public Double getLatitude2() {
		return latitude2;
	}

	public void setLatitude2(Double latitude2) {
		this.latitude2 = latitude2;
	}

	public int getLimitspeed() {
		return limitspeed;
	}

	public void setLimitspeed(int limitspeed) {
		this.limitspeed = limitspeed;
	}

	public Integer getLinewidth() {
		return linewidth;
	}

	public void setLinewidth(Integer linewidth) {
		this.linewidth = linewidth;
	}

	public Double getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(Double longitude1) {
		this.longitude1 = longitude1;
	}

	public Double getLongitude2() {
		return longitude2;
	}

	public void setLongitude2(Double longitude2) {
		this.longitude2 = longitude2;
	}

	public Integer getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Integer maxspeed) {
		this.maxspeed = maxspeed;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOverspeedtime() {
		return overspeedtime;
	}

	public void setOverspeedtime(Integer overspeedtime) {
		this.overspeedtime = overspeedtime;
	}

	public Integer getPointid() {
		return pointid;
	}

	public void setPointid(Integer pointid) {
		this.pointid = pointid;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

}