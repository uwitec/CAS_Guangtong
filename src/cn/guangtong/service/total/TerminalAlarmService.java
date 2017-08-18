package cn.guangtong.service.total;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.total.TerminalAlarm;
import cn.guangtong.utils.TerminalFormPageBean;
import cn.guangtong.utils.TerminalPageBean;

public interface TerminalAlarmService {
	/**
	 *  分页查询终端报警信息 超管
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalForms(TerminalFormPageBean pageBean);

	/**
	 *  分页查询并导出终端报警信息 超管
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalFormsExcel(TerminalFormPageBean pageBean);

	/**
	 *  统计终端报警信息条数 超管
	 * @param pageBean
	 * @return
	 */
	int getCounts(TerminalFormPageBean pageBean);

	/**
	 *  分页查询终端报警信息 普通
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalFormsByOther(TerminalFormPageBean pageBean);
	
	/**
	 *  分页查询并导出终端报警信息 普通
	 * @param pageBean
	 * @return
	 */
	List<TerminalAlarm> getTerminalFormsExcelByOther(TerminalFormPageBean pageBean);
	
	/**
	 *  统计终端报警信息条数 普通
	 * @param pageBean
	 * @return
	 */
	int getCountsByOther(TerminalFormPageBean pageBean);

	// 增加一条终端报警信息
	Integer iterminalAlarmInfo(TerminalAlarm terminalAlarm);

	// 通过simNo 查询终端报警统计饼图
	Map<String, Object> getTerminalChartsTotal(String simNo);

	/**
	 * 分页终端报警统计 
	 * @param pageBean
	 */
	void getTerminalChartsPage(TerminalPageBean pageBean);

	/**
	 * 分页表信息
	 * @param pageBean
	 */
	void getTerminalCharts(TerminalPageBean pageBean);

	// 查询最新报警状态信息
	List<TerminalAlarm> getALLNewestAlarm(String dealTime);

}
