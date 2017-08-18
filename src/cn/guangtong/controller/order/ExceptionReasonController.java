package cn.guangtong.controller.order;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.order.ExceptionReasonService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 异常订单
 * 
 * @author 赵发志
 */
@Controller
@RequestMapping("/exception")
public class ExceptionReasonController {

	@Autowired
	private ExceptionReasonService exceptionReasonService;

	public ExceptionReasonService getExceptionReasonService() {
		return exceptionReasonService;
	}

	/**
	 * 异常订单查询+分页 赵发志
	 */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping("/sExceptionReasonA")
	public ResponseModel<Map<String, Object>> sExceptionReasonA(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			SettingMessageHeaders.setHeaders(response);// 设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");// 切换到orderDs的数据源
			// 查询订单总数
			Integer totalCount = getExceptionReasonService().sManagementOrderCount();
			PageBean pageBean = calculatePage(request, totalCount);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(getExceptionReasonService().sExceptionReasonA(pageBean));
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 取消异常订单 赵发志
	 */
	@ResponseBody
	@RequestMapping("/uManagementOrder")
	public ResponseModel uManagementOrder(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);// 设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");// 切换到orderDs的数据源
			String orderNum = request.getParameter("orderNum");// 订单编号
			Integer result = getExceptionReasonService().uManagementOrder(orderNum);

			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 重建订单 赵发志
	 */
	@ResponseBody
	@RequestMapping("/uRebuildOrderOrder")
	public ResponseModel uRebuildOrderOrder(UnfinishedOrder unfinishedOrder, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);// 设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");// 切换到orderDs的数据源
			Integer result = getExceptionReasonService().uRebuildOrderOrder(unfinishedOrder);

			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 重建订单 返回查询 赵发志
	 */
	@ResponseBody
	@RequestMapping("/sManagementOrderOne")
	public ResponseModel<Map<String, Object>> sManagementOrderOne(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			SettingMessageHeaders.setHeaders(response);// 设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");// 切换到orderDs的数据源
			String orderNum = request.getParameter("orderNum");// 订单编号
			Map<String, Object> result = getExceptionReasonService().sManagementOrderOne(orderNum);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(result);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 计算分页 赵发志
	 */
	@SuppressWarnings({ "rawtypes" })
	public PageBean calculatePage(HttpServletRequest request, Integer totalCount) {
		PageBean pageBean = new PageBean();
		Integer currentPage = 1;// 当前页码
		Integer pageCount = 10;// 每页记录数
		String currentPages = request.getParameter("currentPage");
		String pageCounts = request.getParameter("pageCount");
		if (currentPages != null && !currentPages.trim().isEmpty()) {
			try {
				currentPage = Integer.parseInt(currentPages);
			} catch (RuntimeException e) {
				throw e;
			}
		}
		if (pageCounts != null && !pageCounts.trim().isEmpty()) {
			try {
				pageCount = Integer.parseInt(pageCounts);
			} catch (RuntimeException e) {
				throw e;
			}
		}
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageCount(pageCount);
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}
}
