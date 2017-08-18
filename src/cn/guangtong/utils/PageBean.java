package cn.guangtong.utils;

import java.util.List;

/**
 * 分页Bean,它会在各层之间传递
 * 
 * @author sutong
 */
public class PageBean<T> {

	private String sortInfo; // 排序字段
	private String sortType; // 排序类型（升须,降须） 
	private int currentPage;// 当前页码
	private int totalCount;// 总记录数
	private int pageCount;// 每页记录数
	private String url;// 请求路径和参数
	private List<T> beanList; // T类集合
	private int begin; // 分页起点
	private int end; // 分页终点
	private double weight;
	public String getSortInfo() {
		return sortInfo;
	}
	public void setSortInfo(String sortInfo) {
		this.sortInfo = sortInfo;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public int getBegin() {
		// 在mapper.xml使用begin属性时，对其进行计算
		begin = (currentPage - 1) * pageCount;
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		this.end=pageCount;
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	// 计算总页数
	public int getTp() {
		int tp = totalCount / pageCount;
		return totalCount % pageCount == 0 ? tp : tp + 1;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
