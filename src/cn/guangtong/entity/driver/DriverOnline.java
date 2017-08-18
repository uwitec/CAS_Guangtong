package cn.guangtong.entity.driver;

import java.util.Date;
/**
 * 在线司机实体类
 * @author sutong
 *
 */
public class DriverOnline {
    private String driverid;
    private String vehicleid;
    private Date onlinetime; //上线时间
    private int driverstatus; //上线状态（0:下线；1：上线；2：已上车；3：在途;）
    private int driveravailable; //司机可用状态（1:正常；0：冻结）
    private int cooprationavailable; // 企业可用状态（1：正常：0：冻结）
    private double vtotalcapacity;
    private double vremaincapacity;
    private String vehiclemodel;
    private int receiveautosend; //是否接收自动派单(0:不接收;1:接收)
    private double longitude;
    private double latitude;
    private String positionupdatetime;
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public Date getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(Date onlinetime) {
		this.onlinetime = onlinetime;
	}
	public int getDriverstatus() {
		return driverstatus;
	}
	public void setDriverstatus(int driverstatus) {
		this.driverstatus = driverstatus;
	}
	public int getDriveravailable() {
		return driveravailable;
	}
	public void setDriveravailable(int driveravailable) {
		this.driveravailable = driveravailable;
	}
	public int getCooprationavailable() {
		return cooprationavailable;
	}
	public void setCooprationavailable(int cooprationavailable) {
		this.cooprationavailable = cooprationavailable;
	}
	public double getVtotalcapacity() {
		return vtotalcapacity;
	}
	public void setVtotalcapacity(double vtotalcapacity) {
		this.vtotalcapacity = vtotalcapacity;
	}
	public double getVremaincapacity() {
		return vremaincapacity;
	}
	public void setVremaincapacity(double vremaincapacity) {
		this.vremaincapacity = vremaincapacity;
	}
	public String getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(String vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public int getReceiveautosend() {
		return receiveautosend;
	}
	public void setReceiveautosend(int receiveautosend) {
		this.receiveautosend = receiveautosend;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getPositionupdatetime() {
		return positionupdatetime;
	}
	public void setPositionupdatetime(String positionupdatetime) {
		this.positionupdatetime = positionupdatetime;
	}
	public DriverOnline(String driverid, String vehicleid, Date onlinetime,
			int driverstatus, int driveravailable, int cooprationavailable,
			double vtotalcapacity, double vremaincapacity, String vehiclemodel,
			int receiveautosend, double longitude, double latitude,
			String positionupdatetime) {
		super();
		this.driverid = driverid;
		this.vehicleid = vehicleid;
		this.onlinetime = onlinetime;
		this.driverstatus = driverstatus;
		this.driveravailable = driveravailable;
		this.cooprationavailable = cooprationavailable;
		this.vtotalcapacity = vtotalcapacity;
		this.vremaincapacity = vremaincapacity;
		this.vehiclemodel = vehiclemodel;
		this.receiveautosend = receiveautosend;
		this.longitude = longitude;
		this.latitude = latitude;
		this.positionupdatetime = positionupdatetime;
	}
	public DriverOnline() {
		super();
	}
}