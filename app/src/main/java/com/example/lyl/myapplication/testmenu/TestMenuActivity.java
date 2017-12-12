package com.example.lyl.myapplication.testmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyl.myapplication.R;

/**
 * Created by lyl on 2017/8/6.
 * <p>
 * menu 菜单测试
 */

public class TestMenuActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private TextView tv_test;

    private EditText ed_username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_test = (TextView) findViewById(R.id.tv_test);
        toolbar.setTitle("Menu测试");
        //修改返回键默认图标
      //  toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        if (toolbar != null) {
            //使用Toolbar代替ActionBar需要调用该方法
            this.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                           }
        });
        // 打开左侧菜单选项
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_test.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_test.setText("修改过后的");
                i=1;
                //在textview 中调用supportInvalidateOptionsMenu()方法，通知系统刷新菜单，
                //系统会回调onPrepareOptionsMenu 方法，在这里面根据自己的逻辑，进行菜单的处理。
                supportInvalidateOptionsMenu();
            }
        }, 1000);

        toolbar.setLogo(R.mipmap.ic_launcher);
        //toolBar返回键监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestMenuActivity.this, "点击了返回键", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_one:
                Toast.makeText(this, "点击了1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_two:
                Toast.makeText(this, "点击了2", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    int i = 0;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem me = menu.findItem(R.id.menu_one);
        if (i == 0) {
            me.setIcon(R.mipmap.ic_launcher);
        } else {
            me.setIcon(R.mipmap.wifi_connect);
        }
        return true;
    }
}
