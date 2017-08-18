package cn.guangtong.service.vehicle.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.beidou.TerminalDao;
import cn.guangtong.entity.beidou.Terminal;
import cn.guangtong.service.vehicle.TerminalService;
import cn.guangtong.utils.TerminalPageBean;

@Service
public class TerminalServiceImpl implements TerminalService {

	@Autowired
	private TerminalDao terminalDao;

	@Override
	public boolean insert(Terminal record) {
		try {
			terminalDao.insert(record);
			return  true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public void getTerminalByPageBean(TerminalPageBean terminalPageBean) {
		int counts = terminalDao.getTerminalByPageBeanCounts(terminalPageBean);
		terminalPageBean.setTotalCount(counts);
		List<Map<String,Object>> dataList=	terminalDao.getTerminalByPageBean(terminalPageBean);
		terminalPageBean.setBeanList(dataList);
	}

	@Override
	public Terminal getTerminalByTermNo(String termNo) {
		return terminalDao.getTerminalByTermNo(termNo);
	}

	@Override
	public boolean delete(String termNo) {
		try {
			terminalDao.delete(termNo);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean upDateTerminal(Terminal terminal) {
		try {
			terminalDao.upDateTerminal(terminal);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
	}

}
