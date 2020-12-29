package com.wyait.manage.entity;

public class BusinessDTO {

    /**
     * 办事人
     */
    private String name;
    /**
     * 身份证
     */
    private String idcardnumber;
    /**
     * 流水号
     */
    private String busineseId;

    /**
     * 1 表示个人用户 2 表示法人用户
     */
    private Integer usertype;

    public BusinessDTO(String name, String idcardnumber, String busineseId, Integer usertype) {
        this.name = name;
        this.idcardnumber = idcardnumber;
        this.busineseId = busineseId;
        this.usertype = usertype;
    }

    public BusinessDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcardnumber() {
        return idcardnumber;
    }

    public void setIdcardnumber(String idcardnumber) {
        this.idcardnumber = idcardnumber;
    }

    public String getBusineseId() {
        return busineseId;
    }

    public void setBusineseId(String busineseId) {
        this.busineseId = busineseId;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}
