package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 行政区部门组织
 */
public class Organ implements Serializable {
    /**
     * 机构编码
     */
    private String org_code;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 简称（省无此要素）
     */
    private String short_name;

    /**
     * 区划名称
     */
    private String division;

    /**
     * 区划编码
     */
    private String division_code;

    /**
     * 描述
     */
    private String description;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 排序
     */
    private String sort_order;

    /**
     * 统一社会信用代码
     */
    private String tongyi_code;

    public Organ(String org_code, String name, String short_name, String division, String division_code, String description, String phone, String address, String sort_order, String tongyi_code) {
        this.org_code = org_code;
        this.name = name;
        this.short_name = short_name;
        this.division = division;
        this.division_code = division_code;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.sort_order = sort_order;
        this.tongyi_code = tongyi_code;
    }

    public Organ() {
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivision_code() {
        return division_code;
    }

    public void setDivision_code(String division_code) {
        this.division_code = division_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public String getTongyi_code() {
        return tongyi_code;
    }

    public void setTongyi_code(String tongyi_code) {
        this.tongyi_code = tongyi_code;
    }
}