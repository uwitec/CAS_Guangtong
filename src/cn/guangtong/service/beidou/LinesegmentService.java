package cn.guangtong.service.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.Enclosure;

public interface LinesegmentService {
	/**
	 * 添加一条线路
	 * 
	 * @param enclosure
	 * @param vehArr
	 * @return
	 */
	boolean insertSelective(Enclosure enclosure, String[] vehArr, String[] plateNo, String[] simNo, String[] vehicleId);

	/**
	 * 获取所有线路
	 * 
	 * @return
	 */
	List<Enclosure> getLinesegmentServiceAll();

	/**
	 * 获取关键线路点列表
	 * 
	 * @return
	 */
	List<Enclosure> getLinesegmentkeyPointAll();

}
