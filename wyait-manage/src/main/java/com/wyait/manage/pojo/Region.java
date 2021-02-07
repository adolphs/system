package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 广州市下级区域编码
 */
public class Region implements Serializable {
    /**
     * 区划编码
     */
    private String code;

    /**
     * 父节点编码
     */
    private String parentCode;

    /**
     * 父节点名称
     */
    private String parentName;

    /**
     * 排序（省无此要素）
     */
    private String sortOrder;

    /**
     * 简称（省无此要素）
     */
    private String shortCode;

    /**
     * 名称
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Region other = (Region) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getParentCode() == null ? other.getParentCode() == null : this.getParentCode().equals(other.getParentCode()))
            && (this.getParentName() == null ? other.getParentName() == null : this.getParentName().equals(other.getParentName()))
            && (this.getSortOrder() == null ? other.getSortOrder() == null : this.getSortOrder().equals(other.getSortOrder()))
            && (this.getShortCode() == null ? other.getShortCode() == null : this.getShortCode().equals(other.getShortCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getParentCode() == null) ? 0 : getParentCode().hashCode());
        result = prime * result + ((getParentName() == null) ? 0 : getParentName().hashCode());
        result = prime * result + ((getSortOrder() == null) ? 0 : getSortOrder().hashCode());
        result = prime * result + ((getShortCode() == null) ? 0 : getShortCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", parentName=").append(parentName);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", shortCode=").append(shortCode);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}