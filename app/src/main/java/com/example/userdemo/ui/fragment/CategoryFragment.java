package com.example.userdemo.ui.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userdemo.Constants;
import com.example.userdemo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    private String[] titles = {
//            Constants.flagAll,
            Constants.flagAndroid,
            Constants.flagIOS,
            Constants.flagFlutter,
            Constants.flagFrontend,
            Constants.flagBackend,
            Constants.flagApp,
    };

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        initAdapter();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View view) {
        smartTabLayout = view.findViewById(R.id.viewpagertab);
        viewPager = view.findViewById(R.id.viewpager);
    }

    private void initAdapter() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageMargin(20);
        smartTabLayout.setViewPager(viewPager);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CommonFragment.newInstance(titles[position]);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
