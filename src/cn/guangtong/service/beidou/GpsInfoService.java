package cn.guangtong.service.beidou;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.controller.vehicle.VehiclePageBean;
import cn.guangtong.excel.OnlineRate;
import cn.guangtong.excel.RunLocus;
import cn.guangtong.model.AccessArea;
import cn.guangtong.model.FuleStatisticsCharts;
import cn.guangtong.model.LocusInfo;
import cn.guangtong.utils.DataReportPageBean;
import cn.guangtong.utils.FuleStatisticsPageBean;
import cn.guangtong.utils.GpsInfoFormPageBean;

public interface GpsInfoService {

	// 根据simNo获取车辆实时信息
	HashMap<String, Object> getNowVehicleStatus(String simNo);

	/**
	 * 通过id查询运行轨迹信息
	 * 
	 * @param id
	 * @return
	 */
	LocusInfo getLocusInfoById(String id,String num);

	/**
	 * 运行轨迹报表 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<RunLocus> tableOfLocusByOther(VehiclePageBean pageBean);

	/**
	 * 运行轨迹报表总记录数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int countOfLocusByOther(VehiclePageBean pageBean);

	/**
	 * 运行轨迹报表导出 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<RunLocus> tableOfLocusExcelByOther(VehiclePageBean pageBean);

	/**
	 * 运行轨迹报表 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<RunLocus> tableOfLocus(VehiclePageBean pageBean);

	/**
	 * 运行轨迹报表导出 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<RunLocus> tableOfLocusExcel(VehiclePageBean pageBean);

	/**
	 * 运行轨迹报表总记录数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int countOfLocus(VehiclePageBean pageBean);

	// 查询所有车辆实时位置监控
	List<HashMap<String, Object>> sRealtimePosition();

	/**
	 * 油耗统计分页
	 * 
	 * @param pageBean
	 */
	void findFuleStatisticsPage(FuleStatisticsPageBean pageBean);

	/**
	 * 导出油耗统计
	 * 
	 * @param pageBean
	 */
	void findFuleStatistics(FuleStatisticsPageBean pageBean);

	/**
	 * 油耗统计折线图
	 * 
	 * @param SimNo
	 */
	void findFuleStatisticsCharts(FuleStatisticsCharts fuleStatisticsCharts);

	/**
	 * 里程报表数据
	 * 
	 * @param pageBean
	 * @return
	 */
	void mileageOfTable(VehiclePageBean pageBean);

	/**
	 * 里程报表数据导出
	 * 
	 * @param pageBean
	 * @return
	 */
	void mileageOfTableExcel(VehiclePageBean pageBean);

	/**
	 * 里程报表记录数
	 * 
	 * @param pageBean
	 * @return
	 */
	int countOfMileage(VehiclePageBean pageBean);

	/**
	 * 数据上报统计表格分页
	 * 
	 * @param pageBean
	 * @return
	 */
	void getTableOfDateReportPage(DataReportPageBean pageBean);

	/**
	 * 数据上报统计表格
	 * 
	 * @param pageBean
	 * @return
	 */
	void getTableOfDateReport(DataReportPageBean pageBean);

	/**
	 * 在线率分页查询
	 * 
	 * @param pageBean
	 * @return
	 */
	void getTongjiOnlinePage(GpsInfoFormPageBean pageBean);

	/**
	 * 导出上线率分页查询
	 * 
	 * @param pageBean
	 * @return
	 */
	List<OnlineRate> getTongjiOnline(GpsInfoFormPageBean pageBean);

	/**
	 * 数据上报统计图
	 * 
	 * @param simNo
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	Map<String, Object> getChartOfDataReport(@Param("simNo") String simNo, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") String type);

	/**
	 * 上线率统计图
	 * 
	 * @param simNo
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	Map<String, Object> getChartOfOnlineRate(@Param("simNo") List<String> simNo, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") String type);

	/**
	 * 地图轨迹查询
	 */
	Map<String, Object> mapTrajectory(String simNo, String startTime, String endTime, int parkingTime);
	/**
	 * 进出区域
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AccessArea> IsAccessArea(String startTime, String endTime,String points);
}
