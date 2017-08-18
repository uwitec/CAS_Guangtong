package cn.guangtong.entity.cms;

import cn.guangtong.utils.excel.ExportConfig;

public class PlatLog {
	
	@ExportConfig(value = "日志编号", width = 80)
	private int id;
	@ExportConfig(value = "ip地址", width = 100)
	private String ip; 
	@ExportConfig(value = "管理员名称", width = 100)
	private String username; //管理员名称、
	@ExportConfig(value = "操作模块", width = 200)
	private String module; //操作模块
	@ExportConfig(value = "操作内容", width = 400)
	private String context; //操作内容
	@ExportConfig(value = "操作时间", width = 100)
	private String issueTime; //操作时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlatLog [id=" + id + ", ip=" + ip + ", username=" + username + ", module=" + module + ", context=" + context + ", issueTime=" + issueTime + "]";
	}
	/**
	 * @param id
	 * @param ip
	 * @param username
	 * @param module
	 * @param context
	 * @param issueTime
	 */
	public PlatLog(int id, String ip, String username, String module, String context, String issueTime) {
		super();
		this.id = id;
		this.ip = ip;
		this.username = username;
		this.module = module;
		this.context = context;
		this.issueTime = issueTime;
	}
	/**
	 * 
	 */
	public PlatLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
