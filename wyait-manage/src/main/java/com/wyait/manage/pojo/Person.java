package com.wyait.manage.pojo;

public class Person {
    /**
     * 账户名
     */
    private String uid;
    /**
     * 手机号
     */
    private String telephonenumber;
    /**
     * 电子邮箱
     */
    private String mail;
    /**
     * 姓名
     */
    private String cn;
    /**
     * 个人用户证件类型： 10： 身 份 证 20 ： 护 照 40：其他
     */
    private String idcardtype;
    /**
     * 证件号码
     */
    private String idcardnumber;
    /**
     * 证件地址
     */
    private String address;
    /**
     * 1 表示个人用户 2 表示法人用户
     */
    private String usertype;
    /**
     * 所属地市（地址填写为拼音全程）
     */
    private String area;
    /**
     * 法人账户（父 账户）唯一标识
     */
    private String parent_uidcode;
    /**
     * 用户来源
     */
    private String origin;
    /**
     * 账户登录方式:1：用户名口令登录 2：数字证书登录 3：粤省事扫码登录 4：微警登录
     */
    private String accout_type;
    /**
     * 用户唯一编码，由省统一 身份认证平台内部生成。
     */
    private String useridcode;
    /**
     * 用户信息创建时间
     */
    private String createtime;
    /**
     * 用户信息修改后需要更 新版本号，每修改一次， 版本号累加 1
     */
    private String uversion;
    /**
     * 1：男 2：女
     */
    private String sex;
    /**
     * true：L1 级及以上可信账 户false：L0 级可信账户
     */
    private String is_real;
    /**
     * 账户当前最 高的可信等级 L0、L1、L2、L3
     */
    private String creditable_level_of_account;
    /**
     *账户已经核验过的可信 方式以及其核验方式组 成的字符串，格式为：账 户可信级别@核验方式@ 核验方式的唯一码（支付 宝[ALV]核验为支付宝账 户唯一编码、数字证书核 验为数字证书内容，其他 为对应的证件号码）||账 户可信级别@核验方式@ 核验方式的唯一码
     */
    private String creditable_level_of_account_way;

    public Person() {
    }

    public Person(String uid, String telephonenumber, String mail, String cn, String idcardtype, String idcardnumber, String address, String usertype, String area, String parent_uidcode, String origin, String accout_type, String useridcode, String createtime, String uversion, String sex, String is_real, String creditable_level_of_account, String creditable_level_of_account_way) {
        this.uid = uid;
        this.telephonenumber = telephonenumber;
        this.mail = mail;
        this.cn = cn;
        this.idcardtype = idcardtype;
        this.idcardnumber = idcardnumber;
        this.address = address;
        this.usertype = usertype;
        this.area = area;
        this.parent_uidcode = parent_uidcode;
        this.origin = origin;
        this.accout_type = accout_type;
        this.useridcode = useridcode;
        this.createtime = createtime;
        this.uversion = uversion;
        this.sex = sex;
        this.is_real = is_real;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIs_real() {
        return is_real;
    }

    public void setIs_real(String is_real) {
        this.is_real = is_real;
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
