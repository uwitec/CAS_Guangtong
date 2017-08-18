package cn.guangtong.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.order.UnfinishedMoney;

/**
 * 未完成订单
 * 赵发志
 */
public interface UnfinishedMoneyDao {
	//新建订单
	Integer iUnfinishedMoney(UnfinishedMoney unfinishedMoney);

	
	//查询orderNum的未完成记录
	List<UnfinishedMoney> queryUnfinishedMoneyNum(@Param("orderNum")String orderNum);
	
	//删除orderNum的未完成记录
	Integer deleteUnfinishedMoneyNum(@Param("orderNum")String orderNum);
	
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
