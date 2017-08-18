package cn.guangtong.service.order;

import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.entity.order.UnfinishedOrder;

/**
 * 完成订单
 * 赵发志
 */
public interface OperationOrderService {
	
	//完成订单
	void orderComplete(String orderNum);
	
	//取消订单
	void orderCancellation(String orderNum);
	
	//编辑订单
	void orderEdit(UnfinishedMoney unfinishedMoney,UnfinishedOrder unfinishedOrder);
	
	//修改订单状态
	void orderStatus(String orderNum,Integer orderStatus);
	
}
