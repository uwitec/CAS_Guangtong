package cn.guangtong.dao.cms;

import cn.guangtong.entity.cms.AdminCommand;

public interface AdminCommandDao {
	AdminCommand getAdminCommand(int adminId);

	int insert(AdminCommand record);

	int update(AdminCommand record);
}