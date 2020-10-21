package com.wyait.manage.pojo;


import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 事项表
 * @author ：lp
 * @date ：Created in 2020/9/16 14:58
 * @modified By：
 * @version: 1.0$
 */
@TableName("program_def")
public class ProgramDef implements Serializable{

    private static final long serialVersionUID = -1835058689389105350L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @Column(name = "program_id")
    private String programId;       //事项ID

//    @Column(name = "program_name")
    private String programName;     //事项名称

//    @Column(name = "program_type")
    private String programType;    //事项类型

//    @Column(name = "admin_dept")
    private String adminDept;       //实施机关

//    @Column(name = "program_code")
    private String programCode;   //（法制办）目录编码

//    @Column(name = "service_object")
    private String serviceObject;   //办理对象类型

//    @Column(name = "report_date")
    private String reportDate;   // --创建时间

//    @Column(name = "latest_update_date")
    private String latestUpdateDate;   //--  最后修改时间

//    @Column(name = "legal_time_limit")
    private String legalTimeLimit;    //法定期限

//    @Column(name = "promise_time_limit")      //承诺期限
    private String promiseTimeLimit;

//    @Column(name = "is_online_open")   //是否提供网上服务 1：提供，0：不提供
    private String isOnlineOpen;

//    @Column(name = "apply_url")
    private String applyUrl;   //在线申办服务网址

//    @Column(name = "ask_url")
    private String askUrl;     //业务咨询服务网址

//    @Column(name = "result_query_url")
    private String resultQueryUrl;    //结果反馈服务网址

//    @Column(name = "process_query_url")
    private String processQueryUrl;    //进度查询服务网址

//    @Column(name = "base_code")
    private String baseCode;       //基本编码

//    @Column(name = "impl_code")
    private String implCode;       //实施编码

//    @Column(name = "category_id")
    private String categoryId;   //服务分类ID列表，eg:1,2,3

//    @Column(name = "version")
    private String version;       //事项版本

//    @Column(name = "can_self_terminal_apply")
    private String canSelfTerminalApply; //是否支持自助终端办理

//    @Column(name = "insert_uid")
    private Integer insertUid;   //操作人

//    @Column(name = "create_date")
    private Date createDate;    //创建时间

//    @Column(name = "modify_data")
    private Date modifyDate;    //修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getAdminDept() {
        return adminDept;
    }

    public void setAdminDept(String adminDept) {
        this.adminDept = adminDept;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(String serviceObject) {
        this.serviceObject = serviceObject;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getLatestUpdateDate() {
        return latestUpdateDate;
    }

    public void setLatestUpdateDate(String latestUpdateDate) {
        this.latestUpdateDate = latestUpdateDate;
    }

    public String getLegalTimeLimit() {
        return legalTimeLimit;
    }

    public void setLegalTimeLimit(String legalTimeLimit) {
        this.legalTimeLimit = legalTimeLimit;
    }

    public String getPromiseTimeLimit() {
        return promiseTimeLimit;
    }

    public void setPromiseTimeLimit(String promiseTimeLimit) {
        this.promiseTimeLimit = promiseTimeLimit;
    }

    public String getIsOnlineOpen() {
        return isOnlineOpen;
    }

    public void setIsOnlineOpen(String isOnlineOpen) {
        this.isOnlineOpen = isOnlineOpen;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    public String getAskUrl() {
        return askUrl;
    }

    public void setAskUrl(String askUrl) {
        this.askUrl = askUrl;
    }

    public String getResultQueryUrl() {
        return resultQueryUrl;
    }

    public void setResultQueryUrl(String resultQueryUrl) {
        this.resultQueryUrl = resultQueryUrl;
    }

    public String getProcessQueryUrl() {
        return processQueryUrl;
    }

    public void setProcessQueryUrl(String processQueryUrl) {
        this.processQueryUrl = processQueryUrl;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getImplCode() {
        return implCode;
    }

    public void setImplCode(String implCode) {
        this.implCode = implCode;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCanSelfTerminalApply() {
        return canSelfTerminalApply;
    }

    public void setCanSelfTerminalApply(String canSelfTerminalApply) {
        this.canSelfTerminalApply = canSelfTerminalApply;
    }

    public Integer getInsertUid() {
        return insertUid;
    }

    public void setInsertUid(Integer insertUid) {
        this.insertUid = insertUid;
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
}
