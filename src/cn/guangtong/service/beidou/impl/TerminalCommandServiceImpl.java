package cn.guangtong.service.beidou.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.guangtong.dao.beidou.TerminalCommandDao;
import cn.guangtong.entity.beidou.CommandLog;
import cn.guangtong.entity.beidou.TerminalCommand;
import cn.guangtong.entity.beidou.Terminalparam;
import cn.guangtong.service.beidou.TerminalCommandService;
import cn.guangtong.utils.CommandLogPageBean;

@Service
public class TerminalCommandServiceImpl implements TerminalCommandService {

	@Autowired
	private TerminalCommandDao terminalCommandDao;

	@Override
	public boolean insert(TerminalCommand terminalCommand) {
		try {
			terminalCommandDao.insert(terminalCommand);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<CommandLog> commandLogOfTable(CommandLogPageBean pageBean) {
		List<CommandLog> list = terminalCommandDao.commandLogOfTable(pageBean);
		for (CommandLog ob : list) {
			String con = ob.getContent();
			String col = ob.getPlateColor();
			String tim = ob.getSendTime();
			String stu = ob.getStatus();
			if (con == null || con.equals("")) {
				con = "暂无内容";
				ob.setContent(con);
			}
			if (col == null || col.equals("")) {
				col = "暂无颜色";
				ob.setPlateColor(col);
			}
			if (tim == null || tim.equals("")) {
				tim = "0000-00-00 00:00:00";
				ob.setSendTime(tim);
			}
			if (stu == null || stu.equals("")) {
				stu = "暂无回执";
				ob.setStatus(stu);
			}
			if (stu.equals("Success")) {
				ob.setStatus("成功");
			} else if (stu.equals("Invalid")) {
				ob.setStatus("非法");
			} else if (stu.equals("New")) {
				ob.setStatus("新指令");
			} else if (stu.equals("Offline")) {
				ob.setStatus("离线");
			} else if (stu.equals("Processing")) {
				ob.setStatus("处理中");
			} else if (stu.equals("Failed")) {
				ob.setStatus("失败");
			} else if (stu.equals("NotSupport")) {
				ob.setStatus("不支持");
			}
		}
		return list;
	}

	@Override
	public List<CommandLog> commandLogOfTableExcel(CommandLogPageBean pageBean) {
		List<CommandLog> list = terminalCommandDao.commandLogOfTableExcel(pageBean);
		for (CommandLog ob : list) {
			String con = ob.getContent();
			String col = ob.getPlateColor();
			String tim = ob.getSendTime();
			String stu = ob.getStatus();
			if (con == null || con.equals("")) {
				con = "暂无内容";
				ob.setContent(con);
			}
			if (col == null || col.equals("")) {
				col = "暂无颜色";
				ob.setPlateColor(col);
			}
			if (tim == null || tim.equals("")) {
				tim = "0000-00-00 00:00:00";
				ob.setSendTime(tim);
			}
			if (stu == null || stu.equals("")) {
				stu = "暂无回执";
				ob.setStatus(stu);
			}
			if (stu.equals("Success")) {
				ob.setStatus("成功");
			} else if (stu.equals("Invalid")) {
				ob.setStatus("非法");
			} else if (stu.equals("New")) {
				ob.setStatus("新指令");
			} else if (stu.equals("Offline")) {
				ob.setStatus("离线");
			} else if (stu.equals("Processing")) {
				ob.setStatus("处理中");
			} else if (stu.equals("Failed")) {
				ob.setStatus("失败");
			} else if (stu.equals("NotSupport")) {
				ob.setStatus("不支持");
			}
		}
		return list;
	}

	@Override
	public int countOfLog(CommandLogPageBean pageBean) {
		return terminalCommandDao.countOfLog(pageBean);
	}

	@Override
	public List<Integer> insertCommand(TerminalCommand terminalCommand,String createDate,String[] plateNo, String[] simNo, String[] vehicleId) {
		List<Integer> list=new ArrayList<Integer>();
		try {
			if (plateNo.length > 0) {
				for (int i = 0; i < plateNo.length; i++) {
					// 车牌号
					terminalCommand.setPlateno(plateNo[i]);
					// 终端SIM卡号
					terminalCommand.setSimno(simNo[i]);
					// 车辆id
					int vehicleid = Integer.parseInt(vehicleId[i]);
					terminalCommand.setCreatedate(createDate);
					terminalCommand.setVehicleid(vehicleid);
					terminalCommand.setCmdid(null);
					terminalCommandDao.insert(terminalCommand);
					list.add(terminalCommand.getCmdid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return list;
	}

	@Override
	public List<Integer> insertCommandByterminal(String Command,String createDate, String[] plateNo, String[] simNo, String[] vehicleId) {
		List<Integer> list=new ArrayList<Integer>();
		try {
			TerminalCommand terminalCommand = new TerminalCommand();
			// 命令消息类型
			terminalCommand.setCmdtype(33027);
			terminalCommand.setCmddata(Command);
			terminalCommand.setCreatedate(createDate);
			if (plateNo.length > 0) {
				for (int i = 0; i < plateNo.length; i++) {
					// 车牌号
					terminalCommand.setPlateno(plateNo[i]);
					// 终端SIM卡号
					terminalCommand.setSimno(simNo[i]);
					// 车辆id
					int vehicleid = Integer.parseInt(vehicleId[i]);
					terminalCommand.setVehicleid(vehicleid);
					terminalCommand.setCmdid(null);
					terminalCommandDao.insert(terminalCommand);
					list.add(terminalCommand.getCmdid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return list;
	}

	@Override
	public List<Integer> selectCommandByterminal(TerminalCommand terminalCommand, String[] plateNo, String[] simNo, String[] vehicleId) {
		List<Integer> list=new ArrayList<Integer>();
		try {
			if (plateNo.length > 0) {
				for (int i = 0; i < plateNo.length; i++) {
					// 车牌号
					terminalCommand.setPlateno(plateNo[i]);
					// 终端SIM卡号
					terminalCommand.setSimno(simNo[i]);
					// 车辆id
					int vehicleid = Integer.parseInt(vehicleId[i]);
					terminalCommand.setCmdid(null);
					terminalCommand.setVehicleid(vehicleid);
					terminalCommandDao.insert(terminalCommand);
					list.add(terminalCommand.getCmdid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 就是这一句了，加上之后，如果抛了异常,会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return list;
	}

	@Override
	public List<CommandLog> commandLogOfTableByOther(CommandLogPageBean pageBean) {
		List<CommandLog> list = terminalCommandDao.commandLogOfTableByOther(pageBean);
		for (CommandLog ob : list) {
			String con = ob.getContent();
			String col = ob.getPlateColor();
			String tim = ob.getSendTime();
			String stu = ob.getStatus();
			if (con == null || con.equals("")) {
				con = "暂无内容";
				ob.setContent(con);
			}
			if (col == null || col.equals("")) {
				col = "暂无颜色";
				ob.setPlateColor(col);
			}
			if (tim == null || tim.equals("")) {
				tim = "0000-00-00 00:00:00";
				ob.setSendTime(tim);
			}
			if (stu == null || stu.equals("")) {
				stu = "暂无回执";
				ob.setStatus(stu);
			}
			if (stu.equals("Success")) {
				ob.setStatus("成功");
			} else if (stu.equals("Invalid")) {
				ob.setStatus("非法");
			} else if (stu.equals("New")) {
				ob.setStatus("新指令");
			} else if (stu.equals("Offline")) {
				ob.setStatus("离线");
			} else if (stu.equals("Processing")) {
				ob.setStatus("处理中");
			} else if (stu.equals("Failed")) {
				ob.setStatus("失败");
			} else if (stu.equals("NotSupport")) {
				ob.setStatus("不支持");
			}
		}
		return list;
	}

	@Override
	public List<CommandLog> commandLogOfTableExcelByOther(CommandLogPageBean pageBean) {
		List<CommandLog> list = terminalCommandDao.commandLogOfTableExcelByOther(pageBean);
		for (CommandLog ob : list) {
			String con = ob.getContent();
			String col = ob.getPlateColor();
			String tim = ob.getSendTime();
			String stu = ob.getStatus();
			if (con == null || con.equals("")) {
				con = "暂无内容";
				ob.setContent(con);
			}
			if (col == null || col.equals("")) {
				col = "暂无颜色";
				ob.setPlateColor(col);
			}
			if (tim == null || tim.equals("")) {
				tim = "0000-00-00 00:00:00";
				ob.setSendTime(tim);
			}
			if (stu == null || stu.equals("")) {
				stu = "暂无回执";
				ob.setStatus(stu);
			}
			if (stu.equals("Success")) {
				ob.setStatus("成功");
			} else if (stu.equals("Invalid")) {
				ob.setStatus("非法");
			} else if (stu.equals("New")) {
				ob.setStatus("新指令");
			} else if (stu.equals("Offline")) {
				ob.setStatus("离线");
			} else if (stu.equals("Processing")) {
				ob.setStatus("处理中");
			} else if (stu.equals("Failed")) {
				ob.setStatus("失败");
			} else if (stu.equals("NotSupport")) {
				ob.setStatus("不支持");
			}
		}
		return list;
	}

	@Override
	public int countOfLogByOther(CommandLogPageBean pageBean) {
		return terminalCommandDao.countOfLogByOther(pageBean);
	}

	@Override
	public List<TerminalCommand> getTerminalCommandInfo(String[] cmdid) {
		List<TerminalCommand> list = new ArrayList<TerminalCommand>();
		if (cmdid != null && cmdid.length > 0) {
			for (int i = 0; i < cmdid.length; i++) {
				TerminalCommand tCommand = terminalCommandDao.getTerminalCommandInfo(Integer.parseInt(cmdid[i]));
				if (tCommand.getCmdtype() == 34817 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0805");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 33282 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0001");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 33027 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0001");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 33028 && tCommand.getStatus().equals("Success")) {
					String terminalparam = this.getTerminalCommandInfoByComId(tCommand.getCmdid());
					tCommand.setReplyParameter(terminalparam);
					tCommand.setReplyId("0x0104");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 33029 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0001");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 34304 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0001");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 34306 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0001");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 34308 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0001");
					list.add(tCommand);
				} else if (tCommand.getCmdtype() == 34310 && tCommand.getStatus().equals("Success")) {
					tCommand.setReplyParameter("被应答的消息流水号:" + tCommand.getSn() + "," + "被应答的消息ID:" + tCommand.getCmdtype() + "," + "应答结果:成功/确认");
					tCommand.setReplyId("0x0001");
					list.add(tCommand);
				} else {
					list.add(tCommand);
				}
			}
		}
		return list;
	}

	@Override
	public String getTerminalCommandInfoByComId(int comId) {

		List<Terminalparam> list = terminalCommandDao.getTerminalCommandInfoByComId(comId);
		String s = null;
		for (Terminalparam terminalparam : list) {
			if (terminalparam.getCode().equals("0x0001")) {
				s += "终端心跳发送间隔:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0002")) {
				s += "TCP消息应答超时时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0003")) {
				s += "TCP消息重传次数:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0004")) {
				s += "UDP消息应答超时时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0005")) {
				s += "UDP消息重传次数:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0006")) {
				s += "SMS消息应答超时时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0007")) {
				s += "SMS消息重传次数:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0011")) {
				s += "主服务器无限通信拨号用户名:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0012")) {
				s += "主服务器无线通信拨号密码:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0013")) {
				s += "主服务器地址IP或域名:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0018")) {
				s += "服务器TCP端口:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0019")) {
				s += "服务器UDP端口:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0022")) {
				s += "驾驶员未登录汇报时间间隔:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0027")) {
				s += "休眠时汇报时间间隔:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0028")) {
				s += "紧急报警时汇报时间间隔:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x002D")) {
				s += "驾驶员未登录汇报距离间隔:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x002E")) {
				s += "休眠时汇报距离间隔:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x002F")) {
				s += "紧急报警时汇报距离间隔:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0030")) {
				s += "拐点补传角度:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0040")) {
				s += "监控平台电话号码:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0046")) {
				s += "每次最长通话时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0047")) {
				s += "当月最长通话时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0048")) {
				s += "监听电话号码:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0055")) {
				s += "最高速度:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0056")) {
				s += "超速持续时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0057")) {
				s += "连续驾驶时间门限:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0058")) {
				s += "当天累计驾驶时间门限:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0059")) {
				s += "最小休息时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x005A")) {
				s += "最长停车时间:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0070")) {
				s += "图像/视频质量:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0071")) {
				s += "亮度:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0072")) {
				s += "对比度:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0073")) {
				s += "饱和度:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0074")) {
				s += "色度:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0080")) {
				s += "车辆里程报表数:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0081")) {
				s += "车辆所在的省域ID:" + terminalparam.getValue();
			}
			if (terminalparam.getCode().equals("0x0082")) {
				s += "车辆所在的市域ID:" + terminalparam.getValue();
			}
		}
		return s;
	}
}
