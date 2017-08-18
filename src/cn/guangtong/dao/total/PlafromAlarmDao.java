package cn.guangtong.dao.total;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.total.PlafromAlarm;
import cn.guangtong.entity.total.PlatTotalAlarm;
import cn.guangtong.model.PlatAndTerAlarmChar;
import cn.guangtong.utils.AlarmCharPageBean;
import cn.guangtong.utils.PlatFormPageBean;

public interface PlafromAlarmDao {

	/**
	 * 分页查询平台报警报表 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlafromAlarm> getPlatForms(PlatFormPageBean pageBean);

	/**
	 * 平台报警报表导出 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlafromAlarm> getPlatFormsExcel(PlatFormPageBean pageBean);

	/**
	 * 统计平台报警总数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int getCounts(PlatFormPageBean pageBean);

	/**
	 * 分页查询平台报警报表 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlafromAlarm> getPlatFormsSuperTube(PlatFormPageBean pageBean);

	/**
	 * 平台报警报表导出 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlafromAlarm> getPlatFormsExcelSuperTube(PlatFormPageBean pageBean);

	/**
	 * 统计平台报警总数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int getCountsSuperTube(PlatFormPageBean pageBean);

	/**
	 * 获取警报信息
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlatAndTerAlarmChar> getDataOfAlarm(AlarmCharPageBean pageBean);

	/**
	 * 获取警报数量
	 * 
	 * @param pageBean
	 * @return
	 */
	int getCountOfAlarm(PlatFormPageBean pageBean);

	// 增加一条平台报警信息
	Integer iplafromAlarmInfo(PlafromAlarm plAlarm);

	/**
	 * 平台报警统计总数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int getTongjiCounts(PlatFormPageBean pageBean);

	/**
	 * 平台报警统计分页 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlatTotalAlarm> getTongjiPlatformPage(PlatFormPageBean pageBean);

	/**
	 * 平台报警统计下载 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlatTotalAlarm> getTongjiPlatform(PlatFormPageBean pageBean);

	/**
	 * 平台报警统计总数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int getTongjiCountsSuperTube(PlatFormPageBean pageBean);

	/**
	 * 平台报警统计分页 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlatTotalAlarm> getTongjiPlatformPageSuperTube(PlatFormPageBean pageBean);

	/**
	 * 平台报警统计下载 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PlatTotalAlarm> getTongjiPlatformSuperTube(PlatFormPageBean pageBean);

	List<Map<String, Object>> getPlatFormBySimNo(@Param("simNo") String simNo);

}
