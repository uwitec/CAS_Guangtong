package cn.guangtong.entity.cms;

public class MenuLev extends Menu{
	private int lev; // 记录权限级联数

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}
	
	 /**
     * 类型的转换
     * @param menu
     */
	public void from(Menu menu) {
		this.setId(menu.getId());
		this.setParentId(menu.getParentId());
		this.setName(menu.getName());
		this.setContentDiv(menu.getContentDiv());
		this.setTime(menu.getTime());
		this.setIcon(menu.getIcon());
		this.setAdminId(menu.getAdminId()); // 管理员Id
		this.setmSort(menu.getmSort()); //栏目排序，越大越靠前
		this.setIscoop(menu.getIscoop()); //是否显示在加盟企业后台
		this.setOnlyPermission(menu.getOnlyPermission()); 
	}
	
}
