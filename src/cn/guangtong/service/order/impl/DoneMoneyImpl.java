package cn.guangtong.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.order.DoneMoneyDao;
import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.service.order.DoneMoneyService;
/**
 * 订单管理已完成（Money）
 * 赵发志
 */
@Service
public class DoneMoneyImpl  implements DoneMoneyService{
	
	@Autowired
	private DoneMoneyDao doneMoneyDao;

	public DoneMoneyDao getDoneMoneyDao() {
		return doneMoneyDao;
	}

	//插入到已完成订单钱数
	public Integer insertDoneMoney(UnfinishedMoney unfinishedMoney) {
		return getDoneMoneyDao().insertDoneMoney(unfinishedMoney);
	}
	
	
	
}
