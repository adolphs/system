package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 权利与义务
 * @author ：lp
 * @date ：Created in 2020/9/16 14:58
 * @modified By：
 * @version: 1.0
 */
@TableName("program_item_right_duty")
public class ProgramItemRightDuty implements Serializable{

    private static final long serialVersionUID = 1841083552149193039L;
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//指定ID生成策略为自增长
    private Integer id;

//    @Column(name = "program_id")
    private String programId;     //事项主键

//    @Column(name = "content_type")
    private String contentType;       //类型（right：权利,duty：义务）

//    @Column(name = "seq")
    private String seq;     //主键

//    @Column(name = "content")
    private String content;       //附件名称

//    @Column(name = "order_num")
    private String orderNum;   //

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
