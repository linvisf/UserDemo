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
