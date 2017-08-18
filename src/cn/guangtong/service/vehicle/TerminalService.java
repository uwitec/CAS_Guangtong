package cn.guangtong.service.vehicle;

import cn.guangtong.entity.beidou.Terminal;
import cn.guangtong.utils.TerminalPageBean;

/**
 * 终端管理（黑盒子）
 * 
 * @author SunTo
 * 
 */
public interface TerminalService {

	/**
	 * 添加终端
	 * 
	 * @param record
	 * @return
	 */
	boolean insert(Terminal record);
	
	/**
	 * 删除终端
	 * @param termNo
	 * @return
	 */
	boolean delete(String termNo);
	
	/**
	 * 更新终端
	 * @param terminal
	 * @return
	 */
	boolean upDateTerminal(Terminal terminal);
	
	/**
	 * 根据id查询终端
	 * @param terminalId
	 * @return
	 */
	Terminal getTerminalByTermNo(String termNo);

	/**
	 * 分页查询终端
	 * 
	 * @param terminalPageBean
	 */
	void getTerminalByPageBean(TerminalPageBean terminalPageBean);
}
