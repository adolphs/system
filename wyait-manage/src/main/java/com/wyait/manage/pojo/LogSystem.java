package com.wyait.manage.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/3 20:44
 */
public class LogSystem {
    private Integer id;
    private Integer userId;
    private String userName;
    private String content;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date newTime;
    private Integer type;

    public LogSystem() {
    }

    public LogSystem(Integer id, Integer userId, String userName, String content, Date newTime, Integer type) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.newTime = newTime;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LogSystem{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", newTime=" + newTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }
}
