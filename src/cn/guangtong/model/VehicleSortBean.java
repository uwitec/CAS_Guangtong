package cn.guangtong.model;
/**
 * 车辆多条件搜索实体类
 * 
 * @author sutong
 *
 */
public class VehicleSortBean {
	
	private int aId;
	private int aType;
	private String simNo;// 设备ID
	private String cName; //所属车队
	private String vType; //车辆类型
	private String plateNo; //车牌号
	private String specType; //特别运输管理类型
	
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public int getaType() {
		return aType;
	}
	public void setaType(int aType) {
		this.aType = aType;
	}
	public String getSimNo() {
		return simNo;
	}
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getvType() {
		return vType;
	}
	public void setvType(String vType) {
		this.vType = vType;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getSpecType() {
		return specType;
	}
	public void setSpecType(String specType) {
		this.specType = specType;
	}
	public VehicleSortBean(int aId, int aType, String simNo, String cName,
			String vType, String plateNo, String specType) {
		super();
		this.aId = aId;
		this.aType = aType;
		this.simNo = simNo;
		this.cName = cName;
		this.vType = vType;
		this.plateNo = plateNo;
		this.specType = specType;
	}
	public VehicleSortBean() {
		super();
	}
	@Override
	public String toString() {
		return "VehicleSortBean [aId=" + aId + ", aType=" + aType + ", simNo="
				+ simNo + ", cName=" + cName + ", vType=" + vType
				+ ", plateNo=" + plateNo + ", specType=" + specType + "]";
	}
}
