package cn.guangtong.model;

public class PlatAndTerAlarmChar {
	private String plateNo;		//车牌号
	private String plateColor;	//车辆颜色
	private int count;			//报警数量
	
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public PlatAndTerAlarmChar() {
		super();
	}
	public PlatAndTerAlarmChar(String plateNo, String plateColor, int count) {
		super();
		this.plateNo = plateNo;
		this.plateColor = plateColor;
		this.count = count;
	}
	@Override
	public String toString() {
		return "PlatAlarmInfo [plateNo=" + plateNo + ", plateColor="
				+ plateColor + ", count=" + count + "]";
	}
	
}
