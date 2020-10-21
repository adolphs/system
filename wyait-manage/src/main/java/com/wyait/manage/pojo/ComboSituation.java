package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/8 15:25
 */
@TableName("combo_situation")
public class ComboSituation extends Model<ComboSituation> {
    private Integer id;
    private String situationDescribe;
    private Integer comboId;
    private Integer situationLevel;
    private Integer pid;
    private Integer type;
    private Integer nodesType;
    private Date newTime;
    private Date updateTime;

    public ComboSituation() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public ComboSituation(Integer id, String situationDescribe, Integer comboId, Integer situationLevel, Integer pid, Integer type, Integer nodesType, Date newTime, Date updateTime) {
        this.id = id;
        this.situationDescribe = situationDescribe;
        this.comboId = comboId;
        this.situationLevel = situationLevel;
        this.pid = pid;
        this.type = type;
        this.nodesType = nodesType;
        this.newTime = newTime;
        this.updateTime = updateTime;
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

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getSituationLevel() {
        return situationLevel;
    }

    public void setSituationLevel(Integer situationLevel) {
        this.situationLevel = situationLevel;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
}
