package cn.guangtong.service.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.Alarmrecord;

public interface AlarmrecordService {
	List<Alarmrecord> getALLNewestAlarm(String now);
}
