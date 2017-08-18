package cn.guangtong.service.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.order.DoneOrderDao;
import cn.guangtong.dao.order.UnfinishedMoneyDao;
import cn.guangtong.dao.order.UnfinishedOrderDao;
import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.service.order.OperationOrderService;
import cn.guangtong.utils.ScopeTimeUtil;
/**
 * 订单管理已完成（Money）
 * 赵发志
 */
@Service
@Transactional
public class OperationOrderImpl  implements OperationOrderService{
	
	@Autowired
	private UnfinishedOrderDao UnfinishedOrderDao;
	
	@Autowired
	private UnfinishedMoneyDao UnfinishedMoeryDao;
	
	@Autowired
	private DoneOrderDao doneOrderDao;
	
	@Autowired
	private DoneOrderDao doneMoneyDao;
	

	public UnfinishedOrderDao getUnfinishedOrderDao() {
		return UnfinishedOrderDao;
	}


	public UnfinishedMoneyDao getUnfinishedMoeryDao() {
		return UnfinishedMoeryDao;
	}


	public DoneOrderDao getDoneOrderDao() {
		return doneOrderDao;
	}


	public DoneOrderDao getDoneMoneyDao() {
		return doneMoneyDao;
	}


	/**
	 * 完成订单
	 */
	public void orderComplete(String orderNum) {
		try{
			//查询未完成订单信息
			List<UnfinishedOrder> unfinishedOrders = getUnfinishedOrderDao().queryUnfinishedOrderNum(orderNum);
			//查询未完成订单钱数
			List<UnfinishedMoney> unfinishedMoneys = getUnfinishedMoeryDao().queryUnfinishedMoneyNum(orderNum);
			
			//将未完成订单信息插入到已完成订单信息
			for(int i =0 ; i<unfinishedOrders.size();i++){
				unfinishedOrders.get(i).setId(null);
				if(i==0){
					unfinishedOrders.get(i).setOrderstatus(1);
					//将当前信息插入到已完成订单信息
					getDoneOrderDao().iDoneOrder(unfinishedOrders.get(i));
					continue;
				}else{
					//将当前信息插入到已完成订单信息
					
					getDoneOrderDao().iDoneOrder(unfinishedOrders.get(i));
				}
			}
			//将未完成订单钱数表插入到已完成订单钱数表
			for(int i =0 ; i<unfinishedMoneys.size();i++){
				getDoneMoneyDao().iDoneOrder(unfinishedOrders.get(i));
			}
			getUnfinishedMoeryDao().deleteUnfinishedMoneyNum(orderNum);
			getUnfinishedOrderDao().deleteUnfinishedOrderNum(orderNum);
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	/**
	 * 取消订单
	 * 赵发志
	 */
	public void orderCancellation(String orderNum){
		try{
			Integer orderStatus = 7;//已取消状态
			orderStatus(orderNum, orderStatus);
			orderComplete(orderNum);
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	}

	//编辑订单
	public void orderEdit(UnfinishedMoney unfinishedMoney, UnfinishedOrder unfinishedOrder) {
		try{
			getUnfinishedMoeryDao().updateMoneyEdit(unfinishedMoney);
			getUnfinishedOrderDao().updateOrderEdit(unfinishedOrder);
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	}
	
	/**
	 * 修改订单状态（0：待结算；1完成；2改派；5:异常;6：已关闭；7：已取消）
	 * 赵发志
	 */
	public void orderStatus(String orderNum,Integer orderStatus){
		getUnfinishedOrderDao().updateOrderStatus(orderNum, orderStatus);
	}


	
	
	
	
}
