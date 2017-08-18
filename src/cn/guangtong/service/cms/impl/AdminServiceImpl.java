package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.AdminCooperationDao;
import cn.guangtong.dao.cms.AdminDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.AdminCooperation;
import cn.guangtong.service.cms.AdminService;
import cn.guangtong.utils.AdminPageBean;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private AdminCooperationDao adminCooperationDao;

	@Override
	public Admin getAdminByName(String adminname) {
		return adminDao.getAdminByName(adminname);
	}

	@Override
	public void adminUpdate(Admin admin) {
		adminDao.adminUpdate(admin);
	}

	@Override
	public List<Admin> getAll() {
		return adminDao.getAll();
	}

	@Override
	public boolean checkLogin(String username, String password) {
		if (adminDao.getByAdminNameAndPassword(username, password) == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Admin> getAllByPid(int pid) {
		return adminDao.getAllByPid(pid);
	}

	@Override
	public List<Admin> getAdmins(AdminPageBean pageBean) {
		return adminDao.getAdmins(pageBean);
	}

	@Override
	public void addAdmin(Admin admin, String[] CooperationArr) {
		try {
			adminDao.addAdmin(admin);
			if (CooperationArr != null) {
				for (int i = 0; i < CooperationArr.length; i++) {
					AdminCooperation temp = new AdminCooperation();
					temp.setAdminid(admin.getId());
					temp.setCooperationid(CooperationArr[i]);
					adminCooperationDao.insert(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public void updateAdmin(Admin admin, String[] cooperationArr) {
		try {
			adminDao.updateAdmin(admin);
			adminCooperationDao.deleteByAdminId(admin.getId());
			if (cooperationArr != null) {
				for (int i = 0; i < cooperationArr.length; i++) {
					AdminCooperation temp = new AdminCooperation();
					temp.setAdminid(admin.getId());
					temp.setCooperationid(cooperationArr[i]);
					adminCooperationDao.insert(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

	@Override
	public int getCounts() {
		return adminDao.getCounts();
	}

	@Override
	public void deleteAdmin(int id) {
		adminDao.deleteAdmin(id);
	}

	@Override
	public Admin toUpdateAdmin(int id) {
		return adminDao.getAdminById(id);
	}

	@Override
	public int judgeAdminByName(String userName) {
		return adminDao.judgeAdminByName(userName);
	}

	@Override
	public List<Admin> getAdmin(AdminPageBean pageBean) {
		return adminDao.getAdmin(pageBean);
	}

	@Override
	public int getCount() {
		return adminDao.getCount();
	}

	@Override
	public void upLoginTime(String adminId, String time) {
		adminDao.upLoginTime(adminId, time);
	}

}