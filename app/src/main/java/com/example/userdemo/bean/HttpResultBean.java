package com.example.userdemo.bean;

import java.io.Serializable;

/**
 * 接口返回Bean
 * @param <T>
 */
public class HttpResultBean<T> implements Serializable {

    private int status;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
