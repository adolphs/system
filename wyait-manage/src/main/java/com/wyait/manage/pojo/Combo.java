package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/3 20:55
 */
@TableName("combo")
public class Combo extends Model<Combo> {
    private Integer id;
    private String comboName;
    private Integer doooId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer approvalType;
    private String putText;
    private String approvalText;
    private String type;
    private String flowChartUrl;

    public Combo() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Combo(Integer id, String comboName, Integer doooId, Date newTime, Date updateTime, Integer approvalType, String putText, String approvalText, String type, String flowChartUrl) {
        this.id = id;
        this.comboName = comboName;
        this.doooId = doooId;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.approvalType = approvalType;
        this.putText = putText;
        this.approvalText = approvalText;
        this.type = type;
        this.flowChartUrl = flowChartUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public Integer getDoooId() {
        return doooId;
    }

    public void setDoooId(Integer doooId) {
        this.doooId = doooId;
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

    public Integer getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Integer approvalType) {
        this.approvalType = approvalType;
    }

    public String getPutText() {
        return putText;
    }

    public void setPutText(String putText) {
        this.putText = putText;
    }

    public String getApprovalText() {
        return approvalText;
    }

    public void setApprovalText(String approvalText) {
        this.approvalText = approvalText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlowChartUrl() {
        return flowChartUrl;
    }

    public void setFlowChartUrl(String flowChartUrl) {
        this.flowChartUrl = flowChartUrl;
    }
}
