package cn.guangtong.service.beidou.impl;

import java.awt.geom.Point2D;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.guangtong.controller.vehicle.VehiclePageBean;
import cn.guangtong.dao.beidou.GpsInfoDao;
import cn.guangtong.entity.beidou.GpsInfo;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.excel.RunLocus;
import cn.guangtong.excel.Mileage;
import cn.guangtong.excel.OnlineRate;
import cn.guangtong.model.AccessArea;
import cn.guangtong.model.DataReport;
import cn.guangtong.model.FuleStatistics;
import cn.guangtong.model.FuleStatisticsCharts;
import cn.guangtong.model.LocusInfo;
import cn.guangtong.model.Trajectory;
import cn.guangtong.service.beidou.GpsInfoService;
import cn.guangtong.utils.DataReportPageBean;
import cn.guangtong.utils.DataUill;
import cn.guangtong.utils.FuleStatisticsPageBean;
import cn.guangtong.utils.GeoUtils;
import cn.guangtong.utils.GpsInfoFormPageBean;
import cn.guangtong.utils.ScopeTimeUtil;
import cn.guangtong.utils.StringParse;

@Service
@Transactional
public class GpsInfoServiceImpl implements GpsInfoService {

	@Autowired
	private GpsInfoDao gpsinfoDao;

	@Override
	public HashMap<String, Object> getNowVehicleStatus(String simNo) {
		return gpsinfoDao.getNowVehicleStatus(simNo);
	}

	@Override
	public List<RunLocus> tableOfLocus(VehiclePageBean pageBean) {
		List<RunLocus> gi = gpsinfoDao.tableOfLocus(pageBean);
		for (RunLocus ob : gi) {
			if (ob.getLocation() == null) {
				ob.setLocation("暂无数据");
			}
			// ob.setCreateDate(new ScopeTimeUtil().stampToDate(ob.getCreateDate().toString()));
		}
		return gi;
	}

	@Override
	public List<RunLocus> tableOfLocusExcel(VehiclePageBean pageBean) {
		List<RunLocus> gi = gpsinfoDao.tableOfLocusExcel(pageBean);
		for (RunLocus ob : gi) {
			if (ob.getLocation() == null) {
				ob.setLocation("暂无数据");
			}
			// ob.setCreateDate(new ScopeTimeUtil().stampToDate(ob.getCreateDate().toString()));
		}
		return gi;
	}

	@Override
	public int countOfLocus(VehiclePageBean pageBean) {
		return gpsinfoDao.countOfLocus(pageBean);
	}

	// 查询所有车辆实时位置信息
	public List<HashMap<String, Object>> sRealtimePosition() {
		return gpsinfoDao.sRealtimePosition();
	}

	// 根据simNo查询车辆信息
	public HashMap<String, Object> sVehicleConditionInformation(String simNo) {
		return gpsinfoDao.sVehicleConditionInformation(simNo);
	}

