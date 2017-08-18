package cn.guangtong.dao.beidou;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.controller.vehicle.VehiclePageBean;
import cn.guangtong.entity.beidou.GpsInfo;
import cn.guangtong.excel.RunLocus;
import cn.guangtong.excel.Mileage;
import cn.guangtong.excel.OnlineRate;
import cn.guangtong.model.AccessArea;
import cn.guangtong.model.DataReport;
import cn.guangtong.model.FuleStatistics;
import cn.guangtong.model.FuleStatisticsCharts;
import cn.guangtong.model.LocusInfo;
import cn.guangtong.utils.DataReportPageBean;
import cn.guangtong.utils.FuleStatisticsPageBean;
import cn.guangtong.utils.GpsInfoFormPageBean;

public interface GpsInfoDao {

	HashMap<String, Object> getNowVehicleStatus(String simNo);

	// 查询所有车辆实时位置监控
	List<HashMap<String, Object>> sRealtimePosition();

	/**
	 * 油耗折线图 普通
	 * 
	 * @return
	 */
	List<Map<String, Object>> findFuleStatisticsCharts(FuleStatisticsCharts fuleStatisticsCharts);

	/**
	 * 油耗折线图 超级
	 * 
	 * @return
	 */
	List<Map<String, Object>> findFuleStatisticsChartsSuperTube(FuleStatisticsCharts fuleStatisticsCharts);

	/**
	 * 查询油耗。分页 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<FuleStatistics> findFuleStatisticsPage(FuleStatisticsPageBean pageBean);

	/**
	 * 导出查询油耗。分页 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<FuleStatistics> findFuleStatistics(FuleStatisticsPageBean pageBean);

	/**
	 * 查询油耗。分页总条数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int findFuleStatisticsCounts(FuleStatisticsPageBean pageBean);

	/**
	 * 查询油耗。分页 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<FuleStatistics> findFuleStatisticsPageSuperTube(FuleStatisticsPageBean pageBean);

	/**
	 * 导出查询油耗。分页 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<FuleStatistics> findFuleStatisticsSuperTube(FuleStatisticsPageBean pageBean);

	/**
	 * 查询油耗。分页总条数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int findFuleStatisticsCountsSuperTube(FuleStatisticsPageBean pageBean);

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

	/**
	 * 里程报表数据 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Mileage> mileageOfTableByOther(VehiclePageBean pageBean);

	/**
	 * 里程报表数据导出 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Mileage> mileageOfTableExcelByOther(VehiclePageBean pageBean);

	/**
	 * 里程报表记录数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int countOfMileageByOther(VehiclePageBean pageBean);

	/**
	 * 里程报表数据 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Mileage> mileageOfTable(VehiclePageBean pageBean);

	/**
	 * 里程报表数据导出 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Mileage> mileageOfTableExcel(VehiclePageBean pageBean);

	/**
	 * 里程报表记录数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int countOfMileage(VehiclePageBean pageBean);

	/**
	 * 数据上报统计表格分页 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<DataReport> getTableOfDateReportPage(DataReportPageBean pageBean);

	/**
	 * 数据上报统计表格 导出 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<DataReport> getTableOfDateReport(DataReportPageBean pageBean);

	/**
	 * 数据上报统计表格列表数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int getCountOfDateReport(DataReportPageBean pageBean);
	
	/**
	 * 在线总数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int getTongjiCountsByOther(GpsInfoFormPageBean pageBean);
	
	/**
	 * 在线率分页查询 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<OnlineRate> getTongjiOnlinePageByOther(GpsInfoFormPageBean pageBean);
	
	/**
	 * 导出上线率分页查询 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<OnlineRate> getTongjiOnlineByOther(GpsInfoFormPageBean pageBean);

	/**
	 * 数据上报统计表格分页 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<DataReport> getTableOfDateReportPageSuperTube(DataReportPageBean pageBean);

	/**
	 * 数据上报统计表格 导出 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<DataReport> getTableOfDateReportSuperTube(DataReportPageBean pageBean);

	/**
	 * 数据上报统计表格列表数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int getCountOfDateReportSuperTube(DataReportPageBean pageBean);

	/**
	 * 在线总数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int getTongjiCounts(GpsInfoFormPageBean pageBean);

	/**
	 * 在线率分页查询 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<OnlineRate> getTongjiOnlinePage(GpsInfoFormPageBean pageBean);

	/**
	 * 导出上线率分页查询 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<OnlineRate> getTongjiOnline(GpsInfoFormPageBean pageBean);

	/**
	 * 数据上报统计图 普通
	 * 
	 * @param simNo
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getChartOfDataReport(@Param("simNo") String simNo, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") String type, @Param("adminId") String adminId);

	/**
	 * 数据上报统计图 超管
	 * 
	 * @param simNo
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getChartOfDataReportSuperTube(@Param("simNo") String simNo, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") String type);

	/**
	 * 上线率统计图 普通
	 * 
	 * @param simNo
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getChartOfOnlineRateByOther(@Param("simNo") List simNo, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") String type, @Param("adminId") String adminId);
	
	/**
	 * 上线率统计图 超管
	 * 
	 * @param simNo
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getChartOfOnlineRate(@Param("simNo") List simNo, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") String type);

	HashMap<String, Object> sVehicleConditionInformation(String simNo);

	/**
	 * 地图轨迹查询
	 */
	List<GpsInfo> mapTrajectory(String simNo, String startTime, String endTime);
	/**
	 * 进出区域 普通
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AccessArea> IsAccessArea(@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("adminId") String adminId);
	/**
	 * 进出区域 超管
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<AccessArea> IsAccessAreaSuperTube(@Param("startTime")String startTime, @Param("endTime")String endTime);
} 
