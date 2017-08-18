package cn.guangtong.entity.cms;

/**
 * 后台提醒实体类
 * @author sutong
 */
public class SystemNotification {
    private int id;
    private int ntype;//通知类型(1:cms,2:cus)
    private String ncontent;//通知内容 (1:新订单,2:处理订单,3:异常订单)
    private String createtime;//提交时间
    private Boolean hasread;//是否已读(0:未读,1:已读)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNtype() {
		return ntype;
	}
	public void setNtype(int ntype) {
		this.ntype = ntype;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Boolean getHasread() {
		return hasread;
	}
	public void setHasread(Boolean hasread) {
		this.hasread = hasread;
	}
}