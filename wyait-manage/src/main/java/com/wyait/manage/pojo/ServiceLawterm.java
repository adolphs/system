package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 事项库 法律实体
 */
public class ServiceLawterm implements Serializable {
    /**
     * 法规名称
     */
    private String term_name;

    /**
     * 排序号
     */
    private String sort_num;

    /**
     * 法规编码。标识属性。
     */
    private String basis_code;

    /**
     * 法规内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public String getTerm_name() {
        return term_name;
    }

    public void setTerm_name(String term_name) {
        this.term_name = term_name;
    }

    public String getSort_num() {
        return sort_num;
    }

    public void setSort_num(String sort_num) {
        this.sort_num = sort_num;
    }

    public String getBasis_code() {
        return basis_code;
    }

    public void setBasis_code(String basis_code) {
        this.basis_code = basis_code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        ServiceLawterm other = (ServiceLawterm) that;
        return (this.getTerm_name() == null ? other.getTerm_name() == null : this.getTerm_name().equals(other.getTerm_name()))
            && (this.getSort_num() == null ? other.getSort_num() == null : this.getSort_num().equals(other.getSort_num()))
            && (this.getBasis_code() == null ? other.getBasis_code() == null : this.getBasis_code().equals(other.getBasis_code()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTerm_name() == null) ? 0 : getTerm_name().hashCode());
        result = prime * result + ((getSort_num() == null) ? 0 : getSort_num().hashCode());
        result = prime * result + ((getBasis_code() == null) ? 0 : getBasis_code().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", term_name=").append(term_name);
        sb.append(", sort_num=").append(sort_num);
        sb.append(", basis_code=").append(basis_code);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}