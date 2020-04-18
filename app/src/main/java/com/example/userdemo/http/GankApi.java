package com.example.userdemo.http;

import com.example.userdemo.bean.BannerBean;
import com.example.userdemo.bean.GankBean;
import com.example.userdemo.bean.HttpResultBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GankApi {

    /**
     * 首页banner轮播数据
     * @param httpCallBack
     * @return
     */
    public static Call<HttpResultBean<List<BannerBean>>> getBanners(final HttpCallBack httpCallBack) {
        Call<HttpResultBean<List<BannerBean>>> call = BaseAPI.getApiService().getBannersData();
        call.enqueue(new Callback<HttpResultBean<List<BannerBean>>>() {
            @Override
            public void onResponse(Call<HttpResultBean<List<BannerBean>>> call, Response<HttpResultBean<List<BannerBean>>> response) {
                if (response.isSuccessful() && response.body()!= null) {
                    httpCallBack.onSuccess(2, response.body().getData());
                } else {
                    httpCallBack.onFailed(0, "获取数据错误");
                }
            }

            @Override
            public void onFailure(Call<HttpResultBean<List<BannerBean>>> call, Throwable t) {
                httpCallBack.onFailed(1, "网络错误");
            }
        });
        return call;
    }

    /**
     * 本周最热 API
     * https://gank.io/api/v2/hot/<hot_type>/category/<category>/count/<count>
     * 请求方式: GET
     * 注:
     *
     * hot_type 可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）❌
     * category 可接受参数 Article | GanHuo | Girl
     * count: [1, 20]
     *
     */
    public static Call<HttpResultBean<List<GankBean>>> getHotData(String hot_type, String category,int count, final HttpCallBack httpCallBack) {
        Call<HttpResultBean<List<GankBean>>> call = BaseAPI.getApiService().getHotData(hot_type, category, count);
        call.enqueue(new Callback<HttpResultBean<List<GankBean>>>() {
            @Override
            public void onResponse(Call<HttpResultBean<List<GankBean>>> call, Response<HttpResultBean<List<GankBean>>> response) {
                if (response.isSuccessful()) {
                    HttpResultBean<List<GankBean>> httpResultBean = response.body();
                    if (httpResultBean != null) {
                        httpCallBack.onSuccess(2, response.body().getData());
                    } else {
                        httpCallBack.onFailed(0, "获取数据错误");
                    }
                } else {
                    httpCallBack.onFailed(0, "获取数据错误");
                }
            }

            @Override
            public void onFailure(Call<HttpResultBean<List<GankBean>>> call, Throwable t) {
                httpCallBack.onFailed(1, "网络错误");
            }
        });
        return call;
    }

    /**
     * 分类数据 API
     * https://gank.io/api/v2/data/category/<category>/type/<type>/page/<page>/count/<count>
     * 请求方式: GET
     * 注:
     *
     * category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * count: [10, 50]
     * page: >=1
     * 示例:
     *
     * 获取妹子列表
     * https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10
     * 获取Android干货列表
     * https://gank.io/api/v2/data/category/GanHuo/type/Android/page/1/count/10
     */
    public static Call<HttpResultBean<List<GankBean>>> getCategoryList(final String category, String type, int page, int count, final HttpCallBack httpCallBack) {
        Call<HttpResultBean<List<GankBean>>> call = BaseAPI.getApiService().getCategoryList(category, type, page, count);
        call.enqueue(new Callback<HttpResultBean<List<GankBean>>>() {
            @Override
            public void onResponse(Call<HttpResultBean<List<GankBean>>> call, Response<HttpResultBean<List<GankBean>>> response) {
                if (response.isSuccessful()) {
                    HttpResultBean<List<GankBean>> httpResultBean = response.body();
                    if (httpResultBean != null) {
                        httpCallBack.onSuccess(2, response.body().getData());
                    } else {
                        httpCallBack.onFailed(0, "获取数据错误");
                    }
                } else {
                    httpCallBack.onFailed(0, "获取数据错误");
                }
            }

            @Override
            public void onFailure(Call<HttpResultBean<List<GankBean>>> call, Throwable t) {
                httpCallBack.onFailed(1, "网络错误");
            }
        });
        return call;
    }
}
