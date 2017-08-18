package cn.guangtong.service.beidou;

import cn.guangtong.entity.beidou.MapStyle;

public interface MapStyleService {
	int insert(MapStyle record);

	MapStyle selectByUserId(Integer UserId);

	int update(MapStyle record);
}
