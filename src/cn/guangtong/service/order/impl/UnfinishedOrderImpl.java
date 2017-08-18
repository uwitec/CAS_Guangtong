package cn.guangtong.service.order.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.order.UnfinishedMoneyDao;
import cn.guangtong.dao.order.UnfinishedOrderDao;
import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.service.order.UnfinishedOrderService;
import cn.guangtong.utils.PageBean;

/**
 * 未完成订单
 * 赵发志
 */
@Service
@Transactional
public class UnfinishedOrderImpl implements UnfinishedOrderService {
	
	@Autowired
	private UnfinishedOrderDao UnfinishedOrderDao;
	
	@Autowired
	private UnfinishedMoneyDao unfinishedMoneyDao;

	public UnfinishedOrderDao getUnfinishedOrderDao() {
		return UnfinishedOrderDao;
	}
	
	public UnfinishedMoneyDao getUnfinishedMoneyDao() {
		return unfinishedMoneyDao;
	}
	
	
	//编辑订单
	public Integer updateOrderEdit(UnfinishedOrder unfinishedOrder){
		return getUnfinishedOrderDao().updateOrderEdit(unfinishedOrder);		
	}

	//异常信息
	public Integer orderAbnormal(String exceptionReason, String orderNum) {
		return getUnfinishedOrderDao().orderAbnormal(exceptionReason, orderNum);
	}
	
	/*
	//查询一条未完成订单记录
	public Map<String,Object> sUnfinishedOrderOne(String orderNum) {
		return getUnfinishedOrderDao().sUnfinishedOrderOne(orderNum);
	}
	
	//查询orderNum的未完成记录
	public List<UnfinishedOrder> queryUnfinishedOrderNum(String orderNum){
		return getUnfinishedOrderDao().queryUnfinishedOrderNum(orderNum);
	}
	
	//删除orderNum订单
	public Integer deleteUnfinishedOrderNum(String orderNum){
		return getUnfinishedOrderDao().deleteUnfinishedOrderNum(orderNum);
	}

	//新建订单
	public Integer iUnfinishedOrder(UnfinishedOrder unfinishedOrder) {
		return getUnfinishedOrderDao().iUnfinishedOrder(unfinishedOrder);
	}

	//修改订单
	public Integer uUnfinishedOrder(UnfinishedOrder unfinishedOrder) {
		return getUnfinishedOrderDao().uUnfinishedOrder(unfinishedOrder);
	}
	
	

	//增加异常信息
	public Integer uUnfinishedOrderAbnormal(String exceptionReason, String orderNum) {
		return getUnfinishedOrderDao().uUnfinishedOrderAbnormal(exceptionReason,orderNum);
	}

	//新建订单
	@Transactional
	public Integer iNewOrder(UnfinishedOrder unfinishedOrder,UnfinishedMoney unfinishedMoney) {
		try{
			//自动生成订单id
			StringBuffer orderNum = new StringBuffer();
			orderNum.append("ZT");
			orderNum.append(FormatDateUtils.getDate(3));
			orderNum.append(getUnfinishedOrderDao().sUnfinishedOrderCount()+10000000);
			unfinishedMoney.setOrdernum(orderNum.toString());
			unfinishedOrder.setOrdernum(orderNum.toString());
			Integer resultOrder = getUnfinishedOrderDao().iUnfinishedOrder(unfinishedOrder);
			Integer resultMoney = getUnfinishedMoneyDao().iUnfinishedMoney(unfinishedMoney);
			return resultOrder==1&&resultMoney==1?1:0;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
	}
	
	//编辑订单
	@Transactional
	public Integer iEditOrder(UnfinishedOrder unfinishedOrder,UnfinishedMoney unfinishedMoney){
		try{
			Integer resultOrder = getUnfinishedOrderDao().uUnfinishedOrder(unfinishedOrder);
			Integer resultMoney = getUnfinishedMoneyDao().uUnfinishedMoney(unfinishedMoney);
			return resultOrder==1&&resultMoney==1?1:0;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
	}
	
	*/
}
