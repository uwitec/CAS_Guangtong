package cn.guangtong.service.customer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.guangtong.dao.customer.CustomerDao;
import cn.guangtong.entity.customer.CommonDeliver;
import cn.guangtong.entity.customer.CommonReceipt;
import cn.guangtong.entity.customer.CustomerInfo;
import cn.guangtong.excel.Customer;
import cn.guangtong.service.customer.CustomerService;
import cn.guangtong.utils.CustomerInfoPageBean;

/**
 * @author chenjunpeng
 * @date 2017年3月4日
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	/**
	 * @Description:
	 * @author 陈俊朋
	 * @date 2017年3月4日 下午5:54:40
	 * @param pageBean
	 * @return
	 */
	@Override
	public List<CustomerInfo> getCustomerInfos(CustomerInfoPageBean pageBean) {
		return customerDao.getCustomers(pageBean);
	}

	/**
	 * @Description:
	 * @author 陈俊朋
	 * @date 2017年3月4日 下午5:54:40
	 * @return
	 */
	@Override
	public int getCounts() {
		return customerDao.getCounts();
	}

	@Override
	public Integer iCustomerInfo(CustomerInfo customerInfo) {
		return customerDao.iCustomerInfo(customerInfo);
	}

	@Override
	public Integer uCustomerInfo(CustomerInfo customerInfo) {
		return customerDao.uCustomerInfo(customerInfo);
	}

	@Override
	public Integer dCustomerInfo(String isDel, String id) {
		return customerDao.dCustomerInfo(isDel, id);
	}

	@Override
	public List<String> sCustomerInfoById(String id) {
		return customerDao.sCustomerInfoById(id);
	}

	@Override
	public List<Customer> getCustomersExcel(CustomerInfoPageBean pageBean) {
		return customerDao.getCustomersExcel(pageBean);
	}

	@Override
	public CommonReceipt getCommonReceiptByCondition(String company,String customerId) {
		return customerDao.getCommonReceiptByCondition(company,customerId);
	}

	@Override
	public CommonDeliver getCommonDeliverByCondition(String company,String customerId) {
		return customerDao.getCommonDeliverByCondition(company,customerId);
	}

	@Override
	public void updateCommonReceipt(Map<String, Object> args) {
		customerDao.updateCommonReceipt(args);
	}

	@Override
	public void addCommonReceipt(CommonReceipt add_commonReceipt) {
		customerDao.addCommonReceipt(add_commonReceipt);
	}

	@Override
	public void updateCommonDeliver(Map<String, Object> args) {
		customerDao.updateCommonDeliver(args);
	}

	@Override
	public void addCommonDeliver(CommonDeliver add_commonDeliver) {
		customerDao.addCommonDeliver(add_commonDeliver);
	}

	@Override
	public List<Customer> getCustomerLikeCname(String cName) {
		return customerDao.getCustomerLikeCname(cName);
	}
	@Override
	public Map<String, Object> getCustomersPie(String type, String name) {
		    //所需全部信息
				Map<String, Object> map = new HashMap<String, Object>();
				//饼图data
				List<Object> listAll = new ArrayList<Object>();
				//处理饼图数据封装进listAll
				List<Map<String, Object>> platForms=customerDao.getCustomersPie();
				for (Map<String, Object> platForm : platForms) {
					List<Object> list=new ArrayList<Object>();
					list.add((String) platForm.get("addr"));
					list.add(platForm.get("count"));
					listAll.add(list);
				}
				map.put("type", type);
				map.put("data", listAll);
				map.put("title", name);
				return map;
	}

}
