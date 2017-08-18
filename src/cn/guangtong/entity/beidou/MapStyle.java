package cn.guangtong.entity.beidou;

public class MapStyle {
	private Integer id;
	// 工具栏
	private Integer toolbar = 1;
	// 地图标注层
	private Integer mapcalloutlayer = 1;
	// 比例尺
	private Integer scale = 1;
	// 地图工具栏
	private Integer maptoolbar = 1;
	// 标尺
	private Integer ruler = 1;
	// 标尺比例
	private Integer rulerproportions = 10;
	// 地图中心坐标
	private Integer mapcentercoordinates = 1;
	// 车辆图标
	private Integer vehicleicon = 1;
	// 一般信息
	private Integer generalinformation = 1;
	// 命令回复信息
	private Integer commandreplyinformation = 1;
	// 车辆上线信息
	private Integer vehicleonlineinformation = 1;
	// 车辆下线信息
	private Integer vehicleinformation = 1;
	// 报警灯
	private Integer alarmlamp = 1;
	// 报警声
	private Integer alarmsound = 1;

	private Integer userid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToolbar() {
		return toolbar;
	}

	public void setToolbar(Integer toolbar) {
		this.toolbar = toolbar;
	}

	public Integer getMapcalloutlayer() {
		return mapcalloutlayer;
	}

	public void setMapcalloutlayer(Integer mapcalloutlayer) {
		this.mapcalloutlayer = mapcalloutlayer;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public Integer getMaptoolbar() {
		return maptoolbar;
	}

	public void setMaptoolbar(Integer maptoolbar) {
		this.maptoolbar = maptoolbar;
	}

	public Integer getRuler() {
		return ruler;
	}

	public void setRuler(Integer ruler) {
		this.ruler = ruler;
	}

	public Integer getRulerproportions() {
		return rulerproportions;
	}

	public void setRulerproportions(Integer rulerproportions) {
		this.rulerproportions = rulerproportions;
	}

	public Integer getMapcentercoordinates() {
		return mapcentercoordinates;
	}

	public void setMapcentercoordinates(Integer mapcentercoordinates) {
		this.mapcentercoordinates = mapcentercoordinates;
	}

	public Integer getVehicleicon() {
		return vehicleicon;
	}

	public void setVehicleicon(Integer vehicleicon) {
		this.vehicleicon = vehicleicon;
	}

	public Integer getGeneralinformation() {
		return generalinformation;
	}

	public void setGeneralinformation(Integer generalinformation) {
		this.generalinformation = generalinformation;
	}

	public Integer getCommandreplyinformation() {
		return commandreplyinformation;
	}

	public void setCommandreplyinformation(Integer commandreplyinformation) {
		this.commandreplyinformation = commandreplyinformation;
	}

	public Integer getVehicleonlineinformation() {
		return vehicleonlineinformation;
	}

	public void setVehicleonlineinformation(Integer vehicleonlineinformation) {
		this.vehicleonlineinformation = vehicleonlineinformation;
	}

	public Integer getVehicleinformation() {
		return vehicleinformation;
	}

	public void setVehicleinformation(Integer vehicleinformation) {
		this.vehicleinformation = vehicleinformation;
	}

	public Integer getAlarmlamp() {
		return alarmlamp;
	}

	public void setAlarmlamp(Integer alarmlamp) {
		this.alarmlamp = alarmlamp;
	}

	public Integer getAlarmsound() {
		return alarmsound;
	}

	public void setAlarmsound(Integer alarmsound) {
		this.alarmsound = alarmsound;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}