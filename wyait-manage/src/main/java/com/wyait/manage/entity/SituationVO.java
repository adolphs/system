package com.wyait.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/28 16:27
 */
public class SituationVO {
    private Integer situationId;
    private Integer doooId;
    private String doooName;
    private Integer departmentId;
    private String departmentName;
    private String situationDescribe;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String situationDetailsDescribe;

    public SituationVO() {
    }

    public SituationVO(Integer situationId, Integer doooId, String doooName, Integer departmentId, String departmentName, String situationDescribe, Date newTime, Date updateTime, String situationDetailsDescribe) {
        this.situationId = situationId;
        this.doooId = doooId;
        this.doooName = doooName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.situationDescribe = situationDescribe;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.situationDetailsDescribe = situationDetailsDescribe;
    }

    public Integer getSituationId() {
        return situationId;
    }

    public void setSituationId(Integer situationId) {
        this.situationId = situationId;
    }

    public Integer getDoooId() {
        return doooId;
    }

    public void setDoooId(Integer doooId) {
        this.doooId = doooId;
    }

    public String getDoooName() {
        return doooName;
    }

    public void setDoooName(String doooName) {
        this.doooName = doooName;
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

    public String getSituationDescribe() {
        return situationDescribe;
    }

    public void setSituationDescribe(String situationDescribe) {
        this.situationDescribe = situationDescribe;
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

    public String getSituationDetailsDescribe() {
        return situationDetailsDescribe;
    }

    public void setSituationDetailsDescribe(String situationDetailsDescribe) {
        this.situationDetailsDescribe = situationDetailsDescribe;
    }
}
