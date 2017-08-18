package cn.guangtong.entity.cms;

/**
 * 客户端用户文章实体类
 * 
 * @author sutong
 *
 */

public class CustomerInformation {
    private Long id;
    private String title;
    private String content;
    private String createtime;
    private String smallimg; // 代表图片
    private String bigimg; // 代表图片
    private String detailsfigure; // 代表图片

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
        this.title = title == null ? null : title.trim();
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
        this.smallimg = smallimg == null ? null : smallimg.trim();
    }

    public String getBigimg() {
        return bigimg;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetailsfigure() {
		return detailsfigure;
	}

	public void setDetailsfigure(String detailsfigure) {
		this.detailsfigure = detailsfigure;
	}

	public void setBigimg(String bigimg) {
		this.bigimg = bigimg;
	}

	public CustomerInformation(Long id, String title, String content,
			String createtime, String smallimg, String bigimg,
			String detailsfigure) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createtime = createtime;
		this.smallimg = smallimg;
		this.bigimg = bigimg;
		this.detailsfigure = detailsfigure;
	}

	public CustomerInformation() {
		super();
	}

}