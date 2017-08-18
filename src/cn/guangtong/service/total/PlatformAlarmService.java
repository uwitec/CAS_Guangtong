package cn.guangtong.service.total;

import java.util.Map;

import cn.guangtong.entity.total.PlafromAlarm;
import cn.guangtong.utils.PlatFormPageBean;

public interface PlatformAlarmService {

	/**
	 * 分页查询平台报警报表
	 * 
	 * @param pageBean
	 * @return
	 */
	void getPlatForms(PlatFormPageBean pageBean);

	/**
	 * 平台报警报表导出
	 * 
	 * @param pageBean
	 * @return
	 */
	void getPlatFormsExcel(PlatFormPageBean pageBean);

	Integer iplafromAlarmInfo(PlafromAlarm plAlarm);

	Map<String, Object> getPlatForms(String type, String name, String simNo);

	/**
	 * 平台报警统计
	 * 
	 * @param pageBean
	 * @return
	 */
	void getTongjiPlatformPage(PlatFormPageBean pageBean);

	/**
	 * 平台报警统计 下载
	 * 
	 * @param pageBean
	 * @return
	 */
	void getTongjiPlatform(PlatFormPageBean pageBean);
}
