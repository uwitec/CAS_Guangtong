package cn.guangtong.service.cms;

import java.util.List;

public interface AdminPermissionService {
	// 删除管理员原有权限
	public void delAdminPerByAid(int adminId);

	public void addAdminPermission(List<Integer> menuIds, int adminId);

	// 保存权限
	public boolean batchAddMenuPermission(Integer adminId, Integer[] menuIdArr, String[] vehicleArr);

}
