package cn.guangtong.dao.vehicle;

import java.util.List;

import cn.guangtong.entity.vehicle.Foreignfaction;
import cn.guangtong.model.ForeignfactionModel;
import cn.guangtong.utils.ForeignfactionPageBean;

public interface ForeignfactionDao {
	int delete(String vehicleId);

	int insert(Foreignfaction record);

	/**
	 * 车辆外派分页查询(普通用户)
	 * 
	 * @param pageBean
	 */
	List<ForeignfactionModel> getForeignfactionByPageBean(ForeignfactionPageBean pageBean);

	/**
	 * 车辆外派总数(普通用户)
	 * 
	 * @param pageBean
	 */
	Integer getForeignfactionByPageBeanCount(ForeignfactionPageBean pageBean);

	/**
	 * 车辆外派分页查询(超级管理员)
	 * 
	 * @param pageBean
	 */
	List<ForeignfactionModel> getForeignfactionSByPageBean(ForeignfactionPageBean pageBean);

	/**
	 * 车辆外派总数(超级管理员)
	 * 
	 * @param pageBean
	 */
	Integer getForeignfactionSByPageBeanCount(ForeignfactionPageBean pageBean);

}