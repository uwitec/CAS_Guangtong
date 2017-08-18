package cn.guangtong.controller.beidou;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.beidou.AlarmsInfo;
import cn.guangtong.entity.beidou.MapStyle;
import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.beidou.AlarmsInfoService;
import cn.guangtong.service.beidou.GpsInfoService;
import cn.guangtong.service.beidou.MapStyleService;
import cn.guangtong.service.beidou.MediaItemService;
import cn.guangtong.service.beidou.TerminalCommandService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.MediaItemPageBean;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.StringParse;

/**
 * 集港物流信息子系统
 * 
 * @author sutong
 * 
 */
@Controller
@RequestMapping("/port")
public class PortlogisticsController {

	@Autowired
	private TerminalCommandService terminalCommandService;

	@Autowired
	private GpsInfoService gpsInfoService;
	@Autowired
	private MediaItemService mediaItemService;
	@Autowired
	private MapStyleService mapStyleService;
	@Autowired
	private AlarmsInfoService alarmsInfoService;
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 拍照 车辆信息实时监控 吴海涛
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/monitorByCamera")
	public ResponseModel monitorByCamera(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 车牌号
			String plateno = request.getParameter("plateno");
			// 终端SIM卡号
			String simno = request.getParameter("simno");
			// 车辆id
			int vehicleid = Integer.parseInt(request.getParameter("vehicleid"));
			// 通道ID
			String passagewayid = request.getParameter("passagewayid");
			// 拍摄命令
			String shootingcommand = request.getParameter("shootingcommand");
			// 拍照间隔 录像时间
			String phtime = request.getParameter("phtime");
			// 保存标志 1保存 0实时上传
			String saveflag = request.getParameter("saveflag");
			// 分辨率
			String resolvingpower = request.getParameter("resolvingpower");
			// 图像 视频质量
			String imagequality = request.getParameter("imagequality");
			// 亮度
			String brightness = request.getParameter("brightness");
			// 对比度
			String contrastratio = request.getParameter("contrastratio");
			// 饱和度
			String saturation = request.getParameter("saturation");
			// 色度
			String chroma = request.getParameter("chroma");

			TerminalCommand terminalCommand = new TerminalCommand();
			// 命令消息体
			terminalCommand.setCmddata(passagewayid + ";" + shootingcommand + ";" + phtime + ";" + saveflag + ";" + resolvingpower + ";" + imagequality + ";" + brightness + ";" + contrastratio + ";" + saturation + ";" + chroma);
			// 命令消息类型
			terminalCommand.setCmdtype(34817);
			// 车牌号
			terminalCommand.setPlateno(plateno);
			// 终端SIM卡号
			terminalCommand.setSimno(simno);
			// 命令执行者id
			terminalCommand.setUserid(0);
			// 车辆id
			terminalCommand.setVehicleid(vehicleid);
			terminalCommandService.insert(terminalCommand);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("拍照 车辆信息实时监控");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}

