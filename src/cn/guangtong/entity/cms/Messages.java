package cn.guangtong.entity.cms;

/**
 * 消息详情实体类
 * @author sutong
 *
 */
public class Messages {
    private Long id;
    private String title;
    private String content;
    private String issuetime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Messages(Long id, String title, String content, String issuetime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.issuetime = issuetime;
	}
	public Messages() {
		super();
	}
}