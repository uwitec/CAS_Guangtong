package cn.guangtong.controller.beidou;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.beidou.TerminalCommandService;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 危险品零售供应链子系统
 * 
 * @author sutong
 * 
 */
@Controller
@RequestMapping("DangerGoods")
public class DangerGoodsController {

	@Autowired
	private TerminalCommandService terminalCommandService;
	ResponseModel rm = new ResponseModel();
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 设置疲劳驾驶 卢赵鹏
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("driverTired")
	public ResponseModel DriveTired(HttpServletRequest request, HttpServletResponse response) {

		try {
			SettingMessageHeaders.setHeaders(response);
			TerminalCommand tc = new TerminalCommand();
			// id
			tc.setCmdid(null);
			// 设置指令参数
			tc.setCmddata("84,14400");
			// 设置指令类型
			tc.setCmdtype(33027);
			// 设置
			tc.setTenantid(0);
			tc.setSn(55);
			// 终端SIM卡号
			tc.setSimno(request.getParameter("simno"));
			// 车辆id
			tc.setVehicleid(Integer.parseInt(request.getParameter("vehicleid")));
			// 设置 车牌号
			tc.setPlateno(request.getParameter("plateno"));
			// 命令执行者id
			tc.setUserid(0);
			// 更新时间
			tc.setUpdatedate(FormatDateUtils.getDate(5));
			terminalCommandService.insert(tc);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("危险品零售供应链子系统");
			log.setContext("设置疲劳驾驶");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();

		}

		return rm;
	}
}
