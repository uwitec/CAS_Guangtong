package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.MenuDao;
import cn.guangtong.entity.cms.Menu;
import cn.guangtong.service.cms.MenuService;
import cn.guangtong.utils.MenuPageBean;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	public MenuDao menuDao;

	@Override
	public List<Menu> getMenus(MenuPageBean pageBean) {
		return menuDao.getMenus(pageBean);
	}

	@Override
	public int getCounts() {
		return menuDao.getCounts();
	}

	@Override
	public void addMenu(Menu menu) {
		try {
			menuDao.addMenu(menu);
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public void deleteMenu(int id) {
		menuDao.deleteMenu(id);
	}

	@Override
	public void updateMenu(Menu menu) {
		menuDao.updateMenu(menu);
	}

	@Override
	public Menu getMenuById(int id) {
		return menuDao.getMenuById(id);
	}

	@Override
	public void batchDelete(Menu menu) {
		menuDao.batchDelete(menu);
	}

	@Override
	public List<Menu> getAll() {
		return menuDao.getAll();
	}

	@Override
	public List<Menu> getMenusByAid(int adminId) {
		return menuDao.getMenusByAid(adminId);
	}

	@Override
	public List<Menu> getFirMenusByAid(int adminId) {
		return menuDao.getFirMenusByAid(adminId);
	}

	@Override
	public List<Menu> getMenusByAdminId(int adminId) {
		return menuDao.getMenusByAdminId(adminId);
	}
	
}
