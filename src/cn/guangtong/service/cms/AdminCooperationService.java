package cn.guangtong.service.cms;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.cms.Admin;

public interface AdminCooperationService {

	/**
	 * 根据当前用户获取，当前用户可见企业列表
	 * 
	 * @return
	 */
	List<Map<String, String>> getCooperationAll();
	
	/**
	 * 根据adminId获取关联的企业列表
	 * @param adminId
	 * @return
	 */
	List<Map<String, String>> getCooperationByAdminId(String adminId);
}
