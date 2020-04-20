package com.example.userdemo.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.userdemo.ui.adapter.RecycleMeiZhiAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiZhiFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener, HttpCallBack {

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private View mView;
    private RecycleMeiZhiAdapter meiZhiAdapter;

    private List<GankBean> mList = new ArrayList<GankBean>();
    private int page = 1;
    private boolean isRefresh = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_mei_zhi, container, false);
        initView(mView);
        initAdapter(mList);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRefresh();
    }

    private void loadData(int page){
        GankApi.getCategoryList("Girl", "Girl", page, Constants.COUNT, this);
    }

    private void initView(View mView){
        swipeToLoadLayout = mView.findViewById(R.id.swipeToLoadLayout);
        recyclerView = mView.findViewById(R.id.swipe_target);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initAdapter(List<GankBean> mList){
        meiZhiAdapter = new RecycleMeiZhiAdapter(getContext(), mList);
        recyclerView.setAdapter(meiZhiAdapter);

    }



    @Override
    public void onLoadMore() {
        isRefresh = false;
        loadData(page);
    }

    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        loadData(page);
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
                meiZhiAdapter.notifyDataSetChanged();
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
