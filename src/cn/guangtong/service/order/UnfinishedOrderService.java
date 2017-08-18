package cn.guangtong.service.order;

import java.util.Map;

import cn.guangtong.entity.order.UnfinishedOrder;

/**
 * 未完成订单 赵发志
 */
public interface UnfinishedOrderService {

	Integer updateOrderEdit(UnfinishedOrder unfinishedOrder);

	Integer orderAbnormal(String exceptionReason, String orderNum);

	/*
	 * //查询一条未完成订单记录 Map<String,Object> sUnfinishedOrderOne(String orderNum);
	 * 
	 * //删除orderNum订单 Integer deleteUnfinishedOrderNum(@Param("orderNum")String orderNum);
	 * 
	 * //查询orderNum的未完成记录 List<UnfinishedOrder> queryUnfinishedOrderNum(String orderNum);
	 * 
	 * //新建Order订单 Integer iUnfinishedOrder(UnfinishedOrder unfinishedOrder);
	 * 
	 * //修改订单 Integer uUnfinishedOrder(UnfinishedOrder unfinishedOrder);
	 * 
	 * //增加异常信息 Integer uUnfinishedOrderAbnormal(@Param("exceptionReason")String exceptionReason,@Param("orderNum")String orderNum);
	 * 
	 * //新建订单 Integer iNewOrder(UnfinishedOrder unfinishedOrder,UnfinishedMoney unfinishedMoney);
	 * 
	 * //编辑订单 Integer iEditOrder(UnfinishedOrder unfinishedOrder,UnfinishedMoney unfinishedMoney);
	 */
}
