/**
 * 
 */
package cn.guangtong.model;

/**
 * @author chenjunpeng
 * @date 2017年3月4日
 */
public class Permission {

	private boolean sysInfoManager;//系统信息管理 8
	private boolean transInfoManager;//运输信息管理 86
	private boolean ordersManager;// 订单管理系统  102 
	private boolean carsRealManager;// 车辆实时监控 108
	private boolean speTransManager;// 特别运输管理 131
	private boolean dataStatManager;// 数据统计分析 138
	private boolean noticesManager;// 公告通知发布 139
	
	private boolean xtsz;//系统设置 81
	private boolean lmgl;//栏目管理  115
	private boolean glygl;//管理员管理 135 
	private boolean appzxgl;//APP资讯管理 136
	private boolean ptrzck;//平台日志查看 137
	
	private boolean clgl;// 车辆管理 87
	private boolean jsygl;// 驾驶员管理 90
	private boolean jmqygl;// 加盟企业管理 91
	private boolean cllxgl;// 车辆类型管理 119
	private boolean czzdgl;// 车载终端管理 140
	private boolean khgl;// 客户管理141
	
	private boolean ddgl;// 订单管理 104
	private boolean gpdd;// 改派订单 105
	private boolean ycddcl;// 异常订单处理 106
	private boolean sjtxsq;// 司机提现申请 113
	
	private boolean jgwlxxzxt;// 集港物流信息子系统 160
	private boolean wxplsgylzxt;// 危险品零售供应链子系统 161
	private boolean ckpsythzxt;// 仓储配送一体化子系统 162
	private boolean llpsythzxt;// 冷链配送一体化子系统 163
	private boolean gxysxxhzxt;// 干线运输信息化子系统 164
	private boolean ctkcdcpszxt;// 长途客车底舱配送子系统 165
	
	private boolean sjbb;// 数据报表 142
	private boolean sjtj;// 数据统计 143

	private boolean fbgg;// 发布公告 173
	private boolean lsgg;// 历史公告 174
	private boolean fsts;// 发送推送 175
	private boolean lsts;// 历史推送 176
	
