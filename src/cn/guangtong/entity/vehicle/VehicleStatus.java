package cn.guangtong.entity.vehicle;

public class VehicleStatus {
    private String vehicleid;
    private int vstatus;
    private double gpsy;
    private double gpsx;
    private String vihiclenum;
    private double remainbulk;
    
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public int getVstatus() {
		return vstatus;
	}
	public void setVstatus(int vstatus) {
		this.vstatus = vstatus;
	}
	public double getGpsy() {
		return gpsy;
	}
	public void setGpsy(double gpsy) {
		this.gpsy = gpsy;
	}
	public double getGpsx() {
		return gpsx;
	}
	public void setGpsx(double gpsx) {
		this.gpsx = gpsx;
	}
	public String getVihiclenum() {
		return vihiclenum;
	}
	public void setVihiclenum(String vihiclenum) {
		this.vihiclenum = vihiclenum;
	}
	public double getRemainbulk() {
		return remainbulk;
	}
	public void setRemainbulk(double remainbulk) {
		this.remainbulk = remainbulk;
	}
	public VehicleStatus(String vehicleid, int vstatus, double gpsy,
			double gpsx, String vihiclenum, double remainbulk) {
		super();
		this.vehicleid = vehicleid;
		this.vstatus = vstatus;
		this.gpsy = gpsy;
		this.gpsx = gpsx;
		this.vihiclenum = vihiclenum;
		this.remainbulk = remainbulk;
	}
	public VehicleStatus() {
		super();
	}
}