package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 主题分类
 * @author ：lp
 * @date ：Created in 2020/9/16 14:58
 * @modified By：
 * @version: 1.0
 */
@TableName("program_category")
public class ProgramCategory implements Serializable{
    private static final long serialVersionUID = 9126916532926722312L;

    private Integer id;

//    @Column(name = "parent_category_id")
    private String parentCategoryId;     //上级服务分类ID

//    @Column(name = "category_id")
    private String category_id;       //服务分类ID

//    @Column(name = "category_code")
    private String categoryCode;     //服务分类代码

//    @Column(name = "category_name")
    private String categoryName;       //服务分类名称

//    @Column(name = "belong_theme")
    private String belongTheme;   //服务分类所属主题

//    @Column(name = "service_object")
    private Date serviceObject;         //服务分类所属服务对象

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBelongTheme() {
        return belongTheme;
    }

    public void setBelongTheme(String belongTheme) {
        this.belongTheme = belongTheme;
    }

    public Date getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(Date serviceObject) {
        this.serviceObject = serviceObject;
    }
}
