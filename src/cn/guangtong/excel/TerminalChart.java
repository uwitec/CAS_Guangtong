package cn.guangtong.excel;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * @ClassName:TerminalChart
 */
public class TerminalChart {
	//@ExportConfig(value = "gpsid", width = 150)
    private String gpsId;
	@ExportConfig(value = "车牌号", width = 150)
    private String plateNo;
	@ExportConfig(value = "车辆颜色", width = 150)
    private String plateColor;
	//@ExportConfig(value = "报警状态", width = 150)
	private int alarmState;
	//@ExportConfig(value = "创建时间", width = 150)
    private String createDate;
	//@ExportConfig(value = "simNo卡号", width = 150)
	private String simNo;
	@ExportConfig(value = "报警总数", width = 150)
	private int size;
	/**
	 * @return the gpsId
	 */
	public String getGpsId() {
		return gpsId;
	}
	/**
	 * @param gpsId the gpsId to set
	 */
	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}
	/**
	 * @return the plateNo
	 */
	public String getPlateNo() {
		return plateNo;
	}
	/**
	 * @param plateNo the plateNo to set
	 */
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	/**
	 * @return the plateColor
	 */
	public String getPlateColor() {
		return plateColor;
	}
	/**
	 * @param plateColor the plateColor to set
	 */
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	/**
	 * @return the alarmState
	 */
	public int getAlarmState() {
		return alarmState;
	}
	/**
	 * @param alarmState the alarmState to set
	 */
	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the simNo
	 */
	public String getSimNo() {
		return simNo;
	}
	/**
	 * @param simNo the simNo to set
	 */
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
