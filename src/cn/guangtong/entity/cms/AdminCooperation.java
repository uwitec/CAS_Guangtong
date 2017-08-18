package cn.guangtong.entity.cms;

public class AdminCooperation {
    private Integer id;

    private Integer adminid;

    private String cooperationid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getCooperationid() {
        return cooperationid;
    }

    public void setCooperationid(String cooperationid) {
        this.cooperationid = cooperationid == null ? null : cooperationid.trim();
    }
}