package cn.guangtong.service.beidou;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.beidou.Enclosure;
import cn.guangtong.entity.beidou.EnclosureBinding;
import cn.guangtong.entity.beidou.TerminalCommand;

/**
 * @ClassName:EnclousureBindService
 */
public interface EnclousureBindService {
	/**
	 * 插入一条区域绑定
	 * 
	 * @param enBinding
	 * @return
	 */
	boolean insertSelective(Enclosure enclosure, String[] plateNo, String[] simNo, String[] vehicleId);

	/**
	 * 根据区域类型查区域绑定
	 * 
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getEnclosureBindByType(String type);

	/**
	 * 根据区域id删除所有绑定及发送删除指令
	 * 
	 * @param bindid
	 */
	boolean deleteAllEnclosureBind(int enclosureId);
	
	/**
	 * 根据区域id查询绑定车辆的simNo集合
	 * @param type
	 * @return
	 */
	List<String> getEnclosureBindSimNoByEnclosureid(int enclosureId);
}
