package com.wyait.manage.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 11:16
 */
public class Department {
    private Integer departmentId; //id
    private String departmentName; //部门名称
    private Integer type; //是否删除: 1、删除 0、正常
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime; //添加时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime; //修改时间

    public Department() {
    }

    public Department(Integer departmentId, String departmentName, Integer type, Date newTime, Date updateTime) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.type = type;
        this.newTime = newTime;
        this.updateTime = updateTime;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", type=" + type +
                ", newTime=" + newTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
