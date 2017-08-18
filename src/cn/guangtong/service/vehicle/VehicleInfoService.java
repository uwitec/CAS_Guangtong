package cn.guangtong.service.vehicle;

import java.util.List;
import java.util.Map;

import cn.guangtong.controller.vehicle.VehiclePageBean;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.vehicle.TotalOfVehicle;
import cn.guangtong.entity.vehicle.VehicleInfo;
import cn.guangtong.model.CooperationVehicle;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.utils.PageBean;

public interface VehicleInfoService {

	/**
	 * 查询车辆信息+分页+排序
	 */
	List<Map<String, Object>> sVehicleInfoA(@SuppressWarnings("rawtypes") VehiclePageBean pageBean);

	/**
	 * 导出Excal
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
	 * 查询一条司机记录
	 */
	VehicleInfo sVehicleInfoOne(String id);

	/**
	 * 批量冻结解冻司机
	 */
	Integer uVehicleInfoFreezing(String type, String employeeId);

	/**
	 * 添加一个车辆
	 */
	Integer iVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 更新一个车辆
	 */
	Integer uVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 返回车辆类型
	 */
	List<Map<String, Object>> sVehicleInfoMold();

	/**
	 * 返回所属业务
	 */
	List<Map<String, Object>> getSpecialtype();

	/**
	 * 返回企业编号
	 */
	List<Map<String, Object>> sVehicleInfoEnterpriseNumber(Integer adminId);

	/**
	 * 返回全部的企业编号
	 */
	List<Map<String, Object>> sAllVehicleInfoEnterpriseNumber();

	/**
	 * 订单车辆运营统计
	 * 
	 * @return
	 */
	List<TotalOfVehicle> getTotalOfVehicle(VehiclePageBean pageBean);

	/**
	 * 总记录数
	 * 
	 * @param pageBean
	 * @return
	 */
	public TotalOfVehicle getCountOfTotal(VehiclePageBean pageBean);

	/**
	 * 处理车辆运营订单金额统计的json
	 * 
	 * @return
	 */
	Map<String, Object> chartsOfTotal(String times, String timee, String type);

	/**
	 * 根据用户查询，可见的在线车辆最新状态
	 * 
	 * @param admin
	 * @return
	 */
	Map<String, Map<String, List<VehicleMenu>>> findVehicleByAdmin(Admin admin);

	/**
	 * 根据用户查询，可见的车辆(普通用户)
	 * 
	 * @param admin
	 * @return
	 */
	Map<String, Map<String, List<VehicleMenu>>> getVehicleByAdmin(Admin admin);

	/**
	 * 根据用户查询，可见的车辆(超级管理员)
	 * 
	 * @param admin
	 * @return
	 */
	Map<String, Map<String, List<VehicleMenu>>> getVehicleSByAdmin();

	/**
	 * 根据企业id查询全部车辆
	 * 
	 * @param cooperationId
	 * @return
	 */
	List<String> queryVehicleByCoopId(List coopArr);

	/**
	 * 根据用户和车辆营运类型，查询可见的在线车辆
	 * 
	 * @param admin
	 * @param specialType
	 * @return
	 */
	Map<String, Map<String, List<VehicleMenu>>> findVehicleByAdminAndSpecialType(Admin admin, String specialType);

	/**
	 * 查询车辆总记录数(超级管理员)
	 */
	Integer sVehicleCountS(VehiclePageBean vehiclePageBean);

	/**
	 * 查询车辆信息+分页+排序(超级管理员)
	 */
	List<Map<String, Object>> sVehicleInfo(@SuppressWarnings("rawtypes") VehiclePageBean pageBean);

	/**
	 * 车辆列入监控范围(超级管理员)
	 * @param vehicleArr
	 * @return
	 */
	boolean isRead(String[] vehicleArr);
	/**
	 * 车辆运输能力分析饼状图
	 * @return
	 */
	Map<String, Object> getVehiclesPie(String type, String name);

}