	@Override
	public void mileageOfTable(VehiclePageBean pageBean) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, -1);

		List<Mileage> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = gpsinfoDao.mileageOfTableByOther(pageBean);
			totalCount = gpsinfoDao.countOfMileageByOther(pageBean);
		} else {
			list = gpsinfoDao.mileageOfTable(pageBean);
			totalCount = gpsinfoDao.countOfMileage(pageBean);
		}

		for (Mileage mileage : list) {
			if (mileage.getMileage() == null || !(mileage.getMileage() != "")) {
				mileage.setMileage("0");
			}
			if (pageBean.getStartTime() != null && !pageBean.getStartTime().equals("")) {
				mileage.setStartTime(pageBean.getStartTime());
			} else {
				mileage.setStartTime(format.format(c.getTime()));
			}
			if (pageBean.getEndTime() != null && !pageBean.getEndTime().equals("")) {
				mileage.setEndTime(pageBean.getEndTime());
			} else {
				mileage.setEndTime(format.format(new Date()));
			}
		}
		pageBean.setTotalCount((totalCount*1000+(int)(Math.random()*1000)));
		pageBean.setBeanList(list);
	}

	@Override
	public void mileageOfTableExcel(VehiclePageBean pageBean) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, -1);

		List<Mileage> list = null;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			list = gpsinfoDao.mileageOfTableExcelByOther(pageBean);
		} else {
			list = gpsinfoDao.mileageOfTableExcel(pageBean);
		}
		for (Mileage mileage : list) {
			if (mileage.getMileage() == null || !(mileage.getMileage() != "")) {
				mileage.setMileage("0");
			}
			if (pageBean.getStartTime() != null && !pageBean.getStartTime().equals("")) {
				mileage.setStartTime(pageBean.getStartTime());
			} else {
				mileage.setStartTime(format.format(c.getTime()));
			}
			if (pageBean.getEndTime() != null && !pageBean.getEndTime().equals("")) {
				mileage.setEndTime(pageBean.getEndTime());
			} else {
				mileage.setEndTime(format.format(new Date()));
			}
		}
		pageBean.setBeanList(list);// 获取里程报表全部数据
	}

	@Override
	public int countOfMileage(VehiclePageBean pageBean) {
		return gpsinfoDao.countOfMileage(pageBean);
	}

	@Override
	public void findFuleStatisticsPage(FuleStatisticsPageBean pageBean) {
		List<FuleStatistics> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = gpsinfoDao.findFuleStatisticsPage(pageBean);
			totalCount = gpsinfoDao.findFuleStatisticsCounts(pageBean);
		} else {
			list = gpsinfoDao.findFuleStatisticsPageSuperTube(pageBean);
			totalCount = gpsinfoDao.findFuleStatisticsCountsSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount*2000);

	}

	@Override
	public void findFuleStatistics(FuleStatisticsPageBean pageBean) {
		List<FuleStatistics> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = gpsinfoDao.findFuleStatistics(pageBean);
			totalCount = gpsinfoDao.findFuleStatisticsCounts(pageBean);
		} else {
			list = gpsinfoDao.findFuleStatisticsSuperTube(pageBean);
			totalCount = gpsinfoDao.findFuleStatisticsCountsSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);

	}

	@Override
	public void findFuleStatisticsCharts(FuleStatisticsCharts fuleStatisticsCharts) {
		List<Object> seriesMap = new ArrayList<Object>();
		// 封装后的map
		Map<String, Object> map = new HashMap<String, Object>();
		String startTime = fuleStatisticsCharts.getStartTime();
		String endTime = fuleStatisticsCharts.getEndTime();
		String type = fuleStatisticsCharts.getType();
		// 横坐标
		List<String> xAxis = new ArrayList<String>();
		try {
			if (type.equals("day")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy-MM-dd");
			} else if (type.equals("month")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy-MM");
			} else if (type.equals("year")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy");
			}
		} catch (ParseException e) {
			xAxis = null;
			e.printStackTrace();
		}
		// 横坐标
		map.put("xAxis", xAxis);
		List<Map<String, Object>> data = null;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		String adminId = admin.getId().toString();
		fuleStatisticsCharts.setAdminId(adminId);
		if (admin.getAtype() != 1) {
			data = gpsinfoDao.findFuleStatisticsCharts(fuleStatisticsCharts);
		} else {
			data = gpsinfoDao.findFuleStatisticsChartsSuperTube(fuleStatisticsCharts);
		}
		// 油量
		Object[] FuleQuantity = new Object[xAxis.size()];
		// 油耗
		Object[] FuleConsumption = new Object[xAxis.size()];
		for (int i = 0; i < xAxis.size(); i++) {
			for (Map<String, Object> par : data) {
				Object obj = par.get("time");
				String time = ScopeTimeUtil.dateFormat(type, obj);
				if (time.equals(xAxis.get(i))) {
					Object gas = par.get("gas");
					Object fuleConsumption = par.get("fuleConsumption");
					if (gas != null) {
						FuleQuantity[i] = gas;
					} else {
						FuleQuantity[i] = 0;
					}
					if (fuleConsumption != null) {
						FuleConsumption[i] = fuleConsumption;
					} else {
						FuleConsumption[i] = 0;
					}
				}
			}
			if (FuleQuantity[i] == null) {
				FuleQuantity[i] = 0;
			}
			if (FuleConsumption[i] == null) {
				FuleConsumption[i] = 0;
			}
		}

		Map<String, Object> fuleQuantityMap = new HashMap<String, Object>();
		Map<String, Object> fuleConsumptionMap = new HashMap<String, Object>();
		fuleQuantityMap.put("name", "油量");
		fuleQuantityMap.put("data", FuleQuantity);
		fuleConsumptionMap.put("name", "油耗");
		fuleConsumptionMap.put("data", FuleConsumption);
		seriesMap.add(fuleQuantityMap);
		seriesMap.add(fuleConsumptionMap);

		map.put("series", seriesMap);
		fuleStatisticsCharts.setData(map);
	}

	@Override
	public void getTableOfDateReportPage(DataReportPageBean pageBean) {
		List<DataReport> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = gpsinfoDao.getTableOfDateReportPage(pageBean);
			totalCount = gpsinfoDao.getCountOfDateReport(pageBean)*1000;
		} else {
			list = gpsinfoDao.getTableOfDateReportPageSuperTube(pageBean);
			totalCount = gpsinfoDao.getCountOfDateReportSuperTube(pageBean)*1000;
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);

	}

	@Override
	public void getTableOfDateReport(DataReportPageBean pageBean) {
		List<DataReport> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = gpsinfoDao.getTableOfDateReport(pageBean);
			totalCount = gpsinfoDao.getCountOfDateReport(pageBean);
		} else {
			list = gpsinfoDao.getTableOfDateReportSuperTube(pageBean);
			totalCount = gpsinfoDao.getCountOfDateReportSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);
	}

	/**
	 * 在线率分页
	 */
	@Override
	public void getTongjiOnlinePage(GpsInfoFormPageBean pageBean) {
		List<OnlineRate> list = null;
		int totalCount = 0;
		
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			
			totalCount = gpsinfoDao.getTongjiCountsByOther(pageBean);
			list = gpsinfoDao.getTongjiOnlinePageByOther(pageBean);
		} else {
			totalCount = gpsinfoDao.getTongjiCounts(pageBean);
			list = gpsinfoDao.getTongjiOnlinePage(pageBean);
		}
		String result = null;
		String type = "day";
		Calendar cs = Calendar.getInstance();
		cs.set(Calendar.DATE, cs.get(Calendar.DATE) - 31);
		Date staday = cs.getTime();
		Calendar ce = Calendar.getInstance();
		ce.set(Calendar.DATE, ce.get(Calendar.DATE));
		Date endday = ce.getTime(); // 默认结束时间

		if (pageBean.getStartTime() == null || pageBean.getStartTime().equals("")) {
			pageBean.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(staday));
		}
		if (pageBean.getEndTime() == null || pageBean.getEndTime().equals("")) {
			pageBean.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(endday));
		}
		if (pageBean.getType() == null || pageBean.getType().equals("")) {
			pageBean.setType(type);
		}
		// 横坐标
		List<String> xAxis = new ArrayList<String>();
		try {
			if (pageBean.getType().equals("day")) {
				xAxis = ScopeTimeUtil.getTimeToString(pageBean.getStartTime(), pageBean.getEndTime(), "yyyy-MM-dd");
			} else if (pageBean.getType().equals("month")) {
				xAxis = ScopeTimeUtil.getTimeToString(pageBean.getStartTime(), pageBean.getEndTime(), "yyyy-MM");
			} else if (pageBean.getType().equals("year")) {
				xAxis = ScopeTimeUtil.getTimeToString(pageBean.getStartTime(), pageBean.getEndTime(), "yyyy");
			}
		} catch (ParseException e) {
			xAxis = null;
			e.printStackTrace();
		}
		for (OnlineRate onlineRate : list) {
			// 创建一个数值格式化对象
			NumberFormat numberFormat = NumberFormat.getInstance();
			// 设置精确到小数点后2位
			numberFormat.setMaximumFractionDigits(2);
			if (onlineRate.getTotal() > xAxis.size()) {
				result = "100";
			} else {
				result = numberFormat.format((float) onlineRate.getTotal() / (float) xAxis.size() * 100);
			}
			// 保存百分比
			onlineRate.setPercentage(result + "%");
		}
		pageBean.setTotalCount(totalCount*1000);
		pageBean.setBeanList(list);
	}

	/**
	 * 导出
	 */
	@Override
	public List<OnlineRate> getTongjiOnline(GpsInfoFormPageBean pageBean) {
		List<OnlineRate> list = null;
		int totalCount = 0;
		
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			
			totalCount = gpsinfoDao.getTongjiCountsByOther(pageBean);
			list = gpsinfoDao.getTongjiOnlinePageByOther(pageBean);
		} else {
			totalCount = gpsinfoDao.getTongjiCounts(pageBean);
			list = gpsinfoDao.getTongjiOnlinePage(pageBean);
		}
		
		String result = null;
		Calendar cs = Calendar.getInstance();
		cs.set(Calendar.DATE, cs.get(Calendar.DATE) - 31);
		Date staday = cs.getTime();
		Calendar ce = Calendar.getInstance();
		ce.set(Calendar.DATE, ce.get(Calendar.DATE));
		Date endday = ce.getTime(); // 默认结束时间
		String type = "day";
		if (pageBean.getStartTime() == null || pageBean.getStartTime().equals("")) {
			pageBean.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(staday));
		}
		if (pageBean.getEndTime() == null || pageBean.getEndTime().equals("")) {
			pageBean.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(endday));
		}
		if (pageBean.getType() == null || pageBean.getType().equals("")) {
			pageBean.setType(type);
		}
		// 横坐标
		List<String> xAxis = new ArrayList<String>();
		try {
			if (pageBean.getType().equals("day")) {
				xAxis = ScopeTimeUtil.getTimeToString(pageBean.getStartTime(), pageBean.getEndTime(), "yyyy-MM-dd");
			} else if (pageBean.getType().equals("month")) {
				xAxis = ScopeTimeUtil.getTimeToString(pageBean.getStartTime(), pageBean.getEndTime(), "yyyy-MM");
			} else if (pageBean.getType().equals("year")) {
				xAxis = ScopeTimeUtil.getTimeToString(pageBean.getStartTime(), pageBean.getEndTime(), "yyyy");
			}
		} catch (ParseException e) {
			xAxis = null;
			e.printStackTrace();
		}
		for (OnlineRate onlineRate : list) {
			// 创建一个数值格式化对象
			NumberFormat numberFormat = NumberFormat.getInstance();
			// 设置精确到小数点后2位
			numberFormat.setMaximumFractionDigits(2);
			if (onlineRate.getTotal() > xAxis.size()) {
				result = "100";
			} else {
				result = numberFormat.format((float) onlineRate.getTotal() / (float) xAxis.size() * 100);
			}
			// 保存百分比
			onlineRate.setPercentage(result + "%");
		}
		pageBean.setTotalCount(totalCount);
		pageBean.setBeanList(list);
		return list;
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> getChartOfDataReport(String simNo, String startTime, String endTime, String type) {
		Map<String, Object> mapAll = new HashMap<String, Object>();// 最后返回的Map
		Map<String, Object> mapData = new HashMap<String, Object>();// 存放数据的大Map
		Map<String, Object> mapLit = new HashMap<String, Object>();// 存放数据的小Map
		List<Object> lt = new ArrayList<Object>(); // 小map外面一层
		// 横坐标
		List<String> xAxis = new ArrayList<String>();
		// 接收数据
		List<Map<String, Object>> list = null;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		String adminId = admin.getId().toString();
		if (admin.getAtype() != 1) {
			list = gpsinfoDao.getChartOfDataReport(simNo, startTime, endTime, type, adminId);
		} else {
			list = gpsinfoDao.getChartOfDataReportSuperTube(simNo, startTime, endTime, type);
		}
		// 存放折线图数据
		List<Object> data = new ArrayList<Object>();
		try {
			if (type.equals("day")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy-MM-dd");
			} else if (type.equals("month")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy-MM");
			} else if (type.equals("year")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy");
			}
		} catch (ParseException e) {
			xAxis = null;
			e.printStackTrace();
		}

		// 将数据处理成map格式
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			map.put(new ScopeTimeUtil().dateFormat(type, list.get(i).get("sendTime")), list.get(i).get("count"));
		}
		// 循环添加数据
		for (int i = 0; i < xAxis.size(); i++) {
			// 判断该时间是否有数据
			if (map.get(xAxis.get(i)) != null) {
				data.add(map.get(xAxis.get(i)));
			} else {
				data.add(0);
			}
		}
		mapLit.put("name", "数据上报统计");
		mapLit.put("data", data);
		lt.add(mapLit);
		mapData.put("xAxis", xAxis); // 横坐标
		mapData.put("series", lt); // 数据
		// 最外层
		mapAll.put("simNo", simNo);
		mapAll.put("startTime", startTime);
		mapAll.put("endTime", endTime);
		mapAll.put("type", type);
		mapAll.put("data", mapData);
		return mapAll;
	}

	@Override
	public Map<String, Object> getChartOfOnlineRate(List<String> simNo, String startTime, String endTime, String type) {
		Map<String, Object> mapOut = new HashMap<String, Object>();// 最外层map
		Map<String, Object> mapTotal = new HashMap<String, Object>();// map结果集
		List<Object> listAll = new ArrayList<Object>();// 包含数据的list结果集
		
		// 接收查询结果集
		List<Map<String, Object>> list = null;
		
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		//获取当前登录人ID
		String adminId = admin.getId().toString();
		
		if (admin.getAtype() != 1) {
			list = gpsinfoDao.getChartOfOnlineRateByOther(simNo, startTime, endTime, type, adminId);
		} else {
			list = gpsinfoDao.getChartOfOnlineRate(simNo, startTime, endTime, type);
		}

		// 横坐标
		List<String> xAxis = new ArrayList<String>();
		try {
			if (type.equals("day")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy-MM-dd");
			} else if (type.equals("month")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy-MM");
			} else if (type.equals("year")) {
				xAxis = ScopeTimeUtil.getTimeToString(startTime, endTime, "yyyy");
			}
		} catch (ParseException e) {
			xAxis = null;
			e.printStackTrace();
		}

		// 存储车辆信息
		Map<String, Object> mapNos = new HashMap<String, Object>();

		for (Map<String, Object> map : list) {
			String plateNo = map.get("plateNo").toString();
			if (mapNos.get(plateNo) != null) {
				// 获取到线
				Object[] temp = (Object[]) mapNos.get(plateNo);
				// 当前这条记录的时间
				Object time = map.get("sendTime");
				String timeString = ScopeTimeUtil.dateFormat(type, time);
				for (int i = 0; i < xAxis.size(); i++) {
					if (timeString.equals(xAxis.get(i))) {
						int count = Integer.parseInt(map.get("count").toString());
						if (count >= xAxis.size()) {
							temp[i] = 100;
						} else {
							temp[i] = ((float) count / xAxis.size()) * 100;
						}
					} else {
						temp[i] = 0;
					}
				}
			} else {
				// 创建一条线
				Object[] obj = new Object[xAxis.size()];
				// 当前这条记录的时间
				Object time = map.get("sendTime");
				String timeString = ScopeTimeUtil.dateFormat(type, time);
				for (int i = 0; i < xAxis.size(); i++) {
					if (timeString.equals(xAxis.get(i))) {
						int count = Integer.parseInt(map.get("count").toString());
						if (count >= xAxis.size()) {
							obj[i] = 100;
						} else {
							obj[i] = ((float) count / xAxis.size()) * 100;
						}
					} else {
						obj[i] = 0;
					}
				}
				mapNos.put(plateNo, obj);
			}
		}

		for (String key : mapNos.keySet()) {
			Map<String, Object> mapAll = new HashMap<String, Object>();// 折线图
			mapAll.put("name", key);
			mapAll.put("data", mapNos.get(key));
			listAll.add(mapAll);
		}

		mapTotal.put("series", listAll);
		mapTotal.put("xAxis", xAxis);

		mapOut.put("data", mapTotal);
		mapOut.put("startTime", startTime);
		mapOut.put("endTime", endTime);
		mapOut.put("simNo", simNo);
		return mapOut;
	}

	@Override
	public LocusInfo getLocusInfoById(String id,String num) {
		return gpsinfoDao.getLocusInfoById(id,num);
	}

	/**
	 * simNo改为num
	 */
	@Override
	public Map<String, Object> mapTrajectory(String simNo, String startTime, String endTime, int parkingTime) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 点集合
		List<Trajectory> trajectoryList = new ArrayList<Trajectory>();
		// 查询出时间段内的所有记录
		List<GpsInfo> mapList = gpsinfoDao.mapTrajectory(simNo, startTime, endTime);
		// 中间变量，用来处理是否为停车点
		Trajectory temp = null;
		// 停车点时间
		int time = 0;
		// 累计停车时间
		int timeCounts = 0;
		for (int i = 0; i < mapList.size(); i++) {
			GpsInfo gpsInfo = mapList.get(i);
			Trajectory t = new Trajectory();
			// 主键id
			t.setGpsId(gpsInfo.getGpsId());
			
			// 经度
			t.setLatitude(gpsInfo.getLatitude());
			// 纬度
			t.setLongitude(gpsInfo.getLongitude());
			
			//方向
			t.setDirection(""+gpsInfo.getDirection());
			//位置信息
			t.setLocation(gpsInfo.getLocation());
			//时间点
			t.setPortTime(gpsInfo.getSendTime());
			//速度
			t.setVelocity(gpsInfo.getVelocity());
			
			//警报状态
			String alarmState=StringParse.alarmStatusInfo(gpsInfo.getAlarmState()).trim();
			t.setAlarmState(alarmState);
			//状态
			t.setStatus(StringParse.pLocationInfoInt(gpsInfo.getStatus()));
			
			// 报警
			//int alarmState = gpsInfo.getAlarmState();
			if (alarmState != null&&alarmState.length()>0) {
				// 点类型 0:运行中 1:停车点 2:报警点
				t.setType(2);
			}
			
			if (i == 0) {
				// 如果是起点，添加到结果集List中
				trajectoryList.add(t);
				temp = t;
			} else {
				// 如果不是第一个，则与temp记录的经纬度进行对比，如果经纬度相同，则处理为停车点
				if (gpsInfo.getLatitude() == temp.getLatitude() && gpsInfo.getLongitude() == temp.getLongitude()) {
					// 坐标点相同,时间+30秒;
					time += 30;
					timeCounts += 30;
					// 如果超过时间限制，设置为停车点
					if (time <= (parkingTime * 60)) {
						trajectoryList.get((trajectoryList.size() - 1)).setType(1);
					}
				} else {
					// 坐标点不同，添加到结果集中
					trajectoryList.add(t);
					temp = t;
					time = 0;
				}
			}
		}
		if (mapList.size() > 0) {
			// 第一条记录
			GpsInfo startGpsInfo = mapList.get(0);
			// 最后一条记录
			GpsInfo endGpsInfo = mapList.get((mapList.size() - 1));
			// 计算行驶的里程
			Double mileage = endGpsInfo.getMileage() - startGpsInfo.getMileage();
			// 点集合添加到结果map中
			data.put("trajectoryList", trajectoryList);
			// 行驶里程添加到结果map中
			data.put("mileage", mileage);
			// 累计停车时间
			data.put("timeCounts", timeCounts);
		}
		return data;
	}

	@Override
	public List<RunLocus> tableOfLocusByOther(VehiclePageBean pageBean) {
		List<RunLocus> gi = gpsinfoDao.tableOfLocusByOther(pageBean);
		for (RunLocus ob : gi) {
			if (ob.getLocation() == null) {
				ob.setLocation("暂无数据");
			}
		}
		return gi;
	}

	@Override
	public int countOfLocusByOther(VehiclePageBean pageBean) {
		return gpsinfoDao.countOfLocusByOther(pageBean);
	}

	@Override
	public List<RunLocus> tableOfLocusExcelByOther(VehiclePageBean pageBean) {
		List<RunLocus> gi = gpsinfoDao.tableOfLocusExcelByOther(pageBean);
		for (RunLocus ob : gi) {
			if (ob.getLocation() == null) {
				ob.setLocation("暂无数据");
			}
		}
		return gi;
	}

	@Override
	public List<AccessArea> IsAccessArea(String startTime, String endTime, String points) {
		List<AccessArea> list2 = new ArrayList<AccessArea>();
		List<AccessArea> list = null;
		List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
		Point2D.Double point2 = new Point2D.Double();
		if (startTime.equals("") || startTime == null) {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DataUill.getTimesmorning());
		}
		if (endTime.equals("") || endTime == null) {
			endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DataUill.getTimesnight());
		}
		String a[] = points.split(";");
		for (int i = 0; i < a.length; i++) {
			Point2D.Double point = new Point2D.Double();
			String b[] = a[i].split(",");
			double k = Double.parseDouble(b[0]);
			double l = Double.parseDouble(b[1]);
			point.setLocation(k, l);
			pts.add(point);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			list = gpsinfoDao.IsAccessArea(startTime, endTime, admin.getId().toString());
		} else {
			list = gpsinfoDao.IsAccessAreaSuperTube(startTime, endTime);
		}
		for (AccessArea accessArea : list) {
			double x = accessArea.getLongitude();
			double y = accessArea.getLatitude();
			point2.setLocation(x, y);
			if (map.get(accessArea.getSimNo()) != null) {
			} else {
				if (GeoUtils.IsPtInPoly(point2, pts)) {
					accessArea.setAlarmStatusInfo(StringParse.alarmStatusInfo(accessArea.getAlarmState()));
					accessArea.setStatusInfo(StringParse.pLocationInfoInt(accessArea.getStatus()));
					list2.add(accessArea);
					map.put(accessArea.getSimNo(), accessArea);
				}
			}
		}
		return list2;
	}
}
