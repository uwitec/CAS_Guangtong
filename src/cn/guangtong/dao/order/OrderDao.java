package cn.guangtong.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.order.DriverWithdraw;
import cn.guangtong.entity.order.OrderAdmin;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.utils.DriverWithdrawPageBean;
import cn.guangtong.utils.ExceptionsOrderPageBean;
import cn.guangtong.utils.OrderPageBean;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.ReassignOrderPageBean;

public interface OrderDao {

	// 添加订单
	public int insertorder();

	// 修改订单
	public int updateorder(UnfinishedOrder unfinishedOrder);

	// 订单列表
	public List<Map<String, Object>> orderlb(@Param("begin") int begin, @Param("pageCount") int pageCount);

	// 订单总数
	public int ordercount();

	// 指派司机
	public int assigndriver(int id, String driverId);

	// 未完成改派订单信息
	public List<Map<String, Object>> listChangeOrderInfo(@SuppressWarnings("rawtypes") PageBean pageBean);

	// 未完成改派订单总数
	public int getChangeOrderCount();

	// 已完成改派订单信息
	public List<Map<String, Object>> listChangeOrderInfoFinshed(@SuppressWarnings("rawtypes") PageBean pageBean);

	// 已完成改派订单总数
	public int getChangeOrderCountFinshed();

	// 司机提现申请查询
	public List<DriverWithdraw> listDriverWithdraw(DriverWithdrawPageBean driverWithdrawPageBean);

	// 司机提现申请记录数
	public int getDriverWithdrawCount(DriverWithdrawPageBean driverWithdrawPageBean);

	// 司机提现申请处理
	public void updateIsPay(DriverWithdraw driverWithdraw);

	// 根据经纬度查询符合车辆
	public List<Map<String, Object>> getArroundCar(Map<String, Double> map);

	// 模糊查询顾客名称
	public List<Map<String, Object>> customerNameFuzzy(String name);

	// 查询最后一次交易记录
	public List<Map<String, Object>> lastTransaction(String id);

	public int getCounts(OrderPageBean pageBean);

	public List<Map<String, Object>> getOrders(OrderPageBean pageBean);

	public int insertOrder(UnfinishedOrder unfinishedOrder);

	public int addAdminOrder(OrderAdmin orderAdmin);

	public int getReassignOrderCounts(ReassignOrderPageBean pageBean);

	// 根据id查询订单
	Map<String, Object> getOrderById(int id);

	// 根据id查询改派订单
	Map<String, Object> getReassignOrderById(int id);

	// 编辑订单
	boolean updateOrder(UnfinishedOrder unfinishedOrder);

	// 取消订单
	boolean cancelOrder(int id);

	// 根据id查询司机信息
	Map<String, Object> getDriverInfoById(int id);

	// 统计司机总数
	int getDriverInfoCount();

	// 给司机指派订单
	boolean appointOrderById(Map<String, Object> map);

	public List<Map<String, Object>> getReassignOrders(ReassignOrderPageBean pageBean);

	public int getExceptionsOrderCounts(ExceptionsOrderPageBean pageBean);

	// 异常订单
	public List<Map<String, Object>> getExceptionsOrders(ExceptionsOrderPageBean pageBean);

	public List<Map<String, Object>> getDriverInfo(PageBean pageBean);

	/**
	 * 根据id查询订单对象
	 * @param id
	 * @return
	 */
	UnfinishedOrder getUnfinishedOrderById(Long id);
	
	/**
	 * 根据id删除订单
	 * @param id
	 * @return
	 */
	int deleteById(Long id);
	UnfinishedOrder	getWeightByOrderNum(String orderNum);

	public UnfinishedOrder getUnfinishedOrderByNum(String ordernum);
}
