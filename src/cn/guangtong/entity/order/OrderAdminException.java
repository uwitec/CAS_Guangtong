package cn.guangtong.entity.order;

/**
 * 后台异常处理记录实体类
 * @author sutong
 *
 */
public class OrderAdminException {
    private Long orderid;
    private String reason;
    private String createtime;
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public OrderAdminException(Long orderid, String reason, String createtime) {
		super();
		this.orderid = orderid;
		this.reason = reason;
		this.createtime = createtime;
	}
	public OrderAdminException() {
		super();
	}
}