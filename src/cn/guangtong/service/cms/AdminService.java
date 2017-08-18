package cn.guangtong.service.cms;

import java.util.List;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.utils.AdminPageBean;

public interface AdminService {

	public Admin getAdminByName(String adminname);

	public void adminUpdate(Admin admin);

	List<Admin> getAll();

	public boolean checkLogin(String username, String password);

	public List<Admin> getAllByPid(int pid);

	/**
	 * 超级管理员 分页管理管理员
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<Admin> getAdmins(AdminPageBean pageBean);

	/**
	 * 超级管理员，查询管理员总数
	 * 
	 * @return
	 */
	public int getCounts();

	public void addAdmin(Admin admin, String[] CooperationArr);

	public void updateAdmin(Admin admin, String[] cooperationArr);

	public void deleteAdmin(int id);

	public Admin toUpdateAdmin(int id);

	/**
	 * 判断用户名是否可用
	 * 
	 * @param userName
	 * @return
	 */
	public int judgeAdminByName(String userName);

	/**
	 * 普通用户 分页管理管理员
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<Admin> getAdmin(AdminPageBean pageBean);

	/**
	 * 普通用户 管理员总数
	 * 
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
