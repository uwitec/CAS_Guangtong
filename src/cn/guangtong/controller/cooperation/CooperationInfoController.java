package cn.guangtong.controller.cooperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
import cn.guangtong.entity.cooperation.CooperationInfo;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.coopertation.CooperationInfoService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

@Controller
@RequestMapping("/cooperation")
public class CooperationInfoController {

	@Autowired
	private CooperationInfoService cooperationInfoService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	public CooperationInfoService getCooperationInfoService() {
		return cooperationInfoService;
	}

	/**
	 * 查询企业信息+分页+排序+模糊查询
	 */
	@ResponseBody
	@RequestMapping("/sCooperationInfoA")
	public ResponseModel<CooperationPageBean> sDriverInfoA(CooperationPageBean cooperationPageBean, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<CooperationPageBean> rm = new ResponseModel<CooperationPageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("cooperationDs");
			cooperationPageBean.setUrl(getUrl(request));
			cooperationPageBean.setSortType(getSortType(request));
			cooperationPageBean.setSortInfo(getSortInfo(request));
			cooperationPageBean.setPageCount(getPageCount(request));
			cooperationPageBean.setCurrentPage(getcurrentPage(request));
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			
			if(admin.getAtype()!=1){
				cooperationPageBean.setAdminId(admin.getId().toString());
				Integer totalCount = getCooperationInfoService().sCooperationCount(cooperationPageBean);
			
				cooperationPageBean.setTotalCount(totalCount);
				cooperationPageBean.setBeanList(cooperationInfoService.sCooperationInfoA(cooperationPageBean));
			}else{
				Integer totalCount = getCooperationInfoService().sCooperationCountS(cooperationPageBean);
				
				cooperationPageBean.setTotalCount(totalCount);
				cooperationPageBean.setBeanList(cooperationInfoService.sCooperationInfo(cooperationPageBean));
			}
			

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(cooperationPageBean);

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
	 * 批量冻结解冻企业
	 */
	@ResponseBody
	@RequestMapping("/uCooperationInfoFreezing")
	public ResponseModel uDriverInfoFreezing(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("cooperationDs");
			String type = request.getParameter("type");
			String[] id = request.getParameterValues("id[]");
			Integer result = 0;
			// 遍历前台返回的id数组
			for (int i = 0; i < id.length; i++) {
				result = getCooperationInfoService().uCooperationInfoFreezing(type, id[i]);
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
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
	 * 修改一条企业信息
	 */
	@ResponseBody
	@RequestMapping("/uCooperationInfo")
	public ResponseModel uCooperationInfo(HttpServletResponse response, HttpServletRequest request, CooperationInfo cooperationInfo) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("cooperationDs");
			cooperationInfoService.uCooperationInfo(cooperationInfo);
			rm.setMsg("成功");
			rm.setSuccess(true);
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
	 * 增加一条企业信息
	 */
	@ResponseBody
	@RequestMapping("/iCooperationInfo")
	public ResponseModel iDriverInfo(HttpServletResponse response, HttpServletRequest request, CooperationInfo cooperationInfo) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("cooperationDs");
			// 自动生成ID
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			cooperationInfo.setId(uuid);
			cooperationInfo.setCreatetime(sdf.format(new Date()));
			getCooperationInfoService().iCooperationInfo(cooperationInfo);

			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("数据统计分析");
			log.setContext("最新状态报表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	// 根据ID查询企业信息
	@ResponseBody
	@RequestMapping("/getCooperationInfo")
	public ResponseModel<CooperationInfo> getCooperationInfo(@RequestParam String id, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<CooperationInfo> rm = new ResponseModel<CooperationInfo>();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("cooperationDs");

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(cooperationInfoService.getCooperationInfo(id));

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
	 * 计算分页 计算排序 ASC升序 DESC降序
	 */
	public CooperationPageBean calculatePage(HttpServletRequest request, Integer totalCount) {
		CooperationPageBean cooperationPageBean = new CooperationPageBean();
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
		cooperationPageBean.setSortInfo(sortInfo);
		cooperationPageBean.setSortType(sortType);
		cooperationPageBean.setCurrentPage(currentPage);
		cooperationPageBean.setPageCount(pageCount);
		cooperationPageBean.setTotalCount(totalCount);
		return cooperationPageBean;
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
