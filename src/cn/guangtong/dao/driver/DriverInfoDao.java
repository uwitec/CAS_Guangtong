package cn.guangtong.dao.driver;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.controller.driver.DriverPageBean;
import cn.guangtong.entity.driver.DriverInfo;
import cn.guangtong.entity.driver.DriverRecord;
import cn.guangtong.excel.Driver;
import cn.guangtong.utils.DriverAuditPageBean;

public interface DriverInfoDao {
	/**
	 * 查询司机总记录数
	 */
	Integer sDriverInfoCount(@Param("adminId") String adminId);

	/**
	 * 查询司机总记录数
	 */
	Integer sDriverInfoCountAll(DriverPageBean driverPageBean);

	/**
	 * 查询一条司机记录
	 */
	Map<String, Object> sDriverInfoOne(@Param("id") String id);

	/**
	 * 查询司机总记录数(超级管理员)
	 */
	Integer sDriverCount(DriverPageBean driverPageBean);

	/**
	 * 查询所有司机信息+分页(超级管理员)
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
	Integer uDriverInfoFreezing(@Param("type") String type, @Param("employeeId") String employeeId);

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
	 * 驾驶证有效期统计 普通
	 * 
	 * @return
	 */
	public Map<String, Object> validCheckByOther(@Param("coopList") List<String> coopList, @Param("adminId") String adminId);

	/**
	 * 行驶证有效期统计 普通
	 * 
	 * @return
	 */
	public Map<String, Object> rankCheckByOther(@Param("coopList") List<String> coopList, @Param("adminId") String adminId);

	/**
	 * 从业资格证有效期统计 普通
	 * 
	 * @return
	 */
	public Map<String, Object> professionValidCheckByOther(@Param("coopList") List<String> coopList, @Param("adminId") String adminId);
	
	/**
	 * 驾驶证有效期统计 超管
	 * 
	 * @return
	 */
	public Map<String, Object> validCheck(@Param("coopList") List<String> coopList);
	
	/**
	 * 行驶证有效期统计 超管
	 * 
	 * @return
	 */
	public Map<String, Object> rankCheck(@Param("coopList") List<String> coopList);
	
	/**
	 * 从业资格证有效期统计 超管
	 * 
	 * @return
	 */
	public Map<String, Object> professionValidCheck(@Param("coopList") List<String> coopList);

	/**
	 *  驾驶员记录信息导出 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<DriverRecord> driverOfTableExcel(DriverPageBean pageBean);

	/**
	 *
	 * 分页查询驾驶员记录信息 普通
	 * @param pageBean
	 * @return
	 */
	public List<DriverRecord> driverOfTable(DriverPageBean pageBean);

	/**
	 * 驾驶员记录数 普通
	 * 
	 * @return
	 */
	public int countOfDriver(DriverPageBean pageBean);

	
	/**
	 *  驾驶员记录信息导出 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<DriverRecord> driverOfTableExcelSuperTube(DriverPageBean pageBean);

	/**
	 *
	 * 分页查询驾驶员记录信息超管
	 * @param pageBean
	 * @return
	 */
	public List<DriverRecord> driverOfTableSuperTube(DriverPageBean pageBean);

	/**
	 * 驾驶员记录数 超管
	 * 
	 * @return
	 */
	public int countOfDriverSuperTube(DriverPageBean pageBean);
	/**
	 * 新增驾驶员chart数据
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getNewDriverCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") String type, @Param("adminId") String adminId);

	/**
	 * 司机审核查询司机信息
	 */
	public List<Map<String, Object>> driverAuditSelect(DriverAuditPageBean driverAuditPageBean);

	/**
	 * 司机审核查询信息记录数
	 */
	public Integer countDriverAudit(DriverAuditPageBean driverAuditPageBean);

	/**
	 * 更新司机审核状态
	 * 
	 * @param map
	 */
	public Integer updateDriverAuditStatus(Map<String, Object> map);

}