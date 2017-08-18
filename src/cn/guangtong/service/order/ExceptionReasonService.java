package cn.guangtong.service.order;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.utils.PageBean;

/**
 * 异常订单
 * 赵发志
 */
public interface ExceptionReasonService {

	//异常订单查询+分页
	List<Map<String,Object>> sExceptionReasonA(@SuppressWarnings("rawtypes") PageBean pageBean);
	
	//异常订单(取消)按钮
	Integer uManagementOrder(String orderNum);
	
	//重建订单
	Integer uRebuildOrderOrder(UnfinishedOrder unfinishedOrder);
	
	//重建订单查询
	Map<String,Object> sManagementOrderOne(String orderNum);
	
	//订单总记录数
	Integer sManagementOrderCount();
}
