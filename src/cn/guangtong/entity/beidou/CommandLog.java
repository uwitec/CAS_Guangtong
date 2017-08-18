package cn.guangtong.entity.beidou;

import cn.guangtong.utils.excel.ExportConfig;

public class CommandLog {

	@ExportConfig(value = "车牌号", width = 100)
	private String plateNo;			//车牌号
	@ExportConfig(value = "车辆颜色", width = 100)
	private String plateColor;		//车辆颜色
	@ExportConfig(value = "命令内容", width = 100)
	private String content;			//命令内容
	@ExportConfig(value = "发送时间", width = 100)
	private String sendTime;		//发送时间
	@ExportConfig(value = "命令回执", width = 100)
	private String status;			//命令回执
	private String count;			//记录数
	
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}
