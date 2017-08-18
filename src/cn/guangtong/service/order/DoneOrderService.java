package cn.guangtong.service.order;

import cn.guangtong.entity.order.UnfinishedOrder;
/**
 * 订单管理
 * 赵发志
 */
public interface DoneOrderService {
	
	//插入一条记录
	Integer iDoneOrder(UnfinishedOrder unfinishedOrder);
	
}
