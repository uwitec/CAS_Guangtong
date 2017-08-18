package cn.guangtong.dao.beidou;

import cn.guangtong.entity.beidou.AlarmsInfo;

/**
 * 设置报警状态信息
 */
public interface AlarmsInfoDao {
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
