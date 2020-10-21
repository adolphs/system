package com.wyait.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyait.manage.pojo.Combo;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/3 21:39
 */
public class ComboVo {
    private Integer id;
    private Integer doooId;
    private String comboName;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer doooCount;
    private Integer departmentCount;
    private Integer approvalType;
    private String putText;
    private String approvalText;
    private String approvalTypeName;

    public ComboVo() {
    }

    public ComboVo(Integer id, Integer doooId, String comboName, Date newTime, Date updateTime, Integer doooCount, Integer departmentCount, Integer approvalType, String putText, String approvalText) {
        this.id = id;
        this.doooId = doooId;
        this.comboName = comboName;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.doooCount = doooCount;
        this.departmentCount = departmentCount;
        this.approvalType = approvalType;
        this.putText = putText;
        this.approvalText = approvalText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoooId() {
        return doooId;
    }

    public void setDoooId(Integer doooId) {
        this.doooId = doooId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
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

    public Integer getDoooCount() {
        return doooCount;
    }

    public void setDoooCount(Integer doooCount) {
        this.doooCount = doooCount;
    }

    public Integer getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(Integer departmentCount) {
        this.departmentCount = departmentCount;
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

    public String getApprovalTypeName() {
        return approvalTypeName;
    }

    public void setApprovalTypeName(String approvalTypeName) {
        this.approvalTypeName = approvalTypeName;
    }
}
