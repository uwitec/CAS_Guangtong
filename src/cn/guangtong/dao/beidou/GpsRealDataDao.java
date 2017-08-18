package cn.guangtong.dao.beidou;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.beidou.GpsRealData;
import cn.guangtong.excel.LatestStatus;
import cn.guangtong.model.VehicleDetails;
import cn.guangtong.utils.TerminalPageBean;
import cn.guangtong.utils.ThelateststatePageBean;

public interface GpsRealDataDao {

	/**
	 * 获取所有在线的车辆状态
	 * 
	 * @return
	 */
	public List<GpsRealData> getRealDatasByOnline();

	/**
	 * 根据车牌号查询车辆信息
	 * 
	 * @return
	 */
	public GpsRealData getRealDatasByPlateNo(String plateNo);

	/**
	 * 根据SimNo精准查询车辆信息
	 * 
	 * @param plateNo
	 * @return
	 */
	public VehicleDetails getRealDatasBySimNo(String simNo);

	/**
	 * 根据SimNo模糊查询车辆信息
	 * 
	 * @param simNo
	 * @return
	 */
	public List<Map<String, Object>> getRealDatasByObsSimNo(String simNo);

	/**
	 * 在线监控报警车辆
	 * 
	 * @return
	 */
	public List<Map<String, Object>> onlineAlarmInformation();

	/**
	 * 分页查询最新状态报表 普通
	 * 
	 * @return
	 */
	public List<LatestStatus> findGpsRealData(ThelateststatePageBean pageBean);

	/**
	 * 最新状态报表导出 普通
	 * 
	 * @return
	 */
	public List<LatestStatus> findGpsRealDataExcel(ThelateststatePageBean pageBean);

	/**
	 * 分页查询最新状态报表总数 普通
	 * 
	 * @return
	 */
	public int findGpsRealDataCounts(ThelateststatePageBean pageBean);

	/**
	 * 分页查询最新状态报表 超管
	 * 
	 * @return
	 */
	public List<LatestStatus> findGpsRealDataSuperTube(ThelateststatePageBean pageBean);

	/**
	 * 最新状态报表导出 超管
	 * 
	 * @return
	 */
	public List<LatestStatus> findGpsRealDataExcelSuperTube(ThelateststatePageBean pageBean);

	/**
	 * 分页查询最新状态报表总数 超管
	 * 
	 * @return
	 */
	public int findGpsRealDataCountsSuperTube(ThelateststatePageBean pageBean);

	/**
	 * 获取所有的GpsRealData数据
	 * 
	 * @param simNo
	 * @return
	 */
	public List<Map<String, Object>> getRealDatas(TerminalPageBean pageBean);
}
