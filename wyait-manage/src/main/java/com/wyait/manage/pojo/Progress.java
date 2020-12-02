package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * （进度表）
 */
public class Progress implements Serializable {
    /**
     * 进展ID
     */
    private String progressId;

    /**
     * 用户凭证
     */
    private String useridcode;

    /**
     * 进度查询  已完成部门 24、25
     */
    private String progressQuery;

    private static final long serialVersionUID = 1L;

    public String getProgressId() {
        return progressId;
    }

    public void setProgressId(String progressId) {
        this.progressId = progressId;
    }

    public String getUseridcode() {
        return useridcode;
    }

    public void setUseridcode(String useridcode) {
        this.useridcode = useridcode;
    }

    public String getProgressQuery() {
        return progressQuery;
    }

    public void setProgressQuery(String progressQuery) {
        this.progressQuery = progressQuery;
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
        Progress other = (Progress) that;
        return (this.getProgressId() == null ? other.getProgressId() == null : this.getProgressId().equals(other.getProgressId()))
            && (this.getUseridcode() == null ? other.getUseridcode() == null : this.getUseridcode().equals(other.getUseridcode()))
            && (this.getProgressQuery() == null ? other.getProgressQuery() == null : this.getProgressQuery().equals(other.getProgressQuery()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProgressId() == null) ? 0 : getProgressId().hashCode());
        result = prime * result + ((getUseridcode() == null) ? 0 : getUseridcode().hashCode());
        result = prime * result + ((getProgressQuery() == null) ? 0 : getProgressQuery().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", progressId=").append(progressId);
        sb.append(", useridcode=").append(useridcode);
        sb.append(", progressQuery=").append(progressQuery);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}