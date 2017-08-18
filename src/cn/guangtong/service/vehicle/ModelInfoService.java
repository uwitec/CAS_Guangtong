package cn.guangtong.service.vehicle;

import java.util.Map;

import cn.guangtong.entity.vehicle.ModelInfo;
import cn.guangtong.utils.PageBean;

public interface ModelInfoService {
	/**
	 * 查询所有车辆类型
	 */
	PageBean<Map<String,Object>> selectModelInfoAll(PageBean pageBean);
	
	
	/**
	 * 根据id查询车辆类型的具体数据
	 */
	Map<String,Object> selectModelInfoById(String id);
	
	/**
	 * 修改车辆信息
	 */
	int updateModelInfo(ModelInfo modelinfo);

	/**
	 * 新增车辆类型
	 * @return
	 */
	int insertModelInfo(ModelInfo modelinfo);
	
	/**
	 * 删除车辆类型
	 * @return
	 */
	int deleteModelInfo(Integer id);
	
	
	int getCounts();
	
}
