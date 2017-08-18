package cn.guangtong.controller.customer;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.guangtong.entity.cms.PlatLog;
import cn.guangtong.entity.customer.CustomerInfo;
import cn.guangtong.excel.Customer;
import cn.guangtong.model.ResponseModel;
import cn.guangtong.service.customer.CustomerService;
import cn.guangtong.utils.CustomerInfoPageBean;
import cn.guangtong.utils.DataSourceContextHolder;
import cn.guangtong.utils.FormatDateUtils;
import cn.guangtong.utils.MD5Util;
import cn.guangtong.utils.PlatLogUtil;
import cn.guangtong.utils.SettingMessageHeaders;
import cn.guangtong.utils.excel.ExcelKit;
import cn.guangtong.utils.excel.OnReadDataHandler;

/**
 * @author chenjunpeng
 * @date 2017年3月4日
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	// 操作日志
	PlatLogUtil plog = new PlatLogUtil();

	/**
	 * 分页显示客户信息
	 * 
	 * @param pageBean
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/getCustomerInfosByPage")
	public ResponseModel<CustomerInfoPageBean> getCustomerInfosByPage(CustomerInfoPageBean pageBean, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ResponseModel<CustomerInfoPageBean> rm = new ResponseModel<CustomerInfoPageBean>();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("customerDs");
			// 封装参数
			pageBean.setUrl(getUrl(request));
			pageBean.setSortType(getSortType(request));
			pageBean.setSortInfo(getSortInfo(request));
			pageBean.setPageCount(getPageCount(request));
			pageBean.setCurrentPage(getcurrentPage(request));
			pageBean.setTotalCount(customerService.getCounts());
			List<CustomerInfo> customers = customerService.getCustomerInfos(pageBean);
			request.getSession().setAttribute("customers", customers);
			pageBean.setBeanList(customers);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(pageBean);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("分页显示客户信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 增加客戶
	 */
	@ResponseBody
	@RequestMapping("/iCustomerInfo")
	public ResponseModel iCustomerInfo(CustomerInfo customerInfo, HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("customerDs");// 设置消息头
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 自动生成ID
			StringBuffer customerId = new StringBuffer();
			customerId.append("CU");
			customerId.append(FormatDateUtils.getDate(3));
			customerId.append(customerService.getCounts() + 100000);
			customerInfo.setId(customerId.toString());
			// 默认密码
			customerInfo.setPassword(MD5Util.encode2hex("123456"));
			// 是否删除
			customerInfo.setIsdel(1);
			// 默认时间
			customerInfo.setCreatetime(sdf.format(new Date()));
			// 默认客户类型
			customerInfo.setCtype(1);
			// 默认余额
			customerInfo.setBalance(0);
			customerService.iCustomerInfo(customerInfo);

			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("增加客戶");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 根据ID查询一条客户信息
	 */
	@ResponseBody
	@RequestMapping("/sCustomerInfoById")
	public ResponseModel<String> sCustomerInfoById(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("customerDs");

			String id = request.getParameter("id");
			List<String> list = customerService.sCustomerInfoById(id);

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setObj(list);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("根据ID查询一条客户信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 修改一条客户信息
	 */
	@ResponseBody
	@RequestMapping("/uCustomerInfo")
	public ResponseModel uCustomerInfo(HttpServletResponse response, HttpServletRequest request, CustomerInfo customerInfo) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("customerDs");
			Integer result = customerService.uCustomerInfo(customerInfo);

			rm.setMsg("成功");
			rm.setSuccess(true);
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("修改一条客户信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 删除一条客户信息
	 */
	@ResponseBody
	@RequestMapping("/dCustomerInfo")
	public ResponseModel dCustomerInfo(HttpServletResponse response, HttpServletRequest request) {
		ResponseModel rm = new ResponseModel();
		try {
			SettingMessageHeaders.setHeaders(response);
			DataSourceContextHolder.setDataSourceType("customerDs");

			String id = request.getParameter("id");
			String isDel = "0";
			Integer result = customerService.dCustomerInfo(isDel, id);

			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("删除一条客户信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
		}
		return rm;
	}

	/**
	 * 导出客户管理信息
	 * 
	 * @param pageBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getCustomersExcel")
	public ResponseModel getCustomersExcel(CustomerInfoPageBean pageBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel rm = new ResponseModel();
		try {
			// 设置消息头
			SettingMessageHeaders.setHeaders(response);
			// 切换到cmsDs的数据源
			DataSourceContextHolder.setDataSourceType("customerDs");
			// 生成Excel并使用浏览器下载
			ExcelKit.$Export(Customer.class, response).toExcel(customerService.getCustomersExcel(pageBean), "客户管理信息下载");
			rm.setMsg("成功");
			rm.setSuccess(true);

			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("导出客户管理信息");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;

		}
		return rm;
	}
	/**
	 * 导出客户信息excel模版
	 * 
	 * @param driverPageBean
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "exportTemplate", method = RequestMethod.GET)
	public void exportTemplate( HttpServletResponse response, HttpServletRequest request) {
		SettingMessageHeaders.setHeaders(response);
		// 切换到cmsDs的数据源
		DataSourceContextHolder.setDataSourceType("customerDs");
		// 生成Excel并使用浏览器下载
		ExcelKit.$Export(Customer.class, response).toExcel(null, "客户信息信息excel模版");
		PlatLog log = new PlatLog();
		log.setModule("运输信息管理");
		log.setContext("导出客户信息excel模版");
		plog.addPlatLog(log, request);
	}
	/**
	 * 导入客户管理excel
	 * 
	 */
	@ResponseBody
	@RequestMapping("/CustomersExcelImport")
	public ResponseModel<String> CustomersExcelImport(MultipartFile file, HttpServletRequest request) throws Exception {
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
					CustomerInfo customerInfo = new CustomerInfo();
					customerInfo.setId(rowData.get(0));
					customerInfo.setCname(rowData.get(1));
					customerInfo.setContactname(rowData.get(2));
					customerInfo.setTel(rowData.get(3));
					customerInfo.setAddr(rowData.get(4));
					if ("后台添加".equals(rowData.get(5))) {
						customerInfo.setCtype(1);
					} else if ("用户端注册".equals(rowData.get(5))) {
						customerInfo.setCtype(2);
					}
					customerService.iCustomerInfo(customerInfo);
				}
			});

			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(realPath + "/" + filename);
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("导入客户管理excel");
			plog.addPlatLog(log, request);
		} catch (Exception e) {
			rm.init();
			throw e;
		}
		return rm;
	}
	/**
	 * 客户地理位置信息饼状图
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCustomersPie")
	public ResponseModel<Map<String, Object>> getCustomersPie( HttpServletRequest request, HttpServletResponse response) {
		ResponseModel<Map<String, Object>> rm = new ResponseModel<Map<String, Object>>();
		try {
			SettingMessageHeaders.setHeaders(response);
			String type = "pie";
			String name = "客户地理位置信息饼状图";
			rm.setMsg("成功");
			rm.setSuccess(true);
			rm.setT(customerService.getCustomersPie(type, name));
			PlatLog log = new PlatLog();
			log.setModule("运输信息管理");
			log.setContext("客户地理位置信息饼状图");
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
