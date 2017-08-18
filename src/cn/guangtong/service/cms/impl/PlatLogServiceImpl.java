package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.PlatLogDao;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.service.cms.PlatLogService;
import cn.guangtong.utils.PlatLogPageBean;

@Service
@Transactional
public class PlatLogServiceImpl implements PlatLogService {

	@Autowired
	private PlatLogDao platLogDao;

	@Transactional
	public List<PlatLog> getPlatLogs(PlatLogPageBean pageBean) {
		return platLogDao.getPlatLogs(pageBean);
	}
	
	@Transactional
	public List<PlatLog> getPlatLogsExcel(PlatLogPageBean pageBean) {
		return platLogDao.getPlatLogsExcel(pageBean);
	}

	@Transactional
	public int getCounts(PlatLogPageBean pageBean) {
		return platLogDao.getCounts(pageBean);
	}

	@Transactional
	public void addPlatLog(PlatLog PlatLog) {
		try {
			platLogDao.addPlatLog(PlatLog);
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

	@Override
	public List<PlatLog> getAllPlatLogs(PlatLogPageBean pageBean) {
		return platLogDao.getAllPlatLogs(pageBean);
	}

	@Override
	public int countOfPlatLogs(PlatLogPageBean pageBean) {
		return platLogDao.countOfPlatLogs(pageBean);
	}

	@Override
	public List<PlatLog> getPlatLogsByOther(PlatLogPageBean pageBean) {
		return platLogDao.getPlatLogsByOther(pageBean);
	}

	@Override
	public List<PlatLog> getPlatLogsExcelByOther(PlatLogPageBean pageBean) {
		return platLogDao.getPlatLogsExcelByOther(pageBean);
	}

	@Override
	public int countOfPlatLogsByOther(PlatLogPageBean pageBean) {
		return platLogDao.countOfPlatLogsByOther(pageBean);
	}


}