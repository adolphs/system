package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wyait.manage.entity.ComboSituationVOTOW;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/8 15:23
 */
@TableName("combo_situation_details")
public class ComboSituationDetails{
    private Integer id;
    private String detailsDescribe;
    private Integer comboSituationId;
    private Date newTime;
    private Date updateTime;
    private Integer type;
    private String remarks;
    private List<ComboSituationVOTOW> comboSituationVOTOWS;

    public ComboSituationDetails(Integer id, String detailsDescribe, Integer comboSituationId, Date newTime, Date updateTime, Integer type, String remarks, List<ComboSituationVOTOW> comboSituationVOTOWS) {
        this.id = id;
        this.detailsDescribe = detailsDescribe;
        this.comboSituationId = comboSituationId;
        this.newTime = newTime;
        this.updateTime = updateTime;
        this.type = type;
        this.remarks = remarks;
        this.comboSituationVOTOWS = comboSituationVOTOWS;
    }

    public ComboSituationDetails() {
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetailsDescribe() {
        return detailsDescribe;
    }

    public void setDetailsDescribe(String detailsDescribe) {
        this.detailsDescribe = detailsDescribe;
    }

    public Integer getComboSituationId() {
        return comboSituationId;
    }

    public void setComboSituationId(Integer comboSituationId) {
        this.comboSituationId = comboSituationId;
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

    public List<ComboSituationVOTOW> getComboSituationVOTOWS() {
        return comboSituationVOTOWS;
    }

    public void setComboSituationVOTOWS(List<ComboSituationVOTOW> comboSituationVOTOWS) {
        this.comboSituationVOTOWS = comboSituationVOTOWS;
    }
}
