package com.example.userdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.userdemo.ui.fragment.CategoryFragment;
import com.example.userdemo.ui.fragment.MainFragment;
import com.example.userdemo.ui.fragment.MeiZhiFragment;
import com.example.userdemo.ui.fragment.MyFragment;
import com.ogaclejapan.smarttablayout.SmartTabIndicationInterpolator;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class NewActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    private String[] flagTab = {
            "首页",
            "分类",
            "妹子",
            "我"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main2);

        initView();
        initAdapter();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        smartTabLayout = findViewById(R.id.viewpagertab);
    }

    private void initAdapter() {
        Log.e("MyPagerAdapter", "initAdapter==========");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new MeiZhiFragment());
        fragments.add(new MyFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setPageMargin(20);
        smartTabLayout.setViewPager(viewPager);
        smartTabLayout.setIndicationInterpolator(SmartTabIndicationInterpolator.LINEAR);
        
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            Log.e("MyPagerAdapter", "size=========="+ fragments.size());
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return flagTab[position];
        }
    }
}
