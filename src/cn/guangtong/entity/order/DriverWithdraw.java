package cn.guangtong.entity.order;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.guangtong.utils.excel.ExportConfig;


/**
 * 司机提现申请记录实体类
 * @author sutong
 *
 */
public class DriverWithdraw {
	@ExportConfig(value="ID",width=100)
    private Long id;
	@ExportConfig(value="司机编号",width=150)
    private String driverid;
	@ExportConfig(value="司机姓名",width=100)
    private String drivername;
	@ExportConfig(value="卡号",width=150)
    private Long banknum;
	@ExportConfig(value="银行",width=100)
    private String bankname;
	@ExportConfig(value="支行",width=100)
    private String branch;
	@ExportConfig(value="提现金额",width=100)
    private double money;
	@ExportConfig(value="申请时间",width=200)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private String createtime;
	@ExportConfig(value="处理时间",width=200)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private String paytime;
    private int ispay; //提现结果（0：未处理;1:提现成功;2:拒绝）
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
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public int getIspay() {
		return ispay;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	public DriverWithdraw(Long id, String driverid, String drivername,
			Long banknum, String bankname, String branch, double money,
			String createtime, String paytime, int ispay) {
		super();
		this.id = id;
		this.driverid = driverid;
		this.drivername = drivername;
		this.banknum = banknum;
		this.bankname = bankname;
		this.branch = branch;
		this.money = money;
		this.createtime = createtime;
		this.paytime = paytime;
		this.ispay = ispay;
	}
	public DriverWithdraw() {
		super();
	}
}