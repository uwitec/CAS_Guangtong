package cn.guangtong.controller.order;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.order.UnfinishedOrder;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.order.DoneOrderService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 已完成订单管理
 * 赵发志
 *
 */
@Controller
@RequestMapping("/doneOrder")
public class DoneOrderController {
	
	@Autowired
	private DoneOrderService doneOrderService;

	
	
	public DoneOrderService getDoneOrderService() {
		return doneOrderService;
	}
	
	/**
	 * 插入一条订单记录
	 * 赵发志
	 */
	@ResponseBody
	@RequestMapping("/iDoneOrder")
    public ResponseModel iDoneOrder(UnfinishedOrder unfinishedOrder,HttpServletResponse response){
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);//设置消息头
			DataSourceContextHolder.setDataSourceType("orderDs");//切换到orderDs的数据源
			getDoneOrderService().iDoneOrder(unfinishedOrder);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
    }
}
