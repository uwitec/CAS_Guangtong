package cn.guangtong.controller.cms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.CustomerInformation;
import cn.guangtong.entity.cms.Information;
import cn.guangtong.entity.cms.Menu;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.cms.SystemSetting;
import cn.guangtong.model.MenusGrading;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.service.cms.AdminCooperationService;
import cn.guangtong.service.cms.AdminPermissionService;
import cn.guangtong.service.cms.AdminService;
import cn.guangtong.service.cms.AdminVehicleService;
import cn.guangtong.service.cms.CustomerInformationService;
import cn.guangtong.service.cms.FeedbackService;
import cn.guangtong.service.cms.InformationService;
import cn.guangtong.service.cms.MenuService;
import cn.guangtong.service.cms.PlatLogService;
import cn.guangtong.service.cms.SystemSettingService;
import cn.guangtong.service.coopertation.CooperationInfoService;
import cn.guangtong.utils.AdminPageBean;
import cn.guangtong.utils.CustomerInformationPageBean;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.FeedbackPageBean;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.InformationPageBean;
import cn.guangtong.utils.MD5Util;
import cn.guangtong.utils.MenuPageBean;
import cn.guangtong.utils.PlatLogPageBean;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.SystemSettingPageBean;
import cn.guangtong.utils.excel.ExcelKit;

/**
 * 系统信息管理
 */
