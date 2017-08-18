package cn.guangtong.service.cms;

import java.util.List;
import java.util.Map;

import cn.guangtong.model.VehicleMenu;

public interface AdminVehicleService {

	/**
	 * 获取车辆的级联菜单
	 * 
	 * @param adminid
	 * @return
	 */
	Map<String, Map<String, List<VehicleMenu>>> getVehicleMenu(Integer adminId);

	/**
	 * 权限操作，获取车辆级联列表
	 * 
	 * @param admin
	 * @return
	 */
	Map<String, Map<String, List<VehicleMenu>>> getVehicleMenuS(String adminId,String selCondition);

	/**
	 * 车辆列入监控列表
	 * 
	 * @param adminId
	 * @param vehicleArr
	 * @return
	 */
	boolean isRead(String adminId, String[] vehicleArr);

}
