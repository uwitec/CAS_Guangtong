package cn.guangtong.dao.beidou;

import java.util.List;

import cn.guangtong.model.PanelVehicle;

/**
 * 面板dao
 * 
 * @author SunTo
 * 
 */
public interface PanelDao {

	/**
	 * 普通用户
	 * 
	 * @param adminId
	 * @param state
	 * @return
	 */
	List<PanelVehicle> findVehicleByAdmin(String adminId);

	/**
	 * 超级管理员
	 * 
	 * @param state
	 * @return
	 */
	List<PanelVehicle> findVehicleByAdminS();
}
