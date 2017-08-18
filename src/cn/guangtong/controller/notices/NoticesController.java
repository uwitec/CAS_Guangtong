package cn.guangtong.controller.notices;

import java.util.Date;
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

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.Notices;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.NoticesGrading;
import cn.guangtong.model.NoticesModel;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.cms.AdminService;
import cn.guangtong.service.notices.NoticesService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.NoReadNoticesPageBean;
import cn.guangtong.utils.NoticesPageBean;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 公告通知发布系统
 * 
 * @author SunTo
 * 
 */
@Controller
@RequestMapping("/notices")
public class NoticesController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private NoticesService noticesService;

	PlatLogUtil plog = new PlatLogUtil(); // 操作日志

	/**
	 * 查看当前管理员的子级用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAllAdminByPid")
	public ResponseModel<NoticesGrading> getAllAdminByPid(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<NoticesGrading> rm = new ResponseModel<NoticesGrading>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			List<NoticesGrading> admins = null;
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			admins = noticesService.getAllByPid(admin);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(admins);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("查看当前管理员的子级用户");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 批量发布公告
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/insertNotices")
	public ResponseModel insertNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel rm = new ResponseModel();
		// 注意这里在调用service前切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("cmsDs");
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			// Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");

			// 公告标题
			String noticesTitle = request.getParameter("title");
			// 公告内容
			String noticesContent = request.getParameter("content");
			Date date = new Date();
			// 发布时间
			String noticesIssueTime = FormatDateUtils.dateToString(date);
			// 接收人数组 (字符串)
			String receivers = request.getParameter("receivers");

			// 创建公告实体类对象
			Notices notices = new Notices();
			notices.setTitle(noticesTitle);
			notices.setContent(noticesContent);
			notices.setIssuetime(noticesIssueTime);
			noticesService.insertNotices(notices, admin, receivers);

			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("批量发布公告");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 获取当前用户的历史公告列表(分页)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getHistoryNoticesAll")
	public ResponseModel<NoticesPageBean> getHistoryNoticesAll(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<NoticesPageBean> rm = new ResponseModel<NoticesPageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			NoticesPageBean pageBean = new NoticesPageBean();
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			// Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");

			pageBean.setAdminId(admin.getId());
			// 获取每页记录数
			pageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			pageBean.setCurrentPage(getcurrentPage(request));
			// 获取查询条件selCondition
			pageBean.setSelCondition(getCondition(request));
			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");

			if (admin.getAtype() != 1) {
				noticesService.getHistoryNoticesAll(pageBean);
			} else {
				noticesService.getHistoryNoticesAllS(pageBean);
			}
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("获取当前用户的历史公告列表(分页)");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 获取当前用户未读的公告总数
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getNoReadNoticesNum")
	public ResponseModel<Integer> getNoReadNoticesNum(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<Integer> rm = new ResponseModel<Integer>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			int counts = 0;
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			counts = noticesService.getNoReadNoticesNum(admin);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(counts);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("获取当前用户未读的公告总数");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 获取当前用户未读公告的列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getNoReadNoticesById")
	public ResponseModel<NoticesModel> getNoReadNoticesById(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<NoticesModel> rm = new ResponseModel<NoticesModel>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			int adminId = admin.getId();
			List<NoticesModel> data = null;
			// 分页条件
			int id = Integer.parseInt(request.getParameter("id"));
			data = noticesService.getNoReadNoticesById(id, adminId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(data);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("获取当前用户未读公告的列表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 根据id获取公告
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getNoticesModelById")
	public ResponseModel<NoticesModel> getNoticesModelById(int id, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<NoticesModel> rm = new ResponseModel<NoticesModel>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			NoticesModel data = noticesService.getNoticesModelById(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(data);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("获取一条公告信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 删除公告
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteAdminNotices")
	public ResponseModel<NoticesModel> deleteAdminNotices(@RequestParam(required = false, value = "ids[]") int[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<NoticesModel> rm = new ResponseModel<NoticesModel>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			noticesService.deleteAdminNotices(ids);
			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("删除公告关联信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 设置公告为已读状态
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("readNotices")
	public ResponseModel readNotices(@RequestParam(required = false, value = "ids[]") int[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			noticesService.readNotices(ids);
			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("设置公告为已读状态");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 获取当前用户的公告信息列表(分页)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getNoticesAll")
	public ResponseModel<NoReadNoticesPageBean> getNoticesAll(NoReadNoticesPageBean pageBean, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ResponseModel<NoReadNoticesPageBean> rm = new ResponseModel<NoReadNoticesPageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);

			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
			// Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");

			pageBean.setAdminId(admin.getId());
			// 获取每页记录数
			pageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			pageBean.setCurrentPage(getcurrentPage(request));
			// 获取查询条件selCondition
			pageBean.setSelCondition(getCondition(request));

			// 注意这里在调用service前切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			noticesService.getNoticesAll(pageBean);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("公告通知发布");
			log.setContext("获取当前用户的历史公告列表(分页)");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}

	/**
	 * 获取每页记录数(默认10)
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
}
