package cn.guangtong.service.cms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.cms.AdminCommandDao;
import cn.guangtong.entity.cms.AdminCommand;
import cn.guangtong.service.cms.AdminCommandService;

@Service
public class AdminCommandServiceImpl implements AdminCommandService {

	@Autowired
	private AdminCommandDao adminCommandDao;

	@Override
	public AdminCommand getAdminCommand(int adminId) {
		AdminCommand adminCommand = adminCommandDao.getAdminCommand(adminId);
		if (adminCommand != null) {
			return adminCommand;
		} else {
			AdminCommand temp=new AdminCommand();
			temp.setAdminId(adminId);
			adminCommandDao.insert(temp);
			return temp;
		}

	}

	@Override
	public int update(AdminCommand record) {
		// TODO Auto-generated method stub
		return adminCommandDao.update(record);
	}

}
