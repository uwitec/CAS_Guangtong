package cn.guangtong.service.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.CommandLog;
import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.utils.CommandLogPageBean;

public interface TerminalCommandService {
	// 添加一条指令
	boolean insert(TerminalCommand terminalCommand);
	/**
	 * 查询指令内容
	 * 
	 * @param simNo
	 * @param createDate
	 * @return
	 */
	List<TerminalCommand> getTerminalCommandInfo(String[] cmdid);
	/**
	 * 查询终端参数 根据命令id
	 * @param comId
	 * @return
	 */
	String getTerminalCommandInfoByComId(int comId);

	// 添加一条指令 根据车辆id simNo 车牌号
	List<Integer> insertCommand(TerminalCommand terminalCommand,String createDate, String[] plateNo, String[] simNo, String[] vehicleId);

	// 终端参数设置(批量添加)
	List<Integer> insertCommandByterminal(String Command, String createDate,String[] plateNo, String[] simNo, String[] vehicleId);

	// 查询终端参数(批量 查找)
	List<Integer> selectCommandByterminal(TerminalCommand terminalCommand, String[] plateNo, String[] simNo, String[] vehicleId);

	/**
	 * 命令日志记录信息 普通
	 * @return
	 */
	List<CommandLog> commandLogOfTableByOther(CommandLogPageBean pageBean);
	
	/**
	 * 命令日志记录信息导出 普通
	 * @return
	 */
	List<CommandLog> commandLogOfTableExcelByOther(CommandLogPageBean pageBean);
	
	/**
	 * 命令日志记录数  普通
	 * @return
	 */
	int countOfLogByOther(CommandLogPageBean pageBean);
	
	/**
	 * 命令日志记录信息
	 * 
	 * @return
	 */
	List<CommandLog> commandLogOfTable(CommandLogPageBean pageBean);
	
	/**
	 * 命令日志记录信息导出
	 * 
	 * @return
	 */
	List<CommandLog> commandLogOfTableExcel(CommandLogPageBean pageBean);

	/**
	 * 命令日志记录数
	 * 
	 * @return
	 */
	int countOfLog(CommandLogPageBean pageBean);

}
