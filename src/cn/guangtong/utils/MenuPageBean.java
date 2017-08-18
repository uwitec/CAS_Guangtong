package cn.guangtong.utils;

import cn.guangtong.entity.cms.Menu;

public class MenuPageBean extends PageBean<Menu>{
	
    private int parentid;
    private String name;
    private String contentDiv;
    
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentDiv() {
		return contentDiv;
	}
	public void setContentDiv(String contentDiv) {
		this.contentDiv = contentDiv;
	}
}
