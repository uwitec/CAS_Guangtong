package cn.guangtong.controller.beidou;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.beidou.RoadSpeedLimit;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.beidou.RoadSpeedLimitService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 
 * 道路等级限速 全路段限速 查询 增加
 * 
 * 
 */
@Controller
@RequestMapping("/road")
public class RoadSpeedLimitController {
	@Autowired
	private RoadSpeedLimitService roaLimitService;
	ResponseModel rm = new ResponseModel();
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 查询道路等级限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllRoute")
	public ResponseModel getAllRoute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(roaLimitService.getAllRoad());
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("查询道路等级限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询全路段限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getALLSection")
	public ResponseModel getALLSection(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(roaLimitService.getAllRoad());
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("查询全路段限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 通过id查询simNo卡号
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSimNoById")
	public ResponseModel getSimNoById(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			int roadId = Integer.parseInt(request.getParameter("roadId"));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(roaLimitService.getSimNoById(roadId));
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("通过id查询simNo卡号");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 增加道路等级限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertRoadGrade")
	public ResponseModel insertRoadGrade(RoadSpeedLimit roadSpeedLimit, @RequestParam(required = false, value = "simNo[]") String[] simNo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// 道路名称
			String roadName = request.getParameter("roadName");
			// 道路限制速度
			Double speedLimit = Double.parseDouble(request.getParameter("speedLimit"));
			// 道路限速等级
			String roadGrade = request.getParameter("roadGrade");
			// 开始时间
			String startTime = request.getParameter("startTime");
			// 结束时间
			String endTime = request.getParameter("endTime");
			roadSpeedLimit.setRoadName(roadName);
			roadSpeedLimit.setRoadGrade(roadGrade);
			roadSpeedLimit.setSpeedLimit(speedLimit);
			roadSpeedLimit.setStartTime(startTime);
			roadSpeedLimit.setEndTime(endTime);
			roaLimitService.insertRoute(roadSpeedLimit, simNo);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("增加道路等级限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 增加全路段限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertWholeSection")
	public ResponseModel insertWholeSection(RoadSpeedLimit roadSpeedLimit, @RequestParam(required = false, value = "simNo[]") String[] simNo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// 道路名称
			String roadName = request.getParameter("roadName");
			// 道路限制速度
			Double speedLimit = Double.parseDouble(request.getParameter("speedLimit"));
			// 开始时间
			String startTime = request.getParameter("startTime");
			// 结束时间
			String endTime = request.getParameter("endTime");
			roadSpeedLimit.setRoadName(roadName);
			roadSpeedLimit.setSpeedLimit(speedLimit);
			roadSpeedLimit.setStartTime(startTime);
			roadSpeedLimit.setEndTime(endTime);
			roaLimitService.insertRoute(roadSpeedLimit, simNo);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("增加全路段限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 更新道路等级限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateRoadGrade")
	public ResponseModel updateRoadGrade(RoadSpeedLimit roadSpeedLimit, @RequestParam(required = false, value = "simNo[]") String[] simNo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// id
			int id = Integer.parseInt(request.getParameter("id"));
			// 道路名称
			String roadName = request.getParameter("roadName");
			// 道路限制速度
			Double speedLimit = Double.parseDouble(request.getParameter("speedLimit"));
			// 道路限速等级
			String roadGrade = request.getParameter("roadGrade");
			// 开始时间
			String startTime = request.getParameter("startTime");
			// 结束时间
			String endTime = request.getParameter("endTime");
			roadSpeedLimit.setId(id);
			roadSpeedLimit.setRoadName(roadName);
			roadSpeedLimit.setRoadGrade(roadGrade);
			roadSpeedLimit.setSpeedLimit(speedLimit);
			roadSpeedLimit.setStartTime(startTime);
			roadSpeedLimit.setEndTime(endTime);
			roaLimitService.updateRoad(roadSpeedLimit, simNo);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("更新道路等级限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 更新全路段限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateWholeSection")
	public ResponseModel updateWholeSection(RoadSpeedLimit roadSpeedLimit, @RequestParam(required = false, value = "simNo[]") String[] simNo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// id
			int id = Integer.parseInt(request.getParameter("id"));
			// 道路名称
			String roadName = request.getParameter("roadName");
			// 道路限制速度
			Double speedLimit = Double.parseDouble(request.getParameter("speedLimit"));
			// 开始时间
			String startTime = request.getParameter("startTime");
			// 结束时间
			String endTime = request.getParameter("endTime");
			roadSpeedLimit.setId(id);
			roadSpeedLimit.setRoadName(roadName);
			roadSpeedLimit.setSpeedLimit(speedLimit);
			roadSpeedLimit.setStartTime(startTime);
			roadSpeedLimit.setEndTime(endTime);
			roaLimitService.updateRoad(roadSpeedLimit, simNo);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("更新全路段限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 删除道路等级限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRoadGrade")
	public ResponseModel deleteRoadGrade(int id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			roaLimitService.deleteRoadById(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("删除道路等级限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 删除全路段限速
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteWholeSection")
	public ResponseModel deleteWholeSection(int id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			roaLimitService.deleteRoadById(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("道路等级限速 全路段限速 查询 增加");
			log.setContext("删除全路段限速");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
}
