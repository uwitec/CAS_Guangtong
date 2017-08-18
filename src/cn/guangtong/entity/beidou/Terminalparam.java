package cn.guangtong.entity.beidou;

import java.util.Date;
/**
 * 查询终端参数
 */
public class Terminalparam {
    private Integer paramid;

    private Date createdate;

    private Boolean deleted;

    private String owner;

    private String remark;

    private Integer tenantid;

    private String code;

    private Integer commandid;

    private String fieldtype;

    private String plateno;

    private Integer sn;

    private String simno;

    private String status;

    private Date updatedate;

    private String value;

    public Integer getParamid() {
        return paramid;
    }

    public void setParamid(Integer paramid) {
        this.paramid = paramid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTenantid() {
        return tenantid;
    }

    public void setTenantid(Integer tenantid) {
        this.tenantid = tenantid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getCommandid() {
        return commandid;
    }

    public void setCommandid(Integer commandid) {
        this.commandid = commandid;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype == null ? null : fieldtype.trim();
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno == null ? null : plateno.trim();
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getSimno() {
        return simno;
    }

    public void setSimno(String simno) {
        this.simno = simno == null ? null : simno.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}