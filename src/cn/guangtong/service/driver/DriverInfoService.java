package cn.guangtong.service.driver;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.controller.driver.DriverPageBean;
import cn.guangtong.entity.driver.DriverInfo;
import cn.guangtong.entity.driver.DriverRecord;
import cn.guangtong.excel.Driver;
import cn.guangtong.utils.DriverAuditPageBean;

public interface DriverInfoService {
	/**
	 * 查询司机总记录数
	 */
	Integer sDriverInfoCount(String adminId);

	/**
	 * 查询司机总记录数
	 */
	Integer sDriverInfoCountAll(DriverPageBean driverPageBean);

	/**
	 * 查询一条司机记录
	 */
	Map<String, Object> sDriverInfoOne(String id);

	/**
	 * 查询司机总记录数(超级管理员)
	 */
	Integer sDriverCount(DriverPageBean driverPageBean);

	/**
	 * 查询所有司机信息+分页+排序+模糊查询 (超级管理员)
	 */
	List<Map<String, Object>> sDriverInfoA(DriverPageBean driverPageBean);
	
	
	/**
	 * 查询司机总记录数(普通用户)
	 */
	Integer sDriverCountS(DriverPageBean driverPageBean);

	/**
	 * 查询所有司机信息+分页+排序+模糊查询 (普通用户)
	 */
	List<Map<String, Object>> sDriverInfo(DriverPageBean driverPageBean);

	/**
	 * 查询所有司机信息
	 */
	List<Driver> excelFul(DriverPageBean driverPageBean);

	/**
	 * 批量冻结解冻司机
	 */
	Integer uDriverInfoFreezing(String type, String employeeId);

	/**
	 * 查询全部的商家
	 */
	List<Map<String, Object>> sCooperationInfoA();

	/**
	 * 修改司机信息
	 */
	Integer uDriverInfo(DriverInfo driverInfo);

	/**
	 * 增加一名司机信息
	 */
	Integer iDriverInfo(DriverInfo deiverInfo);

	/**
	 * 驾驶证有效期统计
	 * 
	 * @return
	 */
	Map<String, Object> validCheck(String type, List<String> coopList);

	/**
	 * 分页查询驾驶员记录信息
	 * 
	 * @return
	 */
	public void driverOfTable(DriverPageBean pageBean);

	/**
	 * 驾驶员记录信息导出
	 * 
	 * @return
	 */
	public void driverOfTableExcel(DriverPageBean pageBean);

	/**
	 * 驾驶员记录数
	 * 
	 * @return
	 */
	public int countOfDriver(DriverPageBean pageBean);

	/**
	 * 新增驾驶员chart数据
	 * 
	 * @return
	 */
	public Map<String, Object> getNewDriverCount(@Param("startTime") String times, @Param("endTime") String timee, @Param("type") String type);

	/**
	 * 司机审核司机信息查询
	 * 
	 */
	public DriverAuditPageBean driverAuditSelect(DriverAuditPageBean driverAuditPageBean);

	/**
	 * 更新司机审核状态
	 */
	public Integer updateDriverAuditStatus(String id, String reviewStatus);

}
