package cn.guangtong.service.vehicle.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.controller.vehicle.VehiclePageBean;
import cn.guangtong.dao.beidou.VehicleDao;
import cn.guangtong.dao.cms.AdminDao;
import cn.guangtong.dao.cms.AdminVehicleDao;
import cn.guangtong.dao.cooperation.CooperationInfoDao;
import cn.guangtong.dao.gpsdb.GpsDBDao;
import cn.guangtong.dao.vehicle.VehicleInfoDao;
import cn.guangtong.dao.vehicle.VehicleTeamDao;
import cn.guangtong.entity.beidou.Vehicle;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.vehicle.TotalOfVehicle;
import cn.guangtong.entity.vehicle.VehicleInfo;
import cn.guangtong.entity.vehicle.VehicleTeam;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.model.VehicleRealData;
import cn.guangtong.service.vehicle.VehicleInfoService;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.ScopeTimeUtil;
import cn.guangtong.utils.StringParse;

@Service
@Transactional
public class VehicleInfoServiceImpl implements VehicleInfoService {

	@Autowired
	private VehicleInfoDao vehicleInfoDao;
	
	@Autowired
	private VehicleTeamDao vehicleTeamDao;
	
	@Autowired
	private CooperationInfoDao cooperationInfoDao;

	@Autowired
	private VehicleDao vehilceDao;

	@Autowired
	private AdminVehicleDao adminVehicledao;

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private GpsDBDao gpsDBDao;

	public VehicleInfoDao getVehicleInfoDao() {
		return vehicleInfoDao;
	}

	// 车辆信息+分页+排序
	public List<Map<String, Object>> sVehicleInfoA(@SuppressWarnings("rawtypes") VehiclePageBean pageBean) {
		return getVehicleInfoDao().sVehicleInfoA(pageBean);
	}

	// 查询车辆总记录数
	public Integer sVehicleCount(VehiclePageBean pageBean) {
		return getVehicleInfoDao().sVehicleCount(pageBean);
	}

	// 批量冻结+解冻
	public Integer uVehicleInfoFreezing(String type, String employeeId) {
		return getVehicleInfoDao().uVehicleInfoFreezing(type, employeeId);
	}

	// 插入一个车辆信息
	public Integer iVehicleInfo(VehicleInfo vehicleInfo) {
		try {
			if (vehicleInfo.getTeamId().equals("") || vehicleInfo.getTeamId() == null) {
				VehicleTeam vehicleTeam = vehicleTeamDao.getTeamId(vehicleInfo.getCooperationId());
				vehicleInfo.setTeamId(vehicleTeam.getId());
				// 往车辆库里面添加一条车辆信息
				getVehicleInfoDao().iVehicleInfo(vehicleInfo);
			} else {
				// 往车辆库里面添加一条车辆信息
				getVehicleInfoDao().iVehicleInfo(vehicleInfo);
			}
			// 创建北斗库的车辆对象
			Vehicle vehicle = new Vehicle();
			vehicle.setSimNo(vehicleInfo.getSimNo());
			vehicle.setPlateNo(vehicleInfo.getNum());
			vehilceDao.insert(vehicle);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}

	}

