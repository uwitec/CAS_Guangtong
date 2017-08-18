package cn.guangtong.entity.cms;

import java.util.List;

/**
 * 管理员权限实体类
 * 
 * @author sutong
 *
 */
public class AdminPermission {
	private long id; 
	private int adminId; // 外键，对应实体类为 Admin
	private int menuId; // 外键，对应实体类为 Menu
	private List<Integer> menuIds;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public List<Integer> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}
	public AdminPermission(int adminId, List<Integer> menuIds) {
		super();
		this.adminId = adminId;
		this.menuIds = menuIds;
	}
	public AdminPermission() {
		super();
	}
	@Override
	public String toString() {
		return "AdminPermission [id=" + id + ", adminId=" + adminId
				+ ", menuId=" + menuId + ", menuIds=" + menuIds + "]";
	}
}
