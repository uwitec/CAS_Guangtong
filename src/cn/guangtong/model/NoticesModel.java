package cn.guangtong.model;

public class NoticesModel {

	// 关联表id
	private int id;

	// 发送人id 名称
	private int senderId;
	private String senderAdminName;

	// 是否已读
	private int isRead;

	private String title;

	private String content;

	private String issuetime;

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
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

	public String getIssuetime() {
		return issuetime;
	}

	public void setIssuetime(String issuetime) {
		this.issuetime = issuetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getSenderAdminName() {
		return senderAdminName;
	}

	public void setSenderAdminName(String senderAdminName) {
		this.senderAdminName = senderAdminName;
	}

}
