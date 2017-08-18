package cn.guangtong.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.order.DriverChargebackDao;
import cn.guangtong.entity.order.DriverChargeback;
import cn.guangtong.service.order.DriverChargebackService;
/**
 * 司机扣款
 * 赵发志
 */
@Service
public class DriverChargebackImpl implements DriverChargebackService {

	@Autowired
	private DriverChargebackDao driverChargebackDao;
	
	public DriverChargebackDao getDriverChargebackDao() {
		return driverChargebackDao;
	}

	//司机扣款
	public Integer iDriverChargeback(DriverChargeback driverChargeback) {
		return getDriverChargebackDao().iDriverChargeback(driverChargeback);
	}

}
