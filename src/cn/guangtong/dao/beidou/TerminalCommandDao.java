package cn.guangtong.dao.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.CommandLog;
import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.entity.beidou.Terminalparam;
import cn.guangtong.utils.CommandLogPageBean;

public interface TerminalCommandDao {

	/**
	 * 插入一条指令
	 * 
	 * @param record
	 * @return
	 */
	int insert(TerminalCommand record);

	/**
	 * 查询指令内容
	 * 
	 * @param simNo
	 * @param createDate
	 * @return
	 */
	TerminalCommand getTerminalCommandInfo(int cmdid);
	/**
	 * 查询终端参数 根据命令id
	 * @param comId
	 * @return
	 */
	List<Terminalparam> getTerminalCommandInfoByComId(int comId);
	/**
	 * 命令日志记录信息 普通
	 * 
	 * @return
	 */
	List<CommandLog> commandLogOfTableByOther(CommandLogPageBean pageBean);

	/**
	 * 命令日志记录信息导出 普通
	 * 
	 * @return
	 */
	List<CommandLog> commandLogOfTableExcelByOther(CommandLogPageBean pageBean);

	/**
	 * 命令日志记录数 普通
	 * 
	 * @return
	 */
	int countOfLogByOther(CommandLogPageBean pageBean);

	/**
	 * 命令日志记录信息 超管
	 * 
	 * @return
	 */
	List<CommandLog> commandLogOfTable(CommandLogPageBean pageBean);

	/**
	 * 命令日志记录信息导出 超管
	 * 
	 * @return
	 */
	List<CommandLog> commandLogOfTableExcel(CommandLogPageBean pageBean);

	/**
	 * 命令日志记录数 超管
	 * 
	 * @return
	 */
	int countOfLog(CommandLogPageBean pageBean);

}