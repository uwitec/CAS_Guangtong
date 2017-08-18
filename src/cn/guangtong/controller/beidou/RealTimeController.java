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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.beidou.Enclosure;
import cn.guangtong.entity.beidou.GpsRealData;
import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.AccessArea;
import cn.guangtong.model.LocusInfo;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.model.TerminalStatusInfo;
import cn.guangtong.model.Trajectory;
import cn.guangtong.model.VehicleDetails;
import cn.guangtong.service.beidou.EnclosureService;
import cn.guangtong.service.beidou.EnclousureBindService;
import cn.guangtong.service.beidou.GpsInfoService;
import cn.guangtong.service.beidou.GpsRealDataService;
import cn.guangtong.service.beidou.TerminalCommandService;
import cn.guangtong.service.vehicle.VehicleInfoService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.StringParse;
import cn.guangtong.utils.excel.ExcelKit;

/**
 * 实时车辆信息监控
 * 
 * @author sutong
 * 
 */
@Controller
@RequestMapping("/realTime")
public class RealTimeController {

	@Autowired
	private TerminalCommandService terminalCommandService;

	@Autowired
	private GpsRealDataService gpsRealDataService;

	@Autowired
	private GpsInfoService gpsInfoService;

	@Autowired
	private EnclosureService enclosureService;

	@Autowired
	private EnclousureBindService enclousureBindService;

	@Autowired
	private VehicleInfoService vehicleInfoService;
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	private static final String CMD_MEDIA_UPLOAD = "0x8803";// 多媒体上传

