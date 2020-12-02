package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 表单-值（表单值）
 */
public class FormValue implements Serializable {
    /**
     * id
     */
    private String formValueId;

    /**
     * 套餐ID
     */
    private String formValueComboId;

    /**
     * 表单列表ID
     */
    private String formFieldId;

    /**
     * 值
     */
    private String formValue;

    /**
     * 业务ID
     */
    private String formBusinessId;

    private static final long serialVersionUID = 1L;

    public String getFormValueId() {
        return formValueId;
    }

    public void setFormValueId(String formValueId) {
        this.formValueId = formValueId;
    }

    public String getFormValueComboId() {
        return formValueComboId;
    }

    public void setFormValueComboId(String formValueComboId) {
        this.formValueComboId = formValueComboId;
    }

    public String getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(String formFieldId) {
        this.formFieldId = formFieldId;
    }

    public String getFormValue() {
        return formValue;
    }

    public void setFormValue(String formValue) {
        this.formValue = formValue;
    }

    public String getFormBusinessId() {
        return formBusinessId;
    }

    public void setFormBusinessId(String formBusinessId) {
        this.formBusinessId = formBusinessId;
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
        FormValue other = (FormValue) that;
        return (this.getFormValueId() == null ? other.getFormValueId() == null : this.getFormValueId().equals(other.getFormValueId()))
            && (this.getFormValueComboId() == null ? other.getFormValueComboId() == null : this.getFormValueComboId().equals(other.getFormValueComboId()))
            && (this.getFormFieldId() == null ? other.getFormFieldId() == null : this.getFormFieldId().equals(other.getFormFieldId()))
            && (this.getFormValue() == null ? other.getFormValue() == null : this.getFormValue().equals(other.getFormValue()))
            && (this.getFormBusinessId() == null ? other.getFormBusinessId() == null : this.getFormBusinessId().equals(other.getFormBusinessId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFormValueId() == null) ? 0 : getFormValueId().hashCode());
        result = prime * result + ((getFormValueComboId() == null) ? 0 : getFormValueComboId().hashCode());
        result = prime * result + ((getFormFieldId() == null) ? 0 : getFormFieldId().hashCode());
        result = prime * result + ((getFormValue() == null) ? 0 : getFormValue().hashCode());
        result = prime * result + ((getFormBusinessId() == null) ? 0 : getFormBusinessId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", formValueId=").append(formValueId);
        sb.append(", formValueComboId=").append(formValueComboId);
        sb.append(", formFieldId=").append(formFieldId);
        sb.append(", formValue=").append(formValue);
        sb.append(", formBusinessId=").append(formBusinessId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}