package cn.guangtong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.service.cms.PlatLogService;

@Component
public class PlatLogUtil {

	@Autowired
	public PlatLogService platLogService;
	private static PlatLogUtil platLogUtil;

	public PlatLogService getPlatLogService() {
		return platLogService;
	}

	public void setPlatLogService(PlatLogService platLogService) {
		this.platLogService = platLogService;
	}

	@PostConstruct
	public void init() {
		platLogUtil = this;
		platLogUtil.platLogService = this.platLogService;
	}

	public static void addPlatLog(PlatLog platLog, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		// 获取当前登录账户信息
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		System.out.println("---------------------------------------" + admin.getId());
		// 获取当前服务器时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String issueTime = simpleDateFormat.format(new Date());
		platLog.setIssueTime(issueTime);
		platLog.setUsername(admin.getUsername());
		String ip = request.getRemoteAddr();
		platLog.setIp(ip);
		System.out.println(platLog);
		// 进行添加
		platLogUtil.platLogService.addPlatLog(platLog);
	}

}
