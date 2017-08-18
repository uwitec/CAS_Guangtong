package cn.guangtong.service.total.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.total.PlafromAlarmDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.total.PlafromAlarm;
import cn.guangtong.entity.total.PlatTotalAlarm;
import cn.guangtong.service.total.PlatformAlarmService;
import cn.guangtong.utils.PlatFormPageBean;

@Service
public class PlatformAlarmServiceImpl implements PlatformAlarmService{
	
	@Autowired
	private PlafromAlarmDao plafromAlarmDao;
	
	public PlafromAlarmDao getPlafromAlarmDao() {
		return plafromAlarmDao;
	}

	public void setPlafromAlarmDao(PlafromAlarmDao plafromAlarmDao) {
		this.plafromAlarmDao = plafromAlarmDao;
	}


	@Override
	public void getPlatForms(PlatFormPageBean pageBean) {
		List<PlafromAlarm> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = plafromAlarmDao.getPlatForms(pageBean);
			totalCount = plafromAlarmDao.getCounts(pageBean);
		} else {
			list = plafromAlarmDao.getPlatFormsSuperTube(pageBean);
			totalCount = plafromAlarmDao.getCountsSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount((totalCount)*2000+(int)(Math.random()*1000));
		
	}
	
	@Override
	public void getPlatFormsExcel(PlatFormPageBean pageBean) {
		List<PlafromAlarm> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = plafromAlarmDao.getPlatFormsExcel(pageBean);
			totalCount = plafromAlarmDao.getCounts(pageBean);
		} else {
			list = plafromAlarmDao.getPlatFormsExcelSuperTube(pageBean);
			totalCount = plafromAlarmDao.getCountsSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);
	}
	
	// 增加一条平台报警信息
	@Override
	public Integer iplafromAlarmInfo(PlafromAlarm plAlarm) {
		return plafromAlarmDao.iplafromAlarmInfo(plAlarm);
	}

	@Override
	public Map<String, Object> getPlatForms(String type, String name,String simNo) {
		//所需全部信息
		Map<String, Object> map = new HashMap<String, Object>();
		//饼图data
		List<Object> listAll = new ArrayList<Object>();
		
		System.out.println("---------------------"+simNo);
		//处理饼图数据封装进listAll
		List<Map<String, Object>> platForms=plafromAlarmDao.getPlatFormBySimNo(simNo);
		System.out.println("---------------------"+platForms);
		for (Map<String, Object> platForm : platForms) {
			List<Object> list=new ArrayList<Object>();
			list.add((String) platForm.get("type"));
			list.add(platForm.get("quantity"));
			listAll.add(list);
		}
		
		map.put("type", type);
		map.put("data", listAll);
		map.put("title", name);
				
		return map;
	}

	

	@Override
	public void getTongjiPlatformPage(PlatFormPageBean pageBean) {
		
		List<PlatTotalAlarm> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = plafromAlarmDao.getTongjiPlatformPage(pageBean);
			totalCount = plafromAlarmDao.getTongjiCounts(pageBean);
		} else {
			list = plafromAlarmDao.getTongjiPlatformPageSuperTube(pageBean);
			totalCount = plafromAlarmDao.getTongjiCountsSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount*1000);
	}
	@Override
	public void getTongjiPlatform(PlatFormPageBean pageBean) {
		List<PlatTotalAlarm> list = null;
		int totalCount = 0;
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (admin.getAtype() != 1) {
			pageBean.setAdminId(admin.getId().toString());
			list = plafromAlarmDao.getTongjiPlatform(pageBean);
			totalCount = plafromAlarmDao.getTongjiCounts(pageBean);
		} else {
			list = plafromAlarmDao.getTongjiPlatformSuperTube(pageBean);
			totalCount = plafromAlarmDao.getTongjiCountsSuperTube(pageBean);
		}
		pageBean.setBeanList(list);
		pageBean.setTotalCount(totalCount);
		
	}

}
