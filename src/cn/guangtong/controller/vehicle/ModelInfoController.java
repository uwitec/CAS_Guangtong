package cn.guangtong.controller.vehicle;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.vehicle.ModelInfo;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.vehicle.ModelInfoService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PageBean;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

@Controller
@RequestMapping("/model")
public class ModelInfoController {

	@Autowired
	private ModelInfoService modelInfoService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();
	
	/**
	 * 查询所有车辆类型
	 */
	@ResponseBody
	@RequestMapping("/selectModelInfoAll")
	public ResponseModel<PageBean> selectModelInfoDaoAll(PageBean pageBean,HttpServletRequest request,HttpServletResponse response) {
		ResponseModel<PageBean> rm = new ResponseModel<PageBean>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到vehicleDs的数据源
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(modelInfoService.getCounts());
			modelInfoService.selectModelInfoAll(pageBean);
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("查询所有车辆类型");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	//根据id查询车辆信息
	@ResponseBody
	@RequestMapping("/selectModelInfoById")
	public ResponseModel<Map<String,Object>> selectModelInfoById(@RequestParam String id,HttpServletResponse response, HttpServletRequest request){
		ResponseModel<Map<String,Object>> rm = new ResponseModel<Map<String,Object>>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到vehicleDs的数据源
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(modelInfoService.selectModelInfoById(id));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("根据id查询车辆信息");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}
	
	//更新车辆类型信息
	@ResponseBody
	@RequestMapping("/updateModelInfo")
	public ResponseModel<Integer> updateModelInfo(ModelInfo modelInfo,HttpServletResponse response, HttpServletRequest request){
		// 设置消息头
		SettingMessageHeaders.setHeaders(response);
		// 切换到vehicleDs的数据源
		DataSourceContextHolder.setDataSourceType("vehicleDs");
		ResponseModel<Integer> rm = new ResponseModel<Integer>();

		try {
			rm.setMsg("成功");
			rm.setSuccess(true);
			System.out.println("-------------------------"+modelInfo);
			rm.setT(modelInfoService.updateModelInfo(modelInfo));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("更新车辆类型信息");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}

	
	//新增车辆类型
	@ResponseBody
	@RequestMapping("/insertModelInfo")
	public ResponseModel<Integer> insertModelInfo(ModelInfo modelInfo,HttpServletResponse response, HttpServletRequest request){
		ResponseModel<Integer> rm = new ResponseModel<Integer>();

		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到vehicleDs的数据源
			DataSourceContextHolder.setDataSourceType("vehicleDs");
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(modelInfoService.insertModelInfo(modelInfo));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("新增车辆类型");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}
	
	//删除车辆信息
	@ResponseBody
	@RequestMapping("/deleteModelInfo")
	public ResponseModel<Integer> deleteModelInfo(@RequestParam Integer id,HttpServletResponse response, HttpServletRequest request){
		ResponseModel<Integer> rm = new ResponseModel<Integer>();

		try{
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到vehicleDs的数据源
			DataSourceContextHolder.setDataSourceType("vehicleDs");
	
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(modelInfoService.deleteModelInfo(id));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("删除车辆信息");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
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
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	 * 
	 * @param request
	 * @return
	 */
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
