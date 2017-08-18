package cn.guangtong.service.cms;

import java.util.List;

import cn.guangtong.entity.cms.CustomerInformation;
import cn.guangtong.utils.CustomerInformationPageBean;

public interface CustomerInformationService {
	CustomerInformation getInformationById(int id);
	int delete(int id);

	int insert(CustomerInformation record);

	int update(CustomerInformation record);
	
	List<CustomerInformation> getInformations(CustomerInformationPageBean pageBean);
	
	int getCounts(CustomerInformationPageBean pageBean);
	
}
