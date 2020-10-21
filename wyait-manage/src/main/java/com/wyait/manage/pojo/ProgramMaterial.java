package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 *  申请材料表
 * @author ：lp
 * @date ：Created in 2020/9/18 15:08
 * @modified By：
 * @version: 1.0$
 */
@TableName("program_material")
public class ProgramMaterial implements Serializable {

    private static final long serialVersionUID = 2630774506278056056L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Integer id;

//    @Column(name = "create_data")
//    private Date createDate;    //创建时间

//    @Column(name = "material_id")
    private String materialId;      //材料ID

//    @Column(name = "program_id")
    private String programId;      //事项ID

//    @Column(name = "material_class")
    private String materialClass;  //材料类型（纸质/电子版)

//    @Column(name = "is_trust")
    private String isTrust;       // 是否支持容后补缺

//    @Column(name = "material_name")
    private String materialName;   // 材料名称

//    @Column(name = "norm_code")
    private String normCode;       // 材料编码

//    @Column(name = "mate_sort")
    private String mateSort;       //顺序号。用于排序。

//    @Column(name = "material_remark")
    private String materialRemark;  // 材料填报要求

//    @Column(name = "access_standard")
    private String accessStandard;  // 受理标准

//    @Column(name = "blank_name")
//    private String blankName;     // 空白表格名称

//    @Column(name = "blank_url")
    private String blankUrl;       //空白表格附件路径

//    @Column(name = "template_table")
//    private String templateTable; // 范本表格名称

//    @Column(name = "template_url")
    private String templateUrl;   //范本表格附件路径

//    @Column(name = "blank_type")
    private String blankType;    // 空白表格附件类型

//    @Column(name = "template_type")
    private String templateType;     //范本表格附件类型

//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getMaterialClass() {
        return materialClass;
    }

    public void setMaterialClass(String materialClass) {
        this.materialClass = materialClass;
    }

    public String getIsTrust() {
        return isTrust;
    }

    public void setIsTrust(String isTrust) {
        this.isTrust = isTrust;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getNormCode() {
        return normCode;
    }

    public void setNormCode(String normCode) {
        this.normCode = normCode;
    }

    public String getMateSort() {
        return mateSort;
    }

    public void setMateSort(String mateSort) {
        this.mateSort = mateSort;
    }

    public String getMaterialRemark() {
        return materialRemark;
    }

    public void setMaterialRemark(String materialRemark) {
        this.materialRemark = materialRemark;
    }

    public String getAccessStandard() {
        return accessStandard;
    }

    public void setAccessStandard(String accessStandard) {
        this.accessStandard = accessStandard;
    }

//    public String getBlankName() {
//        return blankName;
//    }
//
//    public void setBlankName(String blankName) {
//        this.blankName = blankName;
//    }

    public String getBlankUrl() {
        return blankUrl;
    }

    public void setBlankUrl(String blankUrl) {
        this.blankUrl = blankUrl;
    }

//    public String getTemplateTable() {
//        return templateTable;
//    }
//
//    public void setTemplateTable(String templateTable) {
//        this.templateTable = templateTable;
//    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setStuffType(String templateType) {
        this.templateType = templateType;
    }

    public String getBlankType() {
        return blankType;
    }

    public void setBlankType(String blankType) {
        this.blankType = blankType;
    }
}
