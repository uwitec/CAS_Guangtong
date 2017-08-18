package cn.guangtong.service.total.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.beidou.GpsInfoDao;
import cn.guangtong.dao.total.TerminalAlarmDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.total.PlafromAlarm;
import cn.guangtong.entity.total.TerminalAlarm;
import cn.guangtong.excel.TerminalChart;
import cn.guangtong.service.total.TerminalAlarmService;
import cn.guangtong.utils.PercentageUtil;
import cn.guangtong.utils.StringParse;
import cn.guangtong.utils.TerminalFormPageBean;
import cn.guangtong.utils.TerminalPageBean;

@Service
public class TerminalAlarmServiceImpl implements TerminalAlarmService {
	@Autowired
	private TerminalAlarmDao terAlarmDao;
	@Autowired
	private GpsInfoDao gpsInfoDao;

	/**
	 * @return the terAlarmDao
	 */
	public TerminalAlarmDao getTerAlarmDao() {
		return terAlarmDao;
	}

	/**
	 * @param terAlarmDao
	 *            the terAlarmDao to set
	 */
	public void setTerAlarmDao(TerminalAlarmDao terAlarmDao) {
		this.terAlarmDao = terAlarmDao;
	}

	@Override
	public List<TerminalAlarm> getTerminalForms(TerminalFormPageBean pageBean) {

		return terAlarmDao.getTerminalForms(pageBean);
	}

	@Override
	public List<TerminalAlarm> getTerminalFormsExcel(TerminalFormPageBean pageBean) {

		return terAlarmDao.getTerminalFormsExcel(pageBean);
	}

	@Override
	public int getCounts(TerminalFormPageBean pageBean) {

		return terAlarmDao.getCounts(pageBean);
	}

	@Override
	public Integer iterminalAlarmInfo(TerminalAlarm terminalAlarm) {

		return terAlarmDao.iterminalAlarmInfo(terminalAlarm);
	}

