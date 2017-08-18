package cn.guangtong.service.beidou;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.beidou.GpsRealData;
import cn.guangtong.model.VehicleDetails;
import cn.guangtong.utils.ThelateststatePageBean;

public interface GpsRealDataService {
	/**
	 * 分页查询最新状态信息统计
	 * 
	 * @param pageBean
	 */
	public void findGpsRealData(ThelateststatePageBean pageBean);

	/**
	 * 最新状态信息统计导出
	 * 
	 * @param pageBean
	 */
	public void findGpsRealDataExcel(ThelateststatePageBean pageBean);

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
	 *  在线监控报警车辆
	 * @return
	 */
	public List<Map<String, Object>> onlineAlarmInformation();

}
