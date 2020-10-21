package com.wyait.manage.common;

/**
 * @author ：lp
 * @date ：Created in 2020/10/18 14:58
 * @modified By：
 * @version: 1.0
 */
public enum DBTypeEnum {
    db1("db1"), db2("db2");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
