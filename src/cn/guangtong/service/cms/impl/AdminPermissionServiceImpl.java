package cn.guangtong.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.AdminCooperationDao;
import cn.guangtong.dao.cms.AdminPermissionDao;
import cn.guangtong.dao.cms.AdminVehicleDao;
import cn.guangtong.entity.cms.AdminCooperation;
import cn.guangtong.entity.cms.AdminPermission;
import cn.guangtong.entity.cms.AdminVehicle;
import cn.guangtong.service.cms.AdminPermissionService;

@Service
@Transactional
public class AdminPermissionServiceImpl implements AdminPermissionService {

	@Autowired
	private AdminPermissionDao adminPermissionDao;

	@Autowired
	private AdminCooperationDao adminCooperationDao;

	@Autowired
	private AdminVehicleDao adminVehicleDao;

	@Override
	public void delAdminPerByAid(int adminId) {
		adminPermissionDao.delAdminPerByAid(adminId);
	}

	@Override
	public void addAdminPermission(List<Integer> menuIds, int adminId) {
		AdminPermission adminPermission = new AdminPermission();
		for (Integer menuId : menuIds) {
			try {
				adminPermission.setMenuId(menuId);
				adminPermission.setAdminId(adminId);
				adminPermissionDao.addAdminPermission(adminPermission);
			} catch (Exception e) {
				e.printStackTrace();
				// 就是这一句了，加上之后，如果抛了异常,会回滚的
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
	}

	@Override
	public boolean batchAddMenuPermission(Integer adminId, Integer[] menuIdArr, String[] vehicleArr) {

		try {
			// 删除管理员原有权限
			adminPermissionDao.delAdminPerByAid(adminId);
			// 赋值权限
			AdminPermission adminPermission = new AdminPermission();

			if (menuIdArr!=null&&!menuIdArr.toString().equals("")) {
				for (int i = 0; i < menuIdArr.length; i++) {
					adminPermission.setMenuId(menuIdArr[i]);
					adminPermission.setAdminId(adminId);
					adminPermissionDao.addAdminPermission(adminPermission);
				}
			}

			/*
			 * // 删除管理员原有的车辆 adminCooperationDao.deleteByAdminId(adminId); // 赋值企业 AdminCooperation adminCooperation = new AdminCooperation(); if(cooperationArr != null && !cooperationArr.toString().equals("")){ for (int i = 0; i < cooperationArr.length; i++) { adminCooperation.setAdminid(adminId); adminCooperation.setCooperationid(cooperationArr[i]); adminCooperationDao.insert(adminCooperation); } }
			 */
			// 删除原有的绑定车辆
			adminVehicleDao.deleteByAdminId(adminId);
			// 创建新的绑定实体
			AdminVehicle adminVehicle = new AdminVehicle();
			if (vehicleArr != null && !vehicleArr.toString().equals("")) {
				for (int i = 0; i < vehicleArr.length; i++) {
					adminVehicle.setAdminid(adminId);
					adminVehicle.setVehicleid(vehicleArr[i]);
					adminVehicleDao.insert(adminVehicle);
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
}
