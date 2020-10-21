package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 办理依据
 * @author ：lp
 * @date ：Created in 2020/9/16 14:58
 * @modified By：
 * @version: 1.0
 */
@TableName("program_dealbase")
public class ProgramDealbase implements Serializable{

    private static final long serialVersionUID = -1892660875968634935L;
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//指定ID生成策略为自增长
    private Integer id;

//    @Column(name = "law_id")
    private String lawId;     //依据主键

//    @Column(name = "law_content")
    private String lawContent;       //相关法条内容

//    @Column(name = "law_name")
    private String lawName;     //法律法规名称

//    @Column(name = "basis_num")
    private String basisNum;       //依据文号

//    @Column(name = "related_clause")
    private String relatedClause;   //相关法条

//    @Column(name = "issue_organ")
    private Date issueOrgan;         //颁布机关

//    @Column(name = "item_num")
    private String itemNum;   //  条款号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLawId() {
        return lawId;
    }

    public void setLawId(String lawId) {
        this.lawId = lawId;
    }

    public String getLawContent() {
        return lawContent;
    }

    public void setLawContent(String lawContent) {
        this.lawContent = lawContent;
    }

    public String getLawName() {
        return lawName;
    }

    public void setLawName(String lawName) {
        this.lawName = lawName;
    }

    public String getBasisNum() {
        return basisNum;
    }

    public void setBasisNum(String basisNum) {
        this.basisNum = basisNum;
    }

    public String getRelatedClause() {
        return relatedClause;
    }

    public void setRelatedClause(String relatedClause) {
        this.relatedClause = relatedClause;
    }

    public Date getIssueOrgan() {
        return issueOrgan;
    }

    public void setIssueOrgan(Date issueOrgan) {
        this.issueOrgan = issueOrgan;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }
}
