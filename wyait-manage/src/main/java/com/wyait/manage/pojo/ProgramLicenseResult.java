package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 办理结果
 * @author ：lp
 * @date ：Created in 2020/9/16 14:58
 * @modified By：
 * @version: 1.0
 */
@TableName("program_license_result")
public class ProgramLicenseResult implements Serializable{

    private static final long serialVersionUID = 7434313476684792456L;

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "program_id")
    private String programId; //事项主键

//    @Column(name = "result_id")
    private String resultId;     //证件ID

//    @Column(name = "result_name")
    private String resultName;       //证件名称

//    @Column(name = "indate")
    private String indate;     //有效期

//    @Column(name = "ds_remark")
    private String dsRemark;       //延期说明

//    @Column(name = "stuff_type")
    private String stuffType;   //证件类型

//    @Column(name = "law_force")
    private Date lawForce;         //法律效力

//    @Column(name = "is_relate_license")
    private String isRelateLicense;  //是否已关联电子证照

//    @Column(name = "license_code")
    private String licenseCode;  // 已关联电子证照目录编码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getDsRemark() {
        return dsRemark;
    }

    public void setDsRemark(String dsRemark) {
        this.dsRemark = dsRemark;
    }

    public String getStuffType() {
        return stuffType;
    }

    public void setStuffType(String stuffType) {
        this.stuffType = stuffType;
    }

    public Date getLawForce() {
        return lawForce;
    }

    public void setLawForce(Date lawForce) {
        this.lawForce = lawForce;
    }

    public String getIsRelateLicense() {
        return isRelateLicense;
    }

    public void setIsRelateLicense(String isRelateLicense) {
        this.isRelateLicense = isRelateLicense;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }
}
