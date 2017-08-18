package cn.guangtong.model;

import java.util.List;

/**
 * 权限接收人列表分级展示
 * 
 * @author SunTo
 * 
 */
public class MenusGrading {

	// 当前节点id
	private int id;

	// 当前节点的名称
	private String name;

	// 父节点id
	private int parentId;

	// 子节点
	private List<MenusGrading> children;

	// 是否显示
	private int isShown; // 1代表二级关联存在；0代表二级关联

	// 是否拥有权限
	private int isPermissions = 0;

	public int getIsPermissions() {
		return isPermissions;
	}

	public void setIsPermissions(int isPermissions) {
		this.isPermissions = isPermissions;
	}

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

	public List<MenusGrading> getChildren() {
		return children;
	}

	public void setChildren(List<MenusGrading> children) {
		this.children = children;
	}

	public int getIsShown() {
		return isShown;
	}

	public void setIsShown(int isShown) {
		this.isShown = isShown;
	}

}
