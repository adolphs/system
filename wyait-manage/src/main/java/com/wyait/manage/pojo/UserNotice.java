package com.wyait.manage.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * (用户申办关联表)
 */
public class UserNotice implements Serializable {
    /**
     * 用户唯一凭证
     */
    private String useridcode;

    /**
     * 申办说明页面Id
     */
    private String noticeId;

    /**
     * 是否同意：1 同意 2不同意
     */
    private Integer isType;

    /**
     * 签订时间
     */
    private Date newTime;

    private static final long serialVersionUID = 1L;

    public String getUseridcode() {
        return useridcode;
    }

    public void setUseridcode(String useridcode) {
        this.useridcode = useridcode;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getIsType() {
        return isType;
    }

    public void setIsType(Integer isType) {
        this.isType = isType;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
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
        UserNotice other = (UserNotice) that;
        return (this.getUseridcode() == null ? other.getUseridcode() == null : this.getUseridcode().equals(other.getUseridcode()))
            && (this.getNoticeId() == null ? other.getNoticeId() == null : this.getNoticeId().equals(other.getNoticeId()))
            && (this.getIsType() == null ? other.getIsType() == null : this.getIsType().equals(other.getIsType()))
            && (this.getNewTime() == null ? other.getNewTime() == null : this.getNewTime().equals(other.getNewTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUseridcode() == null) ? 0 : getUseridcode().hashCode());
        result = prime * result + ((getNoticeId() == null) ? 0 : getNoticeId().hashCode());
        result = prime * result + ((getIsType() == null) ? 0 : getIsType().hashCode());
        result = prime * result + ((getNewTime() == null) ? 0 : getNewTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", useridcode=").append(useridcode);
        sb.append(", noticeId=").append(noticeId);
        sb.append(", isType=").append(isType);
        sb.append(", newTime=").append(newTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}