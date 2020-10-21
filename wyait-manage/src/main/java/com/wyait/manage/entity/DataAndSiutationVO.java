package com.wyait.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/8/3 16:56
 */
public class DataAndSiutationVO {
    private Integer dataId;
    private String dataName;
    private String blankName;
    private String blankUrl;
    private String templateName;
    private String templateUrl;
    private String remarks;
    private Integer dataType;
    private String dataForm;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;
    private Integer situationDetailsId;
    private String detailsDescribe;
    private Integer situationId;
    private Integer situationDescribe;
    private Integer type;

    public DataAndSiutationVO() {
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

    public String getBlankName() {
        return blankName;
    }

    public void setBlankName(String blankName) {
        this.blankName = blankName;
    }

    public String getBlankUrl() {
        return blankUrl;
    }

    public void setBlankUrl(String blankUrl) {
        this.blankUrl = blankUrl;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
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

    public Integer getSituationDetailsId() {
        return situationDetailsId;
    }

    public void setSituationDetailsId(Integer situationDetailsId) {
        this.situationDetailsId = situationDetailsId;
    }

    public String getDetailsDescribe() {
        return detailsDescribe;
    }

    public void setDetailsDescribe(String detailsDescribe) {
        this.detailsDescribe = detailsDescribe;
    }

    public Integer getSituationId() {
        return situationId;
    }

    public void setSituationId(Integer situationId) {
        this.situationId = situationId;
    }

    public Integer getSituationDescribe() {
        return situationDescribe;
    }

    public void setSituationDescribe(Integer situationDescribe) {
        this.situationDescribe = situationDescribe;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public DataAndSiutationVO(Integer dataId, String dataName, String blankName, String blankUrl, String templateName, String templateUrl, String remarks, Integer dataType, String dataForm, Date newTime, Integer situationDetailsId, String detailsDescribe, Integer situationId, Integer situationDescribe, Integer type) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.blankName = blankName;
        this.blankUrl = blankUrl;
        this.templateName = templateName;
        this.templateUrl = templateUrl;
        this.remarks = remarks;
        this.dataType = dataType;
        this.dataForm = dataForm;
        this.newTime = newTime;
        this.situationDetailsId = situationDetailsId;
        this.detailsDescribe = detailsDescribe;
        this.situationId = situationId;
        this.situationDescribe = situationDescribe;
        this.type = type;
    }
}
