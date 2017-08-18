package cn.guangtong.dao.beidou;

import java.util.List;

import cn.guangtong.entity.beidou.MediaItem;
import cn.guangtong.utils.MediaItemPageBean;

/**
 * 媒体信息
 * 
 * @ClassName:MediaItem
 */
public interface MediaItemDao {
	/**
	 * 分页媒体信息
	 * 
	 * @param pageBean
	 * @return
	 */
	List<MediaItem> getMediaItem(MediaItemPageBean pageBean);

	/**
	 * 统计媒体信息总条数
	 * 
	 * @param pageBean
	 * @return
	 */
	Integer getMediaItemCount(MediaItemPageBean pageBean);
}
