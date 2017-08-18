package cn.guangtong.service.beidou.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.guangtong.dao.beidou.GpsRealDataDao;
import cn.guangtong.dao.gpsdb.GpsDBDao;
import cn.guangtong.entity.beidou.GpsRealData;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.driver.DriverRecord;
import cn.guangtong.excel.LatestStatus;
import cn.guangtong.model.TerminalStatusInfo;
import cn.guangtong.model.VehicleDetails;
import cn.guangtong.service.beidou.GpsRealDataService;
import cn.guangtong.utils.ThelateststatePageBean;
import cn.guangtong.utils.StringParse;

@Service
@Transactional
public class GpsRealDataServiceImpl implements GpsRealDataService {

	@Autowired
	private GpsRealDataDao gpsRealDataDao;
	
	@Autowired
	private GpsDBDao gpsdb;

	@Override
	public List<GpsRealData> getRealDatasByOnline() {
		return gpsRealDataDao.getRealDatasByOnline();
	}

	@Override
	public GpsRealData getRealDatasByPlateNo(String plateNo) {
		return gpsRealDataDao.getRealDatasByPlateNo(plateNo);
	}

	/**
	 * 根据SimNo精准查询车辆信息
	 * 
	 * @return
	 */
	@Override
	public VehicleDetails getRealDatasBySimNo(String simNo) {
		//原平台用simNo直接进行查询
		VehicleDetails data = gpsRealDataDao.getRealDatasBySimNo(simNo);
		if (data != null) {
			String alarmState = data.getAlarmState();
			String status = data.getStatus();
			// 工具类解析字符串
			TerminalStatusInfo sts = StringParse.stringToParse(alarmState, status);
			data.setTerminalStatusInfo(sts);
			return data;
		}else{
			//如果根据simNo查询不到，则认为是809传过来的车辆，根据车牌号去gpsdb进行查询
			VehicleDetails gpsdb_data =gpsdb.getRealDatasByplateNo(simNo);
			if(gpsdb_data!=null){
				String alarmState = gpsdb_data.getAlarmState();
				String status = gpsdb_data.getStatus();
				// 工具类解析字符串
				TerminalStatusInfo sts = StringParse.stringToParse(alarmState, status);
				gpsdb_data.setTerminalStatusInfo(sts);
			}
			return gpsdb_data;
		}
		
	}

	// 根据simNo模糊查询
	public List<Map<String, Object>> getRealDatasByObsSimNo(String simNo) {
		return gpsRealDataDao.getRealDatasByObsSimNo(simNo);
	}

	// 在线监控报警车辆
	public List<Map<String, Object>> onlineAlarmInformation() {
		return gpsRealDataDao.onlineAlarmInformation();
	}

	@Override
	public void findGpsRealData(ThelateststatePageBean pageBean) {
		
		List<LatestStatus> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			totalCount = gpsRealDataDao.findGpsRealDataCounts(pageBean);
			list = gpsRealDataDao.findGpsRealData(pageBean);
			for (LatestStatus latestStatus : list) {
				String status = latestStatus.getStatus();
				String alarm=latestStatus.getAlarmState();
				latestStatus.setAlarmState(StringParse.pAlarmStatus(alarm));
				latestStatus.setStatus(StringParse.pLocationInfo(status));
			}
		} else {
			totalCount = gpsRealDataDao.findGpsRealDataCountsSuperTube(pageBean);
			list = gpsRealDataDao.findGpsRealDataSuperTube(pageBean);
			for (LatestStatus latestStatus : list) {
				String status = latestStatus.getStatus();
				String alarm=latestStatus.getAlarmState();
				latestStatus.setAlarmState(StringParse.pAlarmStatus(alarm));
				latestStatus.setStatus(StringParse.pLocationInfo(status));
			}
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount*2000+(int)(Math.random()*1000));
	}
	
	@Override
	public void findGpsRealDataExcel(ThelateststatePageBean pageBean) {
		List<LatestStatus> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			totalCount = gpsRealDataDao.findGpsRealDataCounts(pageBean);
			list = gpsRealDataDao.findGpsRealDataExcel(pageBean);
			for (LatestStatus latestStatus : list) {
				String status = latestStatus.getStatus();
				String alarm=latestStatus.getAlarmState();
				latestStatus.setAlarmState(StringParse.pAlarmStatus(alarm));
				latestStatus.setStatus(StringParse.pLocationInfo(status));
			}
		} else {
			totalCount = gpsRealDataDao.findGpsRealDataCountsSuperTube(pageBean);
			list = gpsRealDataDao.findGpsRealDataExcelSuperTube(pageBean);
			for (LatestStatus latestStatus : list) {
				String status = latestStatus.getStatus();
				String alarm=latestStatus.getAlarmState();
				latestStatus.setAlarmState(StringParse.pAlarmStatus(alarm));
				latestStatus.setStatus(StringParse.pLocationInfo(status));
			}
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);
	}
}
