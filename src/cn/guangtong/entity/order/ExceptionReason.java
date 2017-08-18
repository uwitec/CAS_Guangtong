package cn.guangtong.entity.order;
/**
 * 订单异常原因实体类
 * @author sutong
 *
 */
public class ExceptionReason {
    private Long reasonid;
    private int type; //原因类型（1：异常原因：2改派原因）
    private String title;
	public Long getReasonid() {
		return reasonid;
	}
	public void setReasonid(Long reasonid) {
		this.reasonid = reasonid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ExceptionReason(Long reasonid, int type, String title) {
		super();
		this.reasonid = reasonid;
		this.type = type;
		this.title = title;
	}
	public ExceptionReason() {
		super();
	}
}