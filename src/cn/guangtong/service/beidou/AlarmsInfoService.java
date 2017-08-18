package cn.guangtong.service.beidou;

import cn.guangtong.entity.beidou.AlarmsInfo;

public interface AlarmsInfoService {
	/**
	 * 查询
	 * 
	 * @return
	 */
	AlarmsInfo getAlarmsInfo(int userId);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insertAlarmsInfo(AlarmsInfo alarmsInfo);

	/**
	 * 更新
	 * 
	 * @param record
	 * @return
	 */
	int updateAlarmsInfo(AlarmsInfo alarmsInfo);
}
