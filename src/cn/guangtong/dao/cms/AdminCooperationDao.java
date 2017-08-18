package cn.guangtong.dao.cms;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.AdminCooperation;

public interface AdminCooperationDao {
	/**
	 * 根据用户id删除旗下所有企业
	 * 
	 * @param adminId
	 * @return
	 */
	int deleteByAdminId(Integer adminId);

	int insert(AdminCooperation record);

	/**
	 * 根据当前用户获取可见企业
	 * 
	 * @param adminId
	 * @return
	 */
	List<Map<String, String>> getCooperationAll();
	
	/**
	 * 根据adminId获取关联的企业列表
	 * @param adminId
	 * @return
	 */
	List<Map<String, String>> getCooperationByAdminId(String adminId);
	
	/**
	 * 根据用户id查询关联的全部企业id
	 * @param adminId
	 * @return
	 */
	List<String> getCooperationIdByAdminId(String adminId);

}