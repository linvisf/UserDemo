package com.example.userdemo.http;

import com.example.userdemo.bean.GankBean;
import com.example.userdemo.bean.HttpResultBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
//    https://gank.io/api/v2/categories/GanHuo

//https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10

//    获取分类数据列表
    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
    Call<HttpResultBean<List<GankBean>>> getCategoryList(@Path("category") String category,
                                                         @Path("type") String type,
                                                         @Path("page") int page,
                                                         @Path("count") int count);
}
