package com.wyait.manage.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 业务流水进展表
 */
public class SerialNumber implements Serializable {
    /**
     * 第三方系统流水号id
     */
    private String serialNumberId;

    /**
     * 业务订单id
     */
    private String businessId;

    /**
     * 1、番禺区一站式审批系统  2、建设工程  3、一网通办
     */
    private Integer serialNumberType;

    /**
     * 流水号
     */
    private String serialNumberValue;

    /**
     * 1、已提交  2、已完成  3、已驳回
     */
    private Integer progress;

    private String remarks;

    /**
     * 创建时间
     */
    private Date newTime;

    /**
     *  查询时间
     */
    private Date selectTime;

    private static final long serialVersionUID = 1L;

    public String getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(String serialNumberId) {
        this.serialNumberId = serialNumberId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Integer getSerialNumberType() {
        return serialNumberType;
    }

    public void setSerialNumberType(Integer serialNumberType) {
        this.serialNumberType = serialNumberType;
    }

    public String getSerialNumberValue() {
        return serialNumberValue;
    }

    public void setSerialNumberValue(String serialNumberValue) {
        this.serialNumberValue = serialNumberValue;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        SerialNumber other = (SerialNumber) that;
        return (this.getSerialNumberId() == null ? other.getSerialNumberId() == null : this.getSerialNumberId().equals(other.getSerialNumberId()))
            && (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()))
            && (this.getSerialNumberType() == null ? other.getSerialNumberType() == null : this.getSerialNumberType().equals(other.getSerialNumberType()))
            && (this.getSerialNumberValue() == null ? other.getSerialNumberValue() == null : this.getSerialNumberValue().equals(other.getSerialNumberValue()))
            && (this.getProgress() == null ? other.getProgress() == null : this.getProgress().equals(other.getProgress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSerialNumberId() == null) ? 0 : getSerialNumberId().hashCode());
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        result = prime * result + ((getSerialNumberType() == null) ? 0 : getSerialNumberType().hashCode());
        result = prime * result + ((getSerialNumberValue() == null) ? 0 : getSerialNumberValue().hashCode());
        result = prime * result + ((getProgress() == null) ? 0 : getProgress().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialNumberId=").append(serialNumberId);
        sb.append(", businessId=").append(businessId);
        sb.append(", serialNumberType=").append(serialNumberType);
        sb.append(", serialNumberValue=").append(serialNumberValue);
        sb.append(", progress=").append(progress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}