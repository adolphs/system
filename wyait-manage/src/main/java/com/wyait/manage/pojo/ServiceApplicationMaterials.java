package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 事项库  材料信息
 */
public class ServiceApplicationMaterials implements Serializable {
    /**
     * 材料id。标识属性。
     */
    private String materials_id;

    /**
     * 材料内部编码（唯一）
     */
    private String inner_code;

    /**
     * 材料编码
     */
    private String materials_code;

    /**
     * 材料名称
     */
    private String materials_name;

    /**
     * 复印件份数
     */
    private String copy;

    /**
     * 材料填报要求
     */
    private String filling_requirement;

    /**
     * 材料收件标准
     */
    private String standard_collection;

    /**
     * 原件份数
     */
    private String origin;

    /**
     * 电子件份数
     */
    private String elect;

    /**
     * 材料类型
     */
    private String materials_type;

    /**
     * 样表编码
     */
    private String sample_code;

    /**
     * 空表编码
     */
    private String empty_code;

    /**
     * 样表文件
     */
    private String sample_file_url;

    /**
     * 空表文件
     */
    private String empty_file_url;

    /**
     * 样表名称
     */
    private String sample_file_name;

    /**
     * 样表路径
     */
    private String empty_file_name;

    /**
     * 电子证照名称
     */
    private String electronic_license;

    /**
     * 电子证照目录编码
     */
    private String electronic_license_code;

    /**
     * 是否已关联电子证照
     */
    private String is_relate_license;

    /**
     * 是否免提交
     */
    private String submission_required;

    /**
     * 事项编码
     */
    private String carry_out_code;

    private static final long serialVersionUID = 1L;

    public String getMaterials_id() {
        return materials_id;
    }

    public void setMaterials_id(String materials_id) {
        this.materials_id = materials_id;
    }

    public String getInner_code() {
        return inner_code;
    }

    public void setInner_code(String inner_code) {
        this.inner_code = inner_code;
    }

    public String getMaterials_code() {
        return materials_code;
    }

    public void setMaterials_code(String materials_code) {
        this.materials_code = materials_code;
    }

    public String getMaterials_name() {
        return materials_name;
    }

