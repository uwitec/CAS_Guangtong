package cn.guangtong.dao.beidou;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.entity.beidou.Linesegment;

public interface LinesegmentDao {

	/**
	 * 插入一条路线数据
	 * 
	 * @param linesegment
	 * @return
	 */
	int insertSelective(Linesegment linesegment);

	/**
	 * 根据区域Id查询路线集合
	 * 
	 * @return
	 */
	List<Linesegment> getLinesegmentByEnclosureId(int enclosureId);

	/**
	 * 根据路线对象更新
	 * 
	 * @param linesegment
	 */
	void updateLinesegment(Linesegment linesegment);

	/**
	 * 根据区域id删除线路
	 * 
	 * @param enclosureId
	 * @return
	 */
	int deleteLinesegmentByEnclosureId(int enclosureId);

	void upLinesegmentByEnclosureId(@Param("enclosureId") int enclosureId, @Param("maxtimelimit") int maxtimelimit, @Param("mintimelimit") int mintimelimit);
}
