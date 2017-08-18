package cn.guangtong.service.cms;

import cn.guangtong.entity.cms.AdminCommon;

public interface AdminCommonService {
	AdminCommon getAdminCommon(int adminId);

	int update(AdminCommon record);
}
