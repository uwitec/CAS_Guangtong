package cn.guangtong.entity.cms;

import javax.persistence.Table;

/**
 * 司机端文章实体类
 * 
 * @author sutong
 *
 */
@Table(name="logistics_cms.information")
public class Information {
	
    private Long id;
    private String title;
    private String content;
    private String createtime;
    private String smallimg;
    private String bigimg;
    private String detailsfigure;
   
  
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
	public String getDetailsfigure() {
		return detailsfigure;
	}
	public void setDetailsfigure(String detailsfigure) {
		this.detailsfigure = detailsfigure;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}