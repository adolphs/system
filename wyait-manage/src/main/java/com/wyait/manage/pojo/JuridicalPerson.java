package com.wyait.manage.pojo;

public class JuridicalPerson {
    /**
     * 用户名
     */
    private String uid;
    /**
     * 联 系 人 手 机 号
     */
    private String telephonenumber;
     /**
     * 联 系 人 电 子 邮箱
     */
    private String mail;
     /**
     * 法人名称
     */
    private String cn;
     /**
     * 法人用户证件类型： 49：统一社会信用代码 50：组织机构代码证 51：工商营业执照 60：税务登记证 80：其他有效机构身份证 件
     */
    private String idcardtype;
     /**
     * 法 人 证 件 号 码
     */
    private String idcardnumber;
     /**
     * 用户名
     */
    private String link_person_name;
     /**
     * 联 系 人 证 件 类型
     */
    private String link_person_type;
     /**
     *  联 系 人 证 件 号码
     */
    private String link_person_code;
     /**
     * 用户名
     */
    private String address;
     /**
     * 用户类型 1 表示个人用户 2 表示法人用户
     */
    private String usertype;
     /**
     * 所属地市
     */
    private String area;
     /**
     * 法 定 代 表 人 姓名
     */
    private String legal_person;
     /**
     * 法定代表人类型
     */
    private String legal_id_type;
     /**
     * 法定代表人证件号码
     */
    private String legal_code;
     /**
     * 法人账户（父 账户）唯一标 识
     */
    private String parent_uidcode;
     /**
     * 用户来源  信任源编码： gdbs：省统一认证平台用 户
     */
    private String origin;
     /**
     * 账号类型 1：用户名口令登录 2：数字证书登录 3：粤省事扫码登录 4：微警登录
     */
    private String accout_type;
     /**
     * 用户唯一编码，由省统一 身份认证平台内部生成。
     */
    private String useridcode;
     /**
     * 用户信息更新时间  用户信息修改后需要更新时间
     */
    private String createtime;
     /**
     * 用户信息版本号 用户信息修改后需要更 新版本号，每修改一次， 版本号累加 1
     */
    private String uversion;
     /**
     * 实名状态 true：L1 级及以上可信账 户false：L0 级可信账户
     */
    private String isreal;
    /**
     * 实名注册类型  PLV:通过了公安人口库 的身份核验 SI:通过了社保的身份核 验IC:通过了工商企业库核验
     */
    private String realtype;
     /**
     * 账户当前最 高的可信等 级 L0、L1、L2、L3
     */
    private String creditable_level_of_account;
    /**
     * 账户可信级 别以及核验 方式字符串   账户已经核验过的可信 方式以及其核验方式组 成的字符串，格式为：账 户可信级别@核验方式@ 核验方式的唯一码（支付 宝[ALV]核验为支付宝账 户唯一编码、数字证书核 验为数字证书内容，其他 为对应的证件号码）||账 户可信级别@核验方式@ 核验方式的唯一码
     */
    private String creditable_level_of_account_way;

    public JuridicalPerson() {
    }

    public JuridicalPerson(String uid, String telephonenumber, String mail, String cn, String idcardtype, String idcardnumber, String link_person_name, String link_person_type, String link_person_code, String address, String usertype, String area, String legal_person, String legal_id_type, String legal_code, String parent_uidcode, String origin, String accout_type, String useridcode, String createtime, String uversion, String isreal, String realtype, String creditable_level_of_account, String creditable_level_of_account_way) {
        this.uid = uid;
        this.telephonenumber = telephonenumber;
        this.mail = mail;
        this.cn = cn;
        this.idcardtype = idcardtype;
        this.idcardnumber = idcardnumber;
        this.link_person_name = link_person_name;
        this.link_person_type = link_person_type;
        this.link_person_code = link_person_code;
        this.address = address;
        this.usertype = usertype;
        this.area = area;
        this.legal_person = legal_person;
        this.legal_id_type = legal_id_type;
        this.legal_code = legal_code;
        this.parent_uidcode = parent_uidcode;
        this.origin = origin;
        this.accout_type = accout_type;
        this.useridcode = useridcode;
        this.createtime = createtime;
        this.uversion = uversion;
        this.isreal = isreal;
        this.realtype = realtype;
        this.creditable_level_of_account = creditable_level_of_account;
        this.creditable_level_of_account_way = creditable_level_of_account_way;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getIdcardtype() {
        return idcardtype;
    }

    public void setIdcardtype(String idcardtype) {
        this.idcardtype = idcardtype;
    }

    public String getIdcardnumber() {
        return idcardnumber;
    }

    public void setIdcardnumber(String idcardnumber) {
        this.idcardnumber = idcardnumber;
    }

    public String getLink_person_name() {
        return link_person_name;
    }

    public void setLink_person_name(String link_person_name) {
        this.link_person_name = link_person_name;
    }

    public String getLink_person_type() {
        return link_person_type;
    }

    public void setLink_person_type(String link_person_type) {
        this.link_person_type = link_person_type;
    }

    public String getLink_person_code() {
        return link_person_code;
    }

    public void setLink_person_code(String link_person_code) {
        this.link_person_code = link_person_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }

    public String getLegal_id_type() {
        return legal_id_type;
    }

    public void setLegal_id_type(String legal_id_type) {
        this.legal_id_type = legal_id_type;
    }

    public String getLegal_code() {
        return legal_code;
    }

    public void setLegal_code(String legal_code) {
        this.legal_code = legal_code;
    }

    public String getParent_uidcode() {
        return parent_uidcode;
    }

    public void setParent_uidcode(String parent_uidcode) {
        this.parent_uidcode = parent_uidcode;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAccout_type() {
        return accout_type;
    }

    public void setAccout_type(String accout_type) {
        this.accout_type = accout_type;
    }

    public String getUseridcode() {
        return useridcode;
    }

    public void setUseridcode(String useridcode) {
        this.useridcode = useridcode;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUversion() {
        return uversion;
    }

    public void setUversion(String uversion) {
        this.uversion = uversion;
    }

    public String getIsreal() {
        return isreal;
    }

    public void setIsreal(String isreal) {
        this.isreal = isreal;
    }

    public String getRealtype() {
        return realtype;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }

    public String getCreditable_level_of_account() {
        return creditable_level_of_account;
    }

    public void setCreditable_level_of_account(String creditable_level_of_account) {
        this.creditable_level_of_account = creditable_level_of_account;
    }

    public String getCreditable_level_of_account_way() {
        return creditable_level_of_account_way;
    }

    public void setCreditable_level_of_account_way(String creditable_level_of_account_way) {
        this.creditable_level_of_account_way = creditable_level_of_account_way;
    }
}
