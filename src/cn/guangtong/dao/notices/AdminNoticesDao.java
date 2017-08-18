package cn.guangtong.dao.notices;

import java.util.List;

import cn.guangtong.entity.cms.AdminNotices;
import cn.guangtong.model.NoticesModel;
import cn.guangtong.utils.NoReadNoticesPageBean;

public interface AdminNoticesDao {

	int insertAdminNotices(AdminNotices adminNotices);

	int getNoReadNoticesNum(int adminId);

	/**
	 * 查询未读公告
	 * 
	 * @param id
	 * @param adminId
	 * @return
	 */
	List<NoticesModel> getNoReadNoticesById(int id, int adminId);

	/**
	 * 查询未读公告
	 * 
	 * @param id
	 * @param adminId
	 * @return
	 */
	List<NoticesModel> getNoReadNotices(int adminId);

	void isRead(int id);

	/**
	 * 根据关联表id，查询一条公告信息
	 * 
	 * @param id
	 * @return
	 */
	NoticesModel getNoticesModelById(int id);

	/**
	 * 删除公告关联
	 */

	void deleteAdminNotices(int id);
	
	/**
	 * 查询公告信息
	 */
	List<NoticesModel> getNoticesAll(NoReadNoticesPageBean pageBean);
	
	/**
	 * 查询公告信息总数
	 */
	int getNoticesAllCounts(NoReadNoticesPageBean pageBean);
}
