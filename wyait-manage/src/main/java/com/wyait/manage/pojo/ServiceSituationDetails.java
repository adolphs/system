package com.wyait.manage.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 情形详情表
 */
public class ServiceSituationDetails implements Serializable {
    /**
     * id
     */
    private String situationDetailsId;

    /**
     * 情形id
     */
    private String situationId;

    /**
     * 详情描述
     */
    private String detailsDescribe;

    /**
     * 创建时间
     */
    private Date newTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 类型 1. 材料  2.节点
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

    public String getSituationDetailsId() {
        return situationDetailsId;
    }

    public void setSituationDetailsId(String situationDetailsId) {
        this.situationDetailsId = situationDetailsId;
    }

    public String getSituationId() {
        return situationId;
    }

    public void setSituationId(String situationId) {
        this.situationId = situationId;
    }

    public String getDetailsDescribe() {
        return detailsDescribe;
    }

    public void setDetailsDescribe(String detailsDescribe) {
        this.detailsDescribe = detailsDescribe;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        ServiceSituationDetails other = (ServiceSituationDetails) that;
        return (this.getSituationDetailsId() == null ? other.getSituationDetailsId() == null : this.getSituationDetailsId().equals(other.getSituationDetailsId()))
            && (this.getSituationId() == null ? other.getSituationId() == null : this.getSituationId().equals(other.getSituationId()))
            && (this.getDetailsDescribe() == null ? other.getDetailsDescribe() == null : this.getDetailsDescribe().equals(other.getDetailsDescribe()))
            && (this.getNewTime() == null ? other.getNewTime() == null : this.getNewTime().equals(other.getNewTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSituationDetailsId() == null) ? 0 : getSituationDetailsId().hashCode());
        result = prime * result + ((getSituationId() == null) ? 0 : getSituationId().hashCode());
        result = prime * result + ((getDetailsDescribe() == null) ? 0 : getDetailsDescribe().hashCode());
        result = prime * result + ((getNewTime() == null) ? 0 : getNewTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", situationDetailsId=").append(situationDetailsId);
        sb.append(", situationId=").append(situationId);
        sb.append(", detailsDescribe=").append(detailsDescribe);
        sb.append(", newTime=").append(newTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}