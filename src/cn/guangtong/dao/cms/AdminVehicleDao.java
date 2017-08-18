package cn.guangtong.dao.cms;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.cms.AdminVehicle;
import cn.guangtong.entity.vehicle.VehicleTeam;
import cn.guangtong.model.VehicleMenu;

public interface AdminVehicleDao {

	int insert(AdminVehicle record);

	int deleteByAdminId(Integer adminId);

	/**
	 * 根据关联表查出车辆的信息--根据账户id
	 * 
	 * @param adminId
	 * @return
	 */
	List<VehicleMenu> getVehicleMenuByAdminId(Integer adminId);

	/**
	 * 权限操作，获取到的车辆列表
	 * 
	 * @param adminId
	 * @return
	 */
	List<VehicleMenu> getVehicleMenuS(String adminId,String selCondition);

	int update(AdminVehicle record);

	/**
	 * 根据adminid和车辆id查找关联对象
	 * 
	 * @param adminId
	 * @param vehicleId
	 * @return
	 */
	AdminVehicle getByadminIdAndVehicelId(String adminId, String vehicleId);
	
	/**
	 * 根据车辆id查询车辆关联的全部账号
	 * @param vehicleId
	 * @return
	 */
	List<String> getAdminIdByVehicleId(String vehicleId);
	
	/**
	 * 根据账户id和车辆id删除该条关联
	 * @param adminId
	 * @param vehicleId
	 * @return
	 */
	int deleteByAdminIdAndvehicleId(String adminId, String vehicleId);

	/**
	 * 将账号绑定的所有车辆的isread全部设置为0
	 * @param adminId
	 * @return
	 */
	int upNoIsRead(String adminId);
	
	/**
	 * 将账号指定的某辆车isread设置为1
	 * @param adminId
	 * @param vehicleId
	 * @return
	 */
	int upIsRead(String adminId,String vehicleId);
}