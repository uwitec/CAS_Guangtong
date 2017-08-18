package cn.guangtong.entity.driver;
/**
 * 司机银行卡实体类
 * @author sutong
 *
 */
public class DriverBank {
    private Long id;
    private String driverid;
    private String drivername;
    private Long banknum;
    private String bankname;
    private String branch;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public Long getBanknum() {
		return banknum;
	}
	public void setBanknum(Long banknum) {
		this.banknum = banknum;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public DriverBank(Long id, String driverid, String drivername,
			Long banknum, String bankname, String branch) {
		super();
		this.id = id;
		this.driverid = driverid;
		this.drivername = drivername;
		this.banknum = banknum;
		this.bankname = bankname;
		this.branch = branch;
	}
	public DriverBank() {
		super();
	}
}