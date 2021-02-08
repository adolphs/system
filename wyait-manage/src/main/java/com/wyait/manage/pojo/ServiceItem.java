package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 事项库事项清晰
 */
public class ServiceItem implements Serializable {
    /**
     * 事项编码
     */
    private String carry_out_code;

    /**
     * 部门code
     */
    private String service_agent_code;

    /**
     * 部门名称
     */
    private String service_agent_name;

    /**
     * 事项名称
     */
    private String name;

    /**
     * 子项内部编码，同子项不同版本值相同
     */
    private String service_code;

    /**
     * 目录内部编码
     */
    private String ctg_code;

    /**
     * 受理时限：
以&&分隔，第一个数字表示受理时限，第二个数字表示受理时限单位（代码项【时限单位】单选）
     */
    private String accept_time;

    /**
     * 办理条件
     */
    private String conditions;

    /**
     * 法定期限
     */
    private String legal_period;

    /**
     * 法定期限单位
     */
    private String legal_period_type;

    /**
     * 承诺期限
     */
    private String promised_period;

    /**
     * 承诺期限单位
     */
    private String promised_period_type;

    /**
     * 申请时限
     */
    private String apply_time;

    /**
     * 办理时限说明
     */
    private String accept_time_sp;

    /**
     * 有无证件 1有0无
     */
    private String have_certificate;

    /**
     * 是否收费  0：否，1：是
     */
    private String need_charge;

    /**
     * 是否提供网上服务 1：提供，0：不提供
     */
    private String suit_online;

    /**
     * 网上服务url
     */
    private String online_service_url;

    /**
     * 是否支持EMS 以逗号隔开，-1表示不支持EMS，0表示支持，1表示发件邮寄，2表示收件邮寄：
例如：0,1,2，表示支持EMS，并且是发件邮寄
     */
    private String ems;

    /**
     * 备份
     */
    private String faq;

    /**
     * 个人主题分类
     */
    private String theme_gr_type;

    /**
     * 法人主题分类
     */
    private String theme_fr_type;

    /**
     * 审批状态：1.待审批 2.审批中 3.审批通过
     */
    private Integer approval_type;

    /**
     * 提交说明
     */
    private String put_text;

    /**
     * 审批说明
     */
    private String approval_text;


    public String getPut_text() {
        return put_text;
    }

    public void setPut_text(String put_text) {
        this.put_text = put_text;
    }

    public String getApproval_text() {
        return approval_text;
    }

    public void setApproval_text(String approval_text) {
        this.approval_text = approval_text;
    }

    public Integer getApproval_type() {
        return approval_type;
    }

    public void setApproval_type(Integer approval_type) {
        this.approval_type = approval_type;
    }

    public String getCarry_out_code() {
        return carry_out_code;
    }

    public void setCarry_out_code(String carry_out_code) {
        this.carry_out_code = carry_out_code;
    }

    public String getService_agent_code() {
        return service_agent_code;
    }

    public void setService_agent_code(String service_agent_code) {
        this.service_agent_code = service_agent_code;
    }

    public String getService_agent_name() {
        return service_agent_name;
    }

    public void setService_agent_name(String service_agent_name) {
        this.service_agent_name = service_agent_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }

    public String getCtg_code() {
        return ctg_code;
    }

    public void setCtg_code(String ctg_code) {
        this.ctg_code = ctg_code;
    }

    public String getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(String accept_time) {
        this.accept_time = accept_time;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getLegal_period() {
        return legal_period;
    }

    public void setLegal_period(String legal_period) {
        this.legal_period = legal_period;
    }

    public String getLegal_period_type() {
        return legal_period_type;
    }

    public void setLegal_period_type(String legal_period_type) {
        this.legal_period_type = legal_period_type;
    }

    public String getPromised_period() {
        return promised_period;
    }

    public void setPromised_period(String promised_period) {
        this.promised_period = promised_period;
    }

    public String getPromised_period_type() {
        return promised_period_type;
    }

    public void setPromised_period_type(String promised_period_type) {
        this.promised_period_type = promised_period_type;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getAccept_time_sp() {
        return accept_time_sp;
    }

    public void setAccept_time_sp(String accept_time_sp) {
        this.accept_time_sp = accept_time_sp;
    }

    public String getHave_certificate() {
        return have_certificate;
    }

    public void setHave_certificate(String have_certificate) {
        this.have_certificate = have_certificate;
    }

    public String getNeed_charge() {
        return need_charge;
    }

    public void setNeed_charge(String need_charge) {
        this.need_charge = need_charge;
    }

    public String getSuit_online() {
        return suit_online;
    }

    public void setSuit_online(String suit_online) {
        this.suit_online = suit_online;
    }

    public String getOnline_service_url() {
        return online_service_url;
    }

    public void setOnline_service_url(String online_service_url) {
        this.online_service_url = online_service_url;
    }

    public String getEms() {
        return ems;
    }

    public void setEms(String ems) {
        this.ems = ems;
    }

    public String getFaq() {
        return faq;
    }

    public void setFaq(String faq) {
        this.faq = faq;
    }

    public String getTheme_gr_type() {
        return theme_gr_type;
    }

    public void setTheme_gr_type(String theme_gr_type) {
        this.theme_gr_type = theme_gr_type;
    }

    public String getTheme_fr_type() {
        return theme_fr_type;
    }

    public void setTheme_fr_type(String theme_fr_type) {
        this.theme_fr_type = theme_fr_type;
    }

    public ServiceItem() {
    }

    @Override
    public String toString() {
        return "ServiceItem{" +
                "carry_out_code='" + carry_out_code + '\'' +
                ", service_agent_code='" + service_agent_code + '\'' +
                ", service_agent_name='" + service_agent_name + '\'' +
                ", name='" + name + '\'' +
                ", service_code='" + service_code + '\'' +
                ", ctg_code='" + ctg_code + '\'' +
                ", accept_time='" + accept_time + '\'' +
                ", conditions='" + conditions + '\'' +
                ", legal_period='" + legal_period + '\'' +
                ", legal_period_type='" + legal_period_type + '\'' +
                ", promised_period='" + promised_period + '\'' +
                ", promised_period_type='" + promised_period_type + '\'' +
                ", apply_time='" + apply_time + '\'' +
                ", accept_time_sp='" + accept_time_sp + '\'' +
                ", have_certificate='" + have_certificate + '\'' +
                ", need_charge='" + need_charge + '\'' +
                ", suit_online='" + suit_online + '\'' +
                ", online_service_url='" + online_service_url + '\'' +
                ", ems='" + ems + '\'' +
                ", faq='" + faq + '\'' +
                ", theme_gr_type='" + theme_gr_type + '\'' +
                ", theme_fr_type='" + theme_fr_type + '\'' +
                '}';
    }
}