package cn.guangtong.dao.cms;

import cn.guangtong.entity.cms.AdminCommon;

public interface AdminCommonDao {
	
	AdminCommon getAdminCommon(int adminId);

	int insert(AdminCommon record);

	int update(AdminCommon record);
}