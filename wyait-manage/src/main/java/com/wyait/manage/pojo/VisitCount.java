package com.wyait.manage.pojo;

import java.util.Date;

public class VisitCount {
    private String visitCountId;
    private Integer comboId;
    private Date visitTime;
    private String userIp;
    private Integer visitType;
    private Integer doooId;

    public VisitCount() {
    }

    public VisitCount(String visitCountId, Integer comboId, Date visitTime, String userIp, Integer visitType, Integer doooId) {
        this.visitCountId = visitCountId;
        this.comboId = comboId;
        this.visitTime = visitTime;
        this.userIp = userIp;
        this.visitType = visitType;
        this.doooId = doooId;
    }

    public String getVisitCountId() {
        return visitCountId;
    }

    public void setVisitCountId(String visitCountId) {
        this.visitCountId = visitCountId;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }

    public Integer getDoooId() {
        return doooId;
    }

    public void setDoooId(Integer doooId) {
        this.doooId = doooId;
    }
}
