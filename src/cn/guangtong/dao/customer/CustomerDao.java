package cn.guangtong.dao.customer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.customer.CommonDeliver;
import cn.guangtong.entity.customer.CommonReceipt;
import cn.guangtong.entity.customer.CustomerInfo;
import cn.guangtong.excel.Customer;
import cn.guangtong.utils.CustomerInfoPageBean;

public interface CustomerDao {
	/**
	 * 分页显示客户信息
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<CustomerInfo> getCustomers(CustomerInfoPageBean pageBean);

	/**
	 * 统计客户信息总数
	 * 
	 * @return
	 */
	public int getCounts();
	
	/**
	 *增加一名客戶信息
	 */
	Integer iCustomerInfo(CustomerInfo customerInfo);
	
	/**
	 * 修改一名客戶信息
	 */
	Integer uCustomerInfo(CustomerInfo customerInfo);
	
	/**
	 * 删除一名客戶信息
	 */
	Integer dCustomerInfo(@Param("isDel")String isDel, @Param("id")String id);
	
	/**
	 * 根据Id查询一名客户信息
	 * @param id
	 * @return
	 */
	List<String> sCustomerInfoById(@Param("id")String id);

	/**
	 * 导出客户信息
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<Customer> getCustomersExcel(CustomerInfoPageBean pageBean);

	public CommonReceipt getCommonReceiptByCondition(String company,String customerId);

	public CommonDeliver getCommonDeliverByCondition(String company,String customerId);

	public void updateCommonReceipt(Map<String, Object> args);

	public void addCommonReceipt(CommonReceipt add_commonReceipt);

	public void updateCommonDeliver(Map<String, Object> args);

	public void addCommonDeliver(CommonDeliver add_commonDeliver);

	public List<Customer> getCustomerLikeCname(String cName);
	/**
	 * 客户地理位置信息饼状图
	 * @return
	 */
	List<Map<String, Object>>	getCustomersPie();
}
