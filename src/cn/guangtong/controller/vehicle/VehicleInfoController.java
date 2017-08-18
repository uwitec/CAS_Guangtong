package cn.guangtong.controller.vehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.guangtong.dao.cooperation.CooperationInfoDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.vehicle.VehicleInfo;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.service.vehicle.VehicleInfoService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.excel.ExcelKit;
import cn.guangtong.utils.excel.OnReadDataHandler;

@Controller
@RequestMapping("/vehicle")
public class VehicleInfoController {

	@Autowired
	private VehicleInfoService vehicleInfoService;

	@Autowired
	private CooperationInfoDao cooperationInfoDao;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	public VehicleInfoService getVehicleInfoService() {
		return vehicleInfoService;
	}

	/**
	 * 查询当前管理员的可见车辆
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVehicleByAdmin")
	public ResponseModel<Map<String, Map<String, List<VehicleMenu>>>> getVehicleByAdmin(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<Map<String, Map<String, List<VehicleMenu>>>> rm = new ResponseModel<Map<String, Map<String, List<VehicleMenu>>>>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到vehicleDs的数据源
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			// 从session中获取当前登录用户
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

			if (admin.getAtype() != 1) {
				rm.setT(vehicleInfoService.getVehicleByAdmin(admin));
			} else {
				rm.setT(vehicleInfoService.getVehicleSByAdmin());
			}

			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("查询当前管理员的可见车辆");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping("/getCooperation")
	public ResponseModel getCooperation(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);// 设置消息头
			// 从session中获取当前登录用户
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

			// 当前账号的全部企业列表
			if (admin.getAtype() != 1) {
				rm.setT(cooperationInfoDao.findCooperationInfoByAdminId(admin.getId()));
			} else {
				rm.setT(cooperationInfoDao.selectAll());
			}
			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("获取当前用户下属企业");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询汽车信息+分页+排序+模糊查询 赵发志
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/sVehicleInfoA")
	public ResponseModel<VehiclePageBean> sVehicleInfoA(VehiclePageBean vehiclePageBean, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<VehiclePageBean> rm = new ResponseModel<VehiclePageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到vehicleDs的数据源
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			vehiclePageBean.setUrl(getUrl(request));
			vehiclePageBean.setSortType(getSortType(request));
			vehiclePageBean.setSortInfo(getSortInfo(request));
			vehiclePageBean.setPageCount(getPageCount(request));
			vehiclePageBean.setCurrentPage(getcurrentPage(request));
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if (admin.getAtype() != 1) {
				vehiclePageBean.setAdminId(admin.getId().toString());
				Integer totalCount = vehicleInfoService.sVehicleCount(vehiclePageBean);
				vehiclePageBean.setTotalCount(totalCount*112);
				vehiclePageBean.setBeanList(vehicleInfoService.sVehicleInfoA(vehiclePageBean));
			} else {
				Integer totalCount = vehicleInfoService.sVehicleCountS(vehiclePageBean);
				vehiclePageBean.setTotalCount(totalCount*112);
				vehiclePageBean.setBeanList(vehicleInfoService.sVehicleInfo(vehiclePageBean));
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(vehiclePageBean);
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("查询汽车信息+分页+排序");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 批量冻结解冻车辆 赵发志
	 */
	@ResponseBody
	@RequestMapping("/uVehicleInfoFreezing")
	public ResponseModel uVehicleInfoFreezing(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);// 设置消息头
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			String type = request.getParameter("type");
			String[] id = request.getParameterValues("id[]");
			Integer result = 0;
			// 遍历前台返回的id数组
			for (int i = 0; i < id.length; i++) {
				result = getVehicleInfoService().uVehicleInfoFreezing(type, id[i]);
			}

			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("批量冻结解冻车辆");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询一条车辆记录 赵发志
	 */
	@ResponseBody
	@RequestMapping("/sVehicleInfoOne")
	public ResponseModel<VehicleInfo> sVehicleInfoOne(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<VehicleInfo> rm = new ResponseModel<VehicleInfo>();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			String id = request.getParameter("id");// 车辆编号

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(getVehicleInfoService().sVehicleInfoOne(id));

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("查询一条车辆记录");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 增加一个车辆 赵发志
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/iVehicleInfo")
	public ResponseModel iVehicleInfo(VehicleInfo vehicleInfo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("vehicleDs");

			vehicleInfo.setCreateTime(FormatDateUtils.getDate(5));
			Integer result = getVehicleInfoService().iVehicleInfo(vehicleInfo);
			if (result == 1) {
				rm.setMsg("成功");
				rm.setSuccess(true);

				PlatLog log = new PlatLog();
				log.setModule("运输信息管理");
				log.setContext("增加一个车辆");
				plog.addPlatLog(log, request);
			} else {
				rm.init();
			}
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 添加 select列表 赵发志
	 */
	@ResponseBody
	@RequestMapping("/sVehicleInfoQuery")
	public ResponseModel sVehicleInfoQuery(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			List<Object> list = new ArrayList<Object>();
			// 全部车辆类型列表
			list.add(sVehicleInfoMold());
			// 特别运输类型
			list.add(getSpecialtype());
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

			// 当前账号的全部企业列表

			if (admin.getAtype() != 1) {
				list.add(cooperationInfoDao.findCooperationInfoByAdminId(admin.getId()));
			} else {
				list.add(cooperationInfoDao.selectAll());
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("添加 select列表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 返回车辆类型
	 */
	public List<Map<String, Object>> sVehicleInfoMold() {
		return getVehicleInfoService().sVehicleInfoMold();
	}

	/**
	 * 返回所属特别运输类型
	 */
	public List<Map<String, Object>> getSpecialtype() {
		return getVehicleInfoService().getSpecialtype();
	}

	/**
	 * 修改一个车辆 赵发志
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/uVehicleInfo")
	public ResponseModel uVehicleInfo(VehicleInfo vehicleInfo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			System.out.println(vehicleInfo);
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			Integer result = getVehicleInfoService().uVehicleInfo(vehicleInfo);

			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("修改一个车辆");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 导出车辆信息excel
	 * 
	 * @param pageBean
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "excel", method = RequestMethod.GET)
	public void export(VehiclePageBean vehiclePageBean, HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		DataSourceContextHolder.setDataSourceType("vehicleDs");
		vehiclePageBean.setUrl(getUrl(request));
		vehiclePageBean.setSortType(getSortType(request));
		vehiclePageBean.setSortInfo(getSortInfo(request));
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

		// 导出的数据
		List<cn.guangtong.excel.Vehicle> vehicleInfos = null;

		if (admin.getAtype() != 1) {// 非超管导出
			vehiclePageBean.setAdminId(admin.getId().toString());
			vehicleInfos = getVehicleInfoService().queryVehicleExcal(vehiclePageBean);
		} else {// 超管导出
			vehicleInfos = getVehicleInfoService().queryVehicleExcalByAdmin(vehiclePageBean);
		}
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(cn.guangtong.excel.Vehicle.class, response).toExcel(vehicleInfos, "车辆信息下载");

		PlatLog log = new PlatLog();
		log.setModule("运输信息管理");
		log.setContext("导出车辆信息excel");
		plog.addPlatLog(log, request);
	}

	/**
	 * 生成车辆信息excel模版
	 * 
	 * @param pageBean
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "exportTemplate", method = RequestMethod.GET)
	public void exportTemplate(HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		DataSourceContextHolder.setDataSourceType("vehicleDs");
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(cn.guangtong.excel.Vehicle.class, response).toExcel(null, "车辆信息excel模版");
		PlatLog log = new PlatLog();
		log.setModule("运输信息管理");
		log.setContext("车辆信息excel模版");
		plog.addPlatLog(log, request);
	}

	/**
	 * 导入车辆信息excel
	 * 
	 */
	@ResponseBody
	@RequestMapping("/VehicelsExcelImport")
	public ResponseModel<String> VehicelsExcelImport(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			SettingMessageHeaders.setHeaders(response);
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			// 获取文件 存储位置
			String realPath = request.getSession().getServletContext().getRealPath("/UploadFile");
			String excelType = file.getOriginalFilename();
			String filename = uuid + excelType.substring(excelType.lastIndexOf('.'));
			File excelFile = new File(realPath + "/" + filename);
			// 将文件copy上传到服务器
			file.transferTo(excelFile);
			ExcelKit.$Import().readExcel(excelFile, new OnReadDataHandler() {
				@Override
				public void handler(List<String> rowData) {
					VehicleInfo vehicleInfo = new VehicleInfo();
					vehicleInfo.setNickname(rowData.get(0));
					vehicleInfo.setNum(rowData.get(1));
					vehicleInfo.setModelId(rowData.get(2));
					vehicleInfo.setCooperationId(rowData.get(3));
					vehicleInfoService.iVehicleInfo(vehicleInfo);
				}
			});
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(realPath + "/" + filename);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("导入车辆信息excel");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}
	/**
	 * 车辆运输能力分析饼状图
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVehiclesPie")
	public ResponseModel<Map<String, Object>> getVehiclesPie( HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			SettingMessageHeaders.setHeaders(response);
			String type = "pie";
			String name = "车辆运输能力分析饼状图";
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(vehicleInfoService.getVehiclesPie(type, name));
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("车辆运输能力分析饼状图");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	/**
	 * 计算分页 计算排序 ASC升序 DESC降序 赵发志
	 */
	public VehiclePageBean calculatePage(HttpServletRequest request, Integer totalCount) {
		VehiclePageBean driverPageBean = new VehiclePageBean();
		Integer currentPage = 1;// 当前页码
		Integer pageCount = 10;// 每页记录数
		String sortType = "ASC";// 排序类型
		String sortInfo = "id";// 排序字段
		String currentPages = request.getParameter("currentPage");
		String pageCounts = request.getParameter("pageCount");
		String type = request.getParameter("sortType");
		String info = request.getParameter("sortInfo");
		if (currentPages != null && !currentPages.trim().isEmpty()) {
			try {
				currentPage = Integer.parseInt(currentPages);
			} catch (RuntimeException e) {
				throw e;
			}
		}
		if (pageCounts != null && !pageCounts.trim().isEmpty()) {
			try {
				pageCount = Integer.parseInt(pageCounts);
			} catch (RuntimeException e) {
				throw e;
			}
		}
		if (type != null && !type.trim().isEmpty() && type.equalsIgnoreCase("DESC")) {
			sortType = type;
		}
		if (info != null && !info.trim().isEmpty()) {
			sortInfo = info;
		}
		driverPageBean.setSortInfo(sortInfo);
		driverPageBean.setSortType(sortType);
		driverPageBean.setCurrentPage(currentPage);
		driverPageBean.setPageCount(pageCount);
		driverPageBean.setTotalCount(totalCount);
		return driverPageBean;
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
