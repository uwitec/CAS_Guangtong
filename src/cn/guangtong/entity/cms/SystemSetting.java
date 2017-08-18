package cn.guangtong.entity.cms;

import javax.persistence.Table;

/**
 * 系统设置实体类
 * @author sutong
 *
 */
@Table(name="logistics_cms.system_setting")
public class SystemSetting {
    private int id;
    private int settingtype; //设置类型(1:key值)
    private String settingkey; //设置的key值
    private String settingvalue; //设置的值
    private String description; //设置的中文描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSettingtype() {
		return settingtype;
	}
	public void setSettingtype(int settingtype) {
		this.settingtype = settingtype;
	}
	public String getSettingkey() {
		return settingkey;
	}
	public void setSettingkey(String settingkey) {
		this.settingkey = settingkey;
	}
	public String getSettingvalue() {
		return settingvalue;
	}
	public void setSettingvalue(String settingvalue) {
		this.settingvalue = settingvalue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SystemSetting(int id, int settingtype, String settingkey,
			String settingvalue, String description) {
		super();
		this.id = id;
		this.settingtype = settingtype;
		this.settingkey = settingkey;
		this.settingvalue = settingvalue;
		this.description = description;
	}
	public SystemSetting() {
		super();
	}
}