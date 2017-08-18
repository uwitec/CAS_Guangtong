package cn.guangtong.entity.cms;

/**
 * 客户端用户推送信息实体类
 * 
 * @author sutong
 *
 */
public class CustomerMsg {
	private String customerid; 
	private Long messageid; //外键，对应实体类为Message
    private int isread; //已读标记,默认0,未读 
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public Long getMessageid() {
		return messageid;
	}
	public void setMessageid(Long messageid) {
		this.messageid = messageid;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public CustomerMsg(String customerid, Long messageid, int isread) {
		super();
		this.customerid = customerid;
		this.messageid = messageid;
		this.isread = isread;
	}
	public CustomerMsg() {
		super();
	}
}