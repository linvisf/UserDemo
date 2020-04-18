package com.example.userdemo.http;

/**
 *  网络回调
 */
public interface HttpCallBack<T> {

    /**  成功回调 */
    void onSuccess(int what, T result);

    /**  成功回调 */
    void onFailed(int what, String message);

}