	// 更新一个车辆信息
	public Integer uVehicleInfo(VehicleInfo vehicleInfo) {
		try {
			// 更新车辆库的车辆信息
			getVehicleInfoDao().uVehicleInfo(vehicleInfo);
			// 根据simno卡号查询北斗库的车辆信息
			Vehicle vehicle = vehilceDao.getVehiclebySimNo(vehicleInfo.getSimNo());

			if (vehicle == null) {
				// 如果为空，则说明，修改了simNo.则，重新添加一条北斗的车辆信息
				Vehicle temp = new Vehicle();
				temp.setSimNo(vehicleInfo.getSimNo());
				temp.setPlateNo(vehicleInfo.getNum());
				vehilceDao.insert(temp);
			} else if (!(vehicle.getPlateNo().equals(vehicleInfo.getNum()))) {
				// 车牌号不一致
				vehicle.setPlateNo(vehicleInfo.getNum());
				vehilceDao.update(vehicle);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
	}

	// 根据ID查询一个车辆
	public VehicleInfo sVehicleInfoOne(String id) {
		return getVehicleInfoDao().sVehicleInfoOne(id);
	}

	// 返回车辆类型
	public List<Map<String, Object>> sVehicleInfoMold() {
		return getVehicleInfoDao().sVehicleInfoMold();
	}

	// 返回特别运输类型
	public List<Map<String, Object>> getSpecialtype() {
		return getVehicleInfoDao().getSpecialtype();
	}

	// 返回企业编号
	public List<Map<String, Object>> sVehicleInfoEnterpriseNumber(Integer adminId) {
		return getVehicleInfoDao().sVehicleInfoEnterpriseNumber(adminId);
	}

	// 返回全部企业编号
	@Override
	public List<Map<String, Object>> sAllVehicleInfoEnterpriseNumber() {
		return getVehicleInfoDao().sAllVehicleInfoEnterpriseNumber();
	}

	@Override
	public Map<String, Object> chartsOfTotal(String times, String timee, String type) {
		// chart图需要的json
		Map<String, Object> allOfMonth = new HashMap<String, Object>();
		// 存放数据的list
		List<Object> dataList = new ArrayList<Object>();
		// 全部运营时间
		List<Object> timeAll = new ArrayList<Object>();
		try {
			if (type.equals("day")) {
				timeAll = new ScopeTimeUtil().getTime(times, timee, "yyyy-MM-dd");
			} else if (type.equals("month")) {
				timeAll = new ScopeTimeUtil().getTime(times, timee, "yyyy-MM");
			} else if (type.equals("year")) {
				timeAll = new ScopeTimeUtil().getTime(times, timee, "yyyy");
			}
		} catch (ParseException e) {
			timeAll = null;
			e.printStackTrace();
		}
		// Collections.sort(times);
		// 每月平均值（折线图数据）
		List<Map<String, Object>> months = vehicleInfoDao.avgOfMonth(times, timee, type);
		// 车队总金额(饼状图数据)
		List<Map<String, Object>> tlist = vehicleInfoDao.totalMoney(times, timee);
		// 各个车队每个月的金额（柱状图数据）
		List<Map<String, Object>> detail = vehicleInfoDao.detailOfMonth(times, timee, type);

		// 处理每月平均金额（折线图）
		Map<String, Object> mapOfMonth = new HashMap<String, Object>();

		Map<String, Object> mapOfOther = new HashMap<String, Object>();// 折线图内的属性
		mapOfOther.put("lineWidth", 2);
		mapOfOther.put("lineColor", "Highcharts.getOptions().colors[3]");
		mapOfOther.put("fillColor", "white");

		List<Object> alist = new ArrayList<Object>(); // 折线图数据
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (int i = 0; i < months.size(); i++) {
			map2.put(new ScopeTimeUtil().dateFormat(type, months.get(i).get("month")), months.get(i).get("avgMoney"));
		}
		for (int i = 0; i < timeAll.size(); i++) {
			if (map2.get(timeAll.get(i)) != null) {
				alist.add(map2.get(timeAll.get(i)));
			} else {
				alist.add(0);
			}
		}

		mapOfMonth.put("name", "平均值");
		mapOfMonth.put("type", "spline");
		mapOfMonth.put("marker", mapOfOther);
		mapOfMonth.put("data", alist);

		// 处理每个车队总金额(饼状图)
		Map<String, Object> mapTotalMoney = new HashMap<String, Object>();

		mapTotalMoney.put("center", new Integer[] { 100, 80 });
		mapTotalMoney.put("name", "Money");
		mapTotalMoney.put("data", tlist);
		mapTotalMoney.put("type", "pie");
		mapTotalMoney.put("size", 100);

		// 处理每个车队每个月的金额
		List<VehicleTeam> list = new ArrayList<VehicleTeam>();
		for (int i = 0; i < detail.size(); i++) {
			VehicleTeam tov = new VehicleTeam();
			Map<String, Object> map = new HashMap<String, Object>();
			tov.settName(detail.get(i).get("tName").toString());
			for (int j = 0; j < detail.size(); j++) {
				if (detail.get(j).get("tName").toString().equals(tov.gettName())) {
					map.put((new ScopeTimeUtil().dateFormat(type, detail.get(j).get("month"))).toString(), detail.get(j).get("money"));
					tov.setMap(map);
					detail.remove(j);
					j--;
				}
			}
			list.add(tov);
			i--;
		}

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> dtmap = new HashMap<String, Object>();
			List<Object> dtlist = new ArrayList<Object>(); // 柱状图数据
			for (int j = 0; j < timeAll.size(); j++) {
				if (list.get(i).getMap().get(timeAll.get(j)) != null) {
					dtlist.add((list.get(i).getMap().get(timeAll.get(j))));
				} else {
					dtlist.add(0);
				}
			}
			dtmap.put("name", tlist.get(i).get("name").toString());
			dtmap.put("type", "column");
			dtmap.put("data", dtlist);
			dataList.add(dtmap); // 封装柱状图数据
		}
		dataList.add(mapOfMonth); // 封装折线图数据
		dataList.add(mapTotalMoney);// 封装饼状图数据

		Map<String, Object> other = new HashMap<String, Object>(); // 存放其他信息
		other.put("text", "车辆运营统计");
		other.put("html", "金额统计");
		other.put("xAxis", timeAll);

		allOfMonth.put("data", dataList); // 再次封装数据
		allOfMonth.put("other", other);

		return allOfMonth;
	}

	/**
	 * 订单报表分页
	 */
	@Override
	public List<TotalOfVehicle> getTotalOfVehicle(VehiclePageBean pageBean) {
		List<TotalOfVehicle> tov = vehicleInfoDao.getTotalOfVehicle(pageBean);
		for (TotalOfVehicle ob : tov) {
			String stu = ob.getOrderStatus();
			if (stu.equals("0")) {
				ob.setOrderStatus("待结算");
			} else if (stu.equals("1")) {
				ob.setOrderStatus("完成");
			} else if (stu.equals("2")) {
				ob.setOrderStatus("改派");
			} else if (stu.equals("5")) {
				ob.setOrderStatus("异常");
			} else if (stu.equals("6")) {
				ob.setOrderStatus("已关闭");
			} else if (stu.equals("7")) {
				ob.setOrderStatus("已取消");
			}
			if (ob.getMoney() == null) {
				ob.setMoney("0.00");
			}
			if (ob.getcName() == null) {
				ob.setcName("暂无数据");
			}
			if (ob.getdName() == null) {
				ob.setdName("暂无数据");
			}
		}
		return tov;
	}

	/**
	 * 订单报表分页总记录数
	 */
	@Override
	public TotalOfVehicle getCountOfTotal(VehiclePageBean pageBean) {
		return vehicleInfoDao.getCountOfTotal(pageBean);
	}

	/**
	 * 在线车辆最新状态
	 */
	@Override
	public Map<String, Map<String, List<VehicleMenu>>> findVehicleByAdmin(Admin admin) {
		// 存储处理之后的结果集
		Map<String, Map<String, List<VehicleMenu>>> data = new HashMap<String, Map<String, List<VehicleMenu>>>();
		// 存储所有车队的map
		Map<String, List<VehicleMenu>> teamMap = new HashMap<String, List<VehicleMenu>>();

		List<VehicleRealData> vehicleList = null;

		if (admin.getAtype() != 1) {
			// 一般管理员查询的是本账号所关联的车辆
			vehicleList = vehicleInfoDao.getVehicleRealDataByAdmin(admin);
		} else {
			// 超级管理员查询的是全部的车辆
			vehicleList = vehicleInfoDao.getVehicleRealDataSByAdmin();
		}

		// 1、 按照车队进行分组
		for (VehicleRealData vehicleMenu : vehicleList) {

			String alarm = null;
			if (vehicleMenu.getAlarmState() != null) {
				alarm = StringParse.pAlarmStatus(vehicleMenu.getAlarmState());
				vehicleMenu.setAlarm(alarm);
			}
			if (alarm == null || alarm.equals("")) {
				// 0报警
				vehicleMenu.setState(3);
			} else if (vehicleMenu.getOnline() == 0) {
				// 1离线
				vehicleMenu.setState(4);
			} else if (vehicleMenu.getVelocity() == 0) {
				// 2停车
				vehicleMenu.setState(2);
			} else if (vehicleMenu.getOnline() == 1) {
				// 3在线
				vehicleMenu.setState(1);
			} else {

			}
			/*
			 * if (vehicleMenu.getVelocity() == 0) { vehicleMenu.setParking(1); } else if (alarm == null || alarm.equals("")) { vehicleMenu.setAlarm(1); }
			 */
			// 车队名称
			String tName = vehicleMenu.gettName();
			if (teamMap.get(tName) != null) {
				// 如果车辆结果集map中存在当前车辆对象所属的车队，则，把当前车辆对象存进该结果集中
				teamMap.get(tName).add(vehicleMenu);
			} else {
				// 如果车辆结果集map中不存在当前车辆所属的车队，则，创建一个车队集合list，存入车队结果集map中
				List<VehicleMenu> temp = new ArrayList<VehicleMenu>();
				temp.add(vehicleMenu);
				teamMap.put(tName, temp);
			}
		}
		// 2、按照企业进行第二次划分

		Set<String> keys = teamMap.keySet();
		for (String key : keys) {
			// 获取车队中第一个车的企业名称
			List<VehicleMenu> temp = teamMap.get(key);
			String cName = temp.get(0).getcName();
			if (data.get(cName) != null) {
				// 如果data结果集中有当前企业的记录，则把当前车队存进该记录中
				data.get(cName).put(key, temp);
			} else {
				// 如果结果集中没有当前企业的记录，则，创建一个并添加进去
				Map<String, List<VehicleMenu>> temp1 = new HashMap<String, List<VehicleMenu>>();
				temp1.put(key, temp);
				data.put(cName, temp1);
			}
		}
		return data;
	}

	/**
	 * 所有可见车辆(普通用户)
	 */
	@Override
	public Map<String, Map<String, List<VehicleMenu>>> getVehicleByAdmin(Admin admin) {
		// 存储处理之后的结果集
		Map<String, Map<String, List<VehicleMenu>>> data = new HashMap<String, Map<String, List<VehicleMenu>>>();
		// 存储所有车队的map
		Map<String, List<VehicleMenu>> teamMap = new HashMap<String, List<VehicleMenu>>();
		List<VehicleMenu> vehicleList = null;

		vehicleList = vehicleInfoDao.getVehicleByAdmin(admin);

		// 1、 按照车队进行分组
		for (VehicleMenu vehicleMenu : vehicleList) {
			// 车队名称
			String tName = vehicleMenu.gettName();
			if (teamMap.get(tName) != null) {
				// 如果车辆结果集map中存在当前车辆对象所属的车队，则，把当前车辆对象存进该结果集中
				teamMap.get(tName).add(vehicleMenu);
			} else {
				// 如果车辆结果集map中不存在当前车辆所属的车队，则，创建一个车队集合list，存入车队结果集map中
				List<VehicleMenu> temp = new ArrayList<VehicleMenu>();
				temp.add(vehicleMenu);
				teamMap.put(tName, temp);
			}
		}
		// 2、按照企业进行第二次划分

		Set<String> keys = teamMap.keySet();
		for (String key : keys) {
			// 获取车队中第一个车的企业名称
			List<VehicleMenu> temp = teamMap.get(key);
			String cName = temp.get(0).getcName();
			if (data.get(cName) != null) {
				// 如果data结果集中有当前企业的记录，则把当前车队存进该记录中
				data.get(cName).put(key, temp);
			} else {
				// 如果结果集中没有当前企业的记录，则，创建一个并添加进去
				Map<String, List<VehicleMenu>> temp1 = new HashMap<String, List<VehicleMenu>>();
				temp1.put(key, temp);
				data.put(cName, temp1);
			}
		}
		return data;
	}

	/**
	 * 所有可见车辆(超级管理员)
	 */
	@Override
	public Map<String, Map<String, List<VehicleMenu>>> getVehicleSByAdmin() {
		// 存储处理之后的结果集
		Map<String, Map<String, List<VehicleMenu>>> data = new HashMap<String, Map<String, List<VehicleMenu>>>();
		// 存储所有车队的map
		Map<String, List<VehicleMenu>> teamMap = new HashMap<String, List<VehicleMenu>>();
		List<VehicleMenu> vehicleList = null;

		vehicleList = vehicleInfoDao.getVehicleSByAdmin();

		// 1、 按照车队进行分组
		for (VehicleMenu vehicleMenu : vehicleList) {
			// 车队名称
			String tName = vehicleMenu.gettName();
			if (teamMap.get(tName) != null) {
				// 如果车辆结果集map中存在当前车辆对象所属的车队，则，把当前车辆对象存进该结果集中
				teamMap.get(tName).add(vehicleMenu);
			} else {
				// 如果车辆结果集map中不存在当前车辆所属的车队，则，创建一个车队集合list，存入车队结果集map中
				List<VehicleMenu> temp = new ArrayList<VehicleMenu>();
				temp.add(vehicleMenu);
				teamMap.put(tName, temp);
			}
		}
		// 2、按照企业进行第二次划分

		Set<String> keys = teamMap.keySet();
		for (String key : keys) {
			// 获取车队中第一个车的企业名称
			List<VehicleMenu> temp = teamMap.get(key);
			String cName = temp.get(0).getcName();
			if (data.get(cName) != null) {
				// 如果data结果集中有当前企业的记录，则把当前车队存进该记录中
				data.get(cName).put(key, temp);
			} else {
				// 如果结果集中没有当前企业的记录，则，创建一个并添加进去
				Map<String, List<VehicleMenu>> temp1 = new HashMap<String, List<VehicleMenu>>();
				temp1.put(key, temp);
				data.put(cName, temp1);
			}
		}
		return data;
	}

	@Override
	public List<String> queryVehicleByCoopId(List coopArr) {
		return vehicleInfoDao.queryVehicleByCoopId(coopArr);
	}

	@Override
	public Map<String, Map<String, List<VehicleMenu>>> findVehicleByAdminAndSpecialType(Admin admin, String specialType) {
		// 存储处理之后的结果集
		Map<String, Map<String, List<VehicleMenu>>> data = new HashMap<String, Map<String, List<VehicleMenu>>>();
		// 存储所有车队的map
		Map<String, List<VehicleMenu>> teamMap = new HashMap<String, List<VehicleMenu>>();
		List<VehicleRealData> vehicleList = null;
		if (admin.getAtype() != 1) {
			// 一般管理员查询的是本账号所关联的车辆
			vehicleList = vehicleInfoDao.getVehicleRealDataByAdminAndType(specialType, admin.getId().toString());
		} else {
			// 超级管理员查询的是全部的车辆
			vehicleList = vehicleInfoDao.getVehicleRealDataSByAdminAndType(specialType);
		}
		// 1、 按照车队进行分组
		for (VehicleMenu vehicleMenu : vehicleList) {
			// 车队名称
			String tName = vehicleMenu.gettName();
			if (teamMap.get(tName) != null) {
				// 如果车辆结果集map中存在当前车辆对象所属的车队，则，把当前车辆对象存进该结果集中
				teamMap.get(tName).add(vehicleMenu);
			} else {
				// 如果车辆结果集map中不存在当前车辆所属的车队，则，创建一个车队集合list，存入车队结果集map中
				List<VehicleMenu> temp = new ArrayList<VehicleMenu>();
				temp.add(vehicleMenu);
				teamMap.put(tName, temp);
			}
		}
		// 2、按照企业进行第二次划分

		Set<String> keys = teamMap.keySet();
		for (String key : keys) {
			// 获取车队中第一个车的企业名称
			List<VehicleMenu> temp = teamMap.get(key);
			String cName = temp.get(0).getcName();
			if (data.get(cName) != null) {
				// 如果data结果集中有当前企业的记录，则把当前车队存进该记录中
				data.get(cName).put(key, temp);
			} else {
				// 如果结果集中没有当前企业的记录，则，创建一个并添加进去
				Map<String, List<VehicleMenu>> temp1 = new HashMap<String, List<VehicleMenu>>();
				temp1.put(key, temp);
				data.put(cName, temp1);
			}
		}
		return data;
	}

	// 导出Excal其他
	public List<cn.guangtong.excel.Vehicle> queryVehicleExcal(PageBean pageBean) {
		return getVehicleInfoDao().queryVehicleExcal(pageBean);
	}

	// 导出Excal超管
	public List<cn.guangtong.excel.Vehicle> queryVehicleExcalByAdmin(PageBean pageBean) {
		return getVehicleInfoDao().queryVehicleExcalByAdmin(pageBean);
	}

	@Override
	public Integer sVehicleCountS(VehiclePageBean vehiclePageBean) {
		return getVehicleInfoDao().sVehicleCountS(vehiclePageBean);
	}

	@Override
	public List<Map<String, Object>> sVehicleInfo(VehiclePageBean pageBean) {
		return getVehicleInfoDao().sVehicleInfo(pageBean);
	}

	@Override
	public boolean isRead(String[] vehicleArr) {
		try {
			// 1、将全部的车isread设置为0
			vehicleInfoDao.upNoIsRead();
			gpsDBDao.upNoIsRead();
			// 2、将选中的车的isread设置为1
			if (vehicleArr != null) {

				for (int i = 0; i < vehicleArr.length; i++) {
					// 车辆库
					vehicleInfoDao.upIsRead(vehicleArr[i]);
					// gpsdb库
					gpsDBDao.upIsRead(vehicleArr[i]);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}

	}

	@Override
	public Map<String, Object> getVehiclesPie(String type, String name) {
		// 所需全部信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 饼图data
		List<Object> listAll = new ArrayList<Object>();
		// 处理饼图数据封装进listAll
		List<Map<String, Object>> platForms = vehicleInfoDao.getVehiclesPie();
		for (Map<String, Object> platForm : platForms) {
			List<Object> list = new ArrayList<Object>();
			list.add((String) platForm.get("desc"));
			list.add(platForm.get("count"));
			listAll.add(list);
		}
		map.put("type", type);
		map.put("data", listAll);
		map.put("title", name);
		return map;
	}

}
