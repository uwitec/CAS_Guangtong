package cn.guangtong.service.coopertation.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.controller.cooperation.CooperationPageBean;
import cn.guangtong.dao.cms.AdminDao;
import cn.guangtong.dao.cms.AdminVehicleDao;
import cn.guangtong.dao.cooperation.CooperationInfoDao;
import cn.guangtong.dao.vehicle.VehicleInfoDao;
import cn.guangtong.dao.vehicle.VehicleTeamDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.cms.AdminVehicle;
import cn.guangtong.entity.cooperation.CooperationInfo;
import cn.guangtong.entity.vehicle.VehicleInfo;
import cn.guangtong.entity.vehicle.VehicleTeam;
import cn.guangtong.model.AdminCooperation;
import cn.guangtong.service.coopertation.CooperationInfoService;

@Service
@Transactional
public class CooperationInfoServiceImpl implements CooperationInfoService {

	@Autowired
	private CooperationInfoDao cooperationInfoDao;

	@Autowired
	private VehicleTeamDao vehicleTeamDao;

	@Autowired
	private VehicleInfoDao vehicleInfoDao;

	@Autowired
	private AdminVehicleDao adminVehicleDao;

	@Autowired
	private AdminDao adminDao;

	public void setCooperationInfoDao(CooperationInfoDao cooperationInfoDao) {
		this.cooperationInfoDao = cooperationInfoDao;
	}

	public CooperationInfoDao getCooperationInfoDao() {
		return cooperationInfoDao;
	}

	@Override
	public List<Map<String, Object>> selectAll() {
		return cooperationInfoDao.selectAll();
	}

	/**
	 * 查询加盟企业总数
	 */
	public Integer sCooperationInfoCount() {

		return cooperationInfoDao.sCooperationInfoCount();
	}

	/**
	 * 查询所有企业信息+分页+模糊查询
	 */
	public List<Map<String, Object>> sCooperationInfoA(CooperationPageBean cooperationPageBean) {

		return cooperationInfoDao.sCooperationInfoA(cooperationPageBean);
	}

	/**
	 * 查询一条企业记录
	 */
	public Map<String, Object> sCooperationInfoOne(String id) {
		return getCooperationInfoDao().sCooperationInfoOne(id);
	}

	/**
	 * 批量冻结企业
	 */
	public Integer uCooperationInfoFreezing(String type, String id) {
		try {
			if (getCooperationInfoDao().uCooperationInfoFreezing(type, id) == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return 0;
	}

	/**
	 * 修改企业信息
	 */
	public Integer uCooperationInfo(CooperationInfo cooperationInfo) {
		return getCooperationInfoDao().uCooperationInfo(cooperationInfo);
	}

	/**
	 * 增加一条企业信息
	 */
	public Boolean iCooperationInfo(CooperationInfo cooperationInfo) {
		try {
			// 1、添加一条企业信息
			VehicleTeam vt = new VehicleTeam();
			getCooperationInfoDao().iCooperationInfo(cooperationInfo);
			vt.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			vt.setCooperationId(cooperationInfo.getId());
			vt.settName(cooperationInfo.getCname() + "TEAM");
			vehicleTeamDao.insert(vt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}

	}

	@Override
	public List<String> findCoopIdByAid(int adminId) {
		return cooperationInfoDao.findCoopIdByAid(adminId);
	}

	@Override
	public CooperationInfo getCooperationInfo(String id) {
		return cooperationInfoDao.getCooperationInfoById(id);
	}

	@Override
	public List<AdminCooperation> getCooperationInfoByAdminId(int adminId) {

		return cooperationInfoDao.getCooperationInfoByAdminId(adminId);
	}

	@Override
	public Integer sCooperationCount(CooperationPageBean coopertationPageBean) {
		return cooperationInfoDao.sCooperationCount(coopertationPageBean);
	}

	@Override
	public List<Map<String, Object>> sCooperationInfo(CooperationPageBean cooperationPageBean) {
		return cooperationInfoDao.sCooperationInfo(cooperationPageBean);
	}

	@Override
	public Integer sCooperationCountS(CooperationPageBean coopertationPageBean) {
		return cooperationInfoDao.sCooperationCountS(coopertationPageBean);
	}

}
