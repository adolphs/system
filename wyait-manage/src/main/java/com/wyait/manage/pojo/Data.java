package com.wyait.manage.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/DataEnding 10:06
 */
public class Data {
    private Integer dataId;
    private String dataName;
    private String blankUrl;
    private String templateUrl;
    private String remarks;
    private Integer dataType;
    private String dataForm;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String type;
    private String blankName;
    private String templateName;
    private String departmentIds;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Data() {
    }

    public String getBlankName() {
        return blankName;
    }

    public void setBlankName(String blankName) {
        this.blankName = blankName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }


    public String getBlankUrl() {
        return blankUrl;
    }

    public void setBlankUrl(String blankUrl) {
        this.blankUrl = blankUrl;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataForm() {
        return dataForm;
    }

    public void setDataForm(String dataForm) {
        this.dataForm = dataForm;
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

    public String getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(String departmentIds) {
        this.departmentIds = departmentIds;
    }

    public Data(Integer dataId, String dataName, String blankUrl, String templateUrl, String remarks, Integer dataType, String dataForm, Date newTime, Date updateTime, String type, String blankName, String templateName, String departmentIds) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.blankUrl = blankUrl;
        this.templateUrl = templateUrl;
        this.remarks = remarks;
        this.dataType = dataType;
        this.dataForm = dataForm;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.type = type;
        this.blankName = blankName;
        this.templateName = templateName;
        this.departmentIds = departmentIds;
    }
}
