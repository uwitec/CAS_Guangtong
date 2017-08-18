package cn.guangtong.controller.beidou;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.guangtong.entity.beidou.Enclosure;
import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.beidou.LinesegmentService;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;

/**
 * 
 * 线路管理
 */
@RestController
@RequestMapping("linesegment")
public class LinesegmentController {

	@Autowired
	private LinesegmentService linesegmentService;
	ResponseModel rm = new ResponseModel();
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 添加一个线路
	 * 
	 * @param response
	 * @param linesegment
	 * @param vehArr坐标点集合
	 * @return
	 */
	@RequestMapping("insert")
	public ResponseModel insert(Enclosure ec, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, @RequestParam(required = false, value = "plateNoS[]") String[] plateNo, @RequestParam(required = false, value = "simNoS[]") String[] simNo, @RequestParam(required = false, value = "vehicleIdS[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			
			boolean state=linesegmentService.insertSelective(ec, vehArr, plateNo, simNo, vehicleId);
			if(state){
				rm.setMsg("成功");
				rm.setSuccess(true);
				PlatLog log = new PlatLog();
				log.setModule("线路管理");
				log.setContext("添加一个线路");
				plog.addPlatLog(log, request);
			}else{
				rm.init();
			}
			
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 获取所有线路
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLinesegmentAll")
	public ResponseModel getLinesegmentAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(linesegmentService.getLinesegmentServiceAll());
			PlatLog log = new PlatLog();
			log.setModule("线路管理");
			log.setContext("获取所有线路");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 添加线路关键点
	 * 
	 * @param linesegment
	 * @param vehArr坐标点集合
	 * @param response
	 * @return
	 */
	@RequestMapping("insertkeyPoint")
	public ResponseModel insertkeyPoint(Enclosure ec, @RequestParam(required = false, value = "vehArr[]") String[] vehArr, @RequestParam(required = false, value = "plateNoS[]") String[] plateNo, @RequestParam(required = false, value = "simNoS[]") String[] simNo, @RequestParam(required = false, value = "vehicleIdS[]") String[] vehicleId, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到beidouDs的数据源
			DataSourceContextHolder.setDataSourceType("beidouDs");
			// 关键点监控
			ec.setKeyPoint(1);
			linesegmentService.insertSelective(ec, vehArr, plateNo, simNo, vehicleId);
			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("线路管理");
			log.setContext("添加线路关键点");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}

		return rm;
	}

	/**
	 * 获取所有关键点线路
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLinesegmentkeyPointAll")
	public ResponseModel getLinesegmentkeyPointAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(linesegmentService.getLinesegmentkeyPointAll());
			PlatLog log = new PlatLog();
			log.setModule("线路管理");
			log.setContext("获取所有关键点线路");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

}
