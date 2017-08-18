package cn.guangtong.utils;

import cn.guangtong.entity.beidou.MediaItem;

/**
 * 媒体信息分页查询pageBean
 * 
 * @author SunTo
 * 
 */
public class MediaItemPageBean extends PageBean<MediaItem> {
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 车牌号
	private String simNo;
	// 媒体信息类型 0为图片 2为视频
	private int mediaType = 0;

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

}
