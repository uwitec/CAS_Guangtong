package cn.guangtong.dao.cms;

import java.util.List;

import cn.guangtong.entity.cms.SystemSetting;
import cn.guangtong.utils.SystemSettingPageBean;

public interface SystemSettingDao {
	
	public List<SystemSetting> getSystemSettings(SystemSettingPageBean pageBean);
	
	public int getCounts(SystemSettingPageBean pageBean);
	
	public int addSystemSetting(SystemSetting systemSetting);
	
	public void deleteSystemSetting(int id);
	
	public void updateSystemSetting(SystemSetting systemSetting);
	
	public SystemSetting getSystemSettingById(int id);
	
}
