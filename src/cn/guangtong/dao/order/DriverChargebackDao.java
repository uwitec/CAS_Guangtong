package cn.guangtong.dao.order;

import cn.guangtong.entity.order.DriverChargeback;

/**
 * 司机扣款
 * 赵发志
 */
public interface DriverChargebackDao {
	//插入扣款信息
	Integer iDriverChargeback(DriverChargeback driverChargeback);
}
