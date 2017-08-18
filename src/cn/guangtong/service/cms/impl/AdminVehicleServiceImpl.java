package cn.guangtong.service.cms.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.AdminVehicleDao;
import cn.guangtong.entity.cms.AdminVehicle;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.service.cms.AdminVehicleService;

@Service
public class AdminVehicleServiceImpl implements AdminVehicleService {

	@Autowired
	private AdminVehicleDao adminVehicleDao;

	@Override
	public Map<String, Map<String, List<VehicleMenu>>> getVehicleMenu(Integer adminId) {

		// 存储处理之后的结果集
		Map<String, Map<String, List<VehicleMenu>>> data = new HashMap<String, Map<String, List<VehicleMenu>>>();
		// 存储所有车队的map
		Map<String, List<VehicleMenu>> teamMap = new HashMap<String, List<VehicleMenu>>();
		List<VehicleMenu> vehicleList = null;

		// 如果不是超级管理员，则从admin_vehilce开始查询
		vehicleList = adminVehicleDao.getVehicleMenuByAdminId(adminId);

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
	public Map<String, Map<String, List<VehicleMenu>>> getVehicleMenuS(String adminId,String selCondition) {

		// 存储处理之后的结果集
		Map<String, Map<String, List<VehicleMenu>>> data = new HashMap<String, Map<String, List<VehicleMenu>>>();
		// 存储所有车队的map
		Map<String, List<VehicleMenu>> teamMap = new HashMap<String, List<VehicleMenu>>();
		List<VehicleMenu> vehicleList = null;
		
		// 如果不是超级管理员，则从admin_vehilce开始查询
		vehicleList = adminVehicleDao.getVehicleMenuS(adminId,selCondition);

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
	public boolean isRead(String adminId, String[] vehicleArr) {
		try {
			//1、取消全部车辆的isread
			adminVehicleDao.upNoIsRead(adminId);
			//2、将选中的车辆isread设置为1
			for (int i=0;i<vehicleArr.length;i++) {
				adminVehicleDao.upIsRead(adminId, vehicleArr[i]);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}

	}
}
