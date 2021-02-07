package com.wyait.manage.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
public class ServiceLegalBasis implements Serializable {
    /**
     * 法规编码。标识属性。
     */
    private String basis_code;

    /**
     * 法规名称
     */
    private String name;

    /**
     * 法律文号
     */
    private String wh;

    /**
     * 依据分类
     */
    private String basis_type;

    /**
     * 颁布机关
     */
    private String office;

    /**
     * 实施时间
     */
    private Date carry_out_date;

    /**
     * 事项编码
     */
    private String carry_out_code;

    private static final long serialVersionUID = 1L;

    public String getBasis_code() {
        return basis_code;
    }

    public void setBasis_code(String basis_code) {
        this.basis_code = basis_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }

    public String getBasis_type() {
        return basis_type;
    }

    public void setBasis_type(String basis_type) {
        this.basis_type = basis_type;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Date getCarry_out_date() {
        return carry_out_date;
    }

    public void setCarry_out_date(Date carry_out_date) {
        this.carry_out_date = carry_out_date;
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
        ServiceLegalBasis other = (ServiceLegalBasis) that;
        return (this.getBasis_code() == null ? other.getBasis_code() == null : this.getBasis_code().equals(other.getBasis_code()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getWh() == null ? other.getWh() == null : this.getWh().equals(other.getWh()))
            && (this.getBasis_type() == null ? other.getBasis_type() == null : this.getBasis_type().equals(other.getBasis_type()))
            && (this.getOffice() == null ? other.getOffice() == null : this.getOffice().equals(other.getOffice()))
            && (this.getCarry_out_date() == null ? other.getCarry_out_date() == null : this.getCarry_out_date().equals(other.getCarry_out_date()))
            && (this.getCarry_out_code() == null ? other.getCarry_out_code() == null : this.getCarry_out_code().equals(other.getCarry_out_code()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBasis_code() == null) ? 0 : getBasis_code().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getWh() == null) ? 0 : getWh().hashCode());
        result = prime * result + ((getBasis_type() == null) ? 0 : getBasis_type().hashCode());
        result = prime * result + ((getOffice() == null) ? 0 : getOffice().hashCode());
        result = prime * result + ((getCarry_out_date() == null) ? 0 : getCarry_out_date().hashCode());
        result = prime * result + ((getCarry_out_code() == null) ? 0 : getCarry_out_code().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", basis_code=").append(basis_code);
        sb.append(", name=").append(name);
        sb.append(", wh=").append(wh);
        sb.append(", basis_type=").append(basis_type);
        sb.append(", office=").append(office);
        sb.append(", carry_out_date=").append(carry_out_date);
        sb.append(", carry_out_code=").append(carry_out_code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}