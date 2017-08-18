package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.SystemSettingDao;
import cn.guangtong.entity.cms.SystemSetting;
import cn.guangtong.service.cms.SystemSettingService;
import cn.guangtong.utils.SystemSettingPageBean;

@Service
@Transactional
public class SystemSettingServiceImpl implements SystemSettingService {

	@Autowired
	public SystemSettingDao systemSettingDao;

	@Transactional
	public List<SystemSetting> getSystemSettings(SystemSettingPageBean pageBean) {
		return systemSettingDao.getSystemSettings(pageBean);
	}

	@Transactional
	public int getCounts(SystemSettingPageBean pageBean) {
		return systemSettingDao.getCounts(pageBean);
	}

	@Transactional
	public void addSystemSetting(SystemSetting systemSetting) {
		try {
			systemSettingDao.addSystemSetting(systemSetting);
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

	@Transactional
	public void deleteSystemSetting(int id) {
		systemSettingDao.deleteSystemSetting(id);
	}

	@Transactional
	public void updateSystemSetting(SystemSetting systemSetting) {
		systemSettingDao.updateSystemSetting(systemSetting);
	}

	@Override
	public SystemSetting getSystemSetting(int id) {
		return systemSettingDao.getSystemSettingById(id);
	}

}
