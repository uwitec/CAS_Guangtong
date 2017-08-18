package cn.guangtong.service.vehicle;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.utils.ForeignfactionPageBean;

/**
 * 车辆外派
 * 
 * @author SunTo
 * 
 */
public interface ForeignfactionService {

	/**
	 * 添加车辆外派
	 * 
	 * @param cooperationId
	 * @param endtime
	 * @param vehArr
	 */
	void insertForeignfaction(String receivedcooperationid, String endtime, String[] vehArr);

	/**
	 * 车辆外派分页查询(普通用户)
	 * 
	 * @param pageBean
	 */
	void getForeignfactionByPageBean(ForeignfactionPageBean pageBean);
	
	/**
	 * 车辆外派分页查询(超级管理员)
	 * 
	 * @param pageBean
	 */
	void getForeignfactionSByPageBean(ForeignfactionPageBean pageBean);
	
	/**
	 * 撤销外派
	 * @param vehArr
	 */
	void delForeignfaction(String[] vehArr);
	
	/**
	 * 
	 * @param admin
	 * @param cooperationId 要排除的企业id
	 * @return
	 */
	Map<String, Map<String, List<VehicleMenu>>> getVehicle(Admin admin,String cooperationId);
}
