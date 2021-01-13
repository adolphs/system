package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 建设工程字典表ID
 */
public class ConstructionDictionary implements Serializable {
    /**
     * 建设工程字典表ID
     */
    private String constructionDictionaryId;

    /**
     * 父表单id
     */
    private String pid;

    /**
     * 字典表名称
     */
    private String name;

    /**
     * 级别 1、主类  2、次类  3、阶段
     */
    private Integer level;

    private String value;

    private static final long serialVersionUID = 1L;

    public String getConstructionDictionaryId() {
        return constructionDictionaryId;
    }

    public void setConstructionDictionaryId(String constructionDictionaryId) {
        this.constructionDictionaryId = constructionDictionaryId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        ConstructionDictionary other = (ConstructionDictionary) that;
        return (this.getConstructionDictionaryId() == null ? other.getConstructionDictionaryId() == null : this.getConstructionDictionaryId().equals(other.getConstructionDictionaryId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getConstructionDictionaryId() == null) ? 0 : getConstructionDictionaryId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", constructionDictionaryId=").append(constructionDictionaryId);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", level=").append(level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}