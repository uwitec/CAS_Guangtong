package cn.guangtong.dao.notices;

import java.util.List;

import cn.guangtong.entity.cms.Notices;
import cn.guangtong.model.HistoryNoticesModel;
import cn.guangtong.utils.NoticesPageBean;

public interface NoticesDao {

	int insertNotices(Notices notices);

	Notices getNoticesbyId(Long noticesId);

	/**
	 * 分页获取
	 * 
	 * @param pageBean
	 * @return
	 */
	List<HistoryNoticesModel> getHistoryNoticesAll(NoticesPageBean pageBean);

	/**
	 * 获取全部 用来统计总条数
	 * 
	 * @param pageBean
	 * @return
	 */
	int getHistoryNoticesCounts(NoticesPageBean pageBean);
	

	/**
	 * 分页获取(超级管理员)
	 * 
	 * @param pageBean
	 * @return
	 */
	List<HistoryNoticesModel> getHistoryNoticesAllS(NoticesPageBean pageBean);

	/**
	 * 获取全部 用来统计总条数(超级管理员)
	 * 
	 * @param pageBean
	 * @return
	 */
	int getHistoryNoticesCountsS(NoticesPageBean pageBean);
}
