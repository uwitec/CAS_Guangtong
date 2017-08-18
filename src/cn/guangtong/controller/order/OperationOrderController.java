package cn.guangtong.controller.order;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.order.UnfinishedMoney;
import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.order.OperationOrderService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 订单操作
 * 赵发志
 */
@Controller
@RequestMapping("/operation")
public class OperationOrderController {
	
	@Autowired
	private OperationOrderService operationOrderService;
	
	

	public OperationOrderService getOperationOrderService() {
		return operationOrderService;
	}

	/**
	 * 完成订单
	 * 赵发志
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "orderComplete",method = RequestMethod.POST)
    public ResponseModel orderComplete(String orderNum,HttpServletResponse response){
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);//设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");//切换到orderDs的数据源
			getOperationOrderService().orderComplete(orderNum);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		} 
		return rm;
    }
	
	/**
	 * 取消订单（0：待结算；1完成；2改派；5:异常;6：已关闭；7：已取消）
	 * 赵发志
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "orderCancellation",method = RequestMethod.POST)
    public ResponseModel orderCancellation(String orderNum,HttpServletResponse response){
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);//设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");//切换到orderDs的数据源
			getOperationOrderService().orderCancellation(orderNum);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		} 
		return rm;
	}
	
	/**
	 * 编辑订单
	 * 赵发志
	 */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "orderEdit",method = RequestMethod.POST)
    public ResponseModel orderEdit(UnfinishedMoney unfinishedMoney, UnfinishedOrder unfinishedOrder,HttpServletResponse response){
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);//设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");//切换到orderDs的数据源
			getOperationOrderService().orderEdit(unfinishedMoney, unfinishedOrder);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		} 
		return rm;
    }
	
}
