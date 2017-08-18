package cn.guangtong.service.beidou.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.beidou.RoadSpeedLimitDao;
import cn.guangtong.entity.beidou.RoadSpeedLimit;
import cn.guangtong.entity.beidou.RoadVehicle;
import cn.guangtong.service.beidou.RoadSpeedLimitService;

/**
 * @ClassName:RoadSpeedLimitImpl
 */
@Service
@Transactional
public class RoadSpeedLimitImpl implements RoadSpeedLimitService {
	@Autowired
	private RoadSpeedLimitDao roadSpeedLimitDao;

	@Override
	public boolean insertRoute(RoadSpeedLimit roadSpeedLimit, String[] simNo) {
		try {
			roadSpeedLimitDao.insertRoad(roadSpeedLimit);
			RoadVehicle roadVehicle = new RoadVehicle();
			if (simNo.length > 0) {
				for (int i = 0; i < simNo.length; i++) {
					roadVehicle.setRoadId(roadSpeedLimit.getId());
					roadVehicle.setSimNo(simNo[i]);
					roadSpeedLimitDao.insertRoadVehicle(roadVehicle);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@Override
	public List<RoadSpeedLimit> getAllRoad() {
		return roadSpeedLimitDao.getAllRoad();
	}

	@Override
	public boolean updateRoad(RoadSpeedLimit roadSpeedLimit, String[] simNo) {

		try {
			roadSpeedLimitDao.updateAllRoad(roadSpeedLimit);
			roadSpeedLimitDao.deleteRoadVehicleById(roadSpeedLimit.getId());
			RoadVehicle roadVehicle = new RoadVehicle();
			if (simNo.length > 0) {
				for (int i = 0; i < simNo.length; i++) {
					roadVehicle.setRoadId(roadSpeedLimit.getId());
					roadVehicle.setSimNo(simNo[i]);
					roadSpeedLimitDao.insertRoadVehicle(roadVehicle);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@Override
	public boolean deleteRoadById(int id) {
		try {
			roadSpeedLimitDao.deleteRoadById(id);
			roadSpeedLimitDao.deleteRoadVehicleById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@Override
	public List<String> getSimNoById(int roadId) {
		return roadSpeedLimitDao.getSimNoById(roadId);
	}

}
