package cn.guangtong.service.vehicle;

import java.util.List;

import cn.guangtong.entity.vehicle.VehicleTeam;
import cn.guangtong.utils.VehicleTeamPageBean;

/**
 * 车队管理
 * 
 * @author SunTo
 * 
 */
public interface VehicleTeamService {

	boolean insert(VehicleTeam record);

	boolean update(VehicleTeam record);

	/**
	 * 根据id获取实体
	 * 
	 * @param id
	 * @return
	 */
	VehicleTeam getById(String id);

	/**
	 * 根据企业id，查询旗下的所有车队
	 * 
	 * @param cooperationId
	 * @return
	 */
	List<VehicleTeam> getByCooperationId(String cooperationId);
	
	/**
	 * 通过adminid获取旗下的所有车队
	 * @param adminId
	 * @return
	 */
	void getVehicleTeamByPageBean(VehicleTeamPageBean pageBean);
	
	/**
	 * 获取所有的车队
	 * @param adminId
	 * @return
	 */
	void getAllVehicleTeamByPageBean(VehicleTeamPageBean pageBean);
}
