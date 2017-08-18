package cn.guangtong.controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.order.UnfinishedOrderService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 未完成订单
 * 
 * @author 赵发志
 * 
 */
@Controller
@RequestMapping("/unfinished")
public class UnfinishedOrderController {
	
	@Autowired
	private UnfinishedOrderService unfinishedOrderService;

	public UnfinishedOrderService getUnfinishedOrderService() {
		return unfinishedOrderService;
	}
	
	/**
	 * 添加异常
	 * 赵发志
	 */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping("/orderAbnormal")
    public ResponseModel orderAbnormal(HttpServletRequest request ,HttpServletResponse response){
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);//设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");//切换到orderDs的数据源
			String exceptionReason = request.getParameter("exceptionReason");
			String orderNum = request.getParameter("orderNum");
			getUnfinishedOrderService().orderAbnormal(exceptionReason, orderNum);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e){
			rm.init();
		} 
		return rm;
    }
		
}
