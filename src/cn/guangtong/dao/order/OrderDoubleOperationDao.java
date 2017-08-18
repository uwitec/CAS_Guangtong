package cn.guangtong.dao.order;

import java.util.List;
import java.util.Map;

import cn.guangtong.utils.PageBean;

/**
 * 未完成订单
 * @author 赵发志
 */
public interface OrderDoubleOperationDao {
	
	//查询订单+分页
	List<Map<String,Object>> orderQueryAll(@SuppressWarnings("rawtypes")PageBean pageBean);
	
	//订单总记录数
	Integer orderQueryCount();
	
}
