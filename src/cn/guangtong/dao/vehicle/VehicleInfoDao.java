package cn.guangtong.dao.vehicle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.controller.vehicle.VehiclePageBean;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.vehicle.TotalOfVehicle;
import cn.guangtong.entity.vehicle.VehicleInfo;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.model.VehicleRealData;
import cn.guangtong.utils.PageBean;

public interface VehicleInfoDao {
	/**
	 * 查询车辆信息+分页+排序
	 */
	List<Map<String, Object>> sVehicleInfoA(@SuppressWarnings("rawtypes") VehiclePageBean pageBean);

	/**
	 * 导出Excal普通
	 */
	List<cn.guangtong.excel.Vehicle> queryVehicleExcal(@SuppressWarnings("rawtypes") PageBean pageBean);
	
	/**
	 * 导出Excal超管
	 */
	List<cn.guangtong.excel.Vehicle> queryVehicleExcalByAdmin(@SuppressWarnings("rawtypes") PageBean pageBean);

	/**
	 * 查询车辆总记录数
	 */
	Integer sVehicleCount(VehiclePageBean vehiclePageBean);

	/**
	 * 根据车辆id 查询车辆信息
	 */
	VehicleInfo sVehicleInfoOne(@Param("id") String id);

	/**
	 * 返回车辆类型
	 */
	List<Map<String, Object>> sVehicleInfoMold();

	/**
	 * 返回特别运输类型
	 */
	List<Map<String, Object>> getSpecialtype();

	/**
	 * 返回企业编号
	 */
	List<Map<String, Object>> sVehicleInfoEnterpriseNumber(Integer adminId);

	/**
	 * 返回全部企业编号
	 */
	List<Map<String, Object>> sAllVehicleInfoEnterpriseNumber();

	/**
	 * 批量冻结解冻司机
	 */
	Integer uVehicleInfoFreezing(@Param("type") String type, @Param("employeeId") String employeeId);

	/**
	 * 添加一个车辆
	 */
	Integer iVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 更新一个车辆
	 */
	Integer uVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 订单车辆运营报表
	 * 
	 * @return
	 */
	public List<TotalOfVehicle> getTotalOfVehicle(VehiclePageBean pageBean);

	/**
	 * 总记录数
	 * 
	 * @param pageBean
	 * @return
	 */
	public TotalOfVehicle getCountOfTotal(VehiclePageBean pageBean);

	/**
	 * 每月平均值
	 * 
	 * @return
	 */
	public List<Map<String, Object>> avgOfMonth(@Param("times") String times, @Param("timee") String timee, @Param("type") String type);

	/**
	 * 车队总金额
	 * 
	 * @return
	 */
	public List<Map<String, Object>> totalMoney(@Param("times") String times, @Param("timee") String timee);

	/**
	 * 各个车队每个月的金额
	 * 
	 * @return
	 */
	public List<Map<String, Object>> detailOfMonth(@Param("times") String times, @Param("timee") String timee, @Param("type") String type);

	/**
	 * 根据企业Id查询，旗下所有在线的车辆信息
	 * 
	 * @param cooperationId
	 * @return
	 */
	List<Map<String, Object>> findVehicleByCooperationId(String cooperationId);

	/**
	 * 根据企业Id查询，旗下所有的车辆信息
	 * 
	 * @param cooperationId
	 * @return
	 */
	List<Map<String, Object>> getVehicleByCooperationId(String cooperationId);

	/**
	 * 根据企业id和车辆运营类型
	 * 
	 * @param cooperationId
	 * @param specialType
	 * @return
	 */
	List<Map<String, Object>> findVehicleByAdminAndSpecialType(String cooperationId, String specialType);

	/**
	 * 根据企业id查询全部车辆
	 * 
	 * @param cooperationId
	 * @return
	 */
	List<String> queryVehicleByCoopId(@Param("coopArr") List coopArr);

	/**
	 * 查询车辆信息+分页+排序(超级管理员)
	 */
	List<Map<String, Object>> sVehicleInfo(@SuppressWarnings("rawtypes") VehiclePageBean pageBean);

	/**
	 * 查询车辆总记录数(超级管理员)
	 */
	Integer sVehicleCountS(VehiclePageBean vehiclePageBean);

	/**
	 * 根据车辆id查询所属企业Id
	 * 
	 * @param vehicleId
	 * @return
	 */
	String getCooperationidByVehicelId(String vehicleId);

	/**
	 * 更新车辆外派状态
	 */
	void upisforeignfaction(Map map);

	/**
	 * 所有可见车辆(普通用户)
	 */
	List<VehicleMenu> getVehicleByAdmin(Admin admin);

	/**
	 * 所有可见车辆(超级管理员)
	 */
	List<VehicleMenu> getVehicleSByAdmin();
	
	/**
	 * 所有可见车辆最新状态(普通用户)
	 */
	List<VehicleRealData> getVehicleRealDataByAdmin(Admin admin);

	/**
	 * 所有可见车辆最新状态(超级管理员)
	 */
	List<VehicleRealData> getVehicleRealDataSByAdmin();
	/**
	 * 特别运输可见车辆最新状态(普通用户)
	 */
	List<VehicleRealData> getVehicleRealDataByAdminAndType(String type,String adminId);

	/**
	 * 特别运输可见车辆最新状态(超级管理员)
	 */
	List<VehicleRealData> getVehicleRealDataSByAdminAndType(String type);
	
	
	/**
	 * 将所有车辆的isread全部设置为0(超级管理员)
	 * @param adminId
	 * @return
	 */
	int upNoIsRead();
	

	/**
	 * 将指定的某辆车isread设置为1(超级管理员)
	 * @param adminId
	 * @param vehicleId
	 * @return
	 */
	int upIsRead(String vehicle);
	
	/**
	 * 车辆外派所有可见车辆(普通用户，排除选中的企业)
	 */
	List<VehicleMenu> getForeignfactionVehicle(String adminId,String cooperationId);

	/**
	 * 车辆外派所有可见车辆(超级管理员,排除选中企业)
	 */
	List<VehicleMenu> getForeignfactionVehicleS(String cooperationId);
	/**
	 * 车辆运输能力分析饼状图
	 * @return
	 */
	List<Map<String, Object>> getVehiclesPie();
}
