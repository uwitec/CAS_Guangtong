package cn.guangtong.controller.vehicle;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.coopertation.CooperationInfoService;
import cn.guangtong.service.vehicle.ForeignfactionService;
import cn.guangtong.service.vehicle.VehicleInfoService;
import cn.guangtong.utils.ForeignfactionPageBean;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

@Controller
@RequestMapping("/foreignfaction")
public class ForeignfactionController {

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	@Autowired
	private ForeignfactionService foreignfactionService;

	@Autowired
	private VehicleInfoService vehicleInfoService;

	@Autowired
	private CooperationInfoService cooperationInfoService;

	/**
	 * 车辆外派管理分页查询
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getForeignfactionByPageBean")
	public ResponseModel getForeignfactionByPageBean(ForeignfactionPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();

		try {
			SettingMessageHeaders.setHeaders(response);
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));

			// 从session中获取当前登录用户
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

			if (admin.getAtype() != 1) {
				pageBean.setAdminId(admin.getId().toString());
				foreignfactionService.getForeignfactionByPageBean(pageBean);
			} else {
				foreignfactionService.getForeignfactionSByPageBean(pageBean);
			}
			rm.setT(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆外派管理");
			log.setContext("车辆外派分页查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 获取车辆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVehicle")
	public ResponseModel getVehicle(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);

			// 从session中获取当前登录用户
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			// 要排除的企业id
			String cooperationId = request.getParameter("cooperationid");

			rm.setT(foreignfactionService.getVehicle(admin, cooperationId));
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆外派");
			log.setContext("获取外派的车辆列表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 获取全部企业列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCooperation")
	public ResponseModel getCooperation(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();

		try {
			SettingMessageHeaders.setHeaders(response);
			rm.setObj(cooperationInfoService.selectAll());
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆外派");
			log.setContext("获取全部企业列表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 车辆外派
	 * 
	 * @param vehArr
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertForeignfaction")
	public ResponseModel insertForeignfaction(@RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();

		try {
			SettingMessageHeaders.setHeaders(response);
			// 借方企业Id
			String receivedcooperationid = request.getParameter("receivedcooperationid");
			// 结束时间
			String endtime = request.getParameter("endtime");
			foreignfactionService.insertForeignfaction(receivedcooperationid, endtime, vehArr);

			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆外派");
			log.setContext("添加车辆外派");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 召回车辆（批量召回）
	 * 
	 * @param vehArr
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delForeignfaction")
	public ResponseModel delForeignfaction(@RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();

		try {
			SettingMessageHeaders.setHeaders(response);

			foreignfactionService.delForeignfaction(vehArr);

			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆外派");
			log.setContext("添加车辆外派");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
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

}
