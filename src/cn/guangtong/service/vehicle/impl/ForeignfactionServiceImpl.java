package cn.guangtong.service.vehicle.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.cms.AdminCooperationDao;
import cn.guangtong.dao.cms.AdminVehicleDao;
import cn.guangtong.dao.vehicle.ForeignfactionDao;
import cn.guangtong.dao.vehicle.VehicleInfoDao;
import cn.guangtong.entity.cms.Admin;
import cn.guangtong.entity.vehicle.Foreignfaction;
import cn.guangtong.model.VehicleMenu;
import cn.guangtong.service.vehicle.ForeignfactionService;
import cn.guangtong.utils.ForeignfactionPageBean;
import cn.guangtong.utils.FormatDateUtils;

@Service
public class ForeignfactionServiceImpl implements ForeignfactionService {

	@Autowired
	private ForeignfactionDao foreignfactionDao;

	@Autowired
	private VehicleInfoDao vehicleInfoDao;

	@Autowired
	private AdminVehicleDao adminVehicleDao;

	@Autowired
	private AdminCooperationDao adminCooperationDao;

	@Override
	public void insertForeignfaction(String receivedcooperationid, String endtime, String[] vehArr) {
		try {
			String starttime = FormatDateUtils.getDate(5);
			if (vehArr != null) {
				for (int i = 0; i < vehArr.length; i++) {
					Foreignfaction temp = new Foreignfaction();
					temp.setStarttime(starttime);
					temp.setEndtime(endtime);
					temp.setReceivedcooperationid(receivedcooperationid);
					temp.setVehicleid(vehArr[i]);
					String takecooperationid = vehicleInfoDao.getCooperationidByVehicelId(vehArr[i]);
					temp.setTakecooperationid(takecooperationid);

					foreignfactionDao.insert(temp);
					// 更新车辆表，车辆外派的状态
					Map<String, Object> para = new HashMap<String, Object>();
					para.put("state", 1);
					para.put("vehicleId", vehArr[i]);
					vehicleInfoDao.upisforeignfaction(para);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public void getForeignfactionByPageBean(ForeignfactionPageBean pageBean) {
		pageBean.setTotalCount((foreignfactionDao.getForeignfactionByPageBeanCount(pageBean))*10);
		pageBean.setBeanList(foreignfactionDao.getForeignfactionByPageBean(pageBean));

	}

	@Override
	public void getForeignfactionSByPageBean(ForeignfactionPageBean pageBean) {
		pageBean.setTotalCount((foreignfactionDao.getForeignfactionSByPageBeanCount(pageBean))*10);
		pageBean.setBeanList(foreignfactionDao.getForeignfactionSByPageBean(pageBean));
	}

	@Override
	public void delForeignfaction(String[] vehArr) {
		try {
			if (vehArr.length > 0) {
				for (int i = 0; i < vehArr.length; i++) {
					String vehicleId = vehArr[i];
					// 更新车辆表，车辆外派的状态
					Map<String, Object> para = new HashMap<String, Object>();
					para.put("state", 0);
					para.put("vehicleId", vehicleId);
					vehicleInfoDao.upisforeignfaction(para);
					// 查看外派车辆是否关联给其它账号，取消关联admin_vehicle ,admin_cooperation
					// 1、根据车辆id查看所属企业
					String cooperationid = vehicleInfoDao.getCooperationidByVehicelId(vehicleId);
					// 2、根据车辆id admin_vehicle查看该车的所有关联用户
					List<String> adminIds = adminVehicleDao.getAdminIdByVehicleId(vehicleId);
					if (adminIds.size() > 0) {
						for (int y = 0; y < adminIds.size(); y++) {
							String adminId = adminIds.get(y);
							// 3、根据用户id，查看其关联的所有企业
							List<String> cooperationIds = adminCooperationDao.getCooperationIdByAdminId(adminId);
							if (cooperationIds.size() > 0) {
								// 定义是否有匹配到的企业
								boolean state = false;
								for (int u = 0; u < cooperationIds.size(); u++) {
									// 4、是否包含该车所属企业，若不包含，则说明该车为外派车辆到该账号
									if (cooperationid.equals(cooperationIds.get(u))) {
										state = true;
										break;
									}
								}
								// 5、如果没有匹配到。删除外派车辆与账号的关联
								if (!state) {
									adminVehicleDao.deleteByAdminIdAndvehicleId(adminId, vehicleId);
								}
							}

						}
					}

					// 删除外派表
					foreignfactionDao.delete(vehArr[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

	@Override
	public Map<String, Map<String, List<VehicleMenu>>> getVehicle(Admin admin, String cooperationId) {
		// 存储处理之后的结果集
		Map<String, Map<String, List<VehicleMenu>>> data = new HashMap<String, Map<String, List<VehicleMenu>>>();
		// 存储所有车队的map
		Map<String, List<VehicleMenu>> teamMap = new HashMap<String, List<VehicleMenu>>();
		List<VehicleMenu> vehicleList = null;
		if (admin.getAtype() != 1) {
			vehicleList=vehicleInfoDao.getForeignfactionVehicle(admin.getId().toString(), cooperationId);
		} else {
			vehicleList=vehicleInfoDao.getForeignfactionVehicleS(cooperationId);
		}
		// 1、 按照车队进行分组
		for (VehicleMenu vehicleMenu : vehicleList) {
			// 车队名称
			String tName = vehicleMenu.gettName();
			if (teamMap.get(tName) != null) {
				// 如果车辆结果集map中存在当前车辆对象所属的车队，则，把当前车辆对象存进该结果集中
				teamMap.get(tName).add(vehicleMenu);
			} else {
				// 如果车辆结果集map中不存在当前车辆所属的车队，则，创建一个车队集合list，存入车队结果集map中
				List<VehicleMenu> temp = new ArrayList<VehicleMenu>();
				temp.add(vehicleMenu);
				teamMap.put(tName, temp);
			}
		}
		// 2、按照企业进行第二次划分

		Set<String> keys = teamMap.keySet();
		for (String key : keys) {
			// 获取车队中第一个车的企业名称
			List<VehicleMenu> temp = teamMap.get(key);
			String cName = temp.get(0).getcName();
			if (data.get(cName) != null) {
				// 如果data结果集中有当前企业的记录，则把当前车队存进该记录中
				data.get(cName).put(key, temp);
			} else {
				// 如果结果集中没有当前企业的记录，则，创建一个并添加进去
				Map<String, List<VehicleMenu>> temp1 = new HashMap<String, List<VehicleMenu>>();
				temp1.put(key, temp);
				data.put(cName, temp1);
			}
		}
		return data;

	}
}
