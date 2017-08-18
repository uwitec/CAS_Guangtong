package cn.guangtong.dao.vehicle;

import java.util.List;

import cn.guangtong.entity.vehicle.VehicleTeam;
import cn.guangtong.utils.VehicleTeamPageBean;

public interface VehicleTeamDao {

	int insert(VehicleTeam record);

	int update(VehicleTeam record);

	VehicleTeam getById(String id);
	/**
	 * 查询车队
	 * @param cooperationId
	 * @return
	 */
	VehicleTeam  getTeamId(String cooperationId);
	/**
	 * 通过企业id获取旗下所有车队
	 * 
	 * @param cooperationId
	 * @return
	 */
	List<VehicleTeam> getByCooperationId(String cooperationId);

	/**
	 * 分页获取当前用户所有的车队
	 * 
	 * @param pageBean
	 * @return
	 */
	List<VehicleTeam> getVehicleTeamByPageBean(VehicleTeamPageBean pageBean);

	/**
	 * 分页获取当前用户所有的车队总数
	 * 
	 * @param pageBean
	 * @return
	 */
	Integer getVehicleTeamByPageBeanCount(VehicleTeamPageBean pageBean);
	
	/**
	 * 分页获取所有的车队总数
	 * 
	 * @param pageBean
	 * @return
	 */
	Integer getAllVehicleTeamByPageBeanCount(VehicleTeamPageBean pageBean);
	
	/**
	 * 分页获取所有的车队
	 * 
	 * @param pageBean
	 * @return
	 */
	List<VehicleTeam> getAllVehicleTeamByPageBean(VehicleTeamPageBean pageBean);
}