	@Override
	public void getTerminalChartsPage(TerminalPageBean pageBean) {
		List<TerminalChart> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			totalCount = terAlarmDao.getChartCounts(pageBean);
			list = terAlarmDao.getTerminalChartsPage(pageBean);
			for (TerminalChart terminalChart : list) {
				List<String> li = StringParse.stringAlarm(terminalChart.getAlarmState());
				// 保存报警数量
				terminalChart.setSize(list.size());
			}
		} else {
			totalCount = terAlarmDao.getChartCountsSuperTube(pageBean);
			list = terAlarmDao.getTerminalChartsPageSuperTube(pageBean);
			for (TerminalChart terminalChart : list) {
				List<String> li = StringParse.stringAlarm(terminalChart.getAlarmState());
				// 保存报警数量
				terminalChart.setSize(list.size());
			}
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount*1000);
		
	}

	@Override
	public void getTerminalCharts(TerminalPageBean pageBean) {
		List<TerminalChart> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			totalCount = terAlarmDao.getChartCounts(pageBean);
			list = terAlarmDao.getTerminalCharts(pageBean);
			for (TerminalChart terminalChart : list) {
				List<String> li = StringParse.stringAlarm(terminalChart.getAlarmState());
				// 保存报警数量
				terminalChart.setSize(list.size());
			}
		} else {
			totalCount = terAlarmDao.getChartCountsSuperTube(pageBean);
			list = terAlarmDao.getTerminalChartsSuperTube(pageBean);
			for (TerminalChart terminalChart : list) {
				List<String> li = StringParse.stringAlarm(terminalChart.getAlarmState());
				// 保存报警数量
				terminalChart.setSize(list.size());
			}
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);		
	
	}

	@Override
	public Map<String, Object> getTerminalChartsTotal(String simNo) {
		// 所需所有信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 饼图data
		List<Object> listAll = new ArrayList<Object>();
		// 处理饼图数据封装进listAll
		List<Map<String, Object>> li = terAlarmDao.getTerminalChartsBySimNo(simNo);
		String s = "";
		for (Map<String, Object> terminalForms : li) {
			s += StringParse.alarmStatusInfo((Integer) terminalForms.get("alarmState"));

		}
		// 紧急告警
		String emergencyAlarm = "紧急告警";
		// 超速告警
		String overspeedAlarm = "超速告警";
		// 疲劳驾驶
		String fatigueDriving = "疲劳驾驶";
		// 预警
		String earlyWarning = "预警";
		// GNSS模块发生故障
		String gnssFailure = "GNSS模块发生故障";
		// GNSS模块天线未接
		String antennaFailure = "GNSS模块天线未接";
		// GNSS模块天线短路
		String antennashort = "GNSS模块天线短路";
		// 终端主电源欠压
		String undervoltage = "终端主电源欠压";
		// 终端主电源掉电
		String powerOff = "终端主电源掉电";
		// 终端LCD或显示器故障
		String monitorfailure = "终端LCD或显示器故障";
		// TTS模块故障
		String moduleFault = "TTS模块故障";
		// 摄像头故障
		String cameraFault = " 摄像头故障";
		// 当天累计驾驶超时
		String driveTimeout = "当天累计驾驶超时";
		// 计价器故障
		String meterfault = "计价器故障";
		// 超时停车
		String overtimePark = "超时停车";
		// 进出区域
		String exitArea = "进出区域";
		// 进出路线
		String exitRoute = "进出路线";
		// 路段行驶时间不足/过长
		String travelTime = " 路段行驶时间不足/过长";
		// 路线偏离报警
		String routeDevAlarm = "路线偏离报警";
		// 车辆vss报警
		String vvssAlarm = "车辆vss报警";
		// 车辆油量异常
		String vAbnormaLoil = "车辆油量异常";
		// 车辆被盗
		String vTheft = "车辆被盗";
		// 车辆非法点火
		String vIgnition = "车辆非法点火";
		// 车辆非法位移
		String vDisplacement = "车辆非法位移";
		map.put(emergencyAlarm, PercentageUtil.percent(emergencyAlarm, s));
		map.put(overspeedAlarm, PercentageUtil.percent(overspeedAlarm, s));
		map.put(fatigueDriving, PercentageUtil.percent(fatigueDriving, s));
		map.put(earlyWarning, PercentageUtil.percent(earlyWarning, s));
		map.put(gnssFailure, PercentageUtil.percent(gnssFailure, s));
		map.put(antennaFailure, PercentageUtil.percent(antennaFailure, s));
		map.put(antennashort, PercentageUtil.percent(antennashort, s));
		map.put(undervoltage, PercentageUtil.percent(undervoltage, s));
		map.put(powerOff, PercentageUtil.percent(powerOff, s));
		map.put(monitorfailure, PercentageUtil.percent(monitorfailure, s));
		map.put(moduleFault, PercentageUtil.percent(moduleFault, s));
		map.put(cameraFault, PercentageUtil.percent(cameraFault, s));
		map.put(driveTimeout, PercentageUtil.percent(driveTimeout, s));
		map.put(meterfault, PercentageUtil.percent(meterfault, s));
		map.put(overtimePark, PercentageUtil.percent(overtimePark, s));
		map.put(exitArea, PercentageUtil.percent(exitArea, s));
		map.put(exitRoute, PercentageUtil.percent(exitRoute, s));
		map.put(travelTime, PercentageUtil.percent(travelTime, s));
		map.put(routeDevAlarm, PercentageUtil.percent(routeDevAlarm, s));
		map.put(vvssAlarm, PercentageUtil.percent(vvssAlarm, s));
		map.put(vAbnormaLoil, PercentageUtil.percent(vAbnormaLoil, s));
		map.put(vTheft, PercentageUtil.percent(vTheft, s));
		map.put(vIgnition, PercentageUtil.percent(vIgnition, s));
		map.put(vDisplacement, PercentageUtil.percent(vDisplacement, s));

		Set<String> ksySet = map.keySet();
		for (String key : ksySet) {
			List<Object> list = new ArrayList<Object>();
			if ((Integer) map.get(key) != 0) {
				list.add(key);
				list.add(map.get(key));
				listAll.add(list);
			}
		}
		map.put("data", listAll);
		return map;
	}

	@Override
	public List<TerminalAlarm> getALLNewestAlarm(String dealTime) {
		return terAlarmDao.getALLNewestAlarm(dealTime);
	}

	@Override
	public List<TerminalAlarm> getTerminalFormsByOther(
			TerminalFormPageBean pageBean) {
		return terAlarmDao.getTerminalFormsByOther(pageBean);
	}

	@Override
	public List<TerminalAlarm> getTerminalFormsExcelByOther(
			TerminalFormPageBean pageBean) {
		return terAlarmDao.getTerminalFormsExcelByOther(pageBean);
	}

	@Override
	public int getCountsByOther(TerminalFormPageBean pageBean) {
		return terAlarmDao.getCountsByOther(pageBean);
	}

}
