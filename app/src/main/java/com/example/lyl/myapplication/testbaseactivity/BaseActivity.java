package com.example.lyl.myapplication.testbaseactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.lyl.myapplication.R;
import com.wuxiaolong.androidutils.library.LogUtil;

/**
 * Created by lyl on 2017/8/28.
 */

public class BaseActivity extends AppCompatActivity {

    private TextView tv_testbase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("lyl",this.getClass().getSimpleName().toString());


    }

    public void initView() {
        tv_testbase= (TextView) findViewById(R.id.tv_testbase);
        tv_testbase.setText("baseActivity更新");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.connected_menu,menu);
        LogUtil.e("lyl","BaseActivity:onCreateOptionsMenu");
        return true;
    }
    public void setToolBar(Toolbar toolbar) {
        toolbar.setTitle("测试mac地址连接");
        toolbar.setTitleTextColor(Color.WHITE);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
