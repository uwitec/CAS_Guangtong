package cn.guangtong.dao.gpsdb;

import cn.guangtong.model.VehicleDetails;

public interface GpsDBDao {
	VehicleDetails getRealDatasByplateNo(String plateNo);
	
	/**
	 * 将指定的某辆车isread设置为1(超级管理员)
	 * @param adminId
	 * @param vehicleId
	 * @return
	 */
	int upIsRead(String vehicle);
	
	int upNoIsRead();
}
