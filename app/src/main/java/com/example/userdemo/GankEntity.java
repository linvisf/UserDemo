package com.example.userdemo;

import com.example.userdemo.bean.GankBean;

import java.util.List;

public class GankEntity {

    private int status;
    private List<GankBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GankBean> getData() {
        return data;
    }

    public void setData(List<GankBean> data) {
        this.data = data;
    }
}
