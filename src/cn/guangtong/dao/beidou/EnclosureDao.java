package cn.guangtong.dao.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.Enclosure;

public interface EnclosureDao {

	int insert(Enclosure record);

	/**
	 * 获取所有的区域
	 * 
	 * @return
	 */
	public List<Enclosure> getEnclosureAll();

	/**
	 * 获取线路区域
	 * 
	 * @return
	 */
	List<Enclosure> getLinesegmentServiceAll();

	/**
	 * 关键点线路
	 * 
	 * @return
	 */
	List<Enclosure> getLinesegmentkeyPointAll();
	/**
	 * 根据区域对象更新
	 * 
	 * @param enclosure
	 */
	void updateByEnclosure(Enclosure enclosure);
	/**
	 * 根据区域id删除
	 * 
	 * @param bindid
	 */
	void deleteEnclosure(int enclosureId);
	/**
	 * 根据区域id查询区域
	 * @param enclosureId
	 * @return
	 */
	Enclosure getEnclosureByid(int enclosureId);
}