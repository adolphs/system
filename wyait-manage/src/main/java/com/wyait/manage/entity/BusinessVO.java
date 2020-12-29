package com.wyait.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BusinessVO {
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date submit;
    private String department_ids;
    private String cn;
    private String idcardnumber;
    private String businessId;

    public BusinessVO() {
    }

    public BusinessVO(Date submit, String department_ids, String cn, String idcardnumber, String business_id) {
        this.submit = submit;
        this.department_ids = department_ids;
        this.cn = cn;
        this.idcardnumber = idcardnumber;
        this.businessId = business_id;
    }

    public Date getSubmit() {
        return submit;
    }

    public void setSubmit(Date submit) {
        this.submit = submit;
    }

    public String getDepartment_ids() {
        return department_ids;
    }

    public void setDepartment_ids(String department_ids) {
        this.department_ids = department_ids;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getIdcardnumber() {
        return idcardnumber;
    }

    public void setIdcardnumber(String idcardnumber) {
        this.idcardnumber = idcardnumber;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String business_id) {
        this.businessId = business_id;
    }
}
