package cn.guangtong.dao.cms;

import cn.guangtong.entity.cms.AdminPermission;

public interface AdminPermissionDao {
	
	public int addAdminPermission(AdminPermission adminPermission);

	public void delAdminPerByAid(int adminId);
	
}
