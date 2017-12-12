package com.example.lyl.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lyl.myapplication.testblemac.MyService;
import com.example.lyl.myapplication.testmenu.BlueToothControl;

import static com.example.lyl.myapplication.testmenu.BlueToothControl.isConnected;

/**
 * Created by lyl on 2017/8/9.
 */

public abstract class BaseActivity extends AppCompatActivity implements MyService.StateListner {
    public static final String TAG = "lyl";

    private String mac = "lyl";

    private BlueToothControl blueToothControl;
    //是否开启扫描
    private boolean isScan = true;

    private MyService myService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blueToothControl = BlueToothControl.getInstance();

    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    public void setProgressBar(boolean is) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        if (is) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void state(boolean listen) {
        if (listen) {
            Log.e(TAG, "state:连接成功 ");
            isConnected = true;
            invalidateOptionsMenu();
        } else {
            Log.e(TAG, "state:连接失败 ");
            isConnected = false;
            //刷新菜单
            invalidateOptionsMenu();
        }
    }


    @Override
    public void scanState(boolean scanState) {
        setProgressBar(scanState);
    }


    public void bindService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, this.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder) service).getMyService();
            myService.setStateListner(BaseActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: service异常");
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connected:
                //开始连接
                if (!blueToothControl.isSupport()) {
                    return false;
                }
                if (!isConnected) {
                    if (blueToothControl.isOpen()) {
                        myService.connect(MyService.isScan);
                    } else {
                        showToast("请打开蓝牙");
                    }
                } else {
                    showToast("已经连接");
                }

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.connected_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.menu_connected);
        if (isConnected) {
            menuItem.setIcon(R.mipmap.wifi_connect);
        } else {
            menuItem.setIcon(R.mipmap.wifi_noconnect);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService();
    }
}
