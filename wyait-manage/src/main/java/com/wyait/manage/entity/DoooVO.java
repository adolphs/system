package com.wyait.manage.entity;

import com.wyait.manage.pojo.Dooo;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/9/7 16:45
 */
public class DoooVO {
    private String doooName;
    private Integer doooId;
    private String departmentName;
    private String departmentId;

    public DoooVO(String doooName, Integer doooId, String departmentName, String departmentId) {
        this.doooName = doooName;
        this.doooId = doooId;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    public DoooVO() {
    }

    public String getDoooName() {
        return doooName;
    }

    public void setDoooName(String doooName) {
        this.doooName = doooName;
    }

    public Integer getDoooId() {
        return doooId;
    }

    public void setDoooId(Integer doooId) {
        this.doooId = doooId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
