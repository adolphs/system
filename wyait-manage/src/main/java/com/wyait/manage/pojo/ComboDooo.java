package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/4 13:31
 */
@TableName("combo_dooo")
public class ComboDooo {

    private Integer combo_id;
    private Integer dooo_id;

    public ComboDooo(Integer combo_id, Integer dooo_id) {
        this.combo_id = combo_id;
        this.dooo_id = dooo_id;
    }

    public ComboDooo() {
    }

    public Integer getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(Integer combo_id) {
        this.combo_id = combo_id;
    }

    public Integer getDooo_id() {
        return dooo_id;
    }

    public void setDooo_id(Integer dooo_id) {
        this.dooo_id = dooo_id;
    }
}
