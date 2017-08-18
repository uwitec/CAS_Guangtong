package cn.guangtong.utils;

import cn.guangtong.entity.cms.SystemSetting;

public class SystemSettingPageBean extends PageBean<SystemSetting>{
	
	private int sortId;
	private int sortSetType; //要搜索的系统设置类型
	private String sortSetkey; //要搜索的系统设置的key值
	private String sortSetValue; //要搜索的系统设置的值
	private String  sortSetDesc; // 要搜索的系统名称
	
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getSortSetType() {
		return sortSetType;
	}
	public void setSortSetType(int sortSetType) {
		this.sortSetType = sortSetType;
	}
	public String getSortSetkey() {
		return sortSetkey;
	}
	public void setSortSetkey(String sortSetkey) {
		this.sortSetkey = sortSetkey;
	}
	public String getSortSetValue() {
		return sortSetValue;
	}
	public void setSortSetValue(String sortSetValue) {
		this.sortSetValue = sortSetValue;
	}
	public String getSortSetDesc() {
		return sortSetDesc;
	}
	public void setSortSetDesc(String sortSetDesc) {
		this.sortSetDesc = sortSetDesc;
	}
}
