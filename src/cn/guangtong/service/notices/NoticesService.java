package cn.guangtong.service.notices;

import java.util.List;

import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.Notices;
import cn.guangtong.model.NoticesGrading;
import cn.guangtong.model.NoticesModel;
import cn.guangtong.utils.NoReadNoticesPageBean;
import cn.guangtong.utils.NoticesPageBean;

public interface NoticesService {

	/**
	 * 添加公告
	 * 
	 * @param notices
	 *            公告实体类对象
	 * @param admin
	 *            发布者用户对象
	 * @param receivers
	 *            接收人用户ID组成的数组字符串
	 * @return
	 */
	Boolean insertNotices(Notices notices, Admin admin, String receivers);

	/**
	 * 获取当前用户的所有历史公告
	 * 
	 * @param admin
	 *            当前用户对象
	 * @param pageBean
	 *            分页bean
	 * @return
	 */
	void getHistoryNoticesAll(NoticesPageBean pageBean);
	
	

	/**
	 * 获取当前用户的所有历史公告(超级管理员)
	 * 
	 * @param admin
	 *            当前用户对象
	 * @param pageBean
	 *            分页bean
	 * @return
	 */
	void getHistoryNoticesAllS(NoticesPageBean pageBean);

	/**
	 * 获取当前用户未读的公告数量(超级管理员)
	 * 
	 * @param admin
	 *            当前用户对象
	 * @return
	 */
	int getNoReadNoticesNum(Admin admin);

	/**
	 * 获取当前用户未读的公告
	 * 
	 * @param admin
	 * @return
	 */
	List<NoticesModel> getNoReadNoticesById(int id, int adminId);

	/**
	 * 设置公告为已读状态
	 * 
	 * @param senderId
	 *            发送人id
	 * @param receiverId
	 *            接收人id
	 * @param noticesId
	 *            公告id
	 * @return
	 */
	boolean readNotices(int[] ids);

	/**
	 * 根据当前用户获取节点数
	 * 
	 * @param admin
	 * @return
	 */
	List<NoticesGrading> getAllByPid(Admin admin);

	/**
	 * 根据关联表id，查询一条公告信息
	 * 
	 * @param id
	 * @return
	 */
	NoticesModel getNoticesModelById(int id);

	/**
	 * 删除公告
	 * 
	 * @param id
	 */
	void deleteAdminNotices(int[] ids);

	/**
	 * 查询公告信息 
	 */
	void getNoticesAll(NoReadNoticesPageBean pageBean);

}
