package com.wyait.manage.pojo;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/31 10:38
 */
public class DataDooo {
    private Integer dataId;
    private Integer doooId;

    public DataDooo(Integer dataId, Integer doooId) {
        this.dataId = dataId;
        this.doooId = doooId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDoooId() {
        return doooId;
    }

    public void setDoooId(Integer doooId) {
        this.doooId = doooId;
    }

    public DataDooo() {
    }
}
