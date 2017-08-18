package cn.guangtong.dao.beidou;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.beidou.Terminal;
import cn.guangtong.utils.TerminalPageBean;

/**
 * 终端查询，添加，更改，删除
 * 
 * @author SunTo
 * 
 */
public interface TerminalDao {

	/**
	 * 添加终端
	 * 
	 * @param record
	 * @return
	 */
	int insert(Terminal record);
	
	/**
	 * 删除终端
	 * @param termId
	 * @return
	 */
	int delete(String termId);
	
	/**
	 * 更新终端
	 * @param terminal
	 */
	int upDateTerminal(Terminal terminal);

	/**
	 * 分页查询终端
	 * 
	 * @param terminalPageBean
	 * @return
	 */
	List<Map<String, Object>> getTerminalByPageBean(TerminalPageBean terminalPageBean);

	/**
	 * 分页查询终端的总条数
	 * 
	 * @param terminalPageBean
	 * @return
	 */
	int getTerminalByPageBeanCounts(TerminalPageBean terminalPageBean);

	/**
	 * 根据termId查询终端
	 * 
	 * @param termId
	 * @return
	 */
	Terminal getTerminalByTermNo(String termId);

}