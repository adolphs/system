package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * （评价表）
 */
public class Evaluation implements Serializable {
    /**
     * 评价id
     */
    private String evaluationId;

    /**
     * 用户唯一凭证
     */
    private String useridcode;

    /**
     * 评价等级  1星 2星 3星
     */
    private String evaluationLevel;

    /**
     * 评价内容（应该用不上）
     */
    private String evaluationContent;

    /**
     * 是否提交: 1. 已提交 2. 未提交
     */
    private Integer isUpload;

    private static final long serialVersionUID = 1L;

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getUseridcode() {
        return useridcode;
    }

    public void setUseridcode(String useridcode) {
        this.useridcode = useridcode;
    }

    public String getEvaluationLevel() {
        return evaluationLevel;
    }

    public void setEvaluationLevel(String evaluationLevel) {
        this.evaluationLevel = evaluationLevel;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
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
        Evaluation other = (Evaluation) that;
        return (this.getEvaluationId() == null ? other.getEvaluationId() == null : this.getEvaluationId().equals(other.getEvaluationId()))
            && (this.getUseridcode() == null ? other.getUseridcode() == null : this.getUseridcode().equals(other.getUseridcode()))
            && (this.getEvaluationLevel() == null ? other.getEvaluationLevel() == null : this.getEvaluationLevel().equals(other.getEvaluationLevel()))
            && (this.getEvaluationContent() == null ? other.getEvaluationContent() == null : this.getEvaluationContent().equals(other.getEvaluationContent()))
            && (this.getIsUpload() == null ? other.getIsUpload() == null : this.getIsUpload().equals(other.getIsUpload()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEvaluationId() == null) ? 0 : getEvaluationId().hashCode());
        result = prime * result + ((getUseridcode() == null) ? 0 : getUseridcode().hashCode());
        result = prime * result + ((getEvaluationLevel() == null) ? 0 : getEvaluationLevel().hashCode());
        result = prime * result + ((getEvaluationContent() == null) ? 0 : getEvaluationContent().hashCode());
        result = prime * result + ((getIsUpload() == null) ? 0 : getIsUpload().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", evaluationId=").append(evaluationId);
        sb.append(", useridcode=").append(useridcode);
        sb.append(", evaluationLevel=").append(evaluationLevel);
        sb.append(", evaluationContent=").append(evaluationContent);
        sb.append(", isUpload=").append(isUpload);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}