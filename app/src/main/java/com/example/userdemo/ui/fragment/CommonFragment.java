package com.example.userdemo.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdemo.R;

/**
 * 包含了ALL，Android, Ios, flutter...
 */
public class CommonFragment extends Fragment {

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
        View view =  inflater.inflate(R.layout.fragment_common, container, false);
        TextView textView = view.findViewById(R.id.tv_title);


        String type = getArguments().getString("type");
        textView.setText(type);
        return view;
    }

}
