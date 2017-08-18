package cn.guangtong.service.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.order.DoneOrderDao;
import cn.guangtong.dao.order.OrderDao;
import cn.guangtong.dao.order.UnfinishedMoneyDao;
import cn.guangtong.entity.order.DriverWithdraw;
import cn.guangtong.entity.order.OrderAdmin;
import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.service.order.OrderService;
import cn.guangtong.utils.DistanceUtil;
import cn.guangtong.utils.DriverWithdrawPageBean;
import cn.guangtong.utils.ExceptionsOrderPageBean;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.OrderPageBean;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.RandomUtils;
import cn.guangtong.utils.ReassignOrderPageBean;

@Service
@Transactional
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UnfinishedMoneyDao unfinishedMoneyDao;
	
	@Autowired
	private DoneOrderDao doneOrderDao;
   /**
	 * 添加订单
	 */
   @Override
	public int insertorder() {
		return 0;
	}
   /**
    * 订单列表
    */
   @Override
	public List<Map<String, Object>> orderlb(int begin,int pageCount) {
	   return orderDao.orderlb(begin,pageCount);
	}
   /**
    * 订单总数
    */
   @Override
	public int ordercount() {
		return orderDao.ordercount();
	}
   /**
    * 提现申请
    */
   @Override
	public int txsq(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
   
   /**
    * 指派
    */
   @Override
    public int assigndriver(int id,String driverId) {
	return orderDao.assigndriver(id,driverId);
   }
   
   
   /**
    * 改派订单信息
    */
	@Override
	public PageBean<Map<String, Object>> listChangeOrderInfo(PageBean pageBean,int status) {
		if(pageBean.getCurrentPage() ==0){
			pageBean.setCurrentPage(1);		
		}
		if(pageBean.getPageCount() ==0){
			pageBean.setPageCount(10);
		}
		if(status == 0){
			pageBean.setBeanList(orderDao.listChangeOrderInfo(pageBean));
			pageBean.setTotalCount(orderDao.getChangeOrderCount());
		}else if(status == 1){
			pageBean.setBeanList(orderDao.listChangeOrderInfoFinshed(pageBean));
			pageBean.setTotalCount(orderDao.getChangeOrderCountFinshed());
		}
		return pageBean;
	}
	//司机提现申请查询
	@Override
	public DriverWithdrawPageBean listDriverWithdraw(DriverWithdrawPageBean driverWithdrawPageBean) {
		if(driverWithdrawPageBean.getCurrentPage() ==0){
			driverWithdrawPageBean.setCurrentPage(1);		
		}
		if(driverWithdrawPageBean.getPageCount() ==0){
			driverWithdrawPageBean.setPageCount(10);
		}
		driverWithdrawPageBean.setBeanList(orderDao.listDriverWithdraw(driverWithdrawPageBean));
		driverWithdrawPageBean.setTotalCount(orderDao.getDriverWithdrawCount(driverWithdrawPageBean));
		return driverWithdrawPageBean;
	}
	//司机提现申请处理
	@Override
	public void updateIsPay(DriverWithdraw driverWithdraw) {
		orderDao.updateIsPay(driverWithdraw);
	}
	@Override
	public List<DriverWithdraw> exportDriverWithdraw(DriverWithdrawPageBean driverWithdrawPageBean) {
		System.out.println(orderDao.listDriverWithdraw(driverWithdrawPageBean));
		return orderDao.listDriverWithdraw(driverWithdrawPageBean);
	}
	
	//查询这个范围是否有车,如果有，返回车辆实时表中的id，如果没有返回0
	@Override
	public String getArroundCar(double lon, double lat) {
		List<Map<String,Object>> list = orderDao.getArroundCar(DistanceUtil.getAround(lon, lat,5000));
		System.out.println(list.size());
		Map<Double,String> idmap = new TreeMap<Double,String>();
		if(list.size() != 0 ){
			for(Map<String,Object> map:list){				
				double distance = DistanceUtil.GetDistance(lon, lat,(Double)map.get("longitude"),(Double)map.get("latitude"));
				System.out.println( map.get("plateNo"));
				idmap.put(distance,(String) map.get("plateNo"));
			}
			for(Double key:idmap.keySet()){
				return idmap.get(key);
			}
			
		}
		return list.size()+"";
	}
	
	//模糊查询顾客姓名
	@Override
	public List<Map<String, Object>> fuzzyCustomerName(String name) {
		return orderDao.customerNameFuzzy(name);
	}
	
	//查询最后一次交易记录
	@Override
	public List<Map<String, Object>> lastTransaction(String id) {
		return orderDao.lastTransaction(id);
	}
	
	@Override
	public int getCounts(OrderPageBean pageBean) {
		return orderDao.getCounts(pageBean);
	}
	
	@Override
	public List<Map<String, Object>> getOrders(OrderPageBean pageBean) {
		return orderDao.getOrders(pageBean);
	}
	
	@Override
	public int insertOrder(UnfinishedOrder unfinishedOrder) {
		return orderDao.insertOrder(unfinishedOrder);
	}
	
	@Override
	public int addAdminOrder(OrderAdmin orderAdmin) {
		return orderDao.addAdminOrder(orderAdmin);
	}
	
	@Override
	public Map<String, Object> getOrderById(int id) {
		return orderDao.getOrderById(id);
	}
	
	@Override
	public boolean updateOrder(UnfinishedOrder unfinishedOrder) {
		return orderDao.updateOrder(unfinishedOrder);
	}
	
	@Override
	public boolean cancelOrder(int id) {
		return orderDao.cancelOrder(id);
	}
	
	@Override
	public int getReassignOrderCounts(ReassignOrderPageBean pageBean) {
		return orderDao.getReassignOrderCounts(pageBean);
	}
	
	@Override
	public List<Map<String, Object>> getReassignOrders(ReassignOrderPageBean pageBean) {
		return orderDao.getReassignOrders(pageBean);
	}
	
	@Override
	public int getExceptionsOrderCounts(ExceptionsOrderPageBean pageBean) {
		return orderDao.getExceptionsOrderCounts(pageBean);
	}
	
	@Override
	public List<Map<String, Object>> getExceptionsOrders(ExceptionsOrderPageBean pageBean) {
		return orderDao.getExceptionsOrders(pageBean);
	}
	
	@Override
	public Map<String, Object> getReassignOrderById(int id) {
		return orderDao.getReassignOrderById(id);
	}
	
	@Override
	public List<Map<String, Object>> getDriverInfo(PageBean pageBean) {
		return  orderDao.getDriverInfo(pageBean);
	}
	
	@Override
	public boolean appointOrderById(Map<String, Object> map) {
		return orderDao.appointOrderById(map);
	}
	
	@Override
	public int getDriverInfoCount() {
		return orderDao.getDriverInfoCount();
	}
	
	@Override
	public boolean rebuildsOrder(UnfinishedOrder unfinishedOrder) {
		try {
			//1、根据订单id查询现有订单
			UnfinishedOrder table_UnfinishedOrder=orderDao.getUnfinishedOrderById(unfinishedOrder.getId());
			//2、根据订单orderNum查询金额对象
			UnfinishedMoney unfinishedMoney=unfinishedMoneyDao.getMoneyByNum(table_UnfinishedOrder.getOrdernum());
			//3、移入已完成订单表中（完成金额表不添加，则查询金额为0）
			table_UnfinishedOrder.setOrderstatus(1);
			doneOrderDao.iDoneOrder(table_UnfinishedOrder);
			//4、删除未完成订单表中订单 
			orderDao.deleteById(unfinishedOrder.getId());
			//5、创建新的订单
			String ordernum = "GT" + FormatDateUtils.dateToString2(new Date()) + RandomUtils.random();
			String createtime = FormatDateUtils.dateToString(new Date());
			unfinishedOrder.setOrdernum(ordernum);
			unfinishedOrder.setOrderstatus(0);
			unfinishedOrder.setCreatetime(createtime);
			orderDao.insertOrder(unfinishedOrder);
			//6、新的未完成金额
			unfinishedMoney.setOrdernum(ordernum);
			unfinishedMoneyDao.insertMoney(unfinishedMoney);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
	}
	
	@Override
	public UnfinishedOrder getWeightByOrderNum(String orderNum) {
		return orderDao.getWeightByOrderNum(orderNum);
	}
	
	@Override
	public UnfinishedOrder getUnfinishedOrderByNum(String ordernum) {
		return orderDao.getUnfinishedOrderByNum(ordernum);
	}
}
