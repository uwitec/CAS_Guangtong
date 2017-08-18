package cn.guangtong.service.beidou;

import java.util.Map;

import cn.guangtong.entity.cms.Admin;

/**
 * 面板
 * 
 * @author SunTo
 * 
 */
public interface PanelService {

	Map<String, Object> findVehicleByAdmin(Admin admin,String state);
}
