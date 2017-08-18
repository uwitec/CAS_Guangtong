package cn.guangtong.controller.cms;

import java.util.List;
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
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.service.cms.AdminVehicleService;
import cn.guangtong.service.vehicle.VehicleInfoService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 车辆级联菜单
 * 
 * @author SunTo
 * 
 */
@Controller
@RequestMapping("/vehiclemenu")
public class AdminVehicleMenuController {

	@Autowired
	private AdminVehicleService adminVehicleService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 获取当前用户所管理的车辆（级联-0417创建）
	 * 
	 * @param response
	 * @param request
	 * @return Map<String, Map<String, List<VehicleMenu>>> 
	 * 				map<企业名称,map<车队名称,车辆集合list<>>>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getVehicleMenu")
	public ResponseModel getVehicleMenu(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel<Map<String, Map<String, List<VehicleMenu>>>> rm = new ResponseModel<Map<String, Map<String, List<VehicleMenu>>>>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

			PlatLog log = new PlatLog();
			log.setModule("系统");
			log.setContext("获取车辆级联列表");
			plog.addPlatLog(log, request);
			rm.setT(adminVehicleService.getVehicleMenu(admin.getId()));
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 车辆列入监控范畴的状态改变
	 * 
	 * @param vehicleArr
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isread")
	public ResponseModel isRead(@RequestParam(required = false, value = "vehicleArr[]") String[] vehicleArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

			if(admin.getAtype()!=1){
				adminVehicleService.isRead(admin.getId().toString(), vehicleArr);
			}else{
				vehicleInfoService.isRead(vehicleArr);
			}
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆实时监控");
			log.setContext("车辆是否列入监控范畴");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
}
