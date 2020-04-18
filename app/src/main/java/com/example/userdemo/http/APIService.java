package com.example.userdemo.http;

import com.example.userdemo.bean.BannerBean;
import com.example.userdemo.bean.GankBean;
import com.example.userdemo.bean.HttpResultBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

//    https://gank.io/api/v2/banners
//    首页banner轮播的数据
    @GET("banners")
    Call<HttpResultBean<List<BannerBean>>> getBannersData();



//     本周最热 API
//     *https://gank.io/api/v2/hot/<hot_type>/category/<category>/count/<count>
    @GET("hot/{hot_type}/category/{category}/count/{count}")
    Call<HttpResultBean<List<GankBean>>> getHotData(@Path("hot_type") String hot_type,
                                                    @Path("category") String category,
                                                    @Path("count") int count);

//https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10
//    获取分类数据列表
    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
    Call<HttpResultBean<List<GankBean>>> getCategoryList(@Path("category") String category,
                                                         @Path("type") String type,
                                                         @Path("page") int page,
                                                         @Path("count") int count);
}
