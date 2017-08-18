package cn.guangtong.utils;

import cn.guangtong.model.HistoryNoticesModel;

public class NoticesPageBean extends PageBean<HistoryNoticesModel> {

	//要查询的人的id
	private int adminId;
	
	//查询条件（标题或内容）
	private String selCondition;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getSelCondition() {
		return selCondition;
	}

	public void setSelCondition(String selCondition) {
		this.selCondition = selCondition;
	}

	

	
	
	

}
