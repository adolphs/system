package com.wyait.manage.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 情形表
 */
public class ServiceSituation implements Serializable {
    /**
     * id
     */
    private String situationId;

    /**
     * 事项id
     */
    private String doooId;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 情形描述
     */
    private String situationDescribe;

    /**
     * 情形级别 1.一级 2.二级 3.三级 4.四级 5.五级 6.六级 7.七级
     */
    private Integer situationLevel;

    /**
     * 上级ID
     */
    private String pid;

    /**
     * 类型：1.单选 2.多选
     */
    private Integer type;

    /**
     * 情形形态：0.最终情形 1.节点情形 2.材料情形
     */
    private Integer nodesType;

    /**
     * 创建时间
     */
    private Date newTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getSituationId() {
        return situationId;
    }

    public void setSituationId(String situationId) {
        this.situationId = situationId;
    }

    public String getDoooId() {
        return doooId;
    }

    public void setDoooId(String doooId) {
        this.doooId = doooId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSituationDescribe() {
        return situationDescribe;
    }

    public void setSituationDescribe(String situationDescribe) {
        this.situationDescribe = situationDescribe;
    }

    public Integer getSituationLevel() {
        return situationLevel;
    }

    public void setSituationLevel(Integer situationLevel) {
        this.situationLevel = situationLevel;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNodesType() {
        return nodesType;
    }

    public void setNodesType(Integer nodesType) {
        this.nodesType = nodesType;
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
        ServiceSituation other = (ServiceSituation) that;
        return (this.getSituationId() == null ? other.getSituationId() == null : this.getSituationId().equals(other.getSituationId()))
            && (this.getDoooId() == null ? other.getDoooId() == null : this.getDoooId().equals(other.getDoooId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getSituationDescribe() == null ? other.getSituationDescribe() == null : this.getSituationDescribe().equals(other.getSituationDescribe()))
            && (this.getSituationLevel() == null ? other.getSituationLevel() == null : this.getSituationLevel().equals(other.getSituationLevel()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getNodesType() == null ? other.getNodesType() == null : this.getNodesType().equals(other.getNodesType()))
            && (this.getNewTime() == null ? other.getNewTime() == null : this.getNewTime().equals(other.getNewTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSituationId() == null) ? 0 : getSituationId().hashCode());
        result = prime * result + ((getDoooId() == null) ? 0 : getDoooId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getSituationDescribe() == null) ? 0 : getSituationDescribe().hashCode());
        result = prime * result + ((getSituationLevel() == null) ? 0 : getSituationLevel().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getNodesType() == null) ? 0 : getNodesType().hashCode());
        result = prime * result + ((getNewTime() == null) ? 0 : getNewTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", situationId=").append(situationId);
        sb.append(", doooId=").append(doooId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", situationDescribe=").append(situationDescribe);
        sb.append(", situationLevel=").append(situationLevel);
        sb.append(", pid=").append(pid);
        sb.append(", type=").append(type);
        sb.append(", nodesType=").append(nodesType);
        sb.append(", newTime=").append(newTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}