	public boolean isSysInfoManager() {
		return sysInfoManager;
	}
	public void setSysInfoManager(boolean sysInfoManager) {
		this.sysInfoManager = sysInfoManager;
	}
	public boolean isTransInfoManager() {
		return transInfoManager;
	}
	public void setTransInfoManager(boolean transInfoManager) {
		this.transInfoManager = transInfoManager;
	}
	public boolean isOrdersManager() {
		return ordersManager;
	}
	public void setOrdersManager(boolean ordersManager) {
		this.ordersManager = ordersManager;
	}
	public boolean isCarsRealManager() {
		return carsRealManager;
	}
	public void setCarsRealManager(boolean carsRealManager) {
		this.carsRealManager = carsRealManager;
	}
	public boolean isSpeTransManager() {
		return speTransManager;
	}
	public void setSpeTransManager(boolean speTransManager) {
		this.speTransManager = speTransManager;
	}
	public boolean isDataStatManager() {
		return dataStatManager;
	}
	public void setDataStatManager(boolean dataStatManager) {
		this.dataStatManager = dataStatManager;
	}
	public boolean isNoticesManager() {
		return noticesManager;
	}
	public void setNoticesManager(boolean noticesManager) {
		this.noticesManager = noticesManager;
	}
	public boolean isXtsz() {
		return xtsz;
	}
	public void setXtsz(boolean xtsz) {
		this.xtsz = xtsz;
	}
	public boolean isLmgl() {
		return lmgl;
	}
	public void setLmgl(boolean lmgl) {
		this.lmgl = lmgl;
	}
	public boolean isGlygl() {
		return glygl;
	}
	public void setGlygl(boolean glygl) {
		this.glygl = glygl;
	}
	public boolean isAppzxgl() {
		return appzxgl;
	}
	public void setAppzxgl(boolean appzxgl) {
		this.appzxgl = appzxgl;
	}
	public boolean isPtrzck() {
		return ptrzck;
	}
	public void setPtrzck(boolean ptrzck) {
		this.ptrzck = ptrzck;
	}
	public boolean isClgl() {
		return clgl;
	}
	public void setClgl(boolean clgl) {
		this.clgl = clgl;
	}
	public boolean isJsygl() {
		return jsygl;
	}
	public void setJsygl(boolean jsygl) {
		this.jsygl = jsygl;
	}
	public boolean isJmqygl() {
		return jmqygl;
	}
	public void setJmqygl(boolean jmqygl) {
		this.jmqygl = jmqygl;
	}
	public boolean isCllxgl() {
		return cllxgl;
	}
	public void setCllxgl(boolean cllxgl) {
		this.cllxgl = cllxgl;
	}
	public boolean isCzzdgl() {
		return czzdgl;
	}
	public void setCzzdgl(boolean czzdgl) {
		this.czzdgl = czzdgl;
	}
	public boolean isKhgl() {
		return khgl;
	}
	public void setKhgl(boolean khgl) {
		this.khgl = khgl;
	}
	public boolean isDdgl() {
		return ddgl;
	}
	public void setDdgl(boolean ddgl) {
		this.ddgl = ddgl;
	}
	public boolean isGpdd() {
		return gpdd;
	}
	public void setGpdd(boolean gpdd) {
		this.gpdd = gpdd;
	}
	public boolean isYcddcl() {
		return ycddcl;
	}
	public void setYcddcl(boolean ycddcl) {
		this.ycddcl = ycddcl;
	}
	public boolean isSjtxsq() {
		return sjtxsq;
	}
	public void setSjtxsq(boolean sjtxsq) {
		this.sjtxsq = sjtxsq;
	}
	public boolean isJgwlxxzxt() {
		return jgwlxxzxt;
	}
	public void setJgwlxxzxt(boolean jgwlxxzxt) {
		this.jgwlxxzxt = jgwlxxzxt;
	}
	public boolean isWxplsgylzxt() {
		return wxplsgylzxt;
	}
	public void setWxplsgylzxt(boolean wxplsgylzxt) {
		this.wxplsgylzxt = wxplsgylzxt;
	}
	public boolean isCkpsythzxt() {
		return ckpsythzxt;
	}
	public void setCkpsythzxt(boolean ckpsythzxt) {
		this.ckpsythzxt = ckpsythzxt;
	}
	public boolean isLlpsythzxt() {
		return llpsythzxt;
	}
	public void setLlpsythzxt(boolean llpsythzxt) {
		this.llpsythzxt = llpsythzxt;
	}
	public boolean isGxysxxhzxt() {
		return gxysxxhzxt;
	}
	public void setGxysxxhzxt(boolean gxysxxhzxt) {
		this.gxysxxhzxt = gxysxxhzxt;
	}
	public boolean isCtkcdcpszxt() {
		return ctkcdcpszxt;
	}
	public void setCtkcdcpszxt(boolean ctkcdcpszxt) {
		this.ctkcdcpszxt = ctkcdcpszxt;
	}
	public boolean isSjbb() {
		return sjbb;
	}
	public void setSjbb(boolean sjbb) {
		this.sjbb = sjbb;
	}
	public boolean isSjtj() {
		return sjtj;
	}
	public void setSjtj(boolean sjtj) {
		this.sjtj = sjtj;
	}
	public boolean isFbgg() {
		return fbgg;
	}
	public void setFbgg(boolean fbgg) {
		this.fbgg = fbgg;
	}
	public boolean isLsgg() {
		return lsgg;
	}
	public void setLsgg(boolean lsgg) {
		this.lsgg = lsgg;
	}
	public boolean isFsts() {
		return fsts;
	}
	public void setFsts(boolean fsts) {
		this.fsts = fsts;
	}
	public boolean isLsts() {
		return lsts;
	}
	public void setLsts(boolean lsts) {
		this.lsts = lsts;
	}
	
}
