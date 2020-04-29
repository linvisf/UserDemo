package com.example.userdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class FuliDetailActivity extends AppCompatActivity {

    private Button btn_back;
    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli_detail);
        initView();
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        iv_image = findViewById(R.id.iv_image);
    }
}
