/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: SysIntfMessage
 * Author:   Administrator
 * Date:     2021/1/11
 * History:
 */
package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈接口日志表〉
 *
 * @author Administrator
 * @create 2021/1/11 13:11
 * @since 1.0.0
 */
@TableName("sys_intf_message")
public class SysIntfMessage extends Model<SysIntfMessage> {

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    private int id;
    private Date createDate;  //创建时间
    private Date modifyDate;  //修改时间
    private String outUniqueKey; //日记文件
    private String data;     //发送
    private int type;        //类型
    private String result;  //SUCCESS、FAILURE
    private String resultMsg;  // 返回信息
    private String remarks;  // 备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOutUniqueKey() {
        return outUniqueKey;
    }

    public void setOutUniqueKey(String outUniqueKey) {
        this.outUniqueKey = outUniqueKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
