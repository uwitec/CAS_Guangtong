package cn.guangtong.service.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.entity.order.UnfinishedOrder;
/**
 * 未完成订单金额
 * 赵发志
 */
public interface UnfinishedMoneyService {
	
	//新建订单
	Integer iUnfinishedMoney(UnfinishedMoney unfinishedMoney);
	
	
	//查询orderNum的未完成记录
	List<UnfinishedMoney> queryUnfinishedMoneyNum(@Param("orderNum")String orderNum);

	//删除orderNum的未完成记录
	Integer deleteUnfinishedMoneyNum(String orderNum);

	int insertMoney(UnfinishedMoney unfinishedMoney);
	/**
	 * 编辑订单
	 * @param unfinishedMoney
	 * @return
	 */
	boolean updateMoneyEdit(UnfinishedMoney unfinishedMoney);
	/**
	 * 根据orderNum查询
	 * @param orderNum
	 * @return
	 */
	UnfinishedMoney getMoneyByNum(String orderNum);

}
