package cn.guangtong.controller.total;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.total.PlatTotalAlarm;
import cn.guangtong.excel.OnlineRate;
import cn.guangtong.excel.TerminalChart;
import cn.guangtong.model.DataReport;
import cn.guangtong.model.FuleStatisticsCharts;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.beidou.GpsInfoService;
import cn.guangtong.service.coopertation.CooperationInfoService;
import cn.guangtong.service.driver.DriverInfoService;
import cn.guangtong.service.total.PlatformAlarmService;
import cn.guangtong.service.total.TerminalAlarmService;
import cn.guangtong.utils.DataReportPageBean;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.GpsInfoFormPageBean;
import cn.guangtong.utils.PlatFormPageBean;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.TerminalPageBean;
import cn.guangtong.utils.excel.ExcelKit;

/**
 * 数据统计
 * 
 * @author sutong
 * 
 */
@Controller
@RequestMapping("/charts")
public class ChartsController {

	@Autowired
	private DriverInfoService driverInfoService;

	@Autowired
	private TerminalAlarmService terAlarmService;

	@Autowired
	private PlatformAlarmService platformAlarmService;

	@Autowired
	private GpsInfoService gpsInfoService;

	@Autowired
	private CooperationInfoService cooperationInfoService;

	ResponseModel rm = new ResponseModel();

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 油耗统计折线图
	 * 
	 * @param fuleStatisticsCharts
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findFuleStatisticsCharts")
	public ResponseModel<FuleStatisticsCharts> findFuleStatisticsCharts(FuleStatisticsCharts fuleStatisticsCharts, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<FuleStatisticsCharts> rm = new ResponseModel<FuleStatisticsCharts>();

		try {
			SettingMessageHeaders.setHeaders(response);

			fuleStatisticsCharts.setStartTime(getStartTime(request));
			fuleStatisticsCharts.setEndTime(getEndTime(request));
			fuleStatisticsCharts.setType(getType(request));
			gpsInfoService.findFuleStatisticsCharts(fuleStatisticsCharts);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(fuleStatisticsCharts);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("油耗统计折线图");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 驾驶员数量统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNewDriverCount")
	public ResponseModel<Map<String, Object>> getNewDriverCount(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");

			Calendar cs = Calendar.getInstance();
			cs.set(Calendar.MONTH, cs.get(Calendar.MONTH) - 6);
			Date staday = cs.getTime();
			String startTime = new SimpleDateFormat("yyyy-MM-dd").format(staday); // 默认开始时间
			Calendar ce = Calendar.getInstance();
			ce.set(Calendar.MONTH, ce.get(Calendar.MONTH));
			Date endday = ce.getTime(); // 默认结束时间
			String endTime = new SimpleDateFormat("yyyy-MM-dd").format(endday);

			String type = "month"; // 默认查询类型
			if (request.getParameter("startTime") != null && !request.getParameter("startTime").equals("")) {
				startTime = request.getParameter("startTime");
			}
			if (request.getParameter("endTime") != null && !request.getParameter("endTime").equals("")) {
				endTime = request.getParameter("endTime");
			}
			if (request.getParameter("type") != null && !request.getParameter("type").equals("")) {
				type = request.getParameter("type");
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(driverInfoService.getNewDriverCount(startTime, endTime, type));

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("驾驶员数量统计");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 平台统计报表
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPlatformAlarmsData")
	public ResponseModel<PlatFormPageBean> getPlatformAlarmsData(PlatFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<PlatFormPageBean> rm = new ResponseModel<PlatFormPageBean>();

		try {
			SettingMessageHeaders.setHeaders(response);

			pageBean.setUrl(getUrl(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));

			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			platformAlarmService.getTongjiPlatformPage(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("平台统计报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出平台报警信息统计
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/getPlatChartAlarmsExcel")
	public void getPlatChartAlarmsExcel(PlatFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response) {
		SettingMessageHeaders.setHeaders(response);
		pageBean.setUrl(getUrl(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));

		if (vehArr != null) {
			pageBean.setSimNos(Arrays.asList(vehArr));
		}
		platformAlarmService.getTongjiPlatform(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(PlatTotalAlarm.class, response).toExcel(pageBean.getBeanList(), "平台报警信息统计");
		rm.setMsg("成功");
		rm.setSuccess(true);

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("导出平台报警信息统计");
		plog.addPlatLog(log, request);
	}

	/**
	 * 平台报表饼图
	 * 
	 * @param simNo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPlatformAlarmsTotal")
	public ResponseModel<Map<String, Object>> getPlatformAlarms(@RequestParam("simNo") String simNo, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			SettingMessageHeaders.setHeaders(response);

			String type = "pie'";
			String name = "平台报警统计情况";

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(platformAlarmService.getPlatForms(type, name, simNo));

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("平台报表饼图");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 在线率统计报表
	 * 
	 */
	@ResponseBody
	@RequestMapping("/getGpsInfosData")
	public ResponseModel<GpsInfoFormPageBean> getGpsInfosData(GpsInfoFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<GpsInfoFormPageBean> rm = new ResponseModel<GpsInfoFormPageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// 获取每页记录数
			pageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			pageBean.setCurrentPage(getcurrentPage(request));
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortAlarmInfo(request));
			if (vehArr != null) {
				pageBean.setSimNo(Arrays.asList(vehArr));
			}
			gpsInfoService.getTongjiOnlinePage(pageBean);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("在线率统计报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 在线率报表下载
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping("/OnlineRateExcel")
	public void OnlineRateExcel(GpsInfoFormPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("beidouDs");
		// 获取每页记录数
		pageBean.setPageCount(getPageCount(request));
		// 获取当前页码
		pageBean.setCurrentPage(getcurrentPage(request));
		// 封装参数
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortAlarmInfo(request));
		if (vehArr != null) {
			pageBean.setSimNo(Arrays.asList(vehArr));
		}
		gpsInfoService.getTongjiOnlinePage(pageBean);

		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(OnlineRate.class, response).toExcel(pageBean.getBeanList(), "在线率报表下载");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("在线率报表下载");
		plog.addPlatLog(log, request);
	}

	/**
	 * 在线率统计图
	 * 
	 * @param simNos
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/getChartOfOnlineRate")
	public ResponseModel<Map<String, Object>> getChartOfOnlineRate(@RequestParam(required = false, value = "simNo[]") String[] simNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			SettingMessageHeaders.setHeaders(response);
			Calendar cs = Calendar.getInstance();
			cs.set(Calendar.DATE, cs.get(Calendar.DATE) - 31);
			Date staday = cs.getTime();
			String startTime = new SimpleDateFormat("yyyy-MM-dd").format(staday); // 默认开始时间
			Calendar ce = Calendar.getInstance();
			ce.set(Calendar.DATE, ce.get(Calendar.DATE));
			Date endday = ce.getTime(); // 默认结束时间
			String endTime = new SimpleDateFormat("yyyy-MM-dd").format(endday);

			String type = "day"; // 默认查询类型
			if (request.getParameter("startTime") != null && !request.getParameter("startTime").equals("")) {
				startTime = request.getParameter("startTime");
			}
			if (request.getParameter("endTime") != null && !request.getParameter("endTime").equals("")) {
				endTime = request.getParameter("endTime");
			}
			if (request.getParameter("type") != null && !request.getParameter("type").equals("")) {
				type = request.getParameter("type");
			}

			List<String> simNos = new ArrayList<String>();
			if (simNo != null) {
				simNos = Arrays.asList(simNo);
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(gpsInfoService.getChartOfOnlineRate(simNos, startTime, endTime, type));

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("在线率统计图");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 证件有效期统计
	 * 
	 * @param type
	 *            1:驾驶证,2:行驶证,3:从业资格证
	 * @return map
	 */
	@ResponseBody
	@RequestMapping("/validCheck")
	public ResponseModel<Map<String, Object>> validCheck(@RequestParam(required = false, value = "coop[]") String[] coop, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");

			String type = request.getParameter("type");
			List<String> coopList = new ArrayList<String>();

			if (coop != null) {
				coopList = Arrays.asList(coop);
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(driverInfoService.validCheck(type, coopList));

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("证件有效期统计");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 终端报警统计
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTerminalChartsByPage")
	public ResponseModel<TerminalPageBean> getTerminalChartsByPage(TerminalPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel<TerminalPageBean> rm = new ResponseModel<TerminalPageBean>();

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
			pageBean.setSortInfo(getSortAlarmInfo(request));

			if (vehArr != null) {
				pageBean.setSimNos(Arrays.asList(vehArr));
			}
			terAlarmService.getTerminalChartsPage(pageBean);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("终端报警统计");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 下载终端报警报表
	 * 
	 * @param pageBean
	 * @param vehArr
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTerminalChartsByPageExcel")
	public void getTerminalChartsByPageExcel(TerminalPageBean pageBean, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SettingMessageHeaders.setHeaders(response);
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("beidouDs");
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));
		// 封装参数
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortAlarmInfo(request));

		if (vehArr != null) {
			pageBean.setSimNos(Arrays.asList(vehArr));
		}
		terAlarmService.getTerminalCharts(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(TerminalChart.class, response).toExcel(pageBean.getBeanList(), "终端报警报表");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("下载终端报警报表");
		plog.addPlatLog(log, request);
	}

	/**
	 * 通过simNo 查询终端报警统计饼图
	 * 
	 * @param simNo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTerminalChartsTotal")
	public ResponseModel<Map<String, Object>> getTerminalChartsTotal(@RequestParam("simNo") String simNo, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			SettingMessageHeaders.setHeaders(response);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(terAlarmService.getTerminalChartsTotal(simNo));

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("终端报警统计饼图");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 数据上报统计表格
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTableOfDataRoport")
	public ResponseModel<DataReportPageBean> getTableOfDataRoport(DataReportPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<DataReportPageBean> rm = new ResponseModel<DataReportPageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortAlarmInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			gpsInfoService.getTableOfDateReportPage(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("数据上报统计表格");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 数据上报统计导出
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/getTableOfDataRoportExcel")
	public void getTableOfDataRoportExcel(DataReportPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 封装参数
		pageBean.setUrl(getUrl(request));
		pageBean.setSortType(getSortType(request));
		pageBean.setSortInfo(getSortInfo(request));
		pageBean.setPageCount(getPageCount(request));
		pageBean.setCurrentPage(getcurrentPage(request));
		gpsInfoService.getTableOfDateReport(pageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(DataReport.class, response).toExcel(pageBean.getBeanList(), "数据上报统计导出");

		PlatLog log = new PlatLog();
		log.setModule("数据统计分析");
		log.setContext("数据上报统计导出");
		plog.addPlatLog(log, request);
	}

	/**
	 * 数据上报统计图
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getChartOfDataReport")
	public ResponseModel<Map<String, Object>> getChartOfDataReport(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);

			Calendar cs = Calendar.getInstance();
			cs.set(Calendar.DATE, cs.get(Calendar.DATE) - 11);
			Date staday = cs.getTime();
			String startTime = new SimpleDateFormat("yyyy-MM-dd").format(staday); // 默认开始时间
			Calendar ce = Calendar.getInstance();
			ce.set(Calendar.DATE, ce.get(Calendar.DATE));
			Date endday = ce.getTime(); // 默认结束时间
			String endTime = new SimpleDateFormat("yyyy-MM-dd").format(endday);

			String type = "day"; // 默认查询类型
			if (request.getParameter("startTime") != null && !request.getParameter("startTime").equals("")) {
				startTime = request.getParameter("startTime");
			}
			if (request.getParameter("endTime") != null && !request.getParameter("endTime").equals("")) {
				endTime = request.getParameter("endTime");
			}
			if (request.getParameter("type") != null && !request.getParameter("type").equals("")) {
				type = request.getParameter("type");
			}

			String simNo = request.getParameter("simNo");

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(gpsInfoService.getChartOfDataReport(simNo, startTime, endTime, type));

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("数据上报统计图");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	// ======================================================================================

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
	 * 根据gspId分类
	 * 
	 * @param request
	 * @return
	 */
	private String getSortAlarmInfo(HttpServletRequest request) {
		String sortInfo = "gpsId";
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

	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		/* 如果url中存在pc参数，就截取掉，不存在就不用截取 */
		int index = url.lastIndexOf("&currentPage=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
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
	 * 获取开始时间
	 * 
	 * @param request
	 * @return
	 */
	private String getStartTime(HttpServletRequest request) {
		Date date = new Date();// 获取当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -15);
		Date startDate = calendar.getTime();
		String startTime = FormatDateUtils.dateToString(startDate);
		String param = request.getParameter("startTime");
		if (param != null && !param.trim().isEmpty()) {
			try {
				startTime = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return startTime;
	}

	/**
	 * 获取结束时间
	 * 
	 * @param request
	 * @return
	 */
	private String getEndTime(HttpServletRequest request) {
		Date date = new Date();// 获取当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, +15);
		Date endDate = calendar.getTime();
		String endTime = FormatDateUtils.dateToString(endDate);
		String param = request.getParameter("endTime");
		if (param != null && !param.trim().isEmpty()) {
			try {
				endTime = param;
			} catch (RuntimeException e) {
				throw e;
			}
		}
		return endTime;
	}

}
