package com.example.userdemo.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.userdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_main, container, false);
        ImageView imageView = mView.findViewById(R.id.iv_image);

        Glide.with(this).load("https://ae01.alicdn.com/kf/U1dff91287db04ea9b073a580498d13711.jpg").into(imageView);
        return mView;
    }

}
