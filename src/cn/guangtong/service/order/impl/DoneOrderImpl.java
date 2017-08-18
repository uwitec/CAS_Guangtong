package cn.guangtong.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.order.DoneOrderDao;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.service.order.DoneOrderService;
/**
 * 订单管理
 * 赵发志
 */
@Service
public class DoneOrderImpl  implements DoneOrderService{
	
	@Autowired
	private DoneOrderDao doneOrderDao;
	
	public DoneOrderDao getDoneOrderDao() {
		return doneOrderDao;
	}
	
	//插入一条记录
	public Integer iDoneOrder(UnfinishedOrder unfinishedOrder) {
		return getDoneOrderDao().iDoneOrder(unfinishedOrder);
	}

	
}
