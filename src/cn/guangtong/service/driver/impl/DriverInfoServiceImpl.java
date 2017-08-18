package cn.guangtong.service.driver.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.controller.driver.DriverPageBean;
import cn.guangtong.dao.cooperation.CooperationInfoDao;
import cn.guangtong.dao.driver.DriverInfoDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.driver.DriverInfo;
import cn.guangtong.entity.driver.DriverRecord;
import cn.guangtong.excel.Driver;
import cn.guangtong.excel.Mileage;
import cn.guangtong.service.driver.DriverInfoService;
import cn.guangtong.utils.DriverAuditPageBean;
import cn.guangtong.utils.ListToString;
import cn.guangtong.utils.ScopeTimeUtil;

@Service
@Transactional
public class DriverInfoServiceImpl implements DriverInfoService {
	
	@Autowired
	private DriverInfoDao driverInfoDao;
	
	@Autowired
	private CooperationInfoDao cooperationInfoDao;
	
	//返回司机dao层对象
	public DriverInfoDao getDriverInfoDao() {
		return driverInfoDao;
	}
	
	//查询司机总数
	public Integer sDriverInfoCount(String adminId) {
		return getDriverInfoDao().sDriverInfoCount(adminId);
	}

	//查询一名司机信息
	public Map<String, Object> sDriverInfoOne(String id) {
		return getDriverInfoDao().sDriverInfoOne(id);
	}
	
	//查询所有司机信息+分页
	public List<Map<String,Object>> sDriverInfoA(DriverPageBean driverPageBean) {
		return getDriverInfoDao().sDriverInfoA(driverPageBean);
	}

	//查询全部司机
	public List<Driver> excelFul(DriverPageBean driverPageBean) {
		return getDriverInfoDao().excelFul(driverPageBean);
	}
		
	//批量冻结解冻司机
	public Integer uDriverInfoFreezing(String type ,String employeeId) {
		try{
			if(getDriverInfoDao().uDriverInfoFreezing(type,employeeId)==1){
				return 1;
			}else{
				return 0;
			}
		}catch(Exception e){
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return 0;
	}
	
	//查询全部商家
	public List<Map<String, Object>> sCooperationInfoA() {
		return getDriverInfoDao().sCooperationInfoA();
	}
	
	//修改一名司机
	public Integer uDriverInfo(DriverInfo driverInfo) {
		return getDriverInfoDao().uDriverInfo(driverInfo);
	}
	
	//增加一名司机
	public Integer iDriverInfo(DriverInfo deiverInfo) {
		return driverInfoDao.iDriverInfo(deiverInfo);
	}

	//证件有效期处理
	@Override
	public Map<String, Object> validCheck(String type,List<String> coopList){
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		String adminId = "";
		//动态企业
		List<String> coop = null;
		if (admin.getAtype() != 1) {
			adminId = admin.getId().toString();
			coop = cooperationInfoDao.getCnameByIdByOther(coopList, adminId);
		} else {
			coop = cooperationInfoDao.getCnameById(coopList);
		}
		
		//全部数据
		Map<String, Object> mapAll = new HashMap<String, Object>();
		//chart数据
		List<Object> listAll = new ArrayList<Object>();
		//证件类型
		Map<String, Object> map = null;
		if (type == null || type.equals("")) {
			type = "1";
		}
		
		if(type.equals("1")){
			if (admin.getAtype() != 1) {
				map = driverInfoDao.validCheckByOther(coopList, adminId);
			} else {
				map = driverInfoDao.validCheck(coopList);
			}
		}else if(type.equals("2")){
			if (admin.getAtype() != 1) {
				map = driverInfoDao.rankCheckByOther(coopList, adminId);
			} else {
				map = driverInfoDao.rankCheck(coopList);
			}
		}else if(type.equals("3")){
			if (admin.getAtype() != 1) {
				map = driverInfoDao.professionValidCheckByOther(coopList, adminId);
			} else {
				map = driverInfoDao.professionValidCheck(coopList);
			}
		}
		//处理chart数据
		Set keys = map.keySet();
		if(keys != null){
			Iterator it = keys.iterator();
			while(it.hasNext()){
				List<Object> list = new ArrayList<Object>();
				Object key = it.next();
				if(key.equals("tt")){
					list.add("有效");
				}if(key.equals("ff")){
					list.add("失效");
				}if(key.equals("tf")){
					list.add("即将失效");
				}
				list.add(map.get(key));
				listAll.add(list);
			}
		}
		//处理类型名称
		String ss= null;
		if(type.equals("1")){
			ss = (new ListToString().listToString(coop)) + "驾驶证有效期统计";
		}else if(type.equals("2")){
			ss = (new ListToString().listToString(coop)) + "行驶证有效期统计";
		}else if(type.equals("3")){
			ss = (new ListToString().listToString(coop)) + "从业资格证有效期统计";
		}
		//最后封装
		mapAll.put("data", listAll);
		mapAll.put("name", ss);
		mapAll.put("sname", "valid percentage");
		return mapAll;
	}

	@Override
	public void driverOfTable(DriverPageBean pageBean) {
		List<DriverRecord> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = driverInfoDao.driverOfTable(pageBean);
			totalCount = driverInfoDao.countOfDriver(pageBean);
		} else {
			list = driverInfoDao.driverOfTableSuperTube(pageBean);
			totalCount = driverInfoDao.countOfDriverSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount*1000+(int)(Math.random()*1000));
	}
	
