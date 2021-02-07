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

    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ServiceItem other = (ServiceItem) that;
        return (this.getCarry_out_code() == null ? other.getCarry_out_code() == null : this.getCarry_out_code().equals(other.getCarry_out_code()))
            && (this.getService_agent_code() == null ? other.getService_agent_code() == null : this.getService_agent_code().equals(other.getService_agent_code()))
            && (this.getService_agent_name() == null ? other.getService_agent_name() == null : this.getService_agent_name().equals(other.getService_agent_name()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getService_code() == null ? other.getService_code() == null : this.getService_code().equals(other.getService_code()))
            && (this.getCtg_code() == null ? other.getCtg_code() == null : this.getCtg_code().equals(other.getCtg_code()))
            && (this.getAccept_time() == null ? other.getAccept_time() == null : this.getAccept_time().equals(other.getAccept_time()))
            && (this.getConditions() == null ? other.getConditions() == null : this.getConditions().equals(other.getConditions()))
            && (this.getLegal_period() == null ? other.getLegal_period() == null : this.getLegal_period().equals(other.getLegal_period()))
            && (this.getLegal_period_type() == null ? other.getLegal_period_type() == null : this.getLegal_period_type().equals(other.getLegal_period_type()))
            && (this.getPromised_period() == null ? other.getPromised_period() == null : this.getPromised_period().equals(other.getPromised_period()))
            && (this.getPromised_period_type() == null ? other.getPromised_period_type() == null : this.getPromised_period_type().equals(other.getPromised_period_type()))
            && (this.getApply_time() == null ? other.getApply_time() == null : this.getApply_time().equals(other.getApply_time()))
            && (this.getAccept_time_sp() == null ? other.getAccept_time_sp() == null : this.getAccept_time_sp().equals(other.getAccept_time_sp()))
            && (this.getHave_certificate() == null ? other.getHave_certificate() == null : this.getHave_certificate().equals(other.getHave_certificate()))
            && (this.getNeed_charge() == null ? other.getNeed_charge() == null : this.getNeed_charge().equals(other.getNeed_charge()))
            && (this.getSuit_online() == null ? other.getSuit_online() == null : this.getSuit_online().equals(other.getSuit_online()))
            && (this.getOnline_service_url() == null ? other.getOnline_service_url() == null : this.getOnline_service_url().equals(other.getOnline_service_url()))
            && (this.getEms() == null ? other.getEms() == null : this.getEms().equals(other.getEms()))
            && (this.getFaq() == null ? other.getFaq() == null : this.getFaq().equals(other.getFaq()))
            && (this.getTheme_gr_type() == null ? other.getTheme_gr_type() == null : this.getTheme_gr_type().equals(other.getTheme_gr_type()))
            && (this.getTheme_fr_type() == null ? other.getTheme_fr_type() == null : this.getTheme_fr_type().equals(other.getTheme_fr_type()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCarry_out_code() == null) ? 0 : getCarry_out_code().hashCode());
        result = prime * result + ((getService_agent_code() == null) ? 0 : getService_agent_code().hashCode());
        result = prime * result + ((getService_agent_name() == null) ? 0 : getService_agent_name().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getService_code() == null) ? 0 : getService_code().hashCode());
        result = prime * result + ((getCtg_code() == null) ? 0 : getCtg_code().hashCode());
        result = prime * result + ((getAccept_time() == null) ? 0 : getAccept_time().hashCode());
        result = prime * result + ((getConditions() == null) ? 0 : getConditions().hashCode());
        result = prime * result + ((getLegal_period() == null) ? 0 : getLegal_period().hashCode());
        result = prime * result + ((getLegal_period_type() == null) ? 0 : getLegal_period_type().hashCode());
        result = prime * result + ((getPromised_period() == null) ? 0 : getPromised_period().hashCode());
        result = prime * result + ((getPromised_period_type() == null) ? 0 : getPromised_period_type().hashCode());
        result = prime * result + ((getApply_time() == null) ? 0 : getApply_time().hashCode());
        result = prime * result + ((getAccept_time_sp() == null) ? 0 : getAccept_time_sp().hashCode());
        result = prime * result + ((getHave_certificate() == null) ? 0 : getHave_certificate().hashCode());
        result = prime * result + ((getNeed_charge() == null) ? 0 : getNeed_charge().hashCode());
        result = prime * result + ((getSuit_online() == null) ? 0 : getSuit_online().hashCode());
        result = prime * result + ((getOnline_service_url() == null) ? 0 : getOnline_service_url().hashCode());
        result = prime * result + ((getEms() == null) ? 0 : getEms().hashCode());
        result = prime * result + ((getFaq() == null) ? 0 : getFaq().hashCode());
        result = prime * result + ((getTheme_gr_type() == null) ? 0 : getTheme_gr_type().hashCode());
        result = prime * result + ((getTheme_fr_type() == null) ? 0 : getTheme_fr_type().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carry_out_code=").append(carry_out_code);
        sb.append(", service_agent_code=").append(service_agent_code);
        sb.append(", service_agent_name=").append(service_agent_name);
        sb.append(", name=").append(name);
        sb.append(", service_code=").append(service_code);
        sb.append(", ctg_code=").append(ctg_code);
        sb.append(", accept_time=").append(accept_time);
        sb.append(", conditions=").append(conditions);
        sb.append(", legal_period=").append(legal_period);
        sb.append(", legal_period_type=").append(legal_period_type);
        sb.append(", promised_period=").append(promised_period);
        sb.append(", promised_period_type=").append(promised_period_type);
        sb.append(", apply_time=").append(apply_time);
        sb.append(", accept_time_sp=").append(accept_time_sp);
        sb.append(", have_certificate=").append(have_certificate);
        sb.append(", need_charge=").append(need_charge);
        sb.append(", suit_online=").append(suit_online);
        sb.append(", online_service_url=").append(online_service_url);
        sb.append(", ems=").append(ems);
        sb.append(", faq=").append(faq);
        sb.append(", theme_gr_type=").append(theme_gr_type);
        sb.append(", theme_fr_type=").append(theme_fr_type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}