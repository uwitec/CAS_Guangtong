package cn.guangtong.controller.order;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.order.OrderService;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

@Controller
@RequestMapping("customer")
public class CustomerNameController {

	@Resource
	private OrderService orderService;
	
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	// 模糊查询顾客姓名
	@ResponseBody
	@RequestMapping("fuzzyName")
	public ResponseModel CustomerNameFuzzy(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 返回模型
		ResponseModel rm = new ResponseModel();
		try {
			PlatLog log = new PlatLog();
			log.setModule("订单管理");
			log.setContext("模糊查询客户名称");
			// plog.addPlatLog(log, request);
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(orderService.fuzzyCustomerName(name));

		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	// 查询最后一次交易记录
	@ResponseBody
	@RequestMapping("lastTransaction")
	public ResponseModel<Map<String,Object>> lastTransaction(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 返回模型
		ResponseModel<Map<String,Object>> rm = new ResponseModel<Map<String,Object>>();
		try {
			PlatLog log = new PlatLog();
			log.setModule("订单管理");
			log.setContext("查询最后一次交易记录");
			// plog.addPlatLog(log, request);
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(orderService.lastTransaction(id));

		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

}
