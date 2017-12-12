package com.example.lyl.myapplication.testbaseactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.example.lyl.myapplication.R;
import com.wuxiaolong.androidutils.library.LogUtil;

/**
 * Created by lyl on 2017/8/28.
 * 测试基类的使用 测试网络状态改变 注册广播
 */

public class SubClass1 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_base);
        initView();
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar);
        LogUtil.e("lyl"," SubClass");
        register();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.connected_menu,menu);
        LogUtil.e("lyl","SubClass1:onCreateOptionsMenu");
        return true;
    }
    /**
     * 注册广播，监听网络状态的变化
     */
    private void register() {
        Log.e("lyl", "register: 注册了广播" );
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }
    /**
     * 广播
     */
    private ConnectivityManager connectivityManager;
    private NetworkInfo info;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                Log.e("lyl", "网络状态已经改变");
                connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                if(info != null && info.isAvailable()) {
                    String name = info.getTypeName();
                    Log.e("lyl", "当前网络名称：" + name);
                } else {
                    Log.e("lyl", "没有可用网络");
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
