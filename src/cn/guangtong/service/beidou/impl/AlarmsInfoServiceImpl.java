package cn.guangtong.service.beidou.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.beidou.AlarmsInfoDao;
import cn.guangtong.entity.beidou.AlarmsInfo;
import cn.guangtong.service.beidou.AlarmsInfoService;

@Service
public class AlarmsInfoServiceImpl implements AlarmsInfoService {
	@Autowired
	private AlarmsInfoDao alarmsInfoDao;

	@Override
	public AlarmsInfo getAlarmsInfo(int userId) {
		AlarmsInfo alarmsInfo = alarmsInfoDao.getAlarmsInfo(userId);
		if (alarmsInfo != null) {
			return alarmsInfo;
		} else {
			AlarmsInfo temp = new AlarmsInfo();
			temp.setUserId(userId);
			alarmsInfoDao.insertAlarmsInfo(temp);
			return temp;
		}
	}

	@Override
	public int insertAlarmsInfo(AlarmsInfo alarmsInfo) {
		return alarmsInfoDao.insertAlarmsInfo(alarmsInfo);
	}

	@Override
	public int updateAlarmsInfo(AlarmsInfo alarmsInfo) {
		return alarmsInfoDao.updateAlarmsInfo(alarmsInfo);
	}

}
