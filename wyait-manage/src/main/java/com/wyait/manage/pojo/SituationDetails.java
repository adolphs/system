package com.wyait.manage.pojo;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/28 15:56
 */
public class SituationDetails {
    private Integer situationDetailsId;
    private Integer situationId;
    private String detailsDescribe;
    private Date newTime;
    private Date updateTime;
    private Integer type;
    private String remarks;

    public SituationDetails() {
    }

    public SituationDetails(Integer situationDetailsId, Integer situationId, String detailsDescribe, Date newTime, Date updateTime, Integer type, String remarks) {
        this.situationDetailsId = situationDetailsId;
        this.situationId = situationId;
        this.detailsDescribe = detailsDescribe;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.type = type;
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSituationDetailsId() {
        return situationDetailsId;
    }

    public void setSituationDetailsId(Integer situationDetailsId) {
        this.situationDetailsId = situationDetailsId;
    }

    public Integer getSituationId() {
        return situationId;
    }

    public void setSituationId(Integer situationId) {
        this.situationId = situationId;
    }

    public String getDetailsDescribe() {
        return detailsDescribe;
    }

    public void setDetailsDescribe(String detailsDescribe) {
        this.detailsDescribe = detailsDescribe;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

