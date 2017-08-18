package cn.guangtong.utils;

import cn.guangtong.model.NoticesModel;

/**
 * 未读公告分页
 * 
 * @author SunTo
 * 
 */
public class NoReadNoticesPageBean extends PageBean<NoticesModel> {

	// 要查询的人的id
	private int adminId;

	// 查询条件（标题或内容）
	private String selCondition;

	public String getSelCondition() {
		return selCondition;
	}

	public void setSelCondition(String selCondition) {
		this.selCondition = selCondition;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

}
