package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 情形文件关联表
 */
public class FileSituation implements Serializable {
    /**
     * 文件id
     */
    private String fileId;

    /**
     * 套餐id
     */
    private String comboId;

    /**
     * 情形id
     */
    private String situationId;

    private static final long serialVersionUID = 1L;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getSituationId() {
        return situationId;
    }

    public void setSituationId(String situationId) {
        this.situationId = situationId;
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
        FileSituation other = (FileSituation) that;
        return (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getComboId() == null ? other.getComboId() == null : this.getComboId().equals(other.getComboId()))
            && (this.getSituationId() == null ? other.getSituationId() == null : this.getSituationId().equals(other.getSituationId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getComboId() == null) ? 0 : getComboId().hashCode());
        result = prime * result + ((getSituationId() == null) ? 0 : getSituationId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", comboId=").append(comboId);
        sb.append(", situationId=").append(situationId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}