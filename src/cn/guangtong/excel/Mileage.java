package cn.guangtong.excel;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * @ClassName:Mileage
 */
public class Mileage {
	// 开始时间
	@ExportConfig(value = "开始时间", width = 150)
	private String startTime;
	// 车辆颜色
	@ExportConfig(value = "车辆颜色", width = 150)
	private String plateColor;
	// 里程
	@ExportConfig(value = "里程", width = 150)
	private String mileage;
	// 结束时间
	@ExportConfig(value = "结束时间", width = 150)
	private String endTime;
	// 颜色
	@ExportConfig(value = "车牌号", width = 150)
	private String plateNo;

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the plateColor
	 */
	public String getPlateColor() {
		return plateColor;
	}

	/**
	 * @param plateColor
	 *            the plateColor to set
	 */
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	/**
	 * @return the mileage
	 */
	public String getMileage() {
		return mileage;
	}

	/**
	 * @param mileage
	 *            the mileage to set
	 */
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the plateNo
	 */
	public String getPlateNo() {
		return plateNo;
	}

	/**
	 * @param plateNo
	 *            the plateNo to set
	 */
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

}
