package cn.guangtong.controller.total;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import cn.guangtong.controller.driver.DriverPageBean;
import cn.guangtong.controller.vehicle.VehiclePageBean;
import cn.guangtong.entity.beidou.CommandLog;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.driver.DriverRecord;
import cn.guangtong.entity.total.PlafromAlarm;
import cn.guangtong.entity.total.TerminalAlarm;
import cn.guangtong.excel.LatestStatus;
import cn.guangtong.excel.Mileage;
import cn.guangtong.excel.RunLocus;
import cn.guangtong.model.FuleStatistics;
import cn.guangtong.model.PlatformStatusInfo;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.model.TerminalStatusInfo;
import cn.guangtong.service.beidou.AlarmrecordService;
import cn.guangtong.service.beidou.GpsInfoService;
import cn.guangtong.service.beidou.GpsRealDataService;
import cn.guangtong.service.beidou.TerminalCommandService;
import cn.guangtong.service.cms.PlatLogService;
import cn.guangtong.service.driver.DriverInfoService;
import cn.guangtong.service.total.PlatformAlarmService;
import cn.guangtong.service.total.TerminalAlarmService;
import cn.guangtong.service.vehicle.VehicleInfoService;
import cn.guangtong.utils.CommandLogPageBean;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.FuleStatisticsPageBean;
import cn.guangtong.utils.PlatFormPageBean;
import cn.guangtong.utils.PlatLogPageBean;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.RunTheReportPageBean;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.TerminalFormPageBean;
import cn.guangtong.utils.ThelateststatePageBean;
import cn.guangtong.utils.excel.ExcelKit;

/**
 * 数据报表
 * 
 * @author sutong
 * 
 */
@Controller
@RequestMapping("/forms")
public class FormsController {

	@Autowired
	private PlatLogService platLogService;

	@Autowired
	private GpsInfoService gpsInfoService;

	@Autowired
	private GpsRealDataService gRealDataService;

	@Autowired
	private DriverInfoService driverInfoService;

	@Autowired
	private VehicleInfoService vehicleInfoService;

