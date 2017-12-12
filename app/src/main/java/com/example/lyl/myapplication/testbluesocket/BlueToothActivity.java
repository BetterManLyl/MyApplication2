package com.example.lyl.myapplication.testbluesocket;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.ConnectionService;

import com.example.lyl.myapplication.R;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.beacon.Beacon;
import com.inuker.bluetooth.library.connect.listener.BluetoothStateListener;
import com.inuker.bluetooth.library.receiver.listener.BluetoothBondListener;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;

/**
 * Created by lyl on 2017/8/9.
 * BluetoothKit框架的使用
 * https://github.com/dingjikerbo/BluetoothKit
 */

@TargetApi(Build.VERSION_CODES.M)
public class BlueToothActivity extends AppCompatActivity {
    /**
     * 创建一个BluetoothClient，建议作为一个全局单例，管理所有BLE设备的连接。
     * 所有接口都通过BluetoothClient调用，涉及的常量如回调的错误码都在Constants类中。
     */

    BluetoothClient mClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        mClient = new BluetoothClient(this);
        //注册蓝牙打开过程监听广播
        mClient.registerBluetoothStateListener(mBluetoothStateListener);

        //注册蓝牙配对过程监听广播
        mClient.registerBluetoothBondListener(mBluetoothBondListener);

        if (isOpen()){


        }else{
            openBlueTooth();

        }        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(3000, 3)   // 先扫BLE设备3次，每次3s
                .searchBluetoothClassicDevice(5000) // 再扫经典蓝牙5s
                .searchBluetoothLeDevice(2000)      // 再扫BLE设备2s
                .build();


        mClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {

            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                Beacon beacon = new Beacon(device.scanRecord);
                BluetoothLog.v(String.format("beacon for %s\n%s", device.getAddress(), beacon.toString()));
            }

            @Override
            public void onSearchStopped() {

            }

            @Override
            public void onSearchCanceled() {

            }
        });
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private final BluetoothStateListener mBluetoothStateListener = new BluetoothStateListener() {
        @Override
        public void onBluetoothStateChanged(boolean openOrClosed) {

        }

    };

    private final BluetoothBondListener mBluetoothBondListener = new BluetoothBondListener() {
        @Override
        public void onBondStateChanged(String mac, int bondState) {
            // bondState = Constants.BOND_NONE, BOND_BONDING, BOND_BONDED
        }
    };

    /**
     * 停止扫描
     */
    public void stopScan() {
        if (mClient != null) {
            mClient.stopSearch();
        }
    }

    /**
     * 判断蓝牙是否打开
     *
     * @return
     */
    public boolean isOpen() {
        if (mClient.isBluetoothOpened()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 关闭蓝牙
     */
    public void closeBlueTooth() {
        if (mClient != null) {

            mClient.closeBluetooth();

        }
    }



    /**
     * 打开蓝牙
     */
    public void openBlueTooth() {
        if (mClient != null) {
            mClient.openBluetooth();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册
        mClient.unregisterBluetoothStateListener(mBluetoothStateListener);
        mClient.unregisterBluetoothBondListener(mBluetoothBondListener);
    }
}
