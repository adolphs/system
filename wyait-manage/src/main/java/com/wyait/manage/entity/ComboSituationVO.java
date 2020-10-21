package com.wyait.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/8 15:33
 */
public class ComboSituationVO {
    private Integer id;
    private String situationDescribe; //情形名称
    private String detailsDescribe;  //情形描述
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;  //情形描述
    private Integer type;
    private Integer nodesType;


    public ComboSituationVO(Integer id, String situationDescribe, String detailsDescribe, Date newTime, Integer type, Integer nodesType) {
        this.id = id;
        this.situationDescribe = situationDescribe;
        this.detailsDescribe = detailsDescribe;
        this.newTime = newTime;
        this.type = type;
        this.nodesType = nodesType;
    }

    public ComboSituationVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSituationDescribe() {
        return situationDescribe;
    }

    public void setSituationDescribe(String situationDescribe) {
        this.situationDescribe = situationDescribe;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNodesType() {
        return nodesType;
    }

    public void setNodesType(Integer nodesType) {
        this.nodesType = nodesType;
    }
}
