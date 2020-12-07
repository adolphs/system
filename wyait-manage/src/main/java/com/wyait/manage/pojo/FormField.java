package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 表单列表（表单项）


 */
public class FormField implements Serializable {
    /**
     * ID
     */
    private String formFieldId;

    /**
     * 表单内的name的值 如：nama=form_field_name
     */
    private String formFieldName;

    /**
     * 名称内容  列入nama实际是姓名
     */
    private String formFieldNameValue;

    /**
     * 类型：0=输入框 1=下拉 2=单选 3=多选 4=文本域 5上传
     */
    private String formFieldType;

    /**
     * 内容：如果是下拉，则是下拉内容，如果是单选、多选则根据情况进行处理
     */
    private String formFieldContent;

    /**
     * 套餐ID
     */
    private Integer formFieldComboId;

    /**
     * 关联相关选项
     */
    private String formFieldOptions;

    /**
     * 是否是基础项  1、是 2、否
     */
    private Integer formFieldIsBasis;

    /**
     * 注释
     */
    private Integer formFieldAnnotation;

    private static final long serialVersionUID = 1L;

    public String getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(String formFieldId) {
        this.formFieldId = formFieldId;
    }

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

    public String getFormFieldNameValue() {
        return formFieldNameValue;
    }

    public void setFormFieldNameValue(String formFieldNameValue) {
        this.formFieldNameValue = formFieldNameValue;
    }

    public String getFormFieldType() {
        return formFieldType;
    }

    public void setFormFieldType(String formFieldType) {
        this.formFieldType = formFieldType;
    }

    public String getFormFieldContent() {
        return formFieldContent;
    }

    public void setFormFieldContent(String formFieldContent) {
        this.formFieldContent = formFieldContent;
    }

    public Integer getFormFieldComboId() {
        return formFieldComboId;
    }

    public void setFormFieldComboId(Integer formFieldComboId) {
        this.formFieldComboId = formFieldComboId;
    }

    public String getFormFieldOptions() {
        return formFieldOptions;
    }

    public void setFormFieldOptions(String formFieldOptions) {
        this.formFieldOptions = formFieldOptions;
    }

    public Integer getFormFieldIsBasis() {
        return formFieldIsBasis;
    }

    public void setFormFieldIsBasis(Integer formFieldIsBasis) {
        this.formFieldIsBasis = formFieldIsBasis;
    }

    public Integer getFormFieldAnnotation() {
        return formFieldAnnotation;
    }

    public void setFormFieldAnnotation(Integer formFieldAnnotation) {
        this.formFieldAnnotation = formFieldAnnotation;
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
        FormField other = (FormField) that;
        return (this.getFormFieldId() == null ? other.getFormFieldId() == null : this.getFormFieldId().equals(other.getFormFieldId()))
            && (this.getFormFieldName() == null ? other.getFormFieldName() == null : this.getFormFieldName().equals(other.getFormFieldName()))
            && (this.getFormFieldNameValue() == null ? other.getFormFieldNameValue() == null : this.getFormFieldNameValue().equals(other.getFormFieldNameValue()))
            && (this.getFormFieldType() == null ? other.getFormFieldType() == null : this.getFormFieldType().equals(other.getFormFieldType()))
            && (this.getFormFieldContent() == null ? other.getFormFieldContent() == null : this.getFormFieldContent().equals(other.getFormFieldContent()))
            && (this.getFormFieldComboId() == null ? other.getFormFieldComboId() == null : this.getFormFieldComboId().equals(other.getFormFieldComboId()))
            && (this.getFormFieldOptions() == null ? other.getFormFieldOptions() == null : this.getFormFieldOptions().equals(other.getFormFieldOptions()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFormFieldId() == null) ? 0 : getFormFieldId().hashCode());
        result = prime * result + ((getFormFieldName() == null) ? 0 : getFormFieldName().hashCode());
        result = prime * result + ((getFormFieldNameValue() == null) ? 0 : getFormFieldNameValue().hashCode());
        result = prime * result + ((getFormFieldType() == null) ? 0 : getFormFieldType().hashCode());
        result = prime * result + ((getFormFieldContent() == null) ? 0 : getFormFieldContent().hashCode());
        result = prime * result + ((getFormFieldComboId() == null) ? 0 : getFormFieldComboId().hashCode());
        result = prime * result + ((getFormFieldOptions() == null) ? 0 : getFormFieldOptions().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", formFieldId=").append(formFieldId);
        sb.append(", formFieldName=").append(formFieldName);
        sb.append(", formFieldNameValue=").append(formFieldNameValue);
        sb.append(", formFieldType=").append(formFieldType);
        sb.append(", formFieldContent=").append(formFieldContent);
        sb.append(", formFieldComboId=").append(formFieldComboId);
        sb.append(", formFieldOptions=").append(formFieldOptions);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}