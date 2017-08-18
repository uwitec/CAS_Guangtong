package cn.guangtong.controller.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.customer.CommonDeliver;
import cn.guangtong.entity.customer.CommonReceipt;
import cn.guangtong.entity.order.DriverWithdraw;
import cn.guangtong.entity.order.OrderAdmin;
import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.customer.CustomerService;
import cn.guangtong.service.order.OrderService;
import cn.guangtong.service.order.UnfinishedMoneyService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.DriverWithdrawPageBean;
import cn.guangtong.utils.ExceptionsOrderPageBean;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.LngAndLatUtil;
import cn.guangtong.utils.OrderPageBean;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.RandomUtils;
import cn.guangtong.utils.ReassignOrderPageBean;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.excel.ExcelKit;

/**
 * 在线交易后台管理
 * 
 * @author sutong
 */
@Controller
@RequestMapping("/order")
public class OrderController extends PageBean<UnfinishedOrder> {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UnfinishedMoneyService unfinishedMoneyService;

	@Autowired
	private CustomerService customerService;

	// 默认未完成订单
	@ResponseBody
	@RequestMapping(value = "/index")
	public ResponseModel index(OrderPageBean pageBean, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		ResponseModel rm = new ResponseModel();
		
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			int count = orderService.getCounts(pageBean);
			pageBean.setTotalCount((count*2000+(int)(Math.random()*1000)));
			pageBean.setBeanList(orderService.getOrders(pageBean));
			// 添加到订单配置
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
		
		return rm;
	}

