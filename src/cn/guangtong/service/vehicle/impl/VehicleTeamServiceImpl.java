package cn.guangtong.service.vehicle.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.AdminDao;
import cn.guangtong.dao.cms.AdminVehicleDao;
import cn.guangtong.dao.vehicle.VehicleInfoDao;
import cn.guangtong.dao.vehicle.VehicleTeamDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.AdminVehicle;
import cn.guangtong.entity.vehicle.VehicleInfo;
import cn.guangtong.entity.vehicle.VehicleTeam;
import cn.guangtong.service.vehicle.VehicleTeamService;
import cn.guangtong.utils.VehicleTeamPageBean;

@Service
@Transactional
public class VehicleTeamServiceImpl implements VehicleTeamService {

	@Autowired
	private VehicleTeamDao vehicleTeamDao;
	@Autowired
	private VehicleInfoDao vehicleInfoDao;

	@Autowired
	private AdminVehicleDao adminVehicleDao;

	@Autowired
	private AdminDao adminDao;

	@Override
	public boolean insert(VehicleTeam record) {
		try {
			if(vehicleTeamDao.insert(record)>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

	@Override
	public boolean update(VehicleTeam record) {

		try {
			vehicleTeamDao.update(record);
			return true;
		} catch (Exception e) {
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@Override
	public List<VehicleTeam> getByCooperationId(String cooperationId) {
		return vehicleTeamDao.getByCooperationId(cooperationId);
	}

	@Override
	public VehicleTeam getById(String id) {
		return vehicleTeamDao.getById(id);
	}

	@Override
	public void getVehicleTeamByPageBean(VehicleTeamPageBean pageBean) {
		Integer totalCount = vehicleTeamDao.getVehicleTeamByPageBeanCount(pageBean);
		pageBean.setTotalCount(totalCount*10);
		List<VehicleTeam> list = vehicleTeamDao.getVehicleTeamByPageBean(pageBean);
		pageBean.setBeanList(list);
	}

	@Override
	public void getAllVehicleTeamByPageBean(VehicleTeamPageBean pageBean) {
		Integer totalCount = vehicleTeamDao.getAllVehicleTeamByPageBeanCount(pageBean);
		pageBean.setTotalCount(totalCount*10);
		List<VehicleTeam> list = vehicleTeamDao.getAllVehicleTeamByPageBean(pageBean);
		pageBean.setBeanList(list);
		
	}

}
