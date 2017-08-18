package cn.guangtong.model;

import java.util.Date;

/**
 * 历史公告模型
 * 
 * @author SunTo
 * 
 */
public class HistoryNoticesModel {

	// 公告id
	private int noticesId;
	// 公告标题
	private String title;
	// 公告内容
	private String content;
	// 发布时间
	private String issueTime;
	// 接收人数
	private Long count;

	public int getNoticesId() {
		return noticesId;
	}

	public void setNoticesId(int noticesId) {
		this.noticesId = noticesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
