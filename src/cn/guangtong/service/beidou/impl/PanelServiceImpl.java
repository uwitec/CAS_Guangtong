package cn.guangtong.service.beidou.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.guangtong.dao.beidou.PanelDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.model.PanelVehicle;
import cn.guangtong.service.beidou.PanelService;

@Service
@Transactional
public class PanelServiceImpl implements PanelService {

	@Autowired
	private PanelDao panleDao;

	@Override
	public Map<String, Object> findVehicleByAdmin(Admin admin, String state) {
		Map<String, Object> data = new HashMap<String, Object>();

		int temp = Integer.parseInt(state);
		// 在线集合
		List<PanelVehicle> online = new ArrayList<PanelVehicle>();
		// 报警集合
		List<PanelVehicle> alarm = new ArrayList<PanelVehicle>();
		// 停车
		List<PanelVehicle> parking = new ArrayList<PanelVehicle>();
		// 离线
		List<PanelVehicle> offline = new ArrayList<PanelVehicle>();

		// 查询的结果集
		List<PanelVehicle> list = null;

		if (admin.getAtype() != 1) {
			list = panleDao.findVehicleByAdmin(admin.getId().toString());
		} else {
			list = panleDao.findVehicleByAdminS();
		}
/*
		// 运行车辆(在线online=1)
		for (PanelVehicle pv : list) {
			if (pv.getOnline() == 1) {
				online.add(pv);
			}
		}
		// 停车车辆(速度为0)
		for (PanelVehicle pv : list) {
			if (pv.getVelocity() == 0) {
				parking.add(pv);
			}
		}
		// 离线车辆(online=0)
		for (PanelVehicle pv : list) {
			if (pv.getOnline() == 0) {
				offline.add(pv);
			}
		}
		*/
		// 报警车辆(根据alarmState)
		for (PanelVehicle pv : list) {
			if (pv.getAlarmState() != null && !pv.getAlarmState().equals("")) {
				// 报警车辆(根据alarmState)
				alarm.add(pv);
			}else if(pv.getOnline() == 0){
				// 离线车辆(online=0)
				offline.add(pv);
			}else if(pv.getVelocity() == 0){
				// 停车车辆(速度为0)
				parking.add(pv);
			}else{
				// 运行车辆(在线online=1)
				online.add(pv);
			}
		}
	
		//在线 1,停车 2,报警3,离线4
		if (temp == 1) {
			data.put("data", online);
			data.put("onlineNum", online.size());
			data.put("parkingNum", parking.size());
			data.put("alarmNum", alarm.size());
			data.put("offlineNum", offline.size());
			data.put("Num", list.size());
		} else if (temp == 2) {
			data.put("data", parking);
			data.put("onlineNum", online.size());
			data.put("parkingNum", parking.size());
			data.put("alarmNum", alarm.size());
			data.put("offlineNum", offline.size());
			data.put("Num", list.size());
		} else if (temp == 3) {
			data.put("data", alarm);
			data.put("onlineNum", online.size());
			data.put("parkingNum", parking.size());
			data.put("alarmNum", alarm.size());
			data.put("offlineNum", offline.size());
			data.put("Num", list.size());
		} else if (temp == 4) {
			data.put("data", offline);
			data.put("onlineNum", online.size());
			data.put("parkingNum", parking.size());
			data.put("alarmNum", alarm.size());
			data.put("offlineNum", offline.size());
			data.put("Num", list.size());
		} else {
			data.put("data", list);
			data.put("onlineNum", online.size());
			data.put("parkingNum", parking.size());
			data.put("alarmNum", alarm.size());
			data.put("offlineNum", offline.size());
			data.put("Num", list.size());
		}
		return data;
	
	}
}
