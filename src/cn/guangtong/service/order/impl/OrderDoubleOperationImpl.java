package cn.guangtong.service.order.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.order.OrderDoubleOperationDao;
import cn.guangtong.service.order.OrderDoubleOperationService;
import cn.guangtong.utils.PageBean;
/**
 * 双订单操作
 * 赵发志
 */
@Service
public class OrderDoubleOperationImpl  implements OrderDoubleOperationService{
	
	@Autowired
	private OrderDoubleOperationDao orderDoubleOperationDao;
	
	public OrderDoubleOperationDao getOrderDoubleOperationDao() {
		return orderDoubleOperationDao;
	}

	//查询订单信息
	public List<Map<String, Object>> orderQueryAll(@SuppressWarnings("rawtypes") PageBean pageBean) {
		pageBean.setTotalCount(getOrderDoubleOperationDao().orderQueryCount());
		System.out.println(getOrderDoubleOperationDao().orderQueryCount());
		List<Map<String, Object>> list = getOrderDoubleOperationDao().orderQueryAll(pageBean);
		return list;
	}
	
}
