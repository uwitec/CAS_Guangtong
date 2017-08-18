package cn.guangtong.service.order;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.order.DriverWithdraw;
import cn.guangtong.entity.order.OrderAdmin;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.utils.DriverWithdrawPageBean;
import cn.guangtong.utils.ExceptionsOrderPageBean;
import cn.guangtong.utils.OrderPageBean;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.ReassignOrderPageBean;

public interface OrderService {

	/**
	 * 订单列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> orderlb(int begin, int pageCount);

	/**
	 * 订单总数
	 */
	int ordercount();

	/**
	 * 新增订单
	 */
	int insertorder();

	/**
	 * 根据id查询改派订单
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> getReassignOrderById(int id);

	/**
	 * 根据id查询订单
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> getOrderById(int id);

	/**
	 * 编辑订单
	 * 
	 * @param unfinishedOrder
	 * @return
	 */
	boolean updateOrder(UnfinishedOrder unfinishedOrder);

	/**
	 * 取消订单
	 * 
	 * @param id
	 * @return
	 */
	boolean cancelOrder(int id);

	/**
	 * 查询司机信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getDriverInfo(PageBean pageBean);

	/**
	 * 统计司机总数
	 * 
	 * @return
	 */
	int getDriverInfoCount();

	/**
	 * 给司机指派订单
	 * 
	 * @param map
	 * @return
	 */
	boolean appointOrderById(Map<String, Object> map);
	/**
	 * 根据订单编号查询载重
	 * @param orderNum
	 * @return
	 */
	UnfinishedOrder	getWeightByOrderNum(String orderNum);
	/**
	 * 提现申请
	 * 
	 * @param id
	 * @return
	 */
	int txsq(String id);

	/**
	 * 指派司机
	 * 
	 * @param id
	 * @return
	 */
	int assigndriver(int id, String driverId);

	/**
	 * 改派订单信息
	 * 
	 * @author Luzhaopeng
	 * @param currentPage
	 * @param pageCount
	 */
	PageBean<Map<String, Object>> listChangeOrderInfo(PageBean pageBean, int status);

	/**
	 * 司机提现申请查询
	 * 
	 * @author Luzhaopeng
	 * 
	 */
	PageBean<Map<String, Object>> listDriverWithdraw(DriverWithdrawPageBean driverWithdrawPageBean);

	/**
	 * 司机提现申请处理
	 * 
	 * @author Luzhaopeng
	 * 
	 */
	void updateIsPay(DriverWithdraw driverWithdraw);

	// 导出
	List<DriverWithdraw> exportDriverWithdraw(DriverWithdrawPageBean driverWithdrawPageBean);

	// 查询这个范围是否有车
	public String getArroundCar(double lon, double lat);

	/**
	 * 模糊查询顾客姓名
	 */
	public List<Map<String, Object>> fuzzyCustomerName(String name);

	/**
	 * 查询最后一次交易记录
	 */
	public List<Map<String, Object>> lastTransaction(String id);

	/**
	 * 
	 * 名称: 查询所有订单个数 描述:
	 * 
	 * @author sutong
	 * @date 2017年5月31日 下午1:50:39
	 */
	int getCounts(OrderPageBean pageBean);

	/**
	 * 
	 * 名称: 查询所有订单 描述:
	 * 
	 * @author sutong
	 * @date 2017年5月31日 下午1:50:18
	 */
	List<Map<String, Object>> getOrders(OrderPageBean pageBean);

	/**
	 * 
	 * 名称: 创建订单 描述:
	 * 
	 * @author sutong
	 * @return
	 * @date 2017年5月31日 下午2:38:07
	 */
	int insertOrder(UnfinishedOrder unfinishedOrder);

	/**
	 * 
	 * 名称: 创建管理员订单 描述:
	 * 
	 * @author sutong
	 * @date 2017年5月31日 下午3:44:25
	 */
	int addAdminOrder(OrderAdmin orderAdmin);

	/**
	 * 
	 * 名称: 改派订单数量 描述:
	 * 
	 * @author sutong
	 * @date 2017年6月1日 上午9:26:19
	 */
	int getReassignOrderCounts(ReassignOrderPageBean pageBean);

	/**
	 * 
	 * 名称: 改派订单列表 描述:
	 * 
	 * @author sutong
	 * @date 2017年6月1日 上午9:27:32
	 */
	List<Map<String, Object>> getReassignOrders(ReassignOrderPageBean pageBean);

	/**
	 * 
	 * 名称:异常订单数量 描述:
	 * 
	 * @author sutong
	 * @date 2017年6月1日 上午9:48:55
	 */
	int getExceptionsOrderCounts(ExceptionsOrderPageBean pageBean);

	/**
	 * 
	 * 名称:异常订单表 描述:
	 * 
	 * @author sutong
	 * @date 2017年6月1日 上午9:49:18
	 */
	List<Map<String, Object>> getExceptionsOrders(ExceptionsOrderPageBean pageBean);

	/**
	 * 重建订单
	 * @param unfinishedOrder
	 * @return
	 */
	boolean rebuildsOrder(UnfinishedOrder unfinishedOrder);
	
	/**
	 * 
	 * 名称: 根据orderNum查询
	 * 描述: 
	 * @author sutong
	 * @date 2017年6月8日 下午3:48:40
	 */
	UnfinishedOrder getUnfinishedOrderByNum(String ordernum);
}
