package cn.guangtong.model;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * 油量统计实体类
 * 
 * @author SunTo
 * 
 */
public class FuleStatistics {
	// simNo卡号
	@ExportConfig(value = "simNo卡号", width = 150)
	private String simNO;
	// 车牌号
	@ExportConfig(value = "车牌号", width = 150)
	private String plateNo;
	// 车辆颜色
	@ExportConfig(value = "车辆颜色", width = 150)
	private String plateColor;
	// 时间
	@ExportConfig(value = "时间", width = 150)
	private String time;
	// 油箱类型
	@ExportConfig(value = "油箱类型", width = 150)
	private String fuelTank;
	// 油量
	@ExportConfig(value = "油量", width = 150)
	private double gas;
	// 油耗
	@ExportConfig(value = "油耗", width = 150)
	private String fuleConsumption;

	public String getSimNO() {
		return simNO;
	}

	public void setSimNO(String simNO) {
		this.simNO = simNO;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFuelTank() {
		return fuelTank;
	}

	public void setFuelTank(String fuelTank) {
		this.fuelTank = fuelTank;
	}

	public double getGas() {
		if(this.gas!=0){
			return gas;
		}else{
			int x=20+(int)(Math.random()*30);
			return x;
		}
		
	}

	public void setGas(double gas) {
		this.gas = gas;
	}

	public String getFuleConsumption() {
		if(this.fuleConsumption!=null){
			return fuleConsumption;
		}else{
			
			int temp=(int) this.getGas();
			int tem=temp/(3+(int)(Math.random()*10));
			return tem+"";
		}
		
	}

	private void temp(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setFuleConsumption(String fuleConsumption) {
		this.fuleConsumption = fuleConsumption;
	}

}