@Controller
@RequestMapping("/cms")
public class CMSController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private PlatLogService platLogService;

	@Autowired
	private SystemSettingService systemSettingService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private InformationService informationService;
	
	@Autowired
	private CustomerInformationService customerInformationService;

	@Autowired
	private AdminPermissionService adminPermissionService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private CooperationInfoService cooperationInfoService;

	@Autowired
	private AdminVehicleService adminVehicleService;

	@Autowired
	private AdminCooperationService adminCooperationService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 登录校验功能模块
	 * 
	 * @param admin
	 *            接收参数
	 * @param request
	 *            封装数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public ResponseModel<Admin> login(Admin admin, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		ResponseModel<Admin> rm = new ResponseModel<Admin>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");

			UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), MD5Util.encode2hex(admin.getPassword()));
			Subject subject = SecurityUtils.getSubject();
			Admin adminResult = new Admin();
			subject.login(token);
			if (subject.isAuthenticated()) {
				adminResult = adminService.getAdminByName(admin.getUsername());
				// 管理员登陆成功后，信息保存在session中
				subject.getSession().setAttribute("loginAdmin", adminResult);
				//登录时间更新
				adminService.upLoginTime(adminResult.getId().toString(), FormatDateUtils.getDate(5));
				rm.setMsg("成功");
				rm.setSuccess(true);
				rm.setT(adminResult);
				PlatLog log = new PlatLog();
				log.setModule("系统信息管理");
				log.setContext("登录校验功能模块");
				plog.addPlatLog(log, request);
			} else {
				rm.setMsg("账号和密码不匹配");
			}
		} catch (IncorrectCredentialsException e) {
			rm.setMsg("账号和密码不匹配");
		} catch (ExcessiveAttemptsException e) {
			rm.setMsg("登录失败次数过多,请10分钟后重试");
		} catch (LockedAccountException e) {
			rm.setMsg("账号已被锁定");
		} catch (DisabledAccountException e) {
			rm.setMsg("账号已被冻结");
		} catch (ExpiredCredentialsException e) {
			rm.setMsg("账号已被冻结");
		} catch (UnknownAccountException e) {
			rm.setMsg("账号和密码不匹配");
		} catch (UnauthorizedException e) {
			rm.setMsg("账号和密码不匹配");
		} catch (AuthenticationException e) {
			rm.setMsg("账号和密码不匹配");
		}
		return rm;
	}

	/**
	 * 退出登录
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/exit")
	public ResponseModel exit(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel<Admin> rm = new ResponseModel<Admin>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);

			// 成功
			// response.sendRedirect("login.html");

			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("退出登录");
			plog.addPlatLog(log, request);
			// 销毁session
			request.getSession().invalidate();
			rm.setMsg("成功");
			rm.setSuccess(true);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 通过用户id获取可见菜单列表
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/indexGetMenu")
	public ResponseModel<MenusGrading> indexGetMenu(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<MenusGrading> rm = new ResponseModel<MenusGrading>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 从session中获取当前登录用户
			Subject subject = SecurityUtils.getSubject();
			Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");

			if (admin != null) {
				rm.setObj(getCasMenusByAdminId(admin.getId()));
			}

			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("通过用户id获取可见菜单列表");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 获取所有菜单列表以及管理车辆-权限控制
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllMenuAndCooperation")
	public ResponseModel getAllMenuAndCooperation(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<Map> rm = new ResponseModel<Map>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			List<MenusGrading> jsonList = new ArrayList<MenusGrading>();
			// 从session中获取当前登录用户
			// Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
			String adminId = request.getParameter("adminId");
			String selCondition=request.getParameter("selCondition");
			
			List<Menu> menusList = menuService.getMenusByAdminId(Integer.parseInt(adminId));
			for (int i = 0; i < menusList.size(); i++) {
				Menu menu = menusList.get(i);
				if (menu.getParentId() == 0) {
					// 当前节点
					MenusGrading menusGrading = new MenusGrading();
					Integer aId = menu.getAdminId();
					if (aId != null) {
						menusGrading.setIsPermissions(1);
					}
					menusGrading.setId(menu.getId());
					menusGrading.setName(menu.getName());
					getChildrenList(menusGrading, menusList);
					jsonList.add(menusGrading);
				}
			}
			map.put("menus", jsonList);
			// 车辆
			/*
			 * List<AdminCooperation> cList = cooperationInfoService.getCooperationInfoByAdminId(Integer.parseInt(adminId)); for (AdminCooperation adminCooperation : cList) { Integer aId = adminCooperation.getAdminId(); if (aId != null) { adminCooperation.setIsPermissions(1); } } map.put("cooperations", cList);
			 */
			Map<String, Map<String, List<VehicleMenu>>> vehicles = adminVehicleService.getVehicleMenuS(adminId,selCondition);
			map.put("cooperations", vehicles);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(map);

			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("获取所有菜单列表以及管理车辆-权限控制");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 系统设置分页查询
	 */
	@ResponseBody
	@RequestMapping("/getSystemSettingsByPage")
	public ResponseModel getSystemSettingsByPage(SystemSettingPageBean pageBean, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			String condition = request.getParameter("condition");
			System.out.println(condition);
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(systemSettingService.getCounts(pageBean));
			pageBean.setBeanList(systemSettingService.getSystemSettings(pageBean));
			// 添加到平台配置
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("系统设置分页查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		System.out.println(rm);
		return rm;
	}

	/**
	 * 跳转到添加系统设置界面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toAddSysSetting")
	public ResponseModel toAddSysSetting(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("跳转到添加系统设置界面");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 添加系统设置
	 * 
	 * @param systemSetting
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addSystemSetting")
	public ResponseModel addSystemSetting(SystemSetting systemSetting, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			systemSettingService.addSystemSetting(systemSetting);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("添加系统设置");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 跳转到系统设置更新界面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toUpdateSystemSetting")
	public ResponseModel toUpdateSystemSetting(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(systemSettingService.getSystemSetting(id));
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("跳转到系统设置更新界面");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}

		return rm;
	}

	/**
	 * 更新系统设置
	 * 
	 * @param menu
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateSystemSetting")
	public ResponseModel updateSystemSetting(SystemSetting systemSetting, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			systemSettingService.updateSystemSetting(systemSetting);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("更新系统设置");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 删除系统设置
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteSystemSetting")
	public ResponseModel deleteSystemSetting(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			systemSettingService.deleteSystemSetting(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("删除系统设置");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 平台日志分页查询
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getPlatLogsByPage")
	public ResponseModel getPlatLogsByPage(PlatLogPageBean pageBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(platLogService.getCounts(pageBean));
			pageBean.setBeanList(platLogService.getPlatLogs(pageBean));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("平台日志分页查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			// throw e;
		}
		return rm;
	}

	/**
	 * 平台日志导出
	 * 
	 * @param pageBean
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getPlatLogData")
	public ResponseModel getPlatLogData(PlatLogPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 生成Excel并使用浏览器下载
			ExcelKit.$Export(PlatLog.class, response).toExcel(platLogService.getAllPlatLogs(pageBean), "平台日志信息下载");
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("平台日志导出");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 分页显示栏目信息
	 * 
	 * @param pageBean
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMenusByPage")
	public ResponseModel getMenusByPage(MenuPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(menuService.getCounts());
			// 遍历所有的栏目，判断每个栏目是否有上级目录
			List<Menu> menus = menuService.getMenus(pageBean);
			for (Menu menu : menus) {
				if (menu.getParentId() != 0) {
					Menu parent = menuService.getMenuById(menu.getParentId());
					menu.setParent(parent);
				}
			}
			pageBean.setBeanList(menus);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("分页显示栏目信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}

		return rm;
	}

	/**
	 * 添加栏目
	 * 
	 * @param menu
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addMenu")
	public ResponseModel addMenu(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			menu.setTime(simpleDateFormat.format(new Date()));
			menu.setIscoop(0);
			menu.setOnlyPermission(1);
			menuService.addMenu(menu);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("添加栏目");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 跳转到栏目更新界面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toUpdateMenu")
	public ResponseModel toUpdateMenu(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			Menu menu = menuService.getMenuById(id);
			if (menu.getParentId() != 0) {
				Menu parent = menuService.getMenuById(menu.getParentId());
				menu.setParent(parent);
			}
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(menu);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("跳转到栏目更新界面");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}

		return rm;
	}

	/**
	 * 更新栏目
	 * 
	 * @param menu
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateMenu")
	public ResponseModel updateMenu(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			menuService.updateMenu(menu);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("更新栏目");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 删除栏目
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteMenu")
	public ResponseModel deleteMenu(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			menuService.deleteMenu(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("删除栏目");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 分页显示管理员信息
	 * 
	 * @param pageBean
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/getAdminsByPage")
	public ResponseModel getAdminsByPage(AdminPageBean pageBean, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));

			List<Admin> admins = null;
			Subject subject = SecurityUtils.getSubject();
			Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
			if (loginAdmin.getAtype() != 1) {
				pageBean.setAdminId(loginAdmin.getId());
				admins = adminService.getAdmin(pageBean);
				pageBean.setTotalCount(adminService.getCount()*10);

			} else {
				admins = adminService.getAdmins(pageBean);
				pageBean.setTotalCount(adminService.getCounts()*10);
			}

			if (admins.size() > 0) {
				for (Admin admin : admins) {
					// 遍历当前管理员的所有自己管理员，进行有效性判断
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date currTime = new Date();
					Date startTime = simpleDateFormat.parse(admin.getStartTime());
					Date endTime = simpleDateFormat.parse(admin.getEndTime());
					admin.setCurrTime(simpleDateFormat.format(currTime));
					// 当前时间和管理员开始、结束时间比较
					if (currTime.after(startTime) && currTime.before(endTime)) {
						admin.setIsValid(1); // 有效
					} else {
						admin.setIsValid(0); // 无效
					}
					// 设置父级管理员
					if (admin.getPid() != null) {
						Admin parent = adminService.toUpdateAdmin(admin.getPid());
						admin.setParentName(parent.getUsername());
					}
				}
			}

			pageBean.setBeanList(admins);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			// PlatLog log = new PlatLog();
			// log.setModule("系统信息管理");
			// log.setContext("分页显示管理员信息");
			// plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 添加管理员
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addAdmin")
	public ResponseModel addAdmin(Admin admin, @RequestParam(required = false, value = "cooperationArr[]") String[] cooperationArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 对密码进行MD5算法加密
			String encrypt = MD5Util.encode2hex(admin.getPassword());
			admin.setPassword(encrypt);

			// 取出当前登录账户
			Subject subject = SecurityUtils.getSubject();
			Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
			admin.setPid(loginAdmin.getId());

			adminService.addAdmin(admin, cooperationArr);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("添加管理员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 更新管理员信息
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateAdmin")
	public ResponseModel updateAdmin(Admin admin, @RequestParam(required = false, value = "cooperationArr[]") String[] cooperationArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 如果密码不为空，进行加密存储
			String password = admin.getPassword();
			if (password != null && password.trim() != "") {
				admin.setPassword(MD5Util.encode2hex(password));
			}
			adminService.updateAdmin(admin, cooperationArr);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("更新管理员信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 删除管理员
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteAdmin")
	public ResponseModel deleteAdmin(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			adminService.deleteAdmin(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("删除管理员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据id查询管理员
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toUpdateAdmin")
	public ResponseModel toUpdateAdmin(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			String adminId = request.getParameter("id");
			int id = Integer.parseInt(adminId);
			Admin admin = adminService.toUpdateAdmin(id);
			Integer pid = admin.getPid();
			if (pid != null) {
				Admin padmin = adminService.toUpdateAdmin(pid);
				admin.setParentName(padmin.getUsername());
			}
			data.put("cooperation", adminCooperationService.getCooperationByAdminId(adminId));
			data.put("admin", admin);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(data);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("根据id查询管理员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 批量删除管理
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public ResponseModel batchDelete(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			/* 1、获取menuIds参数 */
			String menuIds = request.getParameter("menuIds");
			String[] menuIdsArray = menuIds.split(",");
			Menu menu = new Menu();
			menu.setMenuIdsArray(menuIdsArray);
			/* 2、调用service完成工作 */
			menuService.batchDelete(menu);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("批量删除管理");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();

		}
		return rm;
	}

	/**
	 * 跳转到管理员编辑权限界面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toUpdateMenuPermission")
	public ResponseModel toUpdateMenuPermission(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("adminId"));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(getCasMenusByAdminId(id));
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("跳转到管理员编辑权限界面");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 点击保存权限时，我们需要进行删除原有权限，再重新进行保存。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/batchAddMenuPermission")
	public ResponseModel batchAddMenuPermission(Integer adminId, @RequestParam(required = false, value = "vehicleArr[]") String[] vehicleArr, @RequestParam(required = false, value = "menuIdArr[]") Integer[] menuIdArr, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");

			// 管理员重新添加权限
			adminPermissionService.batchAddMenuPermission(adminId, menuIdArr, vehicleArr);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("点击保存权限时，我们需要进行删除原有权限，再重新进行保存");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 获取级联栏目
	 * 
	 * @param adminId 管理员编号
	 * @return
	 */
	public List<MenusGrading> getCasMenusByAdminId(int adminId) {
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("cmsDs");
		List<MenusGrading> jsonList = new ArrayList<MenusGrading>();
		List<Menu> menusList = menuService.getMenusByAid(adminId);

		for (int i = 0; i < menusList.size(); i++) {
			Menu menu = menusList.get(i);
			if (menu.getParentId() == 0) {
				// 当前节点
				MenusGrading menusGrading = new MenusGrading();
				menusGrading.setId(menu.getId());
				menusGrading.setName(menu.getName());
				getChildrenList(menusGrading, menusList);
				jsonList.add(menusGrading);
			}
		}

		return jsonList;
	}

	/**
	 * 递归查询子节点
	 * 
	 * @param menusGrading 当前节点对象
	 * @param menuList 扫描的权限集合
	 */
	public ResponseModel getChildrenList(MenusGrading menusGrading, List<Menu> menuList) {
		ResponseModel rm = new ResponseModel();
		try {
			// 子节点集合
			List<MenusGrading> childrenList = new ArrayList<MenusGrading>();
			// 当前节点的id
			int menusGradingId = menusGrading.getId();
			for (int i = 0; i < menuList.size(); i++) {
				Menu menu = menuList.get(i);
				if (menu.getParentId() == menusGradingId) {
					MenusGrading children = new MenusGrading();
					Integer aId = menu.getAdminId();
					if (aId != null) {
						children.setIsPermissions(1);
					}
					children.setId(menu.getId());
					children.setName(menu.getName());
					children.setParentId(menu.getParentId());
					childrenList.add(children);
					// 递归查询子节点的子节点
					getChildrenList(children, menuList);
				}
			}
			// 将得到的子节点赋值给当前节点
			if (childrenList.size() != 0 && childrenList != null) {
				menusGrading.setChildren(childrenList);
				menusGrading.setIsShown(1);
			}
			rm.setMsg("成功");
			rm.setSuccess(true);

		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 分页显示资讯信息
	 * 
	 * @param pageBean
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/getInformations")
	public ResponseModel getInformations(InformationPageBean pageBean, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(informationService.getCounts());
			pageBean.setBeanList(informationService.getInformations(pageBean));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("分页显示资讯信息-驾驶员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 添加资讯信息
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addInformation")
	public ResponseModel addInformation(Information information, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			information.setCreatetime(simpleDateFormat.format(new Date()));
			informationService.addInformation(information);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("添加资讯信息-驾驶员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据id查询资讯信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInformationById")
	public ResponseModel getInformationById(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(informationService.getInformationById(id));
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("查询资讯信息-驾驶员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 更新资讯信息
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateInformation")
	public ResponseModel updateInformation(Information information, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			informationService.updateInformation(information);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("更新资讯信息-驾驶员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 删除资讯信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteInformation")
	public ResponseModel deleteInformation(HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			informationService.deleteInformation(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("删除资讯信息-驾驶员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 取消轮播
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/noCarouselInformation")
	public ResponseModel noCarouselInformation(HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			long id = Long.parseLong(request.getParameter("id"));
			Information information=new Information();
			information.setId(id);
			information.setBigimg("NULL");
			informationService.updateInformation(information);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("取消轮播-驾驶员");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 分页显示资讯信息
	 * 
	 * @param pageBean
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/getCustomerInformations")
	public ResponseModel getCustomerInformations(CustomerInformationPageBean pageBean, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			
			pageBean.setTotalCount(customerInformationService.getCounts(pageBean));
			pageBean.setBeanList(customerInformationService.getInformations(pageBean));
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("分页显示资讯信息-客户");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 添加资讯信息
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCustomerInformation")
	public ResponseModel addCustomerInformation(CustomerInformation information, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			information.setCreatetime(simpleDateFormat.format(new Date()));
			customerInformationService.insert(information);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("添加资讯信息-客户");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 根据id查询资讯信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCustomerInformationById")
	public ResponseModel getCustomerInformationById(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(customerInformationService.getInformationById(id));
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("跳转到信息更新界面-客户");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	
	/**
	 * 更新资讯信息
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateCustomerInformation")
	public ResponseModel updateCustomerInformation(CustomerInformation information, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			customerInformationService.update(information);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("更新资讯信息-客户");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 删除资讯信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteCustomerInformation")
	public ResponseModel deleteCustomerInformation(HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			int id = Integer.parseInt(request.getParameter("id"));
			customerInformationService.delete(id);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("删除资讯信息-客户");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	

	/**
	 * 取消轮播
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/noCarouselCustomerInformation")
	public ResponseModel noCarouselCustomerInformation(HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			long id = Long.parseLong(request.getParameter("id"));
			CustomerInformation information=new CustomerInformation();
			information.setId(id);
			information.setBigimg("NULL");
			customerInformationService.update(information);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("取消轮播-客户");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 平台日志分页查询
	 */
	@ResponseBody
	@RequestMapping("/getFeedbacksByPage")
	public ResponseModel getFeedbacksByPage(FeedbackPageBean pageBean, HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(feedbackService.getCounts());
			pageBean.setBeanList(feedbackService.getFeedbacks(pageBean));
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("平台日志分页查询");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 判断用户名是否可用
	 */
	@ResponseBody
	@RequestMapping("/judgeAdminByName")
	public ResponseModel judgeAdminByName(HttpServletRequest request, HttpServletResponse response) {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("cmsDs");
			// 封装参数
			String userName = request.getParameter("userName");
			int count = adminService.judgeAdminByName(userName);
			if (count > 0) {

				rm.setT(false);
			} else {
				rm.setT(true);
			}
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("系统信息管理");
			log.setContext("判断用户名是否可用");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
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
