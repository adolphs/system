package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class FormMain implements Serializable {
    /**
     * 表单主体
     */
    private String formMainId;

    /**
     * 姓名
     */
    private String formMainName;

    /**
     * 套餐id
     */
    private Integer formMainComboId;

    /**
     * 部门ID
     */
    private Integer departmentId;

    private String departmentName;

    private String formFieldOptions;

    private static final long serialVersionUID = 1L;

    public String getFormMainId() {
        return formMainId;
    }

    public void setFormMainId(String formMainId) {
        this.formMainId = formMainId;
    }

    public String getFormMainName() {
        return formMainName;
    }

    public void setFormMainName(String formMainName) {
        this.formMainName = formMainName;
    }

    public Integer getFormMainComboId() {
        return formMainComboId;
    }

    public void setFormMainComboId(Integer formMainComboId) {
        this.formMainComboId = formMainComboId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFormFieldOptions() {
        return formFieldOptions;
    }

    public void setFormFieldOptions(String formFieldOptions) {
        this.formFieldOptions = formFieldOptions;
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
        FormMain other = (FormMain) that;
        return (this.getFormMainId() == null ? other.getFormMainId() == null : this.getFormMainId().equals(other.getFormMainId()))
            && (this.getFormMainName() == null ? other.getFormMainName() == null : this.getFormMainName().equals(other.getFormMainName()))
            && (this.getFormMainComboId() == null ? other.getFormMainComboId() == null : this.getFormMainComboId().equals(other.getFormMainComboId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFormMainId() == null) ? 0 : getFormMainId().hashCode());
        result = prime * result + ((getFormMainName() == null) ? 0 : getFormMainName().hashCode());
        result = prime * result + ((getFormMainComboId() == null) ? 0 : getFormMainComboId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", formMainId=").append(formMainId);
        sb.append(", formMainName=").append(formMainName);
        sb.append(", formMainComboId=").append(formMainComboId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}