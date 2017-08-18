package cn.guangtong.service.order.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.order.ExceptionReasonDao;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.service.order.ExceptionReasonService;
import cn.guangtong.utils.PageBean;

/**
 * 异常订单
 * 赵发志
 */
@Service
public class ExceptionReasonImpl implements ExceptionReasonService{
	@Autowired
	private ExceptionReasonDao exceptionReasonDao;

	public ExceptionReasonDao getExceptionReasonDao() {
		return exceptionReasonDao;
	}

	//异常订单查询+分页
	public List<Map<String, Object>> sExceptionReasonA(@SuppressWarnings("rawtypes") PageBean pageBean) {
		return getExceptionReasonDao().sExceptionReasonA(pageBean);
	}

	//异常订单(取消)按钮
	public Integer uManagementOrder(String orderNum) {
		return getExceptionReasonDao().uManagementOrder(orderNum);
	}

	//重建订单
	public Integer uRebuildOrderOrder(UnfinishedOrder unfinishedOrder) {
		return getExceptionReasonDao().uRebuildOrderOrder(unfinishedOrder);
	}

	//重建订单查询
	public Map<String,Object> sManagementOrderOne(String orderNum) {
		return getExceptionReasonDao().sManagementOrderOne(orderNum);
	}

	//订单总记录数
	public Integer sManagementOrderCount() {
		return getExceptionReasonDao().sManagementOrderCount();
	}

}
