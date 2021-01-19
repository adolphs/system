package com.wyait.manage.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * （业务表）
 */
public class UserBusiness implements Serializable {
    private String businessId;

    /**
     * 用户唯一识别码
     */
    private String useridcode;

    /**
     * 申请时间
     */
    private Date newTime;

    /**
     * 提交时间
     */
    private Date submit;

    /**
     * 涉及部门
     */
    private String situationIds;

    /**
     * 提交内容留底
     */
    private String formBackup;

    /**
     * 1 用户  2 法人
     */
    private String type;

    /**
     * 套餐id
     */
    private Integer comboId;

    /**
     * 地址id
     */
    private String addressId;

    /**
     * 全部状态 1、新建 2、已提交 3、完成  4、驳回
     */
    private Integer allType;

    private String departmentIds;

    private static final long serialVersionUID = 1L;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getUseridcode() {
        return useridcode;
    }

    public void setUseridcode(String useridcode) {
        this.useridcode = useridcode;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public Date getSubmit() {
        return submit;
    }

    public void setSubmit(Date submit) {
        this.submit = submit;
    }

    public String getSituationIds() {
        return situationIds;
    }

    public void setSituationIds(String situationIds) {
        this.situationIds = situationIds;
    }

    public String getFormBackup() {
        return formBackup;
    }

    public void setFormBackup(String formBackup) {
        this.formBackup = formBackup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Integer getAllType() {
        return allType;
    }

    public void setAllType(Integer allType) {
        this.allType = allType;
    }

    public String getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(String departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserBusiness other = (UserBusiness) that;
        return (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()))
            && (this.getUseridcode() == null ? other.getUseridcode() == null : this.getUseridcode().equals(other.getUseridcode()))
            && (this.getNewTime() == null ? other.getNewTime() == null : this.getNewTime().equals(other.getNewTime()))
            && (this.getSubmit() == null ? other.getSubmit() == null : this.getSubmit().equals(other.getSubmit()))
            && (this.getSituationIds() == null ? other.getSituationIds() == null : this.getSituationIds().equals(other.getSituationIds()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        result = prime * result + ((getUseridcode() == null) ? 0 : getUseridcode().hashCode());
        result = prime * result + ((getNewTime() == null) ? 0 : getNewTime().hashCode());
        result = prime * result + ((getSubmit() == null) ? 0 : getSubmit().hashCode());
        result = prime * result + ((getSituationIds() == null) ? 0 : getSituationIds().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", businessId=").append(businessId);
        sb.append(", useridcode=").append(useridcode);
        sb.append(", newTime=").append(newTime);
        sb.append(", submit=").append(submit);
        sb.append(", departmentIds=").append(situationIds);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}