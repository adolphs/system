package com.wyait.manage.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/8/13 09:11
 */
public class DataEnding {
    private Integer data_ending_id;
    private String data_ending_name;
    private String blank_url;
    private String blank_name;
    private String template_name;
    private String template_url;
    private Integer data_type;
    private String dataTypeName;
    private Integer is_electronic_license;
    private String isElectronicLicense;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date new_time;

    public DataEnding() {
    }

    public DataEnding(Integer data_ending_id, String data_ending_name, String blank_url, String blank_name, String template_name, String template_url, Integer data_type, Integer is_electronic_license, Date new_time) {
        this.data_ending_id = data_ending_id;
        this.data_ending_name = data_ending_name;
        this.blank_url = blank_url;
        this.blank_name = blank_name;
        this.template_name = template_name;
        this.template_url = template_url;
        this.data_type = data_type;
        this.is_electronic_license = is_electronic_license;
        this.new_time = new_time;
    }

    public Integer getData_ending_id() {
        return data_ending_id;
    }

    public void setData_ending_id(Integer data_ending_id) {
        this.data_ending_id = data_ending_id;
    }

    public String getData_ending_name() {
        return data_ending_name;
    }

    public void setData_ending_name(String data_engding_name) {
        this.data_ending_name = data_engding_name;
    }

    public String getBlank_url() {
        return blank_url;
    }

    public void setBlank_url(String blank_url) {
        this.blank_url = blank_url;
    }

    public String getBlank_name() {
        return blank_name;
    }

    public void setBlank_name(String blank_name) {
        this.blank_name = blank_name;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getTemplate_url() {
        return template_url;
    }

    public void setTemplate_url(String template_url) {
        this.template_url = template_url;
    }

    public Integer getData_type() {
        return data_type;
    }

    public void setData_type(Integer data_type) {
        this.data_type = data_type;
    }

    public Integer getIs_electronic_license() {
        return is_electronic_license;
    }

    public void setIs_electronic_license(Integer is_electronic_license) {
        this.is_electronic_license = is_electronic_license;
    }

    public Date getNew_time() {
        return new_time;
    }

    public void setNew_time(Date new_time) {
        this.new_time = new_time;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getIsElectronicLicense() {
        return isElectronicLicense;
    }

    public void setIsElectronicLicense(String isElectronicLicense) {
        this.isElectronicLicense = isElectronicLicense;
    }
}
