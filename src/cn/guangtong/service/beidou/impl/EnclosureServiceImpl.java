package cn.guangtong.service.beidou.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.beidou.EnclosureBindDao;
import cn.guangtong.dao.beidou.EnclosureDao;
import cn.guangtong.dao.beidou.LinesegmentDao;
import cn.guangtong.dao.beidou.TerminalCommandDao;
import cn.guangtong.entity.beidou.Enclosure;
import cn.guangtong.entity.beidou.EnclosureBinding;
import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.service.beidou.EnclosureService;

@Service
@Transactional
public class EnclosureServiceImpl implements EnclosureService {

	@Autowired
	private EnclosureDao enclosureDao;
	@Autowired
	private EnclosureBindDao enclosureBindDao;
	@Autowired
	private TerminalCommandDao terminalCommandDao;
	@Autowired
	private LinesegmentDao linesegmentDao;

	@Override
	public boolean insert(Enclosure enclosure,String[] plateNo, String[] simNo, String[] vehicleId,String createDate) {

		try {
			enclosureDao.insert(enclosure);

			int enclosureId = enclosure.getEnclosureId();
			TerminalCommand terminalCommand = new TerminalCommand();
			String type = enclosure.getEnclosureType();
			System.out.println(type);
			// 34304圆形，34306矩形，34308多边形，34310路线
			if (type.equals("circle"))// 圆形
			{
				terminalCommand.setCmdtype(34304);
			} else if (type.equals("rect"))// 矩形
			{
				terminalCommand.setCmdtype(34306);
			} else if (type.equals("polygon"))// 多边形
			{
				terminalCommand.setCmdtype(34308);
			} else if (type.equals("route"))// 线路
			{
				terminalCommand.setCmdtype(34310);
			}
			terminalCommand.setCmddata(String.valueOf(enclosureId));
			if (plateNo != null && plateNo.length > 0) {
				for (int i = 0; i < plateNo.length; i++) {
					// 车牌号
					terminalCommand.setPlateno(plateNo[i]);
					// 终端SIM卡号
					terminalCommand.setSimno(simNo[i]);
					// 车辆id
					int vehicleid = Integer.parseInt(vehicleId[i]);
					terminalCommand.setVehicleid(vehicleid);
					terminalCommand.setCreatedate(createDate);
					// 绑定对象
					EnclosureBinding enBinding = new EnclosureBinding();
					enBinding.setEnclosureid(enclosureId);
					enBinding.setVehicleid(vehicleid);
					terminalCommand.setCmdid(null);
					terminalCommandDao.insert(terminalCommand);
					enclosureBindDao.insertSelective(enBinding);
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
	public List<Enclosure> getEnclosureAll() {
		return enclosureDao.getEnclosureAll();
	}

	@Override
	public boolean updateByEnclosure(Enclosure enclosure, String[] plateNo, String[] simNo, String[] vehicleId) {

		try {
			int enclosureId = enclosure.getEnclosureId();
			// 更新区域
			enclosureDao.updateByEnclosure(enclosure);

			// 1、删除绑定指令(删除区域绑定)
			EnclosureBindTerminalCommand(enclosureId);
			// 2、 删除已有的绑定(删除区域绑定)
			enclosureBindDao.upEnclosureBind(enclosureId);

			// 添加区域绑定-并设置新的绑定-发送绑定指令
			// EnclousureBindService enclosureBindService = new EnclosureBindServiceImpl();
			// boolean l = enclosureBindService.insertSelective(enclosure, plateNo, simNo, vehicleId);
			TerminalCommand terminalCommand = new TerminalCommand();
			String type = enclosure.getEnclosureType();
			// 34304圆形，34306矩形，34308多边形，34310路线
			if (type.equals("circle"))// 圆形
			{
				terminalCommand.setCmdtype(34304);
			} else if (type.equals("rect"))// 矩形
			{
				terminalCommand.setCmdtype(34306);
			} else if (type.equals("polygon"))// 多边形
			{
				terminalCommand.setCmdtype(34308);
			} else if (type.equals("route"))// 线路
			{
				terminalCommand.setCmdtype(34310);
				// 行驶时间限制
				int isTime = enclosure.getIsTime();
				if (isTime == 1) {
					int maxTimeLimit=enclosure.getMaxtimelimit();
					int mintimelimit=enclosure.getMintimelimit();
					linesegmentDao.upLinesegmentByEnclosureId(enclosureId, maxTimeLimit, mintimelimit);
				}
			}
			terminalCommand.setCmddata(String.valueOf(enclosureId));
			if (plateNo.length > 0) {
				for (int i = 0; i < plateNo.length; i++) {
					// 车牌号
					terminalCommand.setPlateno(plateNo[i]);
					// 终端SIM卡号
					terminalCommand.setSimno(simNo[i]);
					// 车辆id
					int vehicleid = Integer.parseInt(vehicleId[i]);
					terminalCommand.setVehicleid(vehicleid);
					// 绑定对象
					EnclosureBinding enBinding = new EnclosureBinding();
					enBinding.setEnclosureid(enclosureId);
					enBinding.setVehicleid(vehicleid);
					terminalCommand.setCmdid(null);
					terminalCommandDao.insert(terminalCommand);
					enclosureBindDao.insertSelective(enBinding);
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

	/**
	 * 删除原有区域绑定指令
	 * 
	 * @param enclosureId
	 * @return
	 */
	public boolean EnclosureBindTerminalCommand(int enclosureId) {
		try {
			TerminalCommand terminalCommand = new TerminalCommand();
			String type = enclosureDao.getEnclosureByid(enclosureId).getEnclosureType();
			if (type.equals("circle"))// 圆形
			{
				terminalCommand.setCmdtype(34305);
			} else if (type.equals("rect"))// 矩形
			{
				terminalCommand.setCmdtype(34307);
			} else if (type.equals("polygon"))// 多边形
			{
				terminalCommand.setCmdtype(34309);
			} else if (type.equals("route"))// 线路
			{
				terminalCommand.setCmdtype(34311);
			}
			terminalCommand.setCmddata(String.valueOf(enclosureId));
			List<Map<String, Object>> list = enclosureBindDao.getByEnclosureid(enclosureId);
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					// 车牌号
					terminalCommand.setPlateno((String) map.get("plateNo"));
					// 终端SIM卡号
					terminalCommand.setSimno((String) map.get("simNo"));
					// 车辆id
					terminalCommand.setVehicleid(Integer.parseInt(map.get("vehicleId").toString()));
					terminalCommand.setCmdid(null);
					terminalCommandDao.insert(terminalCommand);
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
	public boolean deleteEnclosure(int enclosureId) {
		try {
			TerminalCommand terminalCommand = new TerminalCommand();
			String type = enclosureDao.getEnclosureByid(enclosureId).getEnclosureType();
			if (type.equals("circle"))// 圆形
			{
				terminalCommand.setCmdtype(34305);
			} else if (type.equals("rect"))// 矩形
			{
				terminalCommand.setCmdtype(34307);
			} else if (type.equals("polygon"))// 多边形
			{
				terminalCommand.setCmdtype(34309);
			} else if (type.equals("route"))// 线路
			{
				terminalCommand.setCmdtype(34311);
			}
			terminalCommand.setCmddata(String.valueOf(enclosureId));
			List<Map<String, Object>> list = enclosureBindDao.getByEnclosureid(enclosureId);
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					// 车牌号
					terminalCommand.setPlateno((String) map.get("plateNo"));
					// 终端SIM卡号
					terminalCommand.setSimno((String) map.get("simNo"));
					// 车辆id
					terminalCommand.setVehicleid(Integer.parseInt(map.get("vehicleId").toString()));
					terminalCommand.setCmdid(null);
					terminalCommandDao.insert(terminalCommand);
				}
			}
			enclosureBindDao.deleteEnclosureByid(enclosureId);
			enclosureDao.deleteEnclosure(enclosureId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}

	}
}
