package com.example.userdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.userdemo.bean.BannerBean;
import com.example.userdemo.bean.GankBean;
import com.example.userdemo.bean.HttpResultBean;
import com.example.userdemo.http.BaseAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadData("Girl","Girl",1,10);
                getBanner();
            }
        });

    }

    private void getBanner() {
        Call<HttpResultBean<List<BannerBean>>> call = BaseAPI.getApiService().getBannersData();
        call.enqueue(new Callback<HttpResultBean<List<BannerBean>>>() {
            @Override
            public void onResponse(Call<HttpResultBean<List<BannerBean>>> call, Response<HttpResultBean<List<BannerBean>>> response) {
                textView.setText(response.body().getData().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<HttpResultBean<List<BannerBean>>> call, Throwable t) {

            }
        });
    }


    private void loadData(String category, String type, int page, int count) {

        Call<HttpResultBean<List<GankBean>>> call = BaseAPI.getApiService().getCategoryList(category,type,page,count);
        call.enqueue(new Callback<HttpResultBean<List<GankBean>>>() {
            @Override
            public void onResponse(Call<HttpResultBean<List<GankBean>>> call, Response<HttpResultBean<List<GankBean>>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "=====================================" );
                    GankBean gankBean = response.body().getData().get(0);
                    int stauts = response.body().getStatus();
                    Log.d(TAG, "stauts" + stauts);
                    Log.d(TAG, "url" + gankBean.getUrl());
                    textView.setText(gankBean.getUrl());
                }
            }

            @Override
            public void onFailure(Call<HttpResultBean<List<GankBean>>> call, Throwable t) {

            }
        });
    }
}
