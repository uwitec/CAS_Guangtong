package cn.guangtong.controller.driver;

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

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.driver.DriverInfo;
import cn.guangtong.excel.Driver;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.coopertation.CooperationInfoService;
import cn.guangtong.service.driver.DriverInfoService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.MD5Util;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.excel.ExcelKit;
import cn.guangtong.utils.excel.OnReadDataHandler;

@Controller
@RequestMapping("/driver")
public class DriverInfoController {

	@Autowired
	private DriverInfoService driverInfoService;
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	public DriverInfoService getDriverInfoService() {
		return driverInfoService;
	}

	/**
	 * 查询司机信息+分页+排序+模糊查询
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sDriverInfoA")
	public ResponseModel<DriverPageBean> sDriverInfoA(DriverPageBean driverPageBean, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel<DriverPageBean> rm = new ResponseModel<DriverPageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");
			driverPageBean.setUrl(getUrl(request));
			driverPageBean.setSortType(getSortType(request));
			driverPageBean.setSortInfo(getSortInfo(request));
			driverPageBean.setPageCount(getPageCount(request));
			driverPageBean.setCurrentPage(getcurrentPage(request));
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if(admin.getAtype()!=1){
				driverPageBean.setAdminId(admin.getId().toString());
				driverPageBean.setTotalCount(driverInfoService.sDriverCountS(driverPageBean));
				driverPageBean.setBeanList(driverInfoService.sDriverInfo(driverPageBean));
			}else{
				driverPageBean.setTotalCount(driverInfoService.sDriverCount(driverPageBean));
				driverPageBean.setBeanList(driverInfoService.sDriverInfoA(driverPageBean));
			}		
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(driverPageBean);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("查询司机信息+分页+排序+模糊查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 批量冻结解冻 赵发志
	 */
	@ResponseBody
	@RequestMapping("/uDriverInfoFreezing")
	public ResponseModel uDriverInfoFreezing(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");
			DriverInfo driverInfo = new DriverInfo();
			String type = request.getParameter("type");
			String msg = request.getParameter("msg");
			String[] id = request.getParameterValues("id[]");
			Integer result = 0;
			// 遍历前台返回的id数组
			for (int i = 0; i < id.length; i++) {
				driverInfo.setId(id[i]);
				driverInfo.setReviewNote(msg);
				result = getDriverInfoService().uDriverInfo(driverInfo);
				result = getDriverInfoService().uDriverInfoFreezing(type, id[i]);
			}
			rm.setMsg("成功");
			rm.setSuccess(true);

			// PlatLog log = new PlatLog();
			// log.setModule("运输信息管理");
			// log.setContext("批量冻结解冻");
			// plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 修改一条司机记录 赵发志
	 */
	@ResponseBody
	@RequestMapping("/uDriverInfo")
	public ResponseModel uDriverInfo(DriverInfo driverInfo,HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");
			driverInfoService.uDriverInfo(driverInfo);
			rm.setMsg("成功");
			rm.setSuccess(true);

			// PlatLog log = new PlatLog();
			// log.setModule("运输信息管理");
			// log.setContext("修改一条司机记录");
			// plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 增加一名司机 赵发志
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/iDriverInfo")
	public ResponseModel iDriverInfo(DriverPageBean driverPageBean, DriverInfo driverInfo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");// 设置消息头
			// 自动生成ID
			StringBuffer driverId = new StringBuffer();
			driverId.append("DR");
			driverId.append(FormatDateUtils.getDate(3));
			driverId.append(driverInfoService.sDriverInfoCountAll(driverPageBean) + 100000);
			// 默认密码
			driverInfo.setId(driverId.toString());
			String password = MD5Util.encode2hex("123456");
			driverInfo.setPassword(password);
			Integer result = getDriverInfoService().iDriverInfo(driverInfo);
			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("增加一名司机");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 获取所属企业 用于新建查询 赵发志
	 */
	@ResponseBody
	@RequestMapping("/sCooperationInfoA")
	public ResponseModel sCooperationInfoA(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("cooperationDs");
			List<Map<String, Object>> results = getDriverInfoService().sCooperationInfoA();
			rm.setMsg("成功");
			rm.setSuccess(true);

			// PlatLog log = new PlatLog();
			// log.setModule("运输信息管理");
			// log.setContext("获取所属企业");
			// plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 查询一条司机记录 + 所属企业 用于编辑查询 Map司机记录 + List企业列表 赵发志
	 */
	@ResponseBody
	@RequestMapping("/sDriverInfoOne")
	public ResponseModel<Object> sDriverInfoOne(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<Object> rm = new ResponseModel<Object>();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("driverDs");
			String id = request.getParameter("id");// 司机编号
			List<Object> list = new ArrayList<Object>();// 定义外层List
			Map<String, Object> map = getDriverInfoService().sDriverInfoOne(id);// 司机信息
			List<Map<String, Object>> results = getDriverInfoService().sCooperationInfoA();
			list.add(results);
			list.add(map);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("查询一条司机记录");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出 驾驶员excel
	 * 
	 * @param driverPageBean
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "excel", method = RequestMethod.GET)
	public void export(DriverPageBean driverPageBean, HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		DataSourceContextHolder.setDataSourceType("driverDs");
		driverPageBean.setUrl(getUrl(request));
		driverPageBean.setSortType(getSortType(request));
		driverPageBean.setSortInfo(getSortInfo(request));
		driverPageBean.setPageCount(getPageCount(request));
		driverPageBean.setCurrentPage(getcurrentPage(request));
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() == 0) {
			driverPageBean.setAdminId(admin.getId().toString());
		} 
		driverPageBean.setBeanList(driverInfoService.sDriverInfoA(driverPageBean));
		List<Driver> driverInfos = getDriverInfoService().excelFul(driverPageBean);
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(Driver.class, response).toExcel(driverInfos, "驾驶员信息下载");

		PlatLog log = new PlatLog();
		log.setModule("运输信息管理");
		log.setContext("导出 驾驶员excel");
		plog.addPlatLog(log, request);
	}

	/**
	 * 导出 驾驶员excel模版
	 * 
	 * @param driverPageBean
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "exportTemplate", method = RequestMethod.GET)
	public void exportTemplate(HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		DataSourceContextHolder.setDataSourceType("driverDs");
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(Driver.class, response).toExcel(null, "驾驶员信息excel模版");
		PlatLog log = new PlatLog();
		log.setModule("运输信息管理");
		log.setContext("导出 驾驶员excel模版");
		plog.addPlatLog(log, request);
	}

	/**
	 * 导入 驾驶员excel
	 * 
	 */
	@ResponseBody
	@RequestMapping("/DriversExcelImport")
	public ResponseModel<String> DriversExcelImport(MultipartFile file, HttpServletRequest request) throws Exception {

		ResponseModel<String> rm = new ResponseModel<String>();
		try {
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
					DriverInfo driverInfo = new DriverInfo();
					driverInfo.setId(rowData.get(0));
					driverInfo.setdName(rowData.get(1));
					driverInfo.setCooperationId(rowData.get(3));
					driverInfo.setTel(rowData.get(4));
					driverInfo.setDefaultVehicleNum(rowData.get(5));
					driverInfo.setDriverClass(rowData.get(6));
					driverInfoService.iDriverInfo(driverInfo);
				}
			});

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(realPath + "/" + filename);
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("导入 驾驶员excel");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 计算分页 计算排序 ASC升序 DESC降序 赵发志
	 */
	public DriverPageBean calculatePage(HttpServletRequest request, Integer totalCount) {
		DriverPageBean driverPageBean = new DriverPageBean();
		Integer currentPage = 1;// 当前页码
		Integer pageCount = 10;// 每页记录数
		String sortType = "ASC";// 默认排序类型
		String sortInfo = "id";// 默认排序字段
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