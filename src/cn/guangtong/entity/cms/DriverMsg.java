package cn.guangtong.entity.cms;
/**
 * 司机推送消息实体类
 * @author sutong
 *
 */
public class DriverMsg {
    private String driverid;
    private int messageid; //外键，对应实体为Message
    private int isread; //已读标记,默认0,未读
    
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public DriverMsg(String driverid, int messageid, int isread) {
		super();
		this.driverid = driverid;
		this.messageid = messageid;
		this.isread = isread;
	}
	public DriverMsg() {
		super();
	}    
}