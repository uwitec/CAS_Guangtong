package cn.guangtong.service.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.Enclosure;

/**
 * 区域操作
 * 
 * @author SunTo
 * 
 */
public interface EnclosureService {

	/**
	 * 添加区域
	 * @param enclosure
	 * @return
	 */
	public boolean insert(Enclosure enclosure, String[] plateNo, String[] simNo, String[] vehicleId,String createDate);
	
	/**
	 * 获取所有的区域
	 * @return
	 */
	public List<Enclosure> getEnclosureAll();
	/**
	 * 根据区域对象更新
	 * 
	 * @param enclosure
	 */
	boolean updateByEnclosure(Enclosure enclosure, String[] plateNo, String[] simNo, String[] vehicleId);
	/**
	 * 根据区域id删除
	 * 
	 * @param bindid
	 */
	boolean  deleteEnclosure(int enclosureId);
}
