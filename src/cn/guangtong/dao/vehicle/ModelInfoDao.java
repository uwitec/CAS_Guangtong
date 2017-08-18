package cn.guangtong.dao.vehicle;

import java.util.List;
import java.util.Map;

import cn.guangtong.entity.vehicle.ModelInfo;
import cn.guangtong.utils.PageBean;

public interface ModelInfoDao {

	/**
	 * 查询所有车辆类型
	 */
	List<Map<String,Object>> selectModelInfoAll(PageBean pageBean);
	
	/**
	 * 查询总记录数
	 */
	public int getCounts();
	
	/**
	 * 根据ID查询车辆类型详细信息
	 */
	Map<String,Object> selectModelInfoById(String id);
	
	/**
	 * 新增车辆类型
	 */
	int insertModelInfo(ModelInfo modelInfo);
	
	/**
	 * 修改 车辆类型信息
	 */
	int updateModelInfo(ModelInfo modelInfo);
	
	/**
	 * 删除车辆类型
	 */
	int deleteModelInfo(Integer id);
	
}
