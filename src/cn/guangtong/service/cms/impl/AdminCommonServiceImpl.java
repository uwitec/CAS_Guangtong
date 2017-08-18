package cn.guangtong.service.cms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.cms.AdminCommonDao;
import cn.guangtong.entity.cms.AdminCommon;
import cn.guangtong.service.cms.AdminCommonService;

@Service
public class AdminCommonServiceImpl implements AdminCommonService {

	@Autowired
	private AdminCommonDao adminCommonDao;

	@Override
	public AdminCommon getAdminCommon(int adminId) {
		AdminCommon AdminCommon = adminCommonDao.getAdminCommon(adminId);
		if (AdminCommon != null) {
			return AdminCommon;
		} else {
			AdminCommon temp = new AdminCommon();
			temp.setAdminId(adminId);
			adminCommonDao.insert(temp);
			return temp;
		}
	}

	@Override
	public int update(AdminCommon record) {
		return adminCommonDao.update(record);
	}
}
