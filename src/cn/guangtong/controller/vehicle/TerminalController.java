package cn.guangtong.controller.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.beidou.Terminal;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.vehicle.TerminalService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.TerminalPageBean;

/**
 * 终端管理控制器(黑盒子)
 * 
 * @author SunTo
 * 
 */
@Controller
@RequestMapping("/terminal")
public class TerminalController {

	@Autowired
	private TerminalService terminalService;

	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();
	

	/**
	 * 添加一个终端
	 * 
	 * @param terminal
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertTerminal")
	public ResponseModel<Boolean> insertTerminal(Terminal terminal, HttpServletResponse response,HttpServletRequest request, HttpSession session) {
		ResponseModel<Boolean> rm = new ResponseModel<Boolean>();
		
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(terminalService.insert(terminal));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("添加一个终端");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 更新一个终端
	 * @param terminal
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateTerminal",method=RequestMethod.POST)
	public ResponseModel<Boolean> upDateTerminal(Terminal terminal, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		ResponseModel<Boolean> rm = new ResponseModel<Boolean>();
		
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);

			rm.setMsg("成功");
			rm.setSuccess(true);
			System.out.println("---------------------------------------"+terminal);
			rm.setT(terminalService.upDateTerminal(terminal));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("添加一个终端");
			plog.addPlatLog(log, request);			
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}


	/**
	 * 删除一个终端
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTerminal")
	public ResponseModel<Boolean> deleteTerminal(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ResponseModel<Boolean> rm = new ResponseModel<Boolean>();
		
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			String termId=request.getParameter("termId");

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(terminalService.delete(termId));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("删除一个终端");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 根据termId查询一条Terminal
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTerminalByTermNo")
	public ResponseModel<Terminal> getTerminalByTermNo(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ResponseModel<Terminal> rm = new ResponseModel<Terminal>();
		
		try {
			String termId=request.getParameter("termId");
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(terminalService.getTerminalByTermNo(termId));
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("根据termId查询一条Terminal");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}
	
	/**
	 * 分页查询所有终端
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTerminalAll")
	public ResponseModel<TerminalPageBean> getTerminalAll(TerminalPageBean terminalPageBean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResponseModel<TerminalPageBean> rm = new ResponseModel<TerminalPageBean>();
		
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// 获取每页记录数
			terminalPageBean.setPageCount(getPageCount(request));
			// 获取当前页码
			terminalPageBean.setCurrentPage(getcurrentPage(request));
			terminalService.getTerminalByPageBean(terminalPageBean);
			
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(terminalPageBean);
			
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("分页查询所有终端");
			plog.addPlatLog(log, request);
		} catch(Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 获取每页记录数
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
