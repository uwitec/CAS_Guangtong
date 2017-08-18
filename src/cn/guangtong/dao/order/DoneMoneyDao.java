package cn.guangtong.dao.order;

import cn.guangtong.entity.order.UnfinishedMoney;

/**
 * 已完成订单
 * 赵发志
 */
public interface DoneMoneyDao {
	//插入一条记录
	Integer insertDoneMoney(UnfinishedMoney unfinishedMoney);
}
