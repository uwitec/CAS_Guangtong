package cn.guangtong.entity.cms;

public class AdminCommand {
	//用户ID
    private Integer adminId;
    //车牌号
    private Integer plateNo=1;
    //车辆颜色
    private Integer vColor=1;
    //发送时间
    private Integer sendTime=1;
    //命令ID
    private Integer cmdId=1;
    //命令内容
    private Integer cmdDate=1;
    //发送结果
    private Integer sendResult=1;
    //流水号
    private Integer sn=1;
    //确认时间
    private Integer confirmTime=1;
    //回复ID
    private Integer restore=1;
    //回复参数
    private Integer resData=1;
    //类型
    private Integer type=1;

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

    public Integer getvColor() {
        return vColor;
    }

    public void setvColor(Integer vColor) {
        this.vColor = vColor;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getCmdId() {
        return cmdId;
    }

    public void setCmdId(Integer cmdId) {
        this.cmdId = cmdId;
    }

    public Integer getCmdDate() {
        return cmdDate;
    }

    public void setCmdDate(Integer cmdDate) {
        this.cmdDate = cmdDate;
    }

    public Integer getSendResult() {
        return sendResult;
    }

    public void setSendResult(Integer sendResult) {
        this.sendResult = sendResult;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public Integer getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Integer confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getRestore() {
        return restore;
    }

    public void setRestore(Integer restore) {
        this.restore = restore;
    }

    public Integer getResData() {
        return resData;
    }

    public void setResData(Integer resData) {
        this.resData = resData;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}