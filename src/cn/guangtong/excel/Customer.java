package cn.guangtong.excel;

import cn.guangtong.utils.excel.ExportConfig;

/**
 * @ClassName:Customer
 */
public class Customer {
	@ExportConfig(value = "客户编号", width = 150)
	private String id;
	@ExportConfig(value = "客户名称", width = 150)
	private String cname;
	@ExportConfig(value = "联系人", width = 150)
	private String contactname;
	@ExportConfig(value = "联系电话", width = 150)
	private String tel;
	@ExportConfig(value = "地址", width = 150)
	private String addr;
	@ExportConfig(value = "客户类型名称", width = 150) 
	private String cTypeName;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cname
	 *            the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * @return the contactname
	 */
	public String getContactname() {
		return contactname;
	}

	/**
	 * @param contactname
	 *            the contactname to set
	 */
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the cTypeName
	 */
	public String getcTypeName() {
		return cTypeName;
	}

	/**
	 * @param cTypeName the cTypeName to set
	 */
	public void setcTypeName(String cTypeName) {
		this.cTypeName = cTypeName;
	}

}
