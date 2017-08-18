package cn.guangtong.entity.cms;

/**
 * 用户反馈实体类
 * 
 * @author sutong
 * 
 */
public class Feedback {
    private Long id;
    private String advice;
    private String createTime;
    private String userType; //1:司机 2:用户
    private int userId; //用户Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Feedback(Long id, String advice, String createTime, String userType,
			int userId) {
		super();
		this.id = id;
		this.advice = advice;
		this.createTime = createTime;
		this.userType = userType;
		this.userId = userId;
	}
	public Feedback() {
		super();
	}
}