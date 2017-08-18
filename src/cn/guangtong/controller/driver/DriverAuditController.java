package cn.guangtong.controller.driver;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guangtong.service.driver.DriverInfoService;
import cn.guangtong.utils.DriverAuditPageBean;

@Controller
@RequestMapping("driverAudit")
public class DriverAuditController {

	@Resource
	private DriverInfoService driverInfoService;
	
	/**
	 * 司机审核信息查询
	 * @param currentPage 当前页
	 * @param pageCount  每页记录数
	 * @param reviewStatus 司机审核状态 0为未审核   非0均为已审核的
	 */
	@ResponseBody
	@RequestMapping("selectDriverInfo")
	public DriverAuditPageBean selectDriverInfo(DriverAuditPageBean driverAuditPageBean){
		return driverInfoService.driverAuditSelect(driverAuditPageBean);
	}
	
	
	/**
	 * 更新司机审核状态
	 * @param id 需要更新司机的编号
	 * @param reviewStatus 审核状态 ：0 未审核， 1 通过，-1 审核失败，2 待审核，3 重新提交审核
	 * 
	 */
	@ResponseBody
	@RequestMapping("updateDriverAuditStatus")
	public Integer updateDriverAuditStatus(@RequestParam String id,@RequestParam String reviewStatus){
		return driverInfoService.updateDriverAuditStatus(id, reviewStatus);
	}
	
}
