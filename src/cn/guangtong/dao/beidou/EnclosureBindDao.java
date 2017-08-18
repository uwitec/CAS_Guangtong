package cn.guangtong.dao.beidou;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.beidou.EnclosureBinding;

public interface EnclosureBindDao {
	/**
	 * 插入一条区域绑定
	 * 
	 * @param enBinding
	 * @return
	 */
	int insertSelective(EnclosureBinding enBinding);

	/**
	 * 根据区域类型查区域绑定
	 * 
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getEnclosureBindByType(@Param("type")String type);

	/**
	 * 根据区域id更新(删除绑定)
	 * 
	 * @param bindid
	 */
	void upEnclosureBind(int enclosureid);
	/**
	 * 根据区域id删除
	 * @param enclosureid
	 */
     void deleteEnclosureByid(int enclosureid);

	
	/**
	 * 根据区域id查询相关联的车辆信息
	 * @param enclosureid
	 * @return
	 */
	List<Map<String,Object>> getByEnclosureid(int enclosureid);
	
	/**
	 * 根据区域id获取绑定额车辆simNo集合
	 * @param enclosureid
	 * @return
	 */
	List<String> getEnclosureBindSimNoByEnclosureid(int enclosureid);

}