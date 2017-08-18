package cn.guangtong.service.order;

import java.util.List;
import java.util.Map;

import cn.guangtong.utils.PageBean;

/**
 * 双订单操作
 * 赵发志
 */
public interface OrderDoubleOperationService {
	
	//查询订单+分页
	List<Map<String,Object>> orderQueryAll(@SuppressWarnings("rawtypes")PageBean pageBean);
	
}
