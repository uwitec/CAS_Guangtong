package cn.guangtong.entity.beidou;

/**
 * 道路限速实体类
 * 
 * @ClassName:RoadSpeedLimit
 */
public class RoadSpeedLimit {
	// 道路限速id
	private int id;
	// 道路名称
	private String roadName;
	// 道路限制速度
	private Double speedLimit;
	// 道路限速等级
	private String roadGrade;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the roadName
	 */
	public String getRoadName() {
		return roadName;
	}
	/**
	 * @param roadName the roadName to set
	 */
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	/**
	 * @return the speedLimit
	 */
	public Double getSpeedLimit() {
		return speedLimit;
	}
	/**
	 * @param speedLimit the speedLimit to set
	 */
	public void setSpeedLimit(Double speedLimit) {
		this.speedLimit = speedLimit;
	}
	/**
	 * @return the roadGrade
	 */
	public String getRoadGrade() {
		return roadGrade;
	}
	/**
	 * @param roadGrade the roadGrade to set
	 */
	public void setRoadGrade(String roadGrade) {
		this.roadGrade = roadGrade;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
