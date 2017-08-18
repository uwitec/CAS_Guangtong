package cn.guangtong.service.notices.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.AdminDao;
import cn.guangtong.dao.notices.AdminNoticesDao;
import cn.guangtong.dao.notices.NoticesDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.AdminNotices;
import cn.guangtong.entity.cms.Notices;
import cn.guangtong.model.HistoryNoticesModel;
import cn.guangtong.model.NoticesGrading;
import cn.guangtong.model.NoticesModel;
import cn.guangtong.service.notices.NoticesService;
import cn.guangtong.utils.NoReadNoticesPageBean;
import cn.guangtong.utils.NoticesPageBean;

@Service
@Transactional
public class NoticesServiceImpl implements NoticesService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private NoticesDao noticesDao;

	@Autowired
	private AdminNoticesDao adminNoticesDao;

	public Boolean insertNotices(Notices notices, Admin admin, String receiverIdS) {
		try {
			// 公告信息存储
			noticesDao.insertNotices(notices);
			// 公告管理员关联信息存储
			Long noticesId = notices.getId();
			System.out.println("获取到公告id" + noticesId);
			int id = admin.getId();
			System.out.println("接收人" + receiverIdS);
			String[] receiverId = receiverIdS.replace("[", "").replace("]", "").split(",");
			for (String str : receiverId) {
				str = str.trim();
				AdminNotices adminNotices = new AdminNotices();
				adminNotices.setSenderid(id);
				adminNotices.setNoticesid(noticesId);
				int rid = Integer.parseInt(str);
				adminNotices.setReceiverid(rid);
				adminNoticesDao.insertAdminNotices(adminNotices);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public void getHistoryNoticesAll(NoticesPageBean pageBean) {
		// 获取分页公告内容
		List<HistoryNoticesModel> noticesModelList = noticesDao.getHistoryNoticesAll(pageBean);
		// 总条数赋值
		int counts = noticesDao.getHistoryNoticesCounts(pageBean);
		pageBean.setTotalCount(counts);
		// 结果集合赋值
		pageBean.setBeanList(noticesModelList);
	}

	@Override
	public void getHistoryNoticesAllS(NoticesPageBean pageBean) {
		// 获取分页公告内容
		List<HistoryNoticesModel> noticesModelList = noticesDao.getHistoryNoticesAllS(pageBean);
		// 总条数赋值
		int counts = noticesDao.getHistoryNoticesCountsS(pageBean);
		pageBean.setTotalCount(counts);
		// 结果集合赋值
		pageBean.setBeanList(noticesModelList);
	}

	@Override
	public int getNoReadNoticesNum(Admin admin) {
		int adminId = admin.getId();
		return adminNoticesDao.getNoReadNoticesNum(adminId);
	}

	@Override
	public List<NoticesModel> getNoReadNoticesById(int id, int adminId) {
		if (id == 0) {
			return adminNoticesDao.getNoReadNotices(adminId);
		} else {
			return adminNoticesDao.getNoReadNoticesById(id, adminId);
		}
	}

	@Override
	public boolean readNotices(int[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				adminNoticesDao.isRead(ids[i]);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@Override
	public List<NoticesGrading> getAllByPid(Admin admin) {
		List<NoticesGrading> jsonList = new ArrayList<NoticesGrading>();
		List<Admin> adminList = adminDao.getAll();

		if (admin.getAtype() == 1) {
			// 当前用户为超级管理员时,根节点为所有超级管理员

			for (int i = 0; i < adminList.size(); i++) {
				Admin admin2 = adminList.get(i);
				if (admin2.getAtype() == 1 || admin2.getPid() == null) {
					// 当前节点
					NoticesGrading noticesGrading = new NoticesGrading();
					noticesGrading.setId(admin2.getId());
					noticesGrading.setName(admin2.getUsername());
					getChildrenList(noticesGrading, adminList);
					jsonList.add(noticesGrading);
				}
			}
		} else {
			// 当前节点
			NoticesGrading noticesGrading = new NoticesGrading();
			noticesGrading.setId(admin.getId());
			noticesGrading.setName(admin.getUsername());
			getChildrenList(noticesGrading, adminList);
			jsonList.add(noticesGrading);
		}

		return jsonList;
	}

	/**
	 * 递归查询子节点
	 * 
	 * @param noticesGrading
	 *            当前节点对象
	 * @param adminList
	 *            扫描的用户集合
	 */
	public void getChildrenList(NoticesGrading noticesGrading, List<Admin> adminList) {
		// 子节点集合
		List<NoticesGrading> childrenList = new ArrayList<NoticesGrading>();
		// 当前节点的id
		int noticesGradingId = noticesGrading.getId();
		for (int i = 0; i < adminList.size(); i++) {
			Admin admin = adminList.get(i);

			if (admin.getPid() != null && admin.getAtype() != 1 && admin.getPid() == noticesGradingId) {
				NoticesGrading children = new NoticesGrading();
				children.setId(admin.getId());
				children.setName(admin.getUsername());
				children.setParentId(noticesGrading.getId());
				childrenList.add(children);
				// 递归查询子节点的子节点
				getChildrenList(children, adminList);
			}

		}
		// 将得到的子节点赋值给当前节点
		noticesGrading.setChildren(childrenList);
	}

	@Override
	public NoticesModel getNoticesModelById(int id) {
		NoticesModel data = null;
		try {
			data = adminNoticesDao.getNoticesModelById(id);
			adminNoticesDao.isRead(id);
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return data;
	}

	@Override
	public void deleteAdminNotices(int[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				adminNoticesDao.deleteAdminNotices(ids[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

	@Override
	public void getNoticesAll(NoReadNoticesPageBean pageBean) {
		try {
			pageBean.setTotalCount(adminNoticesDao.getNoticesAllCounts(pageBean));
			pageBean.setBeanList(adminNoticesDao.getNoticesAll(pageBean));
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

}
