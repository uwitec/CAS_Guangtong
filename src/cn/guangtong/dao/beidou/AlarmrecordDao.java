package cn.guangtong.dao.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.Alarmrecord;

public interface AlarmrecordDao {

	public List<Alarmrecord> getAlarmByPlat();

	public List<Alarmrecord> getAlarmByTerminal();

	List<Alarmrecord> getALLNewestAlarm(String now);

}
