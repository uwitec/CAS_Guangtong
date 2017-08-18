package cn.guangtong.entity.customer;


public class ReservationService {
    private Long id;
    private String customerid;
    private String describe;
    private String reservationtime;
    private String createtime;
    private int ishandle;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getReservationtime() {
		return reservationtime;
	}
	public void setReservationtime(String reservationtime) {
		this.reservationtime = reservationtime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getIshandle() {
		return ishandle;
	}
	public void setIshandle(int ishandle) {
		this.ishandle = ishandle;
	}
	public ReservationService(Long id, String customerid, String describe,
			String reservationtime, String createtime, int ishandle) {
		super();
		this.id = id;
		this.customerid = customerid;
		this.describe = describe;
		this.reservationtime = reservationtime;
		this.createtime = createtime;
		this.ishandle = ishandle;
	}
	public ReservationService() {
		super();
	}
}