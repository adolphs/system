package com.wyait.manage.pojo.result;


import java.io.Serializable;

/**
 * 通用的结果返回类
 * @param <T>
 */
public class ResponseResult<T> implements Serializable {

    private String host;  //返回文字信息

    private Integer code;  //返回码  200正常  非200异常

    private String errorMessage; //异常信息

    private T data;   //返回的内容

    public ResponseResult() {
    }

    public ResponseResult(String host, Integer code, String errorMessage, T data) {
        this.host = host;
        this.code = code;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public ResponseResult(Integer code, String errorMessage, T data) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public ResponseResult(String host, Integer code, T data) {
        this.host = host;
        this.code = code;
        this.data = data;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
