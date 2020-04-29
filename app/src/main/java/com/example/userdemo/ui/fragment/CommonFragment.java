package com.example.userdemo.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.userdemo.Constants;
import com.example.userdemo.R;
import com.example.userdemo.bean.GankBean;
import com.example.userdemo.http.GankApi;
import com.example.userdemo.http.HttpCallBack;
import com.example.userdemo.ui.adapter.RecycleCommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含了ALL，Android, Ios, flutter...
 */
public class CommonFragment extends Fragment implements OnLoadMoreListener, OnRefreshListener, HttpCallBack {

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private RecycleCommonAdapter commonAdapter;
    private String type;
    private int page = 1;
    private List<GankBean> mList = new ArrayList<>();
    private boolean isRefresh = true;

    public static CommonFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("type", type);
        CommonFragment fragment = new CommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mei_zhi, container, false);

        initView(view);
        initAdapter();
        type = getArguments().getString("type");
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("onHiddenChanged", "hidden=======" + hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("setUserVisibleHint", "isVisibleToUser=======" + isVisibleToUser);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("CommonFragment", "onViewCreated=======type:" + type);
        onRefresh();
    }

    private void initView(View view) {
        swipeToLoadLayout = view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = view.findViewById(R.id.swipe_target);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initAdapter() {
        commonAdapter = new RecycleCommonAdapter(mList);
        recyclerView.setAdapter(commonAdapter);
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
        Log.e("CommonFragment", "onLoadMore=======type:" + type);
        getData(type, page);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        Log.e("CommonFragment", "onRefresh=======type:" + type);
        getData(type, page);
    }

    private void getData(String type, int page) {
        GankApi.getCategoryList("Article", type, page, Constants.COUNT, this);
    }

    @Override
    public void onSuccess(int what, Object result) {
        switch (what){
            case 0:
                String msg = (String) result;
                break;
            case 2:
                if (isRefresh) {
                    page = 2;
                    mList.clear();
                } else {
                    page++;
                }
                List<GankBean> mDatas = (List<GankBean>) result;
                mList.addAll(mDatas);
                commonAdapter.notifyDataSetChanged();
                if (swipeToLoadLayout.isRefreshing()) {
                    swipeToLoadLayout.setRefreshing(false);
                }
                if (swipeToLoadLayout.isLoadingMore()) {
                    swipeToLoadLayout.setLoadingMore(false);
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String message) {
        if (swipeToLoadLayout != null) {
            if (swipeToLoadLayout.isRefreshing()) {
                swipeToLoadLayout.setRefreshing(false);
            }
            if (swipeToLoadLayout.isLoadingMore()) {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }
    }
}
