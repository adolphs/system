package com.wyait.manage.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 11:08
 */
public class Dooo {


    private Integer doooId;  //id
    private String doooName; //事项名称
    private String remark; //备注
    private String type; //状态: 0.正常 1.删除
    private Integer departmentId; //部门ID
    private String doooCondition; //受理条件
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime; //创建时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime; //修改时间
    private String departmentName; //修改时间
    private Integer approvalType; //审批状态：0.审批通过 1.审批驳回 2.退回
    private String putText; //提交说明
    private String approvalText; //审批说明

    public String getApprovalTextName() {
        return approvalTextName;
    }

    public void setApprovalTextName(String approvalTextName) {
        this.approvalTextName = approvalTextName;
    }

    private String approvalTextName; //审批说明

    public Dooo(Integer doooId, String doooName, String remark, String type, Integer departmentId, String doooCondition, Date newTime, Date updateTime, String departmentName, Integer approvalType, String putText, String approvalText) {
        this.doooId = doooId;
        this.doooName = doooName;
        this.remark = remark;
        this.type = type;
        this.departmentId = departmentId;
        this.doooCondition = doooCondition;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.departmentName = departmentName;
        this.approvalType = approvalType;
        this.putText = putText;
        this.approvalText = approvalText;
    }

    public Dooo() {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDoooCondition() {
        return doooCondition;
    }

    public void setDoooCondition(String doooCondition) {
        this.doooCondition = doooCondition;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
}
