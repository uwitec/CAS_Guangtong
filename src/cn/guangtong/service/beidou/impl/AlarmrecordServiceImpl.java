package cn.guangtong.service.beidou.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.beidou.AlarmrecordDao;
import cn.guangtong.entity.beidou.Alarmrecord;
import cn.guangtong.service.beidou.AlarmrecordService;

@Service
public class AlarmrecordServiceImpl implements AlarmrecordService {

	@Autowired
	private AlarmrecordDao alarmrecordDao;

	@Override
	public List<Alarmrecord> getALLNewestAlarm(String now) {
		return alarmrecordDao.getALLNewestAlarm(now);
	}
}
