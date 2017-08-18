package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.cms.CustomerInformationDao;
import cn.guangtong.entity.cms.CustomerInformation;
import cn.guangtong.service.cms.CustomerInformationService;
import cn.guangtong.utils.CustomerInformationPageBean;
@Service
public class CustomerInformationServiceImpl implements CustomerInformationService {

	@Autowired
	private CustomerInformationDao customerInformationDao;
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return customerInformationDao.delete(id);
	}

	@Override
	public int insert(CustomerInformation record) {
		// TODO Auto-generated method stub
		return customerInformationDao.insert(record);
	}

	@Override
	public int update(CustomerInformation record) {
		// TODO Auto-generated method stub
		return customerInformationDao.update(record);
	}

	@Override
	public List<CustomerInformation> getInformations(CustomerInformationPageBean pageBean) {
		// TODO Auto-generated method stub
		return customerInformationDao.getInformations(pageBean);
	}

	@Override
	public int getCounts(CustomerInformationPageBean pageBean) {
		// TODO Auto-generated method stub
		return customerInformationDao.getCounts(pageBean);
	}

	@Override
	public CustomerInformation getInformationById(int id) {
		// TODO Auto-generated method stub
		return customerInformationDao.getInformationById(id);
	}

}
