package cn.guangtong.dao.cms;

import java.util.List;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.utils.AdminPageBean;

public interface AdminDao {

	public Admin getAdminByName(String adminname);

	public void adminUpdate(Admin admin);

	public List<Admin> getAll();

	public boolean checkLogin(String username, String password);

	public Admin getByAdminNameAndPassword(String username, String password);

	public List<Admin> getAllByPid(int pid);

	public Admin getAdminById(int adminId);

	public List<Admin> getAdmins(AdminPageBean pageBean);

	public int getCounts();

	public int addAdmin(Admin admin);

	public void updateAdmin(Admin admin);

	public void deleteAdmin(int id);

	/**
	 * 判断用户名是否可用
	 * 
	 * @param userName
	 * @return
	 */
	public int judgeAdminByName(String userName);

	/**
	 * 获取所有的超级管理员
	 * 
	 * @return
	 */
	public List<Admin> getAdminAtype();
	
	/**
	 * 普通用户 分页管理管理员
	 * @param pageBean
	 * @return
	 */
	public List<Admin> getAdmin(AdminPageBean pageBean);
	/**
	 * 普通用户 管理员总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 更新登录时间
	 * @param adminId
	 * @param time
	 */
	public void upLoginTime(String adminId, String time);
}
