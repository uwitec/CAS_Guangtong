package cn.guangtong.service.cms;

import cn.guangtong.entity.cms.AdminCommand;

public interface AdminCommandService {
	AdminCommand getAdminCommand(int adminId);
	int update(AdminCommand record);
}
