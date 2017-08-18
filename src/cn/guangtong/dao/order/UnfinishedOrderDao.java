package cn.guangtong.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.utils.PageBean;

/**
 * 未完成订单
 * @author 赵发志
 */
public interface UnfinishedOrderDao {
	
	//查询一条未完成订单记录
	Map<String,Object> sUnfinishedOrderOne(@Param("orderNum")String orderNum);
	
	//查询orderNum的未完成记录
	List<UnfinishedOrder> queryUnfinishedOrderNum(@Param("orderNum")String orderNum);
	
	//删除orderNum订单
	Integer deleteUnfinishedOrderNum(@Param("orderNum")String orderNum);
	
	//修改状态信息
	Integer updateOrderStatus(@Param("orderNum")String orderNum,@Param("orderStatus")Integer orderStatus);

	//新建Order订单
	Integer iUnfinishedOrder(UnfinishedOrder unfinishedOrder);
	
	//修改订单
	Integer updateOrderEdit(UnfinishedOrder unfinishedOrder);
	
	//增加异常信息
	Integer orderAbnormal(@Param("exceptionReason")String exceptionReason,@Param("orderNum")String orderNum);
	
}
