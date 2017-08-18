package cn.guangtong.model;

import java.util.List;

/**
 * 公告发布 接收人列表分级展示
 * 
 * @author SunTo
 * 
 */
public class NoticesGrading {

	// 当前节点id
	private int id;

	// 当前节点的名称
	private String name;

	// 父节点id
	private int parentId;

	// 子节点
	private List<NoticesGrading> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<NoticesGrading> getChildren() {
		return children;
	}

	public void setChildren(List<NoticesGrading> children) {
		this.children = children;
	}

}
