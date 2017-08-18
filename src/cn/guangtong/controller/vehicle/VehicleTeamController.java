package cn.guangtong.controller.vehicle;

import java.util.Map;
import java.util.UUID;

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
import cn.guangtong.entity.vehicle.VehicleTeam;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.vehicle.VehicleInfoService;
import cn.guangtong.service.vehicle.VehicleTeamService;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.VehicleTeamPageBean;

/**
 * 车队管理
 * 
 * @author SunTo
 * 
 */
@Controller
@RequestMapping("/vehicleteam")
public class VehicleTeamController {

	@Autowired
	private VehicleTeamService vehicleTeamService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();
	
	/**
	 * 添加一个车队
	 * @param vehicleTeam
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public ResponseModel insert(VehicleTeam vehicleTeam,HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			vehicleTeam.setId(uuid);
			vehicleTeamService.insert(vehicleTeam);
			PlatLog log = new PlatLog();
			log.setModule("车辆信息管理");
			log.setContext("添加车队");
			plog.addPlatLog(log, request);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}
	
	/**
	 * 更新车队`
	 * @param vehicleTeam
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update")
	public ResponseModel update(VehicleTeam vehicleTeam,HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			
			vehicleTeamService.update(vehicleTeam);
			PlatLog log = new PlatLog();
			log.setModule("车辆信息管理");
			log.setContext("更新车队");
			plog.addPlatLog(log, request);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}
	
	/**
	 * 根据车队id获取车队实体
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getVehicleTeamById")
	public ResponseModel getVehicleTeamById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String vehicleTeamId = request.getParameter("vehicleTeamId");
			VehicleTeam vehicleTeam=vehicleTeamService.getById(vehicleTeamId);
			rm.setT(vehicleTeam);
			PlatLog log = new PlatLog();
			log.setModule("车辆信息管理");
			log.setContext("根据车队id获取车队信息");
			plog.addPlatLog(log, request);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 根据企业id，查询旗下所有车队
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getVehicleTeamByCooperationId")
	public ResponseModel getByCooperationId(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel<VehicleTeam> rm = new ResponseModel<VehicleTeam>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			String cooperationId = request.getParameter("cooperationId");
			rm.setObj(vehicleTeamService.getByCooperationId(cooperationId));
			PlatLog log = new PlatLog();
			log.setModule("系统");
			log.setContext("通过企业id获取旗下车队");
			plog.addPlatLog(log, request);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}
	
	/**
	 * 获取当前账号可见的企业
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getCooperations")
	public ResponseModel getCooperations(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel<Map<String,Object>> rm = new ResponseModel<Map<String,Object>>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if (admin.getAtype()==0) {
				rm.setObj(vehicleInfoService.sVehicleInfoEnterpriseNumber(admin.getId()));
			}else if (admin.getAtype() == 1) {
				rm.setObj(vehicleInfoService.sAllVehicleInfoEnterpriseNumber());
			}
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("获取企业列表");
			plog.addPlatLog(log, request);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 分页管理当前用户可见的车队
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getVehicleTeamByPageBean")
	public ResponseModel getVehicleTeamByPageBean(VehicleTeamPageBean pageBean, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel<VehicleTeamPageBean> rm = new ResponseModel<VehicleTeamPageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			// 获取当前路径
			pageBean.setUrl(getUrl(request));
			// 获取每页记录数
			pageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			pageBean.setCurrentPage(getcurrentPage(request));
			
			if (admin.getAtype()==0) {
				pageBean.setAdminId(admin.getId());
				vehicleTeamService.getVehicleTeamByPageBean(pageBean);
			}else if (admin.getAtype() == 1) {
				vehicleTeamService.getAllVehicleTeamByPageBean(pageBean);
			}
			
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("通过当前用户获取旗下车队");
			plog.addPlatLog(log, request);
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}
	

	/**
	 * 获取每页记录数(默认10)
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
