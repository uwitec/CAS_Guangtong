package cn.guangtong.utils;

import cn.guangtong.entity.cms.CustomerInformation;


public class CustomerInformationPageBean extends PageBean<CustomerInformation>{
	
	private String title; // 搜索标题
	private String createtime; // 创建时间
	private String smallimg; // 缩略图
	private String bigimg; // 轮播图
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSmallimg() {
		return smallimg;
	}
	public void setSmallimg(String smallimg) {
		this.smallimg = smallimg;
	}
	public String getBigimg() {
		return bigimg;
	}
	public void setBigimg(String bigimg) {
		this.bigimg = bigimg;
	}
}
