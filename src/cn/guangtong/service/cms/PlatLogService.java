package cn.guangtong.service.cms;

import java.util.List;

import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.utils.PlatLogPageBean;

public interface PlatLogService {
	
	/**
	 * 操作日志信息 超管
	 * @param pageBean
	 * @return
	 */
	public List<PlatLog> getPlatLogs(PlatLogPageBean pageBean);
	
	/**
	 * 操作日志信息导出 超管
	 * @param pageBean
	 * @return
	 */
	public List<PlatLog> getPlatLogsExcel(PlatLogPageBean pageBean);
	
	/**
	 * 操作日志记录数 超管
	 * @param pageBean
	 * @return
	 */
	public int countOfPlatLogs(PlatLogPageBean pageBean);

	/**
	 * 操作日志信息 普通
	 * @param pageBean
	 * @return
	 */
	public List<PlatLog> getPlatLogsByOther(PlatLogPageBean pageBean);
	
	/**
	 * 操作日志信息导出 普通
	 * @param pageBean
	 * @return
	 */
	public List<PlatLog> getPlatLogsExcelByOther(PlatLogPageBean pageBean);
	
	/**
	 * 操作日志记录数 普通
	 * @param pageBean
	 * @return
	 */
	public int countOfPlatLogsByOther(PlatLogPageBean pageBean);
	
	public int getCounts(PlatLogPageBean pageBean);
	
	public void addPlatLog(PlatLog PlatLog);
	
	public List<PlatLog> getAllPlatLogs(PlatLogPageBean pageBean);
}
