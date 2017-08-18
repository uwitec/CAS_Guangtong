package cn.guangtong.service.beidou.impl;

import java.util.List;

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
import cn.guangtong.entity.beidou.Linesegment;
import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.service.beidou.LinesegmentService;
import cn.guangtong.utils.FormatDateUtils;

@Service
@Transactional
public class LinesegmentServiceImpl implements LinesegmentService {

	@Autowired
	private LinesegmentDao linesegmentDao;

	@Autowired
	private EnclosureDao enclosureDao;

	@Autowired
	EnclosureBindDao enclosureBindDao;

	@Autowired
	TerminalCommandDao terminalCommandDao;

	@Override
	public boolean insertSelective(Enclosure enclosure, String[] vehArr, String[] plateNo, String[] simNo, String[] vehicleId) {
		try {
			enclosureDao.insert(enclosure);
			int enclosureId = enclosure.getEnclosureId();
			TerminalCommand terminalCommand = new TerminalCommand();
			String type = enclosure.getEnclosureType();
			System.out.println(type);

			terminalCommand.setCmdtype(34310);
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
					// 绑定对象
					EnclosureBinding enBinding = new EnclosureBinding();
					enBinding.setEnclosureid(enclosureId);
					enBinding.setVehicleid(vehicleid);
					terminalCommand.setCmdid(null);
					terminalCommandDao.insert(terminalCommand);
					enclosureBindDao.insertSelective(enBinding);
				}
			}
			for (String string : vehArr) {
				Linesegment linesegment = new Linesegment();
				// 名称
				linesegment.setName(enclosure.getName());
				// 所属区域Id
				linesegment.setEnclosureid(enclosureId);

				// 路段宽度,单位为米（m），路段为该拐点到下一拐点
				linesegment.setLinewidth(20);

				// 根据时间
				linesegment.setBytime(enclosure.getByTime());
				if (enclosure.getByTime() == 1) {
					// 路段行驶过长阈值,单位为秒（s），若路段属性0位为0则没有该字段
					linesegment.setMaxtimelimit(FormatDateUtils.stringToDate(enclosure.getEndDate()).getDate());
					// 路段行驶不足阈值,单位为秒（s），若路段属性0位为0则没有该字段
					linesegment.setMintimelimit(FormatDateUtils.stringToDate(enclosure.getStartDate()).getDate());
				}

				// 限速
				linesegment.setLimitspeed(enclosure.getLimitSpeed());
				if (enclosure.getLimitSpeed() == 1) {
					// 路段最高速度,单位为公里每小时（km/h），若路段属性1位为0则没有该字段
					linesegment.setMaxspeed(enclosure.getMaxSpeed().intValue());
					// 路段超速持续时间,单位为秒（s），若路段属性1位为0则没有该字段
					linesegment.setOverspeedtime(enclosure.getDelay());
				}

				// 行驶时间限制
				int isTime = enclosure.getIsTime();
				if (isTime == 1) {
					linesegment.setMaxtimelimit(enclosure.getMaxtimelimit());
					linesegment.setMintimelimit(enclosure.getMintimelimit());
				}

				String[] str = string.split(";");
				// 经度
				linesegment.setLatitude1(Double.valueOf(str[0]));
				// 纬度
				linesegment.setLongitude1(Double.valueOf(str[1]));
				linesegmentDao.insertSelective(linesegment);
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
	public List<Enclosure> getLinesegmentServiceAll() {
		List<Enclosure> enclosureList = enclosureDao.getLinesegmentServiceAll();
		for (int i = 0; i < enclosureList.size(); i++) {
			Enclosure enclosure = enclosureList.get(i);
			List<Linesegment> data = linesegmentDao.getLinesegmentByEnclosureId(enclosure.getEnclosureId());
			if (data.size() > 0) {
				enclosure.setLinesegments(data);
			} else {
				enclosureList.remove(i);
			}
		}
		return enclosureList;
	}

	@Override
	public List<Enclosure> getLinesegmentkeyPointAll() {
		List<Enclosure> enclosureList = enclosureDao.getLinesegmentkeyPointAll();
		for (int i = 0; i < enclosureList.size(); i++) {
			Enclosure enclosure = enclosureList.get(i);
			List<Linesegment> data = linesegmentDao.getLinesegmentByEnclosureId(enclosure.getEnclosureId());
			if (data.size() > 0) {
				enclosure.setLinesegments(data);
			} else {
				enclosureList.remove(i);
			}
		}
		return enclosureList;
	}

}