	@Override
	public void driverOfTableExcel(DriverPageBean pageBean) {
		List<DriverRecord> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = driverInfoDao.driverOfTableExcel(pageBean);
			totalCount = driverInfoDao.countOfDriver(pageBean);
		} else {
			list = driverInfoDao.driverOfTableExcelSuperTube(pageBean);
			totalCount = driverInfoDao.countOfDriverSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);
		
	}

	@Override
	public int countOfDriver(DriverPageBean pageBean) {
		return driverInfoDao.countOfDriver(pageBean);
	}

	@Override
	public Map<String, Object> getNewDriverCount(String startTime, String endTime, String type) {
		//所需全部信息
		Map<String, Object> map = new HashMap<String, Object>();
		//柱状图data
		List<Object> listAll = new ArrayList<Object>();

		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		
		String adminId = "";
		if (admin.getAtype() != 1) {
			adminId = admin.getId().toString();
		}
		
		//新增司机数量全部数据
		List<Map<String, Object>> list = driverInfoDao.getNewDriverCount(startTime, endTime, type, adminId);
		//全部查询时间区域
		List<Object> timeAll = new ArrayList<Object>();
		try {
			if (type.equals("day")) {
				timeAll = new ScopeTimeUtil().getTime(startTime, endTime, "yyyy-MM-dd");
			} else if (type.equals("month")) {
				timeAll = new ScopeTimeUtil().getTime(startTime, endTime, "yyyy-MM");
			} else if (type.equals("year")) {
				timeAll = new ScopeTimeUtil().getTime(startTime, endTime, "yyyy");
			}
		} catch (ParseException e) {
			timeAll = null;
			e.printStackTrace();
		}
		
		if (list.size() > 0) {
			//处理柱状图数据封装进listAll
			String cName = "";
			String cNamePre = "";
			List<Object> dtlist = new ArrayList<Object>(); // 柱状图数据
			Map<String, Object> dtmap = new HashMap<String, Object>();//柱状图企业
			for (int i = 0; i < list.size(); i++) {
				cName =  list.get(i).get("cName").toString();
				if(cNamePre.equals(cName)){
					System.out.println(timeAll.indexOf("2016-"));
					dtlist.set(timeAll.indexOf(new ScopeTimeUtil().dateFormat(type, list.get(i).get("reviewTime"))),list.get(i).get("count"));
				} else {
					if(!cNamePre.equals("")){
						dtmap.put("name", cNamePre);
						dtmap.put("data", dtlist);
						listAll.add(dtmap);
					}
					dtmap = new HashMap<String, Object>();
					
					dtlist = new ArrayList<Object>();
					for (int j = 0; j < timeAll.size(); j++) {
							dtlist.add(0);
					}
					
					dtlist.set(timeAll.indexOf(new ScopeTimeUtil().dateFormat(type, list.get(i).get("reviewTime"))),list.get(i).get("count"));
					cNamePre = cName;
				}
				
			}
			dtmap.put("name", cNamePre);
			dtmap.put("data", dtlist);
			listAll.add(dtmap);
		} else {
			Map<String, Object> dtmap = new HashMap<String, Object>();//柱状图企业
			List<Object> dtlist = new ArrayList<Object>(); // 柱状图数据
			for (int j = 0; j < timeAll.size(); j++) {
				dtlist.add(0);
			}
			dtmap.put("name", "暂无数据");
			dtmap.put("type", "column");
			dtmap.put("data", dtlist);
			listAll.add(dtmap);
		}
		
		
		map.put("data", listAll);		//主要数据
		map.put("title", "驾驶员数量统计");
		map.put("xAxis", timeAll);		//全部时间
		map.put("yAxis", "人数");
		
		return map;
	}

	@Override
	public DriverAuditPageBean driverAuditSelect(
			DriverAuditPageBean driverAuditPageBean) {
		if(driverAuditPageBean.getCurrentPage() == 0){
			driverAuditPageBean.setCurrentPage(1);
		}
		if(driverAuditPageBean.getPageCount() == 0){
			driverAuditPageBean.setPageCount(10);
		}
		driverAuditPageBean.setTotalCount(driverInfoDao.countDriverAudit(driverAuditPageBean));
		driverAuditPageBean.setBeanList(driverInfoDao.driverAuditSelect(driverAuditPageBean));
		return driverAuditPageBean;
	}

	@Override
	public Integer updateDriverAuditStatus(String id, String reviewStatus) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("reviewStatus", reviewStatus);
		return driverInfoDao.updateDriverAuditStatus(map);
	}
	@Override
	public Integer sDriverCount(DriverPageBean driverPageBean) {
		return driverInfoDao.sDriverCount(driverPageBean);
	}

	@Override
	public Integer sDriverInfoCountAll(DriverPageBean driverPageBean) {
		return driverInfoDao.sDriverInfoCountAll(driverPageBean);
	}

	@Override
	public Integer sDriverCountS(DriverPageBean driverPageBean) {
		return driverInfoDao.sDriverCountS(driverPageBean);
	}

	@Override
	public List<Map<String, Object>> sDriverInfo(DriverPageBean driverPageBean) {
		return driverInfoDao.sDriverInfo(driverPageBean);
	}

	
	
}