	/**
	 * 根据当前用户查询可见所有车辆的信息
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findVehicleByAdmin")
	public ResponseModel sRealtimePosition(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(vehicleInfoService.findVehicleByAdmin(admin));
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据当前用户查询可见所有车辆的信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据当前用户,车辆运营类型,查询可见所有车辆的信息
	 */
	@ResponseBody
	@RequestMapping("/findVehicleByAdminAndSpecialType")
	public ResponseModel findVehicleByAdminAndSpecialType(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			// 查询的车辆营运类型
			String specialType = request.getParameter("specialType");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(vehicleInfoService.findVehicleByAdminAndSpecialType(admin, specialType));
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据当前用户,车辆运营类型,查询可见所有车辆的信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 添加区域-区域绑定-区域设置指令
	 * 
	 * @param enBinding
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertEnclosure")
	public ResponseModel insertSelective(Enclosure ec, @RequestParam(required = false, value = "plateNoS[]") String[] plateNo, @RequestParam(required = false, value = "simNoS[]") String[] simNo, @RequestParam(required = false, value = "vehicleIdS[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			String createDate = request.getParameter("time");
			boolean state = enclosureService.insert(ec, plateNo, simNo, vehicleId, createDate);
			if (state) {
				rm.setMsg("成功");
				rm.setSuccess(true);
				PlatLog log = new PlatLog();
				log.setModule("实时车辆信息监控");
				log.setContext("添加区域-区域绑定-区域设置指令");
				plog.addPlatLog(log, request);
			} else {
				rm.init();
			}

		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询指令内容
	 * 
	 * @param simNo
	 * @param createDate
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/getTerminalCommandInfo")
	public ResponseModel<TerminalCommand> getTerminalCommandInfo(@RequestParam(required = false, value = "cmdid[]") String[] cmdid, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<TerminalCommand> rm = new ResponseModel<TerminalCommand>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			List<TerminalCommand> list = terminalCommandService.getTerminalCommandInfo(cmdid);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("查询指令内容");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 区域更新-删除原有绑定-发送删除区域指令-设置新的绑定-发送设置区域指令
	 */
	@ResponseBody
	@RequestMapping("/updateEnclosure")
	public ResponseModel updateEnclosure(Enclosure ec, @RequestParam(required = false, value = "plateNoS[]") String[] plateNo, @RequestParam(required = false, value = "simNoS[]") String[] simNo, @RequestParam(required = false, value = "vehicleIdS[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// 更新区域
			boolean state = enclosureService.updateByEnclosure(ec, plateNo, simNo, vehicleId);
			if (state) {
				rm.setMsg("成功");
				rm.setSuccess(true);
				PlatLog log = new PlatLog();
				log.setModule("实时车辆信息监控");
				log.setContext("区域更新-删除原有绑定-发送删除区域指令-设置新的绑定-发送设置区域指令");
				plog.addPlatLog(log, request);
			} else {
				rm.init();
			}

		} catch (Exception e) {
			rm.init();
		}
		return rm;

	}

	/**
	 * 根据区域id删除--删除区域-删除区域绑定-删除区域指令
	 * 
	 * @param enclosureId
	 */
	@ResponseBody
	@RequestMapping("/deleteEnclosure")
	public ResponseModel deleteEnclosure(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// 区域id
			int enclosureId = Integer.parseInt(request.getParameter("enclosureid"));
			enclosureService.deleteEnclosure(enclosureId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据区域id删除--删除区域-删除区域绑定-删除区域指令");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据simNo(卡号)精准查询车辆信息 吴海涛
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRealDatasBySimNo")
	public ResponseModel<VehicleDetails> getRealDatasBySimNo(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<VehicleDetails> rm = new ResponseModel<VehicleDetails>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			String simNo = request.getParameter("simNo");
			rm.setT(gpsRealDataService.getRealDatasBySimNo(simNo));
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据simNo(卡号)精准查询车辆信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据simNo(卡号)模糊查询车辆信息 赵发志
	 */
	@ResponseBody
	@RequestMapping("/getRealDatasByObsSimNo")
	public ResponseModel<Map<String, Object>> getRealDatasByObsSimNo(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			String simNo = request.getParameter("simNo");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(gpsRealDataService.getRealDatasByObsSimNo(simNo));
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据simNo(卡号)模糊查询车辆信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据PlateNo(车牌号)查询车辆信息 赵发志
	 */
	@ResponseBody
	@RequestMapping("/getRealDatasByPlateNo")
	public ResponseModel<GpsRealData> getRealDatasByPlateNo(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<GpsRealData> rm = new ResponseModel<GpsRealData>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			String plateNo = request.getParameter("plateNo");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(gpsRealDataService.getRealDatasByPlateNo(plateNo));
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据PlateNo(车牌号)查询车辆信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 在线监控车辆状态
	 * 
	 * 赵发志
	 */
	@ResponseBody
	@RequestMapping("/onlineAlarmInformation")
	public ResponseModel<Map<String, Object>> onlineAlarmInformation(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			List<Map<String, Object>> list = gpsRealDataService.onlineAlarmInformation();
			for (Map<String, Object> map : list) {
				int alarmState = Integer.valueOf((String) map.get("alarmState"), 2);
				TerminalStatusInfo statusInfo = StringParse.stringParse(alarmState, 0);
				map.put("statusInfo", statusInfo);
			}
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("在线监控车辆状态");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 在线驾驶员监控(视频) 赵发志
	 */
	@ResponseBody
	@RequestMapping("/onlineDriverMonitoring")
	public ResponseModel onlineDriverMonitoring(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);// 设置消息头
			DataSourceContextHolder.setDataSourceType("beidouDs");// 切换到beidouDs的数据源
			String[] fields = RealTimeController.CMD_MEDIA_UPLOAD.split("[x]", -1);
			int cmdType = Integer.parseInt(fields[1], 16);// 获取cmdType
			String plateNo = request.getParameter("plateNo");// 车牌号
			String simNo = request.getParameter("simNo");// 终端SIM卡号
			String beginTime = request.getParameter("beginTime");// 开始时间
			String endTime = request.getParameter("endTime");// 结束时间
			int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));// 车辆ID
			// Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
			// int userId = admin.getId();// 命令执行者id
			int userId = 0;
			StringBuffer cmdData = new StringBuffer();// 创建命令参数
			cmdData.append("2").append(";");// 多媒体类型
			cmdData.append("1").append(";");// 通道ID
			cmdData.append("0").append(";");// 事件项编码0：平台下发指令 1：定时动作 2：抢劫报警触发
			cmdData.append(beginTime).append(";");// 格式2017/03/07 18:00:07
			cmdData.append(endTime).append(";");// 格式2017/03/07 18:20:07
			cmdData.append("0");// 删除标志0删除1保留
			TerminalCommand terminalCommand = new TerminalCommand();// 创建指令对象
			terminalCommand.setCmdtype(cmdType);// 命令消息类型
			terminalCommand.setCmddata(cmdData.toString());// 命令
			terminalCommand.setPlateno(plateNo);// 车牌号
			terminalCommand.setSimno(simNo);// 终端SIM卡号
			terminalCommand.setVehicleid(vehicleId);// 车辆id
			terminalCommand.setUserid(userId);// 命令执行者id
			terminalCommandService.insert(terminalCommand);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("在线驾驶员监控(视频)");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询所有的区域
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEnclosure")
	public ResponseModel<Enclosure> getEnclosureAll(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Enclosure> rm = new ResponseModel<Enclosure>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(enclosureService.getEnclosureAll());
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("查询所有的区域");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据区域类型查区域绑定
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEnclosureBindByType")
	public ResponseModel<Map<String, Object>> getEnclosureBindByType(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			String type = request.getParameter("type");
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(enclousureBindService.getEnclosureBindByType(type));
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据区域类型查区域绑定");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据区域Id查区域绑定的车辆SimNo卡号
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEnclosureBindByEnclosureid")
	public ResponseModel<String> getEnclosureBindByEnclosureid(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			String enclosureId = request.getParameter("enclosureId");
			SettingMessageHeaders.setHeaders(response);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(enclousureBindService.getEnclosureBindSimNoByEnclosureid(Integer.parseInt(enclosureId)));
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("根据区域Id查区域绑定的车辆SimNo卡号");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 通过id查询运行轨迹信息
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLocusInfoById")
	public ResponseModel<LocusInfo> getLocusInfoById(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<LocusInfo> rm = new ResponseModel<LocusInfo>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			String id = request.getParameter("id");
			String num=request.getParameter("num");
			SettingMessageHeaders.setHeaders(response);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(gpsInfoService.getLocusInfoById(id,num));
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("通过id查询运行轨迹信息");
			PlatLogUtil.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 连接服务器
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/connectionServer")
	public ResponseModel connectionServer(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String status = request.getParameter("status");
			if ("open".equals(status)) {
				rm.setMsg("成功");
				rm.setSuccess(true);
			} else if ("close".equals(status)) {
				rm.setMsg("成功");
				rm.setSuccess(true);
			} else {
				rm.setMsg("成功");
				rm.setSuccess(false);
			}
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("连接服务器");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 通过simNO查询运行轨迹
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/mapTrajectory")
	public ResponseModel<Map<String, Object>> mapTrajectory(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
		
			int parkingTime = Integer.parseInt(request.getParameter("parkingTime"));
			String simNo =request.getParameter("simNo");
			String num =request.getParameter("num");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if("".equals(simNo)||simNo==null){
				simNo=num;
			}
			
			SettingMessageHeaders.setHeaders(response);
			Map<String, Object> map = gpsInfoService.mapTrajectory(simNo, startTime, endTime, parkingTime);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(map);
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("通过simNo查询运行轨迹");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 通过simNO查询运行轨迹信息导出Excel
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/mapTrajectoryExcel")
	public ResponseModel<Map<String, Object>> mapTrajectoryExcel(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");

			int parkingTime = Integer.parseInt(request.getParameter("parkingTime"));
			String simNo =request.getParameter("simNo");
			String num =request.getParameter("num");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if("".equals(simNo)||simNo==null||"null".equals(simNo)){
				simNo=num;
			}
			
			SettingMessageHeaders.setHeaders(response);
			Map<String, Object> data = gpsInfoService.mapTrajectory(simNo, startTime, endTime, parkingTime);
			List<Trajectory> list = (List<Trajectory>) data.get("trajectoryList");

			// 生成Excel并使用浏览器下载
			ExcelKit.$Export(Trajectory.class, response).toExcel(list, num+"历史轨迹信息");

			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("实时车辆信息监控");
			log.setContext("通过simNo查询运行轨迹");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 进出区域
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/IsAccessArea")
	public ResponseModel<AccessArea> IsAccessArea(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<AccessArea> rm = new ResponseModel<AccessArea>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String points = request.getParameter("points");
			List<AccessArea> list = gpsInfoService.IsAccessArea(startTime, endTime, points);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
}
