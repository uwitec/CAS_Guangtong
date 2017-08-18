package cn.guangtong.dao.beidou;

import cn.guangtong.entity.beidou.MapStyle;

public interface MapStyleDao {
   
    int insert(MapStyle record);

    MapStyle selectByUserId(Integer userId);

    int update(MapStyle record);
}