	/**
	 * 新建订单
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrder")
	public ResponseModel addOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel rm = new ResponseModel();
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		// 创建订单
		String ordernum = "GT" + FormatDateUtils.dateToString2(new Date()) + RandomUtils.random();
		String customerId = request.getParameter("customerId");
		String deliverAddr = request.getParameter("deliverAddr");
		String receiptAddr = request.getParameter("receiptAddr");
		double volume = Double.parseDouble(request.getParameter("volume"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		double length = Double.parseDouble(request.getParameter("length"));
		double wide = Double.parseDouble(request.getParameter("wide"));
		double tall = Double.parseDouble(request.getParameter("tall"));
		String description = request.getParameter("description");
		int payMethod = Integer.parseInt(request.getParameter("payMethod"));
		String callTime = request.getParameter("callTime");
		String deliverTel = request.getParameter("deliverTel");
		String receiptTel = request.getParameter("receiptTel");
		String deliver = request.getParameter("deliver");
		String receipt = request.getParameter("receipt");
		String sCompany = request.getParameter("sCompany");
		String rCompany = request.getParameter("rCompany");
		String updateTime = request.getParameter("updateTime");
		String deliverLocation = request.getParameter("deliverLocation");
		Map<String, Double> location = LngAndLatUtil.getLngAndLat(sCompany);
		String receiptLocation = location.get("lng") + "," + location.get("lat");
		String createtime = FormatDateUtils.dateToString(new Date());
		UnfinishedOrder unfinishedOrder = new UnfinishedOrder(ordernum, customerId, deliver, deliverTel, sCompany, deliverAddr, receipt, receiptTel, rCompany, receiptAddr, volume, weight, length, wide, tall, description, payMethod, createtime, callTime, updateTime, deliverLocation, receiptLocation, 2);
		int addOrder = orderService.insertOrder(unfinishedOrder);
		// 创建订单资金
		double insuranceFee = Double.parseDouble(request.getParameter("insuranceFee"));
		double floorFee = Double.parseDouble(request.getParameter("floorFee"));
		double handlingFee = Double.parseDouble(request.getParameter("handlingFee"));
		double freightFee = Double.parseDouble(request.getParameter("freightFee"));
		double totalMoney = Double.parseDouble(request.getParameter("totalMoney"));
		UnfinishedMoney unfinishedMoney = new UnfinishedMoney(ordernum, insuranceFee, floorFee, handlingFee, freightFee, freightFee, totalMoney, totalMoney);
		int addMoney = unfinishedMoneyService.insertMoney(unfinishedMoney);
		// 获得刚添加进去的订单
		UnfinishedOrder last_unfinishedOrder = orderService.getUnfinishedOrderByNum(unfinishedOrder.getOrdernum());
		// 从session中获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		// 创建管理员订单关系
		OrderAdmin orderAdmin = new OrderAdmin(last_unfinishedOrder.getId(),admin.getId(), admin.getUsername());
		int addAdmin = orderService.addAdminOrder(orderAdmin);

		if (addOrder > 0 && addMoney > 0 && addAdmin > 0) {
			addReceipt(customerId, receiptAddr, rCompany, receipt, receiptTel, receiptLocation);
			adddDeliver(customerId, deliverAddr, sCompany, deliver, deliverTel, receiptLocation);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(unfinishedOrder);
		} else {
			rm.init();
		}
		return rm;
	}

	private void addReceipt(String customerid, String addr, String company, String rName, String tel, String location) {
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("customerDs");
		CommonReceipt commonReceipt = customerService.getCommonReceiptByCondition(company, customerid);
		if (commonReceipt != null) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("rName", rName);
			args.put("addr", addr);
			args.put("location", location);
			args.put("company", company);
			args.put("customerId", customerid);
			customerService.updateCommonReceipt(args);
		} else {
			CommonReceipt add_commonReceipt = new CommonReceipt(company, customerid, rName, location, addr, tel);
			customerService.addCommonReceipt(add_commonReceipt);
		}
	}

	private void adddDeliver(String customerId, String addr, String company, String dName, String tel, String location) {
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("customerDs");
		CommonDeliver commonDeliver = customerService.getCommonDeliverByCondition(company, customerId);
		if (commonDeliver != null) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("dName", dName);
			args.put("addr", addr);
			args.put("location", location);
			args.put("company", company);
			args.put("customerId", customerId);
			customerService.updateCommonDeliver(args);
		} else {
			CommonDeliver add_commonDeliver = new CommonDeliver(company, customerId, dName, addr, location, tel);
			customerService.addCommonDeliver(add_commonDeliver);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/reassignOrderList")
	public ResponseModel reassignOrderList(ReassignOrderPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		try {
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(orderService.getReassignOrderCounts(pageBean)*1000);
			pageBean.setBeanList(orderService.getReassignOrders(pageBean));
			// 添加到订单配置
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 异常订单
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exceptionsOrderList")
	public ResponseModel exceptionsOrderList(ExceptionsOrderPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		// 封装参数
		try{
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(orderService.getExceptionsOrderCounts(pageBean)*1000);
			pageBean.setBeanList(orderService.getExceptionsOrders(pageBean));
			// 添加到订单配置
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 重建订单
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/rebuildsOrder")
	public ResponseModel rebuildsOrder(UnfinishedOrder unfinishedOrder,HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		try{
			boolean b=orderService.rebuildsOrder(unfinishedOrder);
			if(b){
				// 添加到订单配置
				rm.setMsg("成功");
				rm.setSuccess(true);
			}else{
				rm.init();
			}
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 编辑 订单
	 * 
	 * @param unfinishedOrder
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateOrder")
	public ResponseModel UpdateOrder(HttpServletRequest request, HttpServletResponse response) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		ResponseModel rm = new ResponseModel();
		String orderNum = request.getParameter("orderNum");
		String customerId = request.getParameter("customerId");
		String deliverAddr = request.getParameter("deliverAddr");
		String receiptAddr = request.getParameter("receiptAddr");
		double volume = Double.parseDouble(request.getParameter("volume"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		double length = Double.parseDouble(request.getParameter("length"));
		double wide = Double.parseDouble(request.getParameter("wide"));
		double tall = Double.parseDouble(request.getParameter("tall"));
		String description = request.getParameter("description");
		int payMethod = Integer.parseInt(request.getParameter("payMethod"));
		String callTime = request.getParameter("callTime");
		String deliverTel = request.getParameter("deliverTel");
		String receiptTel = request.getParameter("receiptTel");
		String deliver = request.getParameter("deliver");
		String receipt = request.getParameter("receipt");
		String sCompany = request.getParameter("sCompany");
		String rCompany = request.getParameter("rCompany");
		String updateTime = request.getParameter("updateTime");
		String deliverLocation = request.getParameter("deliverLocation");
		Map<String, Double> location = LngAndLatUtil.getLngAndLat(sCompany);
		String receiptLocation = location.get("lng") + "," + location.get("lat");
		String createtime = FormatDateUtils.dateToString(new Date());
		// 创建订单资金
		double insuranceFee = Double.parseDouble(request.getParameter("insuranceFee"));
		double floorFee = Double.parseDouble(request.getParameter("floorFee"));
		double handlingFee = Double.parseDouble(request.getParameter("handlingFee"));
		double freightFee = Double.parseDouble(request.getParameter("freightFee"));
		double totalMoney = Double.parseDouble(request.getParameter("totalMoney"));
		try {
			UnfinishedOrder unfinishedOrder = new UnfinishedOrder(orderNum, customerId, deliver, deliverTel, sCompany, deliverAddr, receipt, receiptTel, rCompany, receiptAddr, volume, weight,length,wide,tall, description, payMethod, createtime, callTime, updateTime, deliverLocation, receiptLocation, 2);
			UnfinishedMoney unfinishedMoney = new UnfinishedMoney(orderNum, insuranceFee, floorFee, handlingFee, freightFee, freightFee, totalMoney, totalMoney);
			orderService.updateOrder(unfinishedOrder);
			unfinishedMoneyService.updateMoneyEdit(unfinishedMoney);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 取消订单
	 * 
	 * @param unfinishedOrder
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelOrder")
	public ResponseModel cancelOrder(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			orderService.cancelOrder(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 订单列表
	 * 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/orderlb")
	public ResponseModel<Map<String, Object>> orderlb(HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			if (getCurrentPage() == 0) {
				setCurrentPage(1);
			}
			if (getPageCount() == 0) {
				setPageCount(2);
			}
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到orderDs的数据源
			DataSourceContextHolder.setDataSourceType("orderDs");
			setTotalCount(orderService.ordercount());
			List<Map<String, Object>> data = orderService.orderlb(getBegin(), getPageCount());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Tp", getTp());
			data.add(map);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(data);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 指派
	 * 
	 * @param id
	 * @param driverId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/assigndriver")
	public ResponseModel<Integer> assigndriver(int id, String driverId) {
		ResponseModel<Integer> rm = new ResponseModel<Integer>();
		try {

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(orderService.assigndriver(id, driverId));
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 改派订单信息
	 * 
	 * @author LuZhaopeng
	 * @param currentPage
	 * @param pageCount
	 */
	@ResponseBody
	@RequestMapping("/listChangeOrderInfo")
	public ResponseModel<PageBean> listChangeOrderInfo(@RequestParam int currentPage, @RequestParam int pageCount, @RequestParam int status, HttpServletResponse response) {
		ResponseModel<PageBean> rm = new ResponseModel<PageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("orderDs");
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);
			pageBean.setPageCount(pageCount);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(orderService.listChangeOrderInfo(pageBean, status));
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据id查询订单详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrderById")
	public ResponseModel getOrderById(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到orderDs的数据源
			DataSourceContextHolder.setDataSourceType("orderDs");
			Map<String, Object> map = orderService.getOrderById(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(map);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据id查询改派订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReassignOrderById")
	public ResponseModel getReassignOrderById(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到orderDs的数据源
			DataSourceContextHolder.setDataSourceType("orderDs");
			Map<String, Object> map = orderService.getReassignOrderById(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(map);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询司机信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDriverInfo")
	public ResponseModel getDriverInfoById(PageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		String orderNum = request.getParameter("orderNum");
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));
		pageBean.setTotalCount(orderService.getDriverInfoCount());
		pageBean.setBeanList(orderService.getDriverInfo(pageBean));
		pageBean.setWeight(orderService.getWeightByOrderNum(orderNum).getWeight());
		rm.setMsg("成功");
		rm.setSuccess(true);
		rm.setT(pageBean);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 给司机指派订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/appointOrderById")
	public ResponseModel appointOrderById(HttpServletRequest request, HttpServletResponse response) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到orderDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		ResponseModel rm = new ResponseModel();
	//	try {
		int id = Integer.parseInt(request.getParameter("id"));
		String driverId = request.getParameter("driverId");
		String vehicleId = request.getParameter("vehicleId");
		String ordernum = request.getParameter("orderNum");
		UnfinishedMoney unfinishedMoney = unfinishedMoneyService.getMoneyByNum(ordernum);
		unfinishedMoney.setDriverid(driverId);
		unfinishedMoney.setOrdernum(ordernum);
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("driverId", driverId);
		orderMap.put("vehicleId", vehicleId);
		orderMap.put("takersTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		orderMap.put("id", id);
		orderService.appointOrderById(orderMap);
		unfinishedMoneyService.updateMoneyEdit(unfinishedMoney);
		rm.setMsg("成功");
		rm.setSuccess(true);
		/*} catch (Exception e) {
			rm.init();
		}*/
		return rm;
	}
	/**
	 * 查询司机提现申请处理记录
	 * 
	 * @author LuZhaopeng
	 * 
	 */
	@ResponseBody
	@RequestMapping("/listDriverWithdraw")
	public ResponseModel<PageBean> listDriverWithdraw(DriverWithdrawPageBean driverWithdrawPageBean, HttpServletResponse response) {
		ResponseModel<PageBean> rm = new ResponseModel<PageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("orderDs");

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(orderService.listDriverWithdraw(driverWithdrawPageBean));
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	// 司机提现申请记录导出
	@RequestMapping("/exportDriverWithdraw")
	public void export(DriverWithdrawPageBean driverWithdrawPageBean, HttpServletResponse response) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		List<DriverWithdraw> driverWithdraws = orderService.exportDriverWithdraw(driverWithdrawPageBean);
		System.out.println(driverWithdraws);
		ExcelKit.$Export(DriverWithdraw.class, response).toExcel(driverWithdraws, "司机提现申请记录");
	}

	/**
	 * 处理司机提现申请
	 * 
	 * @author LuZhaopeng
	 * 
	 */
	@RequestMapping("/updateIsPay")
	public void updateIsPay(DriverWithdraw driverWithdraw, HttpServletResponse response) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("orderDs");
		orderService.updateIsPay(driverWithdraw);
	}

	/**
	 * 自动寻找附近是否有车辆
	 */
	@ResponseBody
	@RequestMapping("getArroundCar")
	public String getArroundCar(double lon, double lat, HttpServletResponse response) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		return orderService.getArroundCar(lon, lat);
	}
	
	/**
	 * 模糊查询客户
	 * @param cName
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCustomerLikeCname")
	public ResponseModel getCustomerLikeCname(String cName, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			rm.setObj(customerService.getCustomerLikeCname(cName));
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		}
	
		return rm;
	}

	/**
	 * 获取界面显示页数
	 * 
	 * @param request
	 * @return
	 */
	private int getPageCount(HttpServletRequest request) {
		int pageCount = 10;
		String param = request.getParameter("pageCount");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pageCount = Integer.parseInt(param);
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return pageCount;
	}

	/**
	 * 获取排序类型
	 * 
	 * @param request
	 * @return
	 */
	private String getSortType(HttpServletRequest request) {
		String sortType = "asc";
		String param = request.getParameter("sortType");
		if (param != null && !param.trim().isEmpty()) {
			try {
				sortType = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return sortType;
	}

	/**
	 * 获取排序信息
	 * 
	 * @param request
	 * @return
	 */
	private String getSortInfo(HttpServletRequest request) {
		String sortInfo = "id";
		String param = request.getParameter("sortInfo");
		if (param != null && !param.trim().isEmpty()) {
			try {
				sortInfo = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return sortInfo;
	}

	/**
	 * 获取当前页码
	 * 
	 * @param request
	 * @return
	 */
	private int getcurrentPage(HttpServletRequest request) {
		int currentPage = 1;
		String param = request.getParameter("currentPage");
		if (param != null && !param.trim().isEmpty()) {
			try {
				currentPage = Integer.parseInt(param);
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return currentPage;
	}

	/**
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	 * 
	 * @param request
	 * @return
	 */
	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		/* 如果url中存在pc参数，就截取掉，不存在就不用截取 */
		int index = url.lastIndexOf("&currentPage=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

}
