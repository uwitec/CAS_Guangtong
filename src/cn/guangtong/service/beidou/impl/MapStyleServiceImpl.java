package cn.guangtong.service.beidou.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.guangtong.dao.beidou.MapStyleDao;
import cn.guangtong.entity.beidou.MapStyle;
import cn.guangtong.service.beidou.MapStyleService;
@Service
@Transactional
public class MapStyleServiceImpl implements MapStyleService {

	@Autowired
	private MapStyleDao mapStyleDao;
	
	@Override
	public int insert(MapStyle record) {
		return 0;
	}

	@Override
	public MapStyle selectByUserId(Integer userId) {
		MapStyle mapStyle=mapStyleDao.selectByUserId(userId);
		if(mapStyle!=null){
			return mapStyle;
		}else{
			MapStyle temp=new MapStyle();
			temp.setUserid(userId);
			mapStyleDao.insert(temp);
			return temp;
		}
		 
	}

	@Override
	public int update(MapStyle record) {
		return mapStyleDao.update(record);
	}

}
