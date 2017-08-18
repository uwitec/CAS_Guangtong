package cn.guangtong.entity.cms;

import java.util.List;


/**
 * 后台栏目实体表
 * @author sutong
 *
 */
public class Menu {
	
    private int id;
    private int parentId;
    private String name;
    private String contentDiv;
    private String time;
    private String icon;
    private Menu parent; // 父级权限
    private Integer adminId; // 管理员Id
    private int mSort; //栏目排序，越大越靠前
    private int iscoop; //是否显示在加盟企业后台
    private int onlyPermission; //只用作权限不显示
    private String [] menuIdsArray;
    private List<Menu> childMenus; //子级权限集合
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentDiv() {
		return contentDiv;
	}
	public void setContentDiv(String contentDiv) {
		this.contentDiv = contentDiv;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public int getmSort() {
		return mSort;
	}
	public void setmSort(int mSort) {
		this.mSort = mSort;
	}
	public int getIscoop() {
		return iscoop;
	}
	public void setIscoop(int iscoop) {
		this.iscoop = iscoop;
	}
	public int getOnlyPermission() {
		return onlyPermission;
	}
	public void setOnlyPermission(int onlyPermission) {
		this.onlyPermission = onlyPermission;
	}
	public List<Menu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
	}
	public String[] getMenuIdsArray() {
		return menuIdsArray;
	}
	public void setMenuIdsArray(String[] menuIdsArray) {
		this.menuIdsArray = menuIdsArray;
	}
	public Menu(int id, int parentId, String name, String contentDiv,
			String time, String icon, Integer adminId, int mSort, int iscoop,
			int onlyPermission, String[] menuIdsArray) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.contentDiv = contentDiv;
		this.time = time;
		this.icon = icon;
		this.adminId = adminId;
		this.mSort = mSort;
		this.iscoop = iscoop;
		this.onlyPermission = onlyPermission;
		this.menuIdsArray = menuIdsArray;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public Menu() {
		super();
	}
}