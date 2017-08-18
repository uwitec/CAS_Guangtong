package cn.guangtong.entity.cms;

public class AdminCommon {
	//用户ID
    private Integer adminId;
    //车牌号
    private Integer plateNo=1;
    //终端设备号
    private Integer simNo=1;
    //车辆类型
    private Integer type=1;
    //车辆颜色
    private Integer vColor=1;
    //驾驶员
    private Integer driver=1;
    //速度和方向
    private Integer velDir=1;
    //位置信息
    private Integer localtion=1;
    //发送时间
    private Integer sendTime=1;
    //状态
    private Integer status=1;
    //警报
    private Integer alarm=1;
    //厂商ID
    private Integer coopId=1;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(Integer plateNo) {
        this.plateNo = plateNo;
    }

    public Integer getSimNo() {
        return simNo;
    }

    public void setSimNo(Integer simNo) {
        this.simNo = simNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getvColor() {
        return vColor;
    }

    public void setvColor(Integer vColor) {
        this.vColor = vColor;
    }

    public Integer getDriver() {
        return driver;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    public Integer getVelDir() {
        return velDir;
    }

    public void setVelDir(Integer velDir) {
        this.velDir = velDir;
    }

    public Integer getLocaltion() {
        return localtion;
    }

    public void setLocaltion(Integer localtion) {
        this.localtion = localtion;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAlarm() {
        return alarm;
    }

    public void setAlarm(Integer alarm) {
        this.alarm = alarm;
    }

    public Integer getCoopId() {
        return coopId;
    }

    public void setCoopId(Integer coopId) {
        this.coopId = coopId;
    }
}