package com.example.userdemo.ui.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.example.userdemo.Constants;
import com.example.userdemo.R;
import com.example.userdemo.bean.GankBean;
import com.example.userdemo.http.GankApi;
import com.example.userdemo.http.HttpCallBack;
import com.example.userdemo.ui.adapter.RecycleCommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements OnRefreshListener,  HttpCallBack {

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private RecycleCommonAdapter commonAdapter;

    private int page = 1;
    private List<GankBean> mList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_mei_zhi, container, false);
        initView(mView);
        initAdapter(mList);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRefresh();
    }

    private void initView(View view){
        swipeToLoadLayout = view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = view.findViewById(R.id.swipe_target);
//        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initAdapter(List<GankBean> mList){
        commonAdapter = new RecycleCommonAdapter(mList);
        recyclerView.setAdapter(commonAdapter);
    }

    private void getData() {
        GankApi.getHotData("view", "GanHuo", Constants.COUNT, this);
    }

//    @Override
//    public void onLoadMore() {
//
//    }

    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void onSuccess(int what, Object result) {
        mList.clear();
        List<GankBean> mDatas = (List<GankBean>) result;
        mList.addAll(mDatas);
        commonAdapter.notifyDataSetChanged();
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
    }

    @Override
    public void onFailed(int what, String message) {

    }
}
