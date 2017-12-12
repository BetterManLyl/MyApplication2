package com.example.lyl.myapplication.testnetworkstatelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyl.myapplication.R;

/**
 * Created by lyl on 2017/8/8.
 * <p>
 * 监听 网络状态
 */

public class TestNetWork extends AppCompatActivity {
    private TextView tv_network;
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testnetwork);

        register();
        //获取wifimanager
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        tv_network = (TextView) findViewById(R.id.tv_net_state);
        tv_network.setText(ssid);
    }

    private void register() {
        IntentFilter in = new IntentFilter();
        in.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver, in);
    }

    //广播监听网络状态的改变
    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
            wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null && wifiManager != null) {
                if (wifiInfo.getSSID().contains("Ru")) {
                    Toast.makeText(TestNetWork.this, "net is available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TestNetWork.this, "net is not available", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(TestNetWork.this, "net is not available", Toast.LENGTH_SHORT).show();
            }
//            ConnectivityManager connectivityManager = (ConnectivityManager)
//                    getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isAvailable()) {
//                Toast.makeText(TestNetWork.this, "net is available", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(TestNetWork.this, "net is not available", Toast.LENGTH_SHORT).show();
//            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
    }
}
