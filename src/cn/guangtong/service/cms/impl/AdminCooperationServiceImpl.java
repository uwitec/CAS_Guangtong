package cn.guangtong.service.cms.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.cms.AdminCooperationDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.service.cms.AdminCooperationService;
@Service
public class AdminCooperationServiceImpl implements AdminCooperationService {

	@Autowired
	private AdminCooperationDao adminCooperationDao;
	@Override
	public List<Map<String, String>> getCooperationAll() {
		return adminCooperationDao.getCooperationAll();
	}
	@Override
	public List<Map<String, String>> getCooperationByAdminId(String adminId) {
		return adminCooperationDao.getCooperationByAdminId(adminId);
	}

}
