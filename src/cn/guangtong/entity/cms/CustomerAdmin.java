package cn.guangtong.entity.cms;

/**
 * 客户端用户实体类
 * 
 * @author sutong
 *
 */
public class CustomerAdmin {
    private Long id;
    private String customerid;
    private String username;
    private String password;
    private String updatetime;
    private int cstatus; // 状态 (-1:不在表中,0:已冻结,1,正常)

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
        this.customerid = customerid == null ? null : customerid.trim();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public int getCstatus() {
		return cstatus;
	}
	public void setCstatus(int cstatus) {
		this.cstatus = cstatus;
	}
	public CustomerAdmin(Long id, String customerid, String username,
			String password, String updatetime, int cstatus) {
		super();
		this.id = id;
		this.customerid = customerid;
		this.username = username;
		this.password = password;
		this.updatetime = updatetime;
		this.cstatus = cstatus;
	}
	public CustomerAdmin() {
		super();
	}
}