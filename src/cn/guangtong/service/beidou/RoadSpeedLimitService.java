package cn.guangtong.service.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.RoadSpeedLimit;

/**
 * @ClassName:RoadSpeedLimitService
 */
public interface RoadSpeedLimitService {

	/**
	 * 添加路线 路段
	 * 
	 * @param
	 * @return
	 */
	boolean insertRoute(RoadSpeedLimit roadSpeedLimit, String[] simNo);

	/**
	 * 查询路段 路线
	 * 
	 * @param
	 * @return
	 */
	List<RoadSpeedLimit> getAllRoad();

	/**
	 * 通过id查询simNo卡号
	 * 
	 * @param id
	 * @return
	 */
	List<String> getSimNoById(int roadId);

	/**
	 * 更新
	 * 
	 * @param
	 * @return
	 */
	boolean updateRoad(RoadSpeedLimit roadSpeedLimit, String[] simNo);

	/**
	 * 删除
	 * 
	 * @param roadSpeedLimit
	 * @return
	 */
	boolean deleteRoadById(int id);
}
