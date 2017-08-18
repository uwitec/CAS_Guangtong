package cn.guangtong.dao.order;

import cn.guangtong.entity.order.UnfinishedOrder;

/**
 * 已完成订单
 * 赵发志
 */
public interface DoneOrderDao {
	
	//插入一条记录
	Integer iDoneOrder(UnfinishedOrder unfinishedOrder);
}
