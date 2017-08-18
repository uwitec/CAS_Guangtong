package cn.guangtong.service.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.order.UnfinishedMoneyDao;
import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.service.order.UnfinishedMoneyService;

/**
 * 未完成订单金额
 * 赵发志
 */
@Service
public class UnfinishedMoneyImpl implements UnfinishedMoneyService{
	@Autowired
	private UnfinishedMoneyDao unfinishedMoneyDao;
	
	public UnfinishedMoneyDao getUnfinishedMoneyDao() {
		return unfinishedMoneyDao;
	}

	//新建订单
	public Integer iUnfinishedMoney(UnfinishedMoney unfinishedMoney) {
		return getUnfinishedMoneyDao().iUnfinishedMoney(unfinishedMoney);
	}


	//查询
	public List<UnfinishedMoney> queryUnfinishedMoneyNum(String orderNum) {
		return getUnfinishedMoneyDao().queryUnfinishedMoneyNum(orderNum);
	}

	//删除
	public Integer deleteUnfinishedMoneyNum(String orderNum) {
		return getUnfinishedMoneyDao().deleteUnfinishedMoneyNum(orderNum);
	}

	@Override
	public int insertMoney(UnfinishedMoney unfinishedMoney) {
		return getUnfinishedMoneyDao().insertMoney(unfinishedMoney);
	}
	@Override
	public boolean updateMoneyEdit(UnfinishedMoney unfinishedMoney) {
		return unfinishedMoneyDao.updateMoneyEdit(unfinishedMoney);
	}
	@Override
	public UnfinishedMoney getMoneyByNum(String orderNum) {
		return unfinishedMoneyDao.getMoneyByNum(orderNum);
	}

	
}
