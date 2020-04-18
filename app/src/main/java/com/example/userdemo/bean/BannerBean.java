package com.example.userdemo.bean;

import java.io.Serializable;

/**
 * 首页banner轮播数据实体
 */
public class BannerBean implements Serializable {

    private String image;
    private String url;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