		return rm;
	}

	/**
	 * 位置汇报策略 吴海涛 赵发志
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/vPositionTReport")
	public ResponseModel vPositionTReport(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 车牌号
			String plateno = request.getParameter("plateno");
			// 终端SIM卡号
			String simno = request.getParameter("simno");
			// 车辆id
			int vehicleid = Integer.parseInt(request.getParameter("vehicleid"));
			TerminalCommand terminalCommand = new TerminalCommand();
			// 命令消息体
			terminalCommand.setCmddata("32,2");
			// 命令消息类型
			terminalCommand.setCmdtype(33027);
			// 车牌号
			terminalCommand.setPlateno(plateno);
			// 终端SIM卡号
			terminalCommand.setSimno(simno);
			// 命令执行者id
			terminalCommand.setUserid(0);
			// 车辆id
			terminalCommand.setVehicleid(vehicleid);
			terminalCommandService.insert(terminalCommand);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("位置汇报策略");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 临时位置追踪 吴海涛 赵发志
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/vTimeInterval")
	public ResponseModel vTimeInterval(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 车牌号
			String plateno = request.getParameter("plateno");
			// 终端SIM卡号
			String simno = request.getParameter("simno");
			// 车辆id
			int vehicleid = Integer.parseInt(request.getParameter("vehicleid"));
			TerminalCommand terminalCommand = new TerminalCommand();
			// 命令消息体
			terminalCommand.setCmddata("5;40");
			// 命令消息类型
			terminalCommand.setCmdtype(33282);
			// 车牌号
			terminalCommand.setPlateno(plateno);
			// 终端SIM卡号
			terminalCommand.setSimno(simno);
			// 命令执行者id
			terminalCommand.setUserid(0);
			// 车辆id
			terminalCommand.setVehicleid(vehicleid);

			terminalCommandService.insert(terminalCommand);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("临时位置追踪");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 定时定距上传(终端参数设置) 吴海涛
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/vPositionTimeReport")
	public ResponseModel vPositionTimeReport(@RequestParam(required = false, value = "plateNo[]") String[] plateNo, @RequestParam(required = false, value = "simNo[]") String[] simNo, @RequestParam(required = false, value = "vehicleId[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String createDate = request.getParameter("createDate");
			// 时间间隔
			String timeinterval = request.getParameter("timeinterval");
			// 距离间隔
			String distanceinterval = request.getParameter("distanceinterval");
			TerminalCommand terminalCommand = new TerminalCommand();
			// 命令消息体
			terminalCommand.setCmddata("32,2;41," + timeinterval + ";44," + distanceinterval);
			// 命令消息类型
			terminalCommand.setCmdtype(33027);
			terminalCommandService.insertCommand(terminalCommand,createDate, plateNo, simNo, vehicleId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("定时定距上传");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 1:无线升级/2:控制终端连接指定服务器 3:终端关机:/4:复位:/5:恢复出厂设置 6:关闭数据通信/7:关闭所有无线通信 终端关机/复位/恢复出厂设置 关闭数据通信
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/terminalController")
	public ResponseModel<Integer> terminalController(@RequestParam(required = false, value = "plateNo[]") String[] plateNo, @RequestParam(required = false, value = "simNo[]") String[] simNo, @RequestParam(required = false, value = "vehicleId[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Integer> rm = new ResponseModel<Integer>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String createDate = request.getParameter("createDate");
			TerminalCommand terminalCommand = new TerminalCommand();
			// 获取命令字
			String cmd = request.getParameter("cmd");
			if (cmd != null && (cmd.equals("1") || cmd.equals("2"))) {
				// 命令消息体
				terminalCommand.setCmddata(request.getParameter("cmddata"));
			}
			// 命令字
			terminalCommand.setCmd(cmd);
			// 命令消息类型
			terminalCommand.setCmdtype(33029);

			List<Integer> list=	terminalCommandService.insertCommand(terminalCommand, createDate,plateNo, simNo, vehicleId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("终端参数控制");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 添加终端指令
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/insertTerminalController")
	public ResponseModel<Integer> insertTerminalController(@RequestParam(required = false, value = "plateNo[]") String[] plateNo, @RequestParam(required = false, value = "simNo[]") String[] simNo, @RequestParam(required = false, value = "vehicleId[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Integer> rm = new ResponseModel<Integer>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String createDate = request.getParameter("createDate");
			TerminalCommand terminalCommand = new TerminalCommand();
			// 获取命令字
			String cmd = request.getParameter("cmd");
			// 命令消息体
			terminalCommand.setCmddata(request.getParameter("cmddata"));
			// 命令字
			terminalCommand.setCmd(cmd);
			//时间
			terminalCommand.setCreatedate(request.getParameter("createDate"));
			// 命令消息类型
			terminalCommand.setCmdtype(Integer.parseInt(request.getParameter("cmdType").toString()));
			
			List<Integer> list=	terminalCommandService.insertCommand(terminalCommand,createDate, plateNo, simNo, vehicleId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("终端参数控制");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 终端参数设置(批量添加) 吴海涛
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/insertCommandByterminal")
	public ResponseModel<Integer> insertCommandByterminal(@RequestParam(required = false, value = "plateNo[]") String[] plateNo, @RequestParam(required = false, value = "simNo[]") String[] simNo, @RequestParam(required = false, value = "vehicleId[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Integer> rm = new ResponseModel<Integer>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String Command = request.getParameter("Command");
			String createDate = request.getParameter("createDate");
			List<Integer> list=terminalCommandService.insertCommandByterminal(Command,createDate, plateNo, simNo, vehicleId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("终端参数设置");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询终端参数(批量 查找) 吴海涛
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/selectCommandByterminal")
	public ResponseModel<Integer> selectCommandByterminal(@RequestParam(required = false, value = "plateNo[]") String[] plateNo, @RequestParam(required = false, value = "simNo[]") String[] simNo, @RequestParam(required = false, value = "vehicleId[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Integer> rm = new ResponseModel<Integer>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String cmdData=request.getParameter("cmdData");
			TerminalCommand terminalCommand = new TerminalCommand();
			// 命令消息类型
			terminalCommand.setCmdtype(33028);
			if(cmdData==null||cmdData.equals("")){
				cmdData=";";
			}
			terminalCommand.setCmddata(cmdData);
			List<Integer> list=terminalCommandService.selectCommandByterminal(terminalCommand, plateNo, simNo, vehicleId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("查询终端参数");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 分页媒体信息 吴海涛
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMediaItemByPage")
	public ResponseModel<MediaItemPageBean> getMediaItemByPage(MediaItemPageBean pageBean, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel<MediaItemPageBean> rm = new ResponseModel<MediaItemPageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			mediaItemService.getMediaItem(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("分页媒体信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 设置报警状态 吴海涛
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/setAlarmStatus")
	public ResponseModel setAlarmStatus(@RequestParam(required = false, value = "alarms[]") int[] alarms, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			StringBuffer stringBuffer = StringParse.toParseInt(alarms);
			String s = StringParse.pAlarmStatus(stringBuffer.toString());
			String a[] = s.split("--");
			for (String string : a) {
				System.out.println(string + "**************");
			}
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("集港物流信息子系统");
			log.setContext("设置报警状态");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 报警状态查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAlarmsInfo")
	public ResponseModel getAlarmsInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			int userId = admin.getId();
			AlarmsInfo alarmsInfo = alarmsInfoService.getAlarmsInfo(userId);
			rm.setT(alarmsInfo);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆实时监控");
			log.setContext("报警状态查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 报警状态更新
	 * 
	 * @param alarmsInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateAlarmsInfo")
	public ResponseModel updateAlarmsInfo(AlarmsInfo alarmsInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			alarmsInfoService.updateAlarmsInfo(alarmsInfo);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆实时监控");
			log.setContext("报警状态更新");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 地图样式查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getStyleByUserId")
	public ResponseModel getStyleByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			int userId = admin.getId();
			MapStyle mapStyle = mapStyleService.selectByUserId(userId);
			rm.setT(mapStyle);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆实时监控");
			log.setContext("地图样式查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 地图样式更新
	 * 
	 * @param mapStyle
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateStyle")
	public ResponseModel updateStyle(MapStyle mapStyle, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			mapStyleService.update(mapStyle);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("车辆实时监控");
			log.setContext("地图样式查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 获取界面显示页数
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