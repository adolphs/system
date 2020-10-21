package com.wyait.manage.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/28 15:54
 */
public class Situation {
    private Integer situationId;
    private Integer doooId;
    private Integer departmentId;
    private String situationDescribe;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer situationLevel;  //情形级别 1.一级 2.二级 3.三级 4.四级 5.五级 6.六级 7.七级
    private Integer type;   // 类型：1.单选 2.多选
    private String situationDetailsDescribe;
    private Integer nodesType;
    private Integer pid;

    public Situation() {
    }

    public Situation(Integer situationId, Integer doooId, Integer departmentId, String situationDescribe, Date newTime, Date updateTime, Integer situationLevel, Integer type, String situationDetailsDescribe, Integer nodesType, Integer pid) {
        this.situationId = situationId;
        this.doooId = doooId;
        this.departmentId = departmentId;
        this.situationDescribe = situationDescribe;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.situationLevel = situationLevel;
        this.type = type;
        this.situationDetailsDescribe = situationDetailsDescribe;
        this.nodesType = nodesType;
        this.pid = pid;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    public Integer getSituationLevel() {
        return situationLevel;
    }

    public void setSituationLevel(Integer situationLevel) {
        this.situationLevel = situationLevel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSituationDetailsDescribe() {
        return situationDetailsDescribe;
    }

    public void setSituationDetailsDescribe(String situationDetailsDescribe) {
        this.situationDetailsDescribe = situationDetailsDescribe;
    }

    public Integer getNodesType() {
        return nodesType;
    }

    public void setNodesType(Integer nodesType) {
        this.nodesType = nodesType;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
