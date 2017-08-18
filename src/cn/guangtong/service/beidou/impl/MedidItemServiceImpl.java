package cn.guangtong.service.beidou.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guangtong.dao.beidou.MediaItemDao;
import cn.guangtong.service.beidou.MediaItemService;
import cn.guangtong.utils.MediaItemPageBean;

@Service
public class MedidItemServiceImpl implements MediaItemService {
	@Autowired
	private MediaItemDao mediaItemDao;

	@Override
	public void getMediaItem(MediaItemPageBean pageBean) {
		pageBean.setTotalCount(mediaItemDao.getMediaItemCount(pageBean));
		pageBean.setBeanList(mediaItemDao.getMediaItem(pageBean));
	}
}