	@Autowired
	private PlatformAlarmService platformAlarmService;
	@Autowired
	private TerminalAlarmService terminalAlarmService;
	@Autowired
	private TerminalCommandService terminalCommandService;
	@Autowired
	private GpsRealDataService gpsRealDataService;
	@Autowired
	private AlarmrecordService alarmrecordService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 最新状态报表
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/thelateststate")
	public ResponseModel<ThelateststatePageBean> thelateststate(ThelateststatePageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel<ThelateststatePageBean> rm = new ResponseModel<ThelateststatePageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 获取每页记录数
			pageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			pageBean.setCurrentPage(getcurrentPage(request));

			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			gpsRealDataService.findGpsRealData(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("最新状态报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 最新状态报表下载
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping("/thelateststateExcel")
	public void thelateststateExcel(ThelateststatePageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 获取每页记录数
		pageBean.setPageCount(getPageCount(request));
		// 获取当前页码
		pageBean.setCurrentPage(getcurrentPage(request));

		if (vehArr != null) {
			pageBean.setSimNos(Arrays.asList(vehArr));
		}
		gpsRealDataService.findGpsRealDataExcel(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(LatestStatus.class, response).toExcel(pageBean.getBeanList(), "车辆最新最新状态报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("最新状态报表下载");
		plog.addPlatLog(log, request);
	}

	/**
	 * 油耗统计
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findFuleStatistics")
	public ResponseModel<FuleStatisticsPageBean> findFuleStatistics(FuleStatisticsPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<FuleStatisticsPageBean> rm = new ResponseModel<FuleStatisticsPageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 获取每页记录数
			pageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			pageBean.setCurrentPage(getcurrentPage(request));
			// 获取统计单位
			pageBean.setType(getType(request));
			gpsInfoService.findFuleStatisticsPage(pageBean);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("油耗统计");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 油耗统计报表下载
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/findFuleStatisticsExcel")
	public void findFuleStatisticsExcel(FuleStatisticsPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 获取每页记录数
		pageBean.setPageCount(getPageCount(request));
		// 获取当前页码
		pageBean.setCurrentPage(getcurrentPage(request));
		// 获取统计单位
		pageBean.setType(getType(request));
		gpsInfoService.findFuleStatistics(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(FuleStatistics.class, response).toExcel(pageBean.getBeanList(), "油耗统计报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("油耗统计报表下载");
		plog.addPlatLog(log, request);
	}

	/**
	 * 获取统计单位
	 * 
	 * @param request
	 * @return
	 */
	private String getType(HttpServletRequest request) {
		String type = "day";
		String param = request.getParameter("type");
		if (param != null && !param.trim().isEmpty()) {
			try {
				type = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return type;
	}

	/**
	 * 运行报表
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/runTheReport")
	public ResponseModel<RunTheReportPageBean> runTheReport(RunTheReportPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel<RunTheReportPageBean> rm = new ResponseModel<RunTheReportPageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 获取每页记录数
			pageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			pageBean.setCurrentPage(getcurrentPage(request));
			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("运行报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 获取全部平台报警类型
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllPlatFormAlarm")
	public ResponseModel<Map<String, Object>> getAllPlatFormAlarm(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);

			Map<String, Object> platAlarms = new HashMap<String, Object>();
			platAlarms.put("platform", new PlatformStatusInfo());

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(platAlarms);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("获取全部平台报警类型");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 分页显示平台报警信息
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPlatFormsByPage")
	public ResponseModel<PlatFormPageBean> getPlatFormsByPage(PlatFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel<PlatFormPageBean> rm = new ResponseModel<PlatFormPageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");

			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));

			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			platformAlarmService.getPlatForms(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("分页显示平台报警信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出平台报警信息报表
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping("/getPlatFormsByPageExcel")
	public void getPlatFormsByPageExcel(PlatFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("beidouDs");

		// 封装参数
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));

		if (vehArr != null) {
			pageBean.setSimNos(Arrays.asList(vehArr));
		}
		platformAlarmService.getPlatFormsExcel(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(PlafromAlarm.class, response).toExcel(pageBean.getBeanList(), "平台报警信息报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("导出平台报警信息报表");
		plog.addPlatLog(log, request);
	}

	/**
	 * 命令日志报表
	 */
	@ResponseBody
	@RequestMapping("/getCommandLogReport")
	public ResponseModel<CommandLogPageBean> getCommandLogReport(CommandLogPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<CommandLogPageBean> rm = new ResponseModel<CommandLogPageBean>();

		try {
			SettingMessageHeaders.setHeaders(response);

			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));

			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if (admin.getAtype() != 1) {
				pageBean.setAdminId(admin.getId().toString());

				pageBean.setTotalCount((terminalCommandService.countOfLogByOther(pageBean))*1000+(int)(Math.random()*1000));
				pageBean.setBeanList(terminalCommandService.commandLogOfTableByOther(pageBean));
			} else {
				pageBean.setTotalCount((terminalCommandService.countOfLog(pageBean))*1000+(int)(Math.random()*1000));
				pageBean.setBeanList(terminalCommandService.commandLogOfTable(pageBean));
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("命令日志报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出命令日志报表
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param response
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("/getTerPlagExcel")
	public void getTerPlagExcel(CommandLogPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletResponse response, HttpServletRequest request) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));

		if (vehArr != null) {
			pageBean.setSimNos(Arrays.asList(vehArr));
		}
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());

			pageBean.setBeanList(terminalCommandService.commandLogOfTableExcelByOther(pageBean));
		} else {
			pageBean.setBeanList(terminalCommandService.commandLogOfTableExcel(pageBean));
		}
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(CommandLog.class, response).toExcel(pageBean.getBeanList(), "命令日志信息报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("导出命令日志报表");
		plog.addPlatLog(log, request);
	}

	/**
	 * 获取全部终端报警类型
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllTerminalFormAlarm")
	public ResponseModel<Map<String, Object>> getAllTerminalFormAlarm(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Map<String, Object> terminalAlarms = new HashMap<String, Object>();
			terminalAlarms.put("terminal", new TerminalStatusInfo());

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(terminalAlarms);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("获取全部终端报警类型");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 分页显示终端报警信息
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getTerminalFormsByPage")
	public ResponseModel<TerminalFormPageBean> getTerminalFormsByPage(TerminalFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<TerminalFormPageBean> rm = new ResponseModel<TerminalFormPageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));

			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if (admin.getAtype() != 1) {
				pageBean.setAdminId(admin.getId().toString());

				pageBean.setTotalCount((terminalAlarmService.getCountsByOther(pageBean))*1000+(int)(Math.random()*1000));
				pageBean.setBeanList(terminalAlarmService.getTerminalFormsByOther(pageBean));
			} else {
				pageBean.setTotalCount((terminalAlarmService.getCounts(pageBean))*1000+(int)(Math.random()*1000));
				pageBean.setBeanList(terminalAlarmService.getTerminalForms(pageBean));
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("分页显示终端报警信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 导出终端报警信息报表
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping("/getTerFormsByPageExcel")
	public void getTerFormsByPageExcel(TerminalFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("beidouDs");
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));
		// 封装参数
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));

		if (vehArr != null) {
			pageBean.setSimNos(Arrays.asList(vehArr));
		}
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());

			pageBean.setBeanList(terminalAlarmService.getTerminalFormsExcel(pageBean));
		} else {
			pageBean.setBeanList(terminalAlarmService.getTerminalFormsExcel(pageBean));
		}
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(TerminalAlarm.class, response).toExcel(pageBean.getBeanList(), "终端报警信息报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("导出终端报警信息报表");
		plog.addPlatLog(log, request);
	}

	/**
	 * 根据当前时间查询最新报警状态信息
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getALLNewestAlarm")
	public ResponseModel getALLNewestAlarm(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel rm = new ResponseModel();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");

			// 当前时间-30秒查起
			Calendar c = new GregorianCalendar();
			Date date = new Date();
			c.setTime(date);
			c.add(Calendar.SECOND, -30);

			String time = FormatDateUtils.dateToString(c.getTime());
			
			rm.setObj(alarmrecordService.getALLNewestAlarm(time));

			rm.setMsg("成功");
			rm.setSuccess(true);
			// rm.setObj(terminalAlarmService.getALLNewestAlarm(dealTime));

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("根据当前时间查询最新报警状态信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 操作日志报表数据
	 */
	@ResponseBody
	@RequestMapping("/getOperationLogReport")
	public ResponseModel<PlatLogPageBean> getOperationLogReport(PlatLogPageBean pageBean, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<PlatLogPageBean> rm = new ResponseModel<PlatLogPageBean>();

		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("cmsDs");

			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));

			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if (admin.getAtype() != 1) {
				pageBean.setAdminId(admin.getId().toString());

				pageBean.setTotalCount(platLogService.countOfPlatLogsByOther(pageBean));// 分页总记录数
				pageBean.setBeanList(platLogService.getPlatLogsByOther(pageBean)); // 数据
			} else {
				pageBean.setTotalCount(platLogService.countOfPlatLogs(pageBean));// 分页总记录数
				pageBean.setBeanList(platLogService.getPlatLogs(pageBean)); // 数据
			}
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("操作日志报表数据");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 操作日志报表数据excel导出
	 */
	@ResponseBody
	@RequestMapping("/getOperationLogReportExcel")
	public void getOperationLogReportExcel(PlatLogPageBean pageBean, HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		DataSourceContextHolder.setDataSourceType("cmsDs");

		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));

		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());

			pageBean.setTotalCount(platLogService.countOfPlatLogsByOther(pageBean));// 分页总记录数
			pageBean.setBeanList(platLogService.getPlatLogsExcelByOther(pageBean)); // 数据
		} else {
			pageBean.setTotalCount(platLogService.countOfPlatLogs(pageBean));// 分页总记录数
			pageBean.setBeanList(platLogService.getPlatLogsExcel(pageBean)); // 数据
		}
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(PlatLog.class, response).toExcel(pageBean.getBeanList(), "操作日志报表数据报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("操作日志报表数据excel导出");
		plog.addPlatLog(log, request);
	}

	/**
	 * 驾驶员记录报表数据
	 */
	@ResponseBody
	@RequestMapping("/driverOfTable")
	public ResponseModel<DriverPageBean> driverOfTable(DriverPageBean pageBean, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<DriverPageBean> rm = new ResponseModel<DriverPageBean>();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");

			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			driverInfoService.driverOfTable(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("驾驶员记录报表数据");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出驾驶员记录信息报表
	 * 
	 * @param pageBean
	 * @param response
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("/getDriverFormExcel")
	public void getDriverFormExcel(DriverPageBean pageBean, HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		DataSourceContextHolder.setDataSourceType("driverDs");
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));
		driverInfoService.driverOfTableExcel(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(DriverRecord.class, response).toExcel(pageBean.getBeanList(), "驾驶员记录信息报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("导出驾驶员记录信息报表");
		plog.addPlatLog(log, request);
	}

	/**
	 * 轨迹报表
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tableOfLocus")
	public ResponseModel<VehiclePageBean> tableOfLocus(VehiclePageBean pageBean, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<VehiclePageBean> rm = new ResponseModel<VehiclePageBean>();

		try {
			SettingMessageHeaders.setHeaders(response);

			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));

			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if (admin.getAtype() != 1) {
				pageBean.setAdminId(admin.getId().toString());
				pageBean.setTotalCount(gpsInfoService.countOfLocusByOther(pageBean));// 分页总数
				pageBean.setBeanList(gpsInfoService.tableOfLocusByOther(pageBean));// 报表数据
			} else {
				pageBean.setTotalCount(gpsInfoService.countOfLocus(pageBean));// 分页总数
				pageBean.setBeanList(gpsInfoService.tableOfLocus(pageBean));// 报表数据
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("轨迹报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出车辆运行轨迹报表
	 * 
	 * @param pageBean
	 * @param response
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("/getLocusFormExcel")
	public void getLocusFormExcel(VehiclePageBean pageBean, HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);

		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));

		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			pageBean.setBeanList(gpsInfoService.tableOfLocusExcelByOther(pageBean));// 报表数据
		} else {
			pageBean.setBeanList(gpsInfoService.tableOfLocusExcel(pageBean));// 报表数据
		}
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(RunLocus.class, response).toExcel(pageBean.getBeanList(), "车辆运行轨迹报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("导出车辆运行轨迹报表");
		plog.addPlatLog(log, request);
	}

	/**
	 * 车辆运行报表
	 */
	@ResponseBody
	@RequestMapping("/getVehicleOperationReport")
	public ResponseModel<VehiclePageBean> getVehicleOperationReport(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<VehiclePageBean> rm = new ResponseModel<VehiclePageBean>();

		try {
			SettingMessageHeaders.setHeaders(response);
			VehiclePageBean pageBean = new VehiclePageBean();

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("车辆运行报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 里程报表
	 */
	@ResponseBody
	@RequestMapping("/getMileageReport")
	public ResponseModel<VehiclePageBean> getMileageReport(VehiclePageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<VehiclePageBean> rm = new ResponseModel<VehiclePageBean>();

		try {
			SettingMessageHeaders.setHeaders(response);

			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));

			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			gpsInfoService.mileageOfTable(pageBean);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("里程报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出里程报表
	 */
	@ResponseBody
	@RequestMapping("/getMileageReportExcel")
	public void getMileageReportExcel(VehiclePageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));

		if (vehArr != null) {
			pageBean.setSimNos(Arrays.asList(vehArr));
		}
		gpsInfoService.mileageOfTableExcel(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(Mileage.class, response).toExcel(pageBean.getBeanList(), "导出里程报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("导出里程报表");
		plog.addPlatLog(log, request);
	}

	/**
	 * 进出区域报表
	 */
	@ResponseBody
	@RequestMapping("/getRegionReport")
	public VehiclePageBean getRegionReport(HttpServletResponse response, HttpServletRequest request) {
		return null;
	}

	/**
	 * 返回车牌号数组
	 * 
	 * @param str
	 *            企业ID
	 * @return
	 */
	public List<String> dealCoopId(String[] coopArr, String[] vehArr) {
		List<String> list = null;
		if (coopArr != null && coopArr.length > 0 && !coopArr[0].equals("")) {
			List<String> lt = new ArrayList<String>();
			for (int i = 0; i < coopArr.length; i++) {
				lt.add(coopArr[i]);
			}
			list = vehicleInfoService.queryVehicleByCoopId(lt);
		} else if (vehArr != null && vehArr.length > 0 && !vehArr[0].equals("")) {
			for (int i = 0; i < vehArr.length; i++) {
				list.add(vehArr[i]);
			}
		}
		return list;
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
	 * 获取界面显示页数
	 * 
	 * @param request
	 * @return
	 */
	private int getPageCountNewest(HttpServletRequest request) {
		int pageCount = 5;
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
	 * 获取排序类型
	 * 
	 * @param request
	 * @return
	 */
	private String getSortType(HttpServletRequest request) {
		String sortType = "asc";
		String param = request.getParameter("sortType");
		if (param != null && !param.trim().isEmpty()) {
			try {
				sortType = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return sortType;
	}

	/**
	 * 获取排序信息
	 * 
	 * @param request
	 * @return
	 */
	private String getSortInfo(HttpServletRequest request) {
		String sortInfo = "id";
		String param = request.getParameter("sortInfo");
		if (param != null && !param.trim().isEmpty()) {
			try {
				sortInfo = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return sortInfo;
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
	 * 获取查询条件
	 * 
	 * @param request
	 * @return
	 */
	private String getCondition(HttpServletRequest request) {
		String condition = null;
		String param = request.getParameter("condition");
		if (param != null && !param.trim().isEmpty()) {
			try {
				condition = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return condition;
	}

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
