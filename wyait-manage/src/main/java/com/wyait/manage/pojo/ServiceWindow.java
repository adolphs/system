package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class ServiceWindow implements Serializable {
    /**
     * 【新要素】窗口代码。标识属性。
     */
    private String window_code;

    /**
     * 窗口名称
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 办公时间
     */
    private String office_hour;

    /**
     * 【新要素】所属机构代码 
     */
    private String org_code;

    /**
     * 所属机构名称 
     */
    private String org_name;

    /**
     * 事项编码
     */
    private String carry_out_code;

    /**
     * 窗口地址
     */
    private String address;

    /**
     * 交通指引
     */
    private String traffic_guide;

    private static final long serialVersionUID = 1L;

    public String getWindow_code() {
        return window_code;
    }

    public void setWindow_code(String window_code) {
        this.window_code = window_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice_hour() {
        return office_hour;
    }

    public void setOffice_hour(String office_hour) {
        this.office_hour = office_hour;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getCarry_out_code() {
        return carry_out_code;
    }

    public void setCarry_out_code(String carry_out_code) {
        this.carry_out_code = carry_out_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTraffic_guide() {
        return traffic_guide;
    }

    public void setTraffic_guide(String traffic_guide) {
        this.traffic_guide = traffic_guide;
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
        ServiceWindow other = (ServiceWindow) that;
        return (this.getWindow_code() == null ? other.getWindow_code() == null : this.getWindow_code().equals(other.getWindow_code()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getOffice_hour() == null ? other.getOffice_hour() == null : this.getOffice_hour().equals(other.getOffice_hour()))
            && (this.getOrg_code() == null ? other.getOrg_code() == null : this.getOrg_code().equals(other.getOrg_code()))
            && (this.getOrg_name() == null ? other.getOrg_name() == null : this.getOrg_name().equals(other.getOrg_name()))
            && (this.getCarry_out_code() == null ? other.getCarry_out_code() == null : this.getCarry_out_code().equals(other.getCarry_out_code()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWindow_code() == null) ? 0 : getWindow_code().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getOffice_hour() == null) ? 0 : getOffice_hour().hashCode());
        result = prime * result + ((getOrg_code() == null) ? 0 : getOrg_code().hashCode());
        result = prime * result + ((getOrg_name() == null) ? 0 : getOrg_name().hashCode());
        result = prime * result + ((getCarry_out_code() == null) ? 0 : getCarry_out_code().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", window_code=").append(window_code);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", office_hour=").append(office_hour);
        sb.append(", org_code=").append(org_code);
        sb.append(", org_name=").append(org_name);
        sb.append(", carry_out_code=").append(carry_out_code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}