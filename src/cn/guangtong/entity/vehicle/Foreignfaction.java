package cn.guangtong.entity.vehicle;

/**
 * 车辆外派实体
 * 
 * @author SunTo
 * 
 */
public class Foreignfaction {
	private Integer id;

	private String takecooperationid;

	private String receivedcooperationid;

	private String vehicleid;

	private String starttime;

	private String endtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTakecooperationid() {
		return takecooperationid;
	}

	public void setTakecooperationid(String takecooperationid) {
		this.takecooperationid = takecooperationid;
	}

	public String getReceivedcooperationid() {
		return receivedcooperationid;
	}

	public void setReceivedcooperationid(String receivedcooperationid) {
		this.receivedcooperationid = receivedcooperationid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}