package com.example.userdemo.ui.view;

import com.example.userdemo.bean.GankBean;

import java.util.List;

public interface IMeiZhiView extends IBaseView{

    void initAdapter(List<GankBean> list);

}
