package cn.guangtong.entity.beidou;

import java.util.Date;

/**
 * 媒体实体类
 * 
 * @author SunTo
 * 
 */
public class MediaItem {
	private Integer mediaitemid;

	private Date createdate;

	private Boolean deleted;

	private String owner;

	private String remark;

	private Integer tenantid;

	private Byte channelid;

	private Byte codeformat;

	private Integer commandid;

	private String commandtype;

	private Byte eventcode;

	private String filename;

	private Double latitude;

	private Double longitude;

	private Integer mediadataid;

	private Byte mediatype;

	private String plateno;

	private Date sendtime;

	private String simno;

	private Double speed;

	public Integer getMediaitemid() {
		return mediaitemid;
	}

	public void setMediaitemid(Integer mediaitemid) {
		this.mediaitemid = mediaitemid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
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
		this.owner = owner == null ? null : owner.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getTenantid() {
		return tenantid;
	}

	public void setTenantid(Integer tenantid) {
		this.tenantid = tenantid;
	}

	public Byte getChannelid() {
		return channelid;
	}

	public void setChannelid(Byte channelid) {
		this.channelid = channelid;
	}

	public Byte getCodeformat() {
		return codeformat;
	}

	public void setCodeformat(Byte codeformat) {
		this.codeformat = codeformat;
	}

	public Integer getCommandid() {
		return commandid;
	}

	public void setCommandid(Integer commandid) {
		this.commandid = commandid;
	}

	public String getCommandtype() {
		return commandtype;
	}

	public void setCommandtype(String commandtype) {
		this.commandtype = commandtype == null ? null : commandtype.trim();
	}

	public Byte getEventcode() {
		return eventcode;
	}

	public void setEventcode(Byte eventcode) {
		this.eventcode = eventcode;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename == null ? null : filename.trim();
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getMediadataid() {
		return mediadataid;
	}

	public void setMediadataid(Integer mediadataid) {
		this.mediadataid = mediadataid;
	}

	public Byte getMediatype() {
		return mediatype;
	}

	public void setMediatype(Byte mediatype) {
		this.mediatype = mediatype;
	}

	public String getPlateno() {
		return plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno == null ? null : plateno.trim();
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public String getSimno() {
		return simno;
	}

	public void setSimno(String simno) {
		this.simno = simno == null ? null : simno.trim();
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}
}