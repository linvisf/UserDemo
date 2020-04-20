package com.example.userdemo.ui.presenter.impl;

import android.content.Context;

import com.example.userdemo.Constants;
import com.example.userdemo.bean.GankBean;
import com.example.userdemo.bean.HttpResultBean;
import com.example.userdemo.http.BaseAPI;
import com.example.userdemo.http.GankApi;
import com.example.userdemo.http.HttpCallBack;
import com.example.userdemo.ui.presenter.IMeiZhiPresenter;
import com.example.userdemo.ui.view.IMeiZhiView;

import java.util.List;

public class MeiZhiPresenterImpl extends BasePresenterImpl<IMeiZhiView> implements IMeiZhiPresenter, HttpCallBack {

    private Context context;
    private int page = 1;

    public MeiZhiPresenterImpl(Context context, IMeiZhiView meiZhiView) {
        this.context = context;
        attachView(meiZhiView);
    }

    @Override
    public void getData() {
        GankApi.getCategoryList("Girl","Girl",1, Constants.COUNT, this);
    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void onSuccess(int what, Object result) {

    }

    @Override
    public void onFailed(int what, String message) {
        if (mView == null)
            return;
        mView.hideProgressDialog();
    }
}
