package cn.guangtong.service.beidou;

import cn.guangtong.utils.MediaItemPageBean;

public interface MediaItemService {
	/**
	 * 分页媒体信息
	 * 
	 * @param pageBean
	 */
	void getMediaItem(MediaItemPageBean pageBean);

}
