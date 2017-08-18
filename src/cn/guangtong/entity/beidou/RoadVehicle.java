package cn.guangtong.entity.beidou;

/**
 * @ClassName:RoadVehicle
 */
public class RoadVehicle {
	private int id;
	// 道路id
	private int roadId;
	// simno卡号
	private String simNo;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the roadId
	 */
	public int getRoadId() {
		return roadId;
	}

	/**
	 * @param roadId
	 *            the roadId to set
	 */
	public void setRoadId(int roadId) {
		this.roadId = roadId;
	}

	/**
	 * @return the simNo
	 */
	public String getSimNo() {
		return simNo;
	}

	/**
	 * @param simNo
	 *            the simNo to set
	 */
	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

}
