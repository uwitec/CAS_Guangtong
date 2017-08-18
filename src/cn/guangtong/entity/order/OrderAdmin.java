package cn.guangtong.entity.order;
/**
 * 订单操作记录实体类
 * @author sutong
 *
 */
public class OrderAdmin {
    private Long orderid;
    private int adminid;
    private String adminname;
    
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public OrderAdmin(Long orderid, int adminid, String adminname) {
		super();
		this.orderid = orderid;
		this.adminid = adminid;
		this.adminname = adminname;
	}
	public OrderAdmin() {
		super();
	}
}