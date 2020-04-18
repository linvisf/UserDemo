package com.example.userdemo.http;

import com.example.userdemo.bean.GankBean;
import com.example.userdemo.bean.HttpResultBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GankApi {

    public static Call<HttpResultBean<List<GankBean>>> getCategoryList(final String category, String type, int page, int count, final HttpCallBack callBack) {
        Call<HttpResultBean<List<GankBean>>> call = BaseAPI.getApiService().getCategoryList(category, type, page, count);
        call.enqueue(new Callback<HttpResultBean<List<GankBean>>>() {
            @Override
            public void onResponse(Call<HttpResultBean<List<GankBean>>> call, Response<HttpResultBean<List<GankBean>>> response) {
                if (response.isSuccessful()) {
                    HttpResultBean<List<GankBean>> httpResultBean = response.body();
                    if (httpResultBean != null) {
                        callBack.onSuccess(2, response.body().getData());
                    } else {
                        callBack.onFailed(0, "获取数据错误");
                    }
                } else {
                    callBack.onFailed(0, "获取数据错误");
                }
            }

            @Override
            public void onFailure(Call<HttpResultBean<List<GankBean>>> call, Throwable t) {
                    callBack.onFailed(1, "网络错误");
            }
        });
        return call;
    }
}
