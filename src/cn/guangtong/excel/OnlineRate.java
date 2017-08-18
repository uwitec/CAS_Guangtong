package cn.guangtong.excel;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * @ClassName:OnlineRate
 */
public class OnlineRate {
	@ExportConfig(value = "上线总数", width = 150)
	private int total;
	//@ExportConfig(value = "simNo卡号", width = 150)
	private String simNo;
	@ExportConfig(value = "车牌号", width = 150)
    private String plateNo;
	//@ExportConfig(value = "gpsid", width = 150)
    private String gpsId;
	@ExportConfig(value = "车辆颜色", width = 150)
    private String plateColor;
	@ExportConfig(value = "百分比", width = 150)
    private String percentage;
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
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
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
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
    
}
