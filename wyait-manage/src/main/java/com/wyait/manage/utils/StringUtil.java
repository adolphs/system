package com.wyait.manage.utils;

/**
 * string 截取字符串
 */
public class StringUtil {


    /**
     * 截取前面的字段
     * @param str  源目标
     * @param symbol 定义的截取标准
     * @return
     */
    public static String subStringFront(String str,String symbol){
        return str.substring(0,str.indexOf(symbol));
    }

    /**
     * 截取后面的字段
     * @param str  源目标
     * @param symbol 定义的截取标准
     * @return
     */
    public static String subStringRear(String str,String symbol){
        return str.substring(str.length()+1,str.indexOf(symbol));
    }
}
