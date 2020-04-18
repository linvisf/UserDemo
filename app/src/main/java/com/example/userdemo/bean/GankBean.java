package com.example.userdemo.bean;

import java.io.Serializable;
import java.util.List;

public class GankBean implements Serializable {

//    "_id":"5e9857a4ee6ba981da2af351",
//    "author":"Jinbeen",
//    "category":"GanHuo",
//    "createdAt":"2020-04-16 21:03:32",
//    "desc":"RecyclerView \u4e0b\u62c9\u5237\u65b0\u3001\u52a0\u8f7d\u66f4\u591a\u3001item\u70b9\u51fb/\u957f\u6309\u3001\u5934\u5e03\u5c40/\u5c3e\u5e03\u5c40/\u72b6\u6001\u5e03\u5c40\u3001\u4e07\u80fd\u5206\u5272\u7ebf\u3001\u7c98\u6027Header\u3001Skeleton\u9aa8\u67b6\u56fe\u3001\u6781\u7b80adapter(RV/LV)\u7b49",
//    "images":["https://gank.io/images/8b57eb46d0ed48bbaf2d717188093fb5"],
//    "likeCounts":0,
//    "publishedAt":"2020-04-16 21:03:32",
//    "stars":1,
//    "title":"ByRecyclerView",
//    "type":"Android",
//    "url":"https://github.com/youlookwhat/ByRecyclerView",
//    "views":12

    private String _id;
    private String author;
    private String category;
    private String createdAt;
    private String desc;
    private List<String> images;
    private int likeCounts;
    private String publishedAt;
    private int stars;
    private String title;
    private String type;
    private String url;
    private int views;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
