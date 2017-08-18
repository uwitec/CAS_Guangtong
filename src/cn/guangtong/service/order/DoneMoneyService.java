package cn.guangtong.service.order;

import cn.guangtong.entity.order.UnfinishedMoney;
/**
 * 订单管理
 * 赵发志
 */
public interface DoneMoneyService {
	
	//插入一条记录
	Integer insertDoneMoney(UnfinishedMoney unfinishedMoney);
}