    public void setMaterials_name(String materials_name) {
        this.materials_name = materials_name;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public String getFilling_requirement() {
        return filling_requirement;
    }

    public void setFilling_requirement(String filling_requirement) {
        this.filling_requirement = filling_requirement;
    }

    public String getStandard_collection() {
        return standard_collection;
    }

    public void setStandard_collection(String standard_collection) {
        this.standard_collection = standard_collection;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getElect() {
        return elect;
    }

    public void setElect(String elect) {
        this.elect = elect;
    }

    public String getMaterials_type() {
        return materials_type;
    }

    public void setMaterials_type(String materials_type) {
        this.materials_type = materials_type;
    }

    public String getSample_code() {
        return sample_code;
    }

    public void setSample_code(String sample_code) {
        this.sample_code = sample_code;
    }

    public String getEmpty_code() {
        return empty_code;
    }

    public void setEmpty_code(String empty_code) {
        this.empty_code = empty_code;
    }

    public String getSample_file_url() {
        return sample_file_url;
    }

    public void setSample_file_url(String sample_file_url) {
        this.sample_file_url = sample_file_url;
    }

    public String getEmpty_file_url() {
        return empty_file_url;
    }

    public void setEmpty_file_url(String empty_file_url) {
        this.empty_file_url = empty_file_url;
    }

    public String getSample_file_name() {
        return sample_file_name;
    }

    public void setSample_file_name(String sample_file_name) {
        this.sample_file_name = sample_file_name;
    }

    public String getEmpty_file_name() {
        return empty_file_name;
    }

    public void setEmpty_file_name(String empty_file_name) {
        this.empty_file_name = empty_file_name;
    }

    public String getElectronic_license() {
        return electronic_license;
    }

    public void setElectronic_license(String electronic_license) {
        this.electronic_license = electronic_license;
    }

    public String getElectronic_license_code() {
        return electronic_license_code;
    }

    public void setElectronic_license_code(String electronic_license_code) {
        this.electronic_license_code = electronic_license_code;
    }

    public String getIs_relate_license() {
        return is_relate_license;
    }

    public void setIs_relate_license(String is_relate_license) {
        this.is_relate_license = is_relate_license;
    }

    public String getSubmission_required() {
        return submission_required;
    }

    public void setSubmission_required(String submission_required) {
        this.submission_required = submission_required;
    }

    public String getCarry_out_code() {
        return carry_out_code;
    }

    public void setCarry_out_code(String carry_out_code) {
        this.carry_out_code = carry_out_code;
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
        ServiceApplicationMaterials other = (ServiceApplicationMaterials) that;
        return (this.getMaterials_id() == null ? other.getMaterials_id() == null : this.getMaterials_id().equals(other.getMaterials_id()))
            && (this.getInner_code() == null ? other.getInner_code() == null : this.getInner_code().equals(other.getInner_code()))
            && (this.getMaterials_code() == null ? other.getMaterials_code() == null : this.getMaterials_code().equals(other.getMaterials_code()))
            && (this.getMaterials_name() == null ? other.getMaterials_name() == null : this.getMaterials_name().equals(other.getMaterials_name()))
            && (this.getCopy() == null ? other.getCopy() == null : this.getCopy().equals(other.getCopy()))
            && (this.getFilling_requirement() == null ? other.getFilling_requirement() == null : this.getFilling_requirement().equals(other.getFilling_requirement()))
            && (this.getStandard_collection() == null ? other.getStandard_collection() == null : this.getStandard_collection().equals(other.getStandard_collection()))
            && (this.getOrigin() == null ? other.getOrigin() == null : this.getOrigin().equals(other.getOrigin()))
            && (this.getElect() == null ? other.getElect() == null : this.getElect().equals(other.getElect()))
            && (this.getMaterials_type() == null ? other.getMaterials_type() == null : this.getMaterials_type().equals(other.getMaterials_type()))
            && (this.getSample_code() == null ? other.getSample_code() == null : this.getSample_code().equals(other.getSample_code()))
            && (this.getEmpty_code() == null ? other.getEmpty_code() == null : this.getEmpty_code().equals(other.getEmpty_code()))
            && (this.getSample_file_url() == null ? other.getSample_file_url() == null : this.getSample_file_url().equals(other.getSample_file_url()))
            && (this.getEmpty_file_url() == null ? other.getEmpty_file_url() == null : this.getEmpty_file_url().equals(other.getEmpty_file_url()))
            && (this.getSample_file_name() == null ? other.getSample_file_name() == null : this.getSample_file_name().equals(other.getSample_file_name()))
            && (this.getEmpty_file_name() == null ? other.getEmpty_file_name() == null : this.getEmpty_file_name().equals(other.getEmpty_file_name()))
            && (this.getElectronic_license() == null ? other.getElectronic_license() == null : this.getElectronic_license().equals(other.getElectronic_license()))
            && (this.getElectronic_license_code() == null ? other.getElectronic_license_code() == null : this.getElectronic_license_code().equals(other.getElectronic_license_code()))
            && (this.getIs_relate_license() == null ? other.getIs_relate_license() == null : this.getIs_relate_license().equals(other.getIs_relate_license()))
            && (this.getSubmission_required() == null ? other.getSubmission_required() == null : this.getSubmission_required().equals(other.getSubmission_required()))
            && (this.getCarry_out_code() == null ? other.getCarry_out_code() == null : this.getCarry_out_code().equals(other.getCarry_out_code()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMaterials_id() == null) ? 0 : getMaterials_id().hashCode());
        result = prime * result + ((getInner_code() == null) ? 0 : getInner_code().hashCode());
        result = prime * result + ((getMaterials_code() == null) ? 0 : getMaterials_code().hashCode());
        result = prime * result + ((getMaterials_name() == null) ? 0 : getMaterials_name().hashCode());
        result = prime * result + ((getCopy() == null) ? 0 : getCopy().hashCode());
        result = prime * result + ((getFilling_requirement() == null) ? 0 : getFilling_requirement().hashCode());
        result = prime * result + ((getStandard_collection() == null) ? 0 : getStandard_collection().hashCode());
        result = prime * result + ((getOrigin() == null) ? 0 : getOrigin().hashCode());
        result = prime * result + ((getElect() == null) ? 0 : getElect().hashCode());
        result = prime * result + ((getMaterials_type() == null) ? 0 : getMaterials_type().hashCode());
        result = prime * result + ((getSample_code() == null) ? 0 : getSample_code().hashCode());
        result = prime * result + ((getEmpty_code() == null) ? 0 : getEmpty_code().hashCode());
        result = prime * result + ((getSample_file_url() == null) ? 0 : getSample_file_url().hashCode());
        result = prime * result + ((getEmpty_file_url() == null) ? 0 : getEmpty_file_url().hashCode());
        result = prime * result + ((getSample_file_name() == null) ? 0 : getSample_file_name().hashCode());
        result = prime * result + ((getEmpty_file_name() == null) ? 0 : getEmpty_file_name().hashCode());
        result = prime * result + ((getElectronic_license() == null) ? 0 : getElectronic_license().hashCode());
        result = prime * result + ((getElectronic_license_code() == null) ? 0 : getElectronic_license_code().hashCode());
        result = prime * result + ((getIs_relate_license() == null) ? 0 : getIs_relate_license().hashCode());
        result = prime * result + ((getSubmission_required() == null) ? 0 : getSubmission_required().hashCode());
        result = prime * result + ((getCarry_out_code() == null) ? 0 : getCarry_out_code().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", materials_id=").append(materials_id);
        sb.append(", inner_code=").append(inner_code);
        sb.append(", materials_code=").append(materials_code);
        sb.append(", materials_name=").append(materials_name);
        sb.append(", copy=").append(copy);
        sb.append(", filling_requirement=").append(filling_requirement);
        sb.append(", standard_collection=").append(standard_collection);
        sb.append(", origin=").append(origin);
        sb.append(", elect=").append(elect);
        sb.append(", materials_type=").append(materials_type);
        sb.append(", sample_code=").append(sample_code);
        sb.append(", empty_code=").append(empty_code);
        sb.append(", sample_file_url=").append(sample_file_url);
        sb.append(", empty_file_url=").append(empty_file_url);
        sb.append(", sample_file_name=").append(sample_file_name);
        sb.append(", empty_file_name=").append(empty_file_name);
        sb.append(", electronic_license=").append(electronic_license);
        sb.append(", electronic_license_code=").append(electronic_license_code);
        sb.append(", is_relate_license=").append(is_relate_license);
        sb.append(", submission_required=").append(submission_required);
        sb.append(", carry_out_code=").append(carry_out_code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}