package cn.guangtong.controller.beidou;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.AdminCommand;
import cn.guangtong.entity.cms.AdminCommon;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.total.PlatTotalAlarm;
import cn.guangtong.model.PanelVehicle;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.beidou.PanelService;
import cn.guangtong.service.cms.AdminCommandService;
import cn.guangtong.service.cms.AdminCommonService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.excel.ExcelKit;

/**
 * 面板信息
 * 
 * @author SunTo
 * 
 */
@Controller
@RequestMapping("/panel")
public class PanelController {

	@Autowired
	private PanelService panelService;
	
	@Autowired
	private AdminCommandService adminCommandService;
	
	@Autowired
	private AdminCommonService adminCommonService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 根据当前用户查询可见所有车辆的信息(面板)
	 */
	@ResponseBody
	@RequestMapping("/findPanelVehicle")
	public ResponseModel findVehicleByAdmin(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// Admin admin = (Admin) request.getSession().getAttribute("loginUser");

			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			String state = (String) request.getParameter("state");

			rm.setT(panelService.findVehicleByAdmin(admin, state));
			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("面板信息");
			log.setContext("面板车辆信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据当前用户导出可见所有车辆的信息(面板)
	 */
	@ResponseBody
	@RequestMapping("/panelVehicleExcel")
	public ResponseModel panelVehicleExcel(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// Admin admin = (Admin) request.getSession().getAttribute("loginUser");

			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			String state = (String) request.getParameter("state");

			Map<String, Object> data = panelService.findVehicleByAdmin(admin, state);
			List<PanelVehicle> list = (List<PanelVehicle>) data.get("data");
			// 生成Excel并使用浏览器下载
			ExcelKit.$Export(PanelVehicle.class, response).toExcel(list, "面板车辆信息");
			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("面板信息");
			log.setContext("面板车辆信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 命令显示列
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAdminCommand")
	public ResponseModel getAdminCommand(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");

			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			AdminCommand adminCommand = adminCommandService.getAdminCommand(admin.getId());

			rm.setT(adminCommand);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("面板信息");
			log.setContext("获取命令显示列");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 命令面板显示列，更新
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upAdminCommand")
	public ResponseModel upAdminCommand(AdminCommand adminCommand, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			adminCommand.setAdminId(admin.getId());
			adminCommandService.update(adminCommand);

			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("面板信息");
			log.setContext("命令面板显示列，更新");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 一般信息显示列
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAdminCommon")
	public ResponseModel getAdminCommon(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");

			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			AdminCommon adminCommon = adminCommonService.getAdminCommon(admin.getId());

			rm.setT(adminCommon);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("面板信息");
			log.setContext("一般信息显示列");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 一般信息面板显示列，更新
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upAdminCommon")
	public ResponseModel upAdminCommon(AdminCommon adminCommon, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			adminCommon.setAdminId(admin.getId());
			adminCommonService.update(adminCommon);

			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("面板信息");
			log.setContext("一般信息面板显示列，更新");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

}
