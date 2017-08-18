package cn.guangtong.utils;

import cn.guangtong.entity.cms.Feedback;

public class FeedbackPageBean extends PageBean<Feedback>{
	private String advice;
	private String createTime;
	private String userType; //1:司机 2:用户
	 
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}