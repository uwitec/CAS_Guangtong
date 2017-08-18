package cn.guangtong.model;

import cn.guangtong.utils.excel.ExportConfig;

public class DataReport {
	@ExportConfig(value = "车牌号", width = 150)
	private String plateNo; // 车牌号
	@ExportConfig(value = "车辆颜色", width = 150)
	private String plateColor; // 车辆颜色
	@ExportConfig(value = "发送时间", width = 150)
	private String sendTime; // 发送时间
	@ExportConfig(value = "上报条数", width = 150)
	private int count; // 上报条数

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

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

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public DataReport(String plateNo, String plateColor, String sendTime, int count) {
		super();
		this.plateNo = plateNo;
		this.plateColor = plateColor;
		this.sendTime = sendTime;
		this.count = count;
	}

	public DataReport() {
		super();
	}

	@Override
	public String toString() {
		return "DateReport [plateNo=" + plateNo + ", plateColor=" + plateColor + ", sendTime=" + sendTime + ", count=" + count + "]";
	}

}
