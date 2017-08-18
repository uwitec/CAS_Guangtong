package cn.guangtong.controller.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.order.OrderDoubleOperationService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 双订单操作
 * 赵发志
 */
@Controller
@RequestMapping("/doubleOrder")
public class OrderDoubleOperationController {
	
	@Autowired
	private OrderDoubleOperationService orderDoubleOperationService;
	
	
	
	public OrderDoubleOperationService getOrderDoubleOperationService() {
		return orderDoubleOperationService;
	}

	/**
	 * 查询订单+分页
	 */
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/orderQueryAll")
    public ResponseModel<PageBean> orderQueryAll(HttpServletRequest request,HttpServletResponse response){
		ResponseModel<PageBean> rm = new ResponseModel<PageBean>();
		try {
			SettingMessageHeaders.setHeaders(response);//设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");//切换到orderDs的数据源
			PageBean pageBean= calculatePage(request,0);
			List<Map<String,Object>> list = getOrderDoubleOperationService().orderQueryAll(pageBean);
			pageBean.setBeanList(list);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
		} catch (Exception e) {
			rm.init();
		}
		return rm;

    }
	
	/**
	 * 计算分页
	 * 赵发志
	 */
	@SuppressWarnings({ "rawtypes" })
	public PageBean calculatePage(HttpServletRequest request,Integer totalCount){
		PageBean pageBean = new PageBean();
		Integer currentPage = 1;//当前页码
		Integer pageCount = 10;//每页记录数
		String currentPages = request.getParameter("currentPage");
		String pageCounts = request.getParameter("pageCount");
		if (currentPages != null && !currentPages.trim().isEmpty()){
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
