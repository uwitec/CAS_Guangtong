package cn.guangtong.service.cms;

import java.util.List;

import cn.guangtong.entity.cms.SystemSetting;
import cn.guangtong.utils.SystemSettingPageBean;

public interface SystemSettingService {
	// 分页显示系统设置信息
	public List<SystemSetting> getSystemSettings(SystemSettingPageBean pageBean);
	// 得到系统设置表中的所有记录
	public int getCounts(SystemSettingPageBean pageBean);
	// 添加系统设置信息
	public void addSystemSetting(SystemSetting systemSetting);
	// 删除某项系统设置信息
	public void deleteSystemSetting(int id);
	// 更新某项系统设置信息
	public void updateSystemSetting(SystemSetting systemSetting);
	// 根据id获取某项系统设置信息
	public SystemSetting getSystemSetting(int id);
}
