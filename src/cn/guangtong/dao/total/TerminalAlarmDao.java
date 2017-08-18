package cn.guangtong.dao.total;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.total.TerminalAlarm;
import cn.guangtong.excel.TerminalChart;
import cn.guangtong.utils.TerminalFormPageBean;
import cn.guangtong.utils.TerminalPageBean;

public interface TerminalAlarmDao {
	/**
	 * 分页查询终端报警信息 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalForms(TerminalFormPageBean pageBean);

	/**
	 * 分页查询并导出终端报警信息 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalFormsExcel(TerminalFormPageBean pageBean);

	/**
	 * 统计终端报警信息条数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int getCounts(TerminalFormPageBean pageBean);

	/**
	 * 分页查询终端报警信息 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalFormsByOther(TerminalFormPageBean pageBean);

	/**
	 * 分页查询并导出终端报警信息 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalFormsExcelByOther(TerminalFormPageBean pageBean);

	/**
	 * 统计终端报警信息条数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int getCountsByOther(TerminalFormPageBean pageBean);

	/**
	 * 增加一条终端报警信息
	 * 
	 * @param terminalAlarm
	 * @return
	 */
	Integer iterminalAlarmInfo(TerminalAlarm terminalAlarm);

	/**
	 * 终端报警统计条数 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	int getChartCounts(TerminalPageBean pageBean);

	/**
	 * 分页终端报警统计 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalChart> getTerminalChartsPage(TerminalPageBean pageBean);

	/**
	 * 下载终端报警报表 普通
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalChart> getTerminalCharts(TerminalPageBean pageBean);

	/**
	 * 终端报警统计条数 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	int getChartCountsSuperTube(TerminalPageBean pageBean);

	/**
	 * 分页终端报警统计 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalChart> getTerminalChartsPageSuperTube(TerminalPageBean pageBean);

	/**
	 * 下载终端报警报表 超管
	 * 
	 * @param pageBean
	 * @return
	 */
	List<TerminalChart> getTerminalChartsSuperTube(TerminalPageBean pageBean);

	// 通过simNo 查询终端报警统计饼图
	List<Map<String, Object>> getTerminalChartsBySimNo(@Param("simNo") String simNo);

	// 查询最新5条报警状态信息
	List<TerminalAlarm> getALLNewestAlarm(String dealTime);
}
