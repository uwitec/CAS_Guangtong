package cn.guangtong.controller.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.cms.AdminCooperationService;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

@Controller
@RequestMapping("/AdminCooperation")
public class AdminCooperationController {

	@Autowired
	private AdminCooperationService adminCooperationService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();
	
	/**
	 * 根据当前用户，获取用户管理的全部企业
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCooperationByAdmin")
	public ResponseModel getCooperationByAdmin(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 从session中获取当前登录用户
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			
			if(admin.getAtype()!=1){
				rm.setObj(adminCooperationService.getCooperationByAdminId(admin.getId().toString()));
			}else{
				rm.setObj(adminCooperationService.getCooperationAll());
			}
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("获取当前用户可见企业");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
}
