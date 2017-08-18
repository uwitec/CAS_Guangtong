package cn.guangtong.service.cms;

import java.util.List;

import cn.guangtong.entity.cms.Menu;
import cn.guangtong.utils.MenuPageBean;

public interface MenuService {

	public List<Menu> getMenus(MenuPageBean pageBean);

	public int getCounts();

	public void addMenu(Menu menu);

	public void deleteMenu(int id);

	public void updateMenu(Menu menu);

	public Menu getMenuById(int id);

	public void batchDelete(Menu menu);

	public List<Menu> getAll();

	public List<Menu> getMenusByAid(int adminId);

	public List<Menu> getFirMenusByAid(int adminId);

	/**
	 * 根据用户id获取全部菜单项，可见的状态
	 * 
	 * @param adminId
	 * @return
	 */
	public List<Menu> getMenusByAdminId(int adminId);

}
