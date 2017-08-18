package cn.guangtong.entity.total;

import cn.guangtong.utils.excel.ExportConfig;

public class PlatTotalAlarm {

	@ExportConfig(value = "车牌号", width = 150)
	private String plateNo;//车牌号
	@ExportConfig(value = "车辆颜色", width = 100)
	private String plateColor;//车辆颜色
	@ExportConfig(value = "报警数量", width = 200)
	private int quantity;// 数量
	
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
