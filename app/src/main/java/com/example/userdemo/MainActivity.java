package com.example.userdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.userdemo.bean.BannerBean;
import com.example.userdemo.bean.GankBean;
import com.example.userdemo.bean.HttpResultBean;
import com.example.userdemo.http.BaseAPI;
import com.example.userdemo.ui.fragment.CategoryFragment;
import com.example.userdemo.ui.fragment.MainFragment;
import com.example.userdemo.ui.fragment.MeiZhiFragment;
import com.example.userdemo.ui.fragment.MyFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final static String TAG = "MainActivity";

    private Button btn_hot;
    private Button btn_category;
    private Button btn_mei;
    private Button btn_my;
    private FrameLayout layout_constant;

    private FragmentManager fragmentManager;
    private String curFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
//        textView = findViewById(R.id.textView);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                loadData("Girl","Girl",1,10);
//                getBanner();
//            }
//        });

    }

    private void initView() {
        layout_constant = findViewById(R.id.layout_constant);
        btn_hot = findViewById(R.id.btn_hot);
        btn_category = findViewById(R.id.btn_category);
        btn_mei = findViewById(R.id.btn_mei);
        btn_my = findViewById(R.id.btn_my);
        btn_hot.setOnClickListener(this);
        btn_category.setOnClickListener(this);
        btn_mei.setOnClickListener(this);
        btn_my.setOnClickListener(this);
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
        switchFragment("hot");
    }

    private void switchFragment(String name){
        if (curFragmentTag != null && curFragmentTag.equals(name))
            return;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment currFragment = fragmentManager.findFragmentByTag(curFragmentTag);
        if (currFragment != null)
            ft.hide(currFragment);

        Fragment foundFragment = fragmentManager.findFragmentByTag(name);
        if (foundFragment == null) {
            if (name.equals("hot")){
                foundFragment = new MainFragment();
            } else if (name.equals("category")) {
                foundFragment = new CategoryFragment();
            } else if (name.equals("meizhi")) {
                foundFragment = new MeiZhiFragment();
            } else if (name.equals("my")) {
                foundFragment = new MyFragment();
            }
        }
        if (foundFragment.isAdded()) {
            ft.show(foundFragment);
        } else {
            ft.add(R.id.layout_constant, foundFragment, name);
        }
        ft.commit();
        curFragmentTag = name;
    }

    private void getBanner() {
        Call<HttpResultBean<List<BannerBean>>> call = BaseAPI.getApiService().getBannersData();
        call.enqueue(new Callback<HttpResultBean<List<BannerBean>>>() {
            @Override
            public void onResponse(Call<HttpResultBean<List<BannerBean>>> call, Response<HttpResultBean<List<BannerBean>>> response) {
//                textView.setText(response.body().getData().get(0).getTitle());
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
//                    textView.setText(gankBean.getUrl());
                }
            }

            @Override
            public void onFailure(Call<HttpResultBean<List<GankBean>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hot:
                switchFragment("hot");
                break;
            case R.id.btn_category:
                switchFragment("category");
                break;
            case R.id.btn_mei:
                switchFragment("meizhi");
                break;
            case R.id.btn_my:
                switchFragment("my");
                break;
        }
    }
}
