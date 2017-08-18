package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.InformationDao;
import cn.guangtong.entity.cms.Information;
import cn.guangtong.service.cms.InformationService;
import cn.guangtong.utils.InformationPageBean;

@Service
@Transactional
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationDao InformationDao;

	public List<Information> getInformations(InformationPageBean pageBean) {
		return InformationDao.getInformations(pageBean);
	}

	@Override
	public int getCounts() {
		return InformationDao.getCounts();
	}

	public void addInformation(Information Information) {
		try {
			InformationDao.addInformation(Information);
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	public void deleteInformation(int id) {
		InformationDao.deleteInformation(id);
	}

	public void updateInformation(Information Information) {
		InformationDao.updateInformation(Information);
	}

	public Information getInformationById(int id) {
		return InformationDao.getInformationById(id);
	}

}
