package com.example.userdemo.ui.presenter.impl;

import com.example.userdemo.ui.presenter.IBasePresenter;

public class BasePresenterImpl<T> implements IBasePresenter {

    public T mView;

    protected void attachView(T mView) {
        this.mView = mView;
    }
    @Override
    public void detachView() {
        this.mView = null;
    }
}
