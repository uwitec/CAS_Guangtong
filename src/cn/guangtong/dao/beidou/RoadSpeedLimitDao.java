package cn.guangtong.dao.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.RoadSpeedLimit;
import cn.guangtong.entity.beidou.RoadVehicle;

/**
 * 道路限速相关查询，添加
 * 
 */
public interface RoadSpeedLimitDao {

	/**
	 * 添加路线.路段
	 * 
	 * @param
	 * @return
	 */
	void insertRoad(RoadSpeedLimit roadSpeedLimit);

	/**
	 * 查询路线.路段
	 * 
	 * @param terminalPageBean
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
	 * 添加至绑定路段车辆
	 * 
	 * @param roadSpeedLimit
	 * @return
	 */
	void insertRoadVehicle(RoadVehicle roadVehicle);

	/**
	 * 删除RoadSpeedLimit
	 * 
	 * @param roadSpeedLimit
	 * @return
	 */
	void deleteRoadById(int id);

	/**
	 * 删除RoadVehicle
	 * 
	 * @param roadVehicle
	 * @return
	 */
	void deleteRoadVehicleById(int roadId);

	/**
	 * 更新
	 * 
	 * @param roadSpeedLimit
	 * @return
	 */
	void updateAllRoad(RoadSpeedLimit roadSpeedLimit);
}