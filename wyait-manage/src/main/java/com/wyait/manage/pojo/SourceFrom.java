package com.wyait.manage.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 表单主体
 */
public class SourceFrom implements Serializable {
    /**
     * id
     */
    private String formId;

    /**
     * 来源ID  目前分为事项与套餐
     */
    private Integer sourceId;

    /**
     * 1 套餐  2 事项
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date newTime;

    /**
     * 1 正常  2 删除
     */
    private Integer isStatus;

    private static final long serialVersionUID = 1L;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public Integer getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Integer isStatus) {
        this.isStatus = isStatus;
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
        SourceFrom other = (SourceFrom) that;
        return (this.getFormId() == null ? other.getFormId() == null : this.getFormId().equals(other.getFormId()))
            && (this.getSourceId() == null ? other.getSourceId() == null : this.getSourceId().equals(other.getSourceId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getNewTime() == null ? other.getNewTime() == null : this.getNewTime().equals(other.getNewTime()))
            && (this.getIsStatus() == null ? other.getIsStatus() == null : this.getIsStatus().equals(other.getIsStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFormId() == null) ? 0 : getFormId().hashCode());
        result = prime * result + ((getSourceId() == null) ? 0 : getSourceId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getNewTime() == null) ? 0 : getNewTime().hashCode());
        result = prime * result + ((getIsStatus() == null) ? 0 : getIsStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", formId=").append(formId);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", type=").append(type);
        sb.append(", newTime=").append(newTime);
        sb.append(", isStatus=").append(isStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}