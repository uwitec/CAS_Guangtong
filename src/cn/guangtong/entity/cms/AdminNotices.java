package cn.guangtong.entity.cms;

public class AdminNotices {
	private int id;
	private int senderid;
	private int receiverid;
	private long noticesid;
	private int isread;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderid() {
		return senderid;
	}

	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}

	public int getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}

	public long getNoticesid() {
		return noticesid;
	}

	public void setNoticesid(long noticesid) {
		this.noticesid = noticesid;
	}

	public int getIsread() {
		return isread;
	}

	public void setIsread(int isread) {
		this.isread = isread;
	}
}