package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 咨询与投诉
 * @author ：lp
 * @date ：Created in 2020/9/16 14:58
 * @modified By：
 * @version: 1.0
 */
@TableName("program_item_ask")
public class ProgramItemAsk implements Serializable{

    private static final long serialVersionUID = -7121218463319008807L;
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//指定ID生成策略为自增长
    private Integer id;

//    @Column(name = "seq")
    private String seq;     //主键ID

//    @Column(name = "ask_type")
    private String askType;       //1:咨询，2:投诉

//    @Column(name = "review_org_addr")
    private String reviewOrgAddr;     //地址

//    @Column(name = "review_org_phone")
    private String reviewOrgPhone;       //电话号码

//    @Column(name = "advise_web")
    private String adviseWeb;   //实施机关咨询网址

//    @Column(name = "blog_web")
    private String blogWeb;         //微博号

//    @Column(name = "wechat_num")
    private String wechatNum;         //微信号

//    @Column(name = "email")
    private String email;         //电子邮箱

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getAskType() {
        return askType;
    }

    public void setAskType(String askType) {
        this.askType = askType;
    }

    public String getReviewOrgAddr() {
        return reviewOrgAddr;
    }

    public void setReviewOrgAddr(String reviewOrgAddr) {
        this.reviewOrgAddr = reviewOrgAddr;
    }

    public String getReviewOrgPhone() {
        return reviewOrgPhone;
    }

    public void setReviewOrgPhone(String reviewOrgPhone) {
        this.reviewOrgPhone = reviewOrgPhone;
    }

    public String getAdviseWeb() {
        return adviseWeb;
    }

    public void setAdviseWeb(String adviseWeb) {
        this.adviseWeb = adviseWeb;
    }

    public String getBlogWeb() {
        return blogWeb;
    }

    public void setBlogWeb(String blogWeb) {
        this.blogWeb = blogWeb;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
