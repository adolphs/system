package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数设置
 * @author ：lp
 * @date ：Created in 2020/10/12 14:58
 * @modified By：
 * @version: 1.0
 */
@TableName("wyait.field_setting")
public class FieldSetting extends Model<FieldSetting> {

    private Integer id;

//    @Column(name = "platform")
    private String platform;       // 应用平台

//    @Column(name = "url")
    private String url;     //

//    @Column(name = "app_key")
    private String appKey;    //

//    @Column(name = "app_secret")
    private String appSecret;       //

//    @Column(name = "account")
    private String account;   //

//    @Column(name = "password")
    private String password;   //

//    @Column(name = "content")
    private String content;   // 内容

//    @Column(name = "remarks")
    private String remarks;   // 备注

//    @Column(name = "create_date")
    private Date createDate;    //创建时间

//    @Column(name = "modify_data")
    private Date modifyDate;    //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
