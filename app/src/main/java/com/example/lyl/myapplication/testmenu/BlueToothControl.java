package com.example.lyl.myapplication.testmenu;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by lyl on 2017/8/11.
 */

public class BlueToothControl {

    //定义一个全局变量，用来存储连接的状态
    public static boolean isConnected=false;

    public static BlueToothControl blueToothControl;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private BlueToothControl() {

    }

    public static BlueToothControl getInstance() {

        if (blueToothControl == null) {
            blueToothControl = new BlueToothControl();
        }
        return blueToothControl;
    }

    /**
     * 判断是否支持蓝牙
     *
     * @return
     */
    public boolean isSupport() {
        if (bluetoothAdapter == null) return false;
        return true;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        if (bluetoothAdapter == null) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        return bluetoothAdapter;
    }

    /**
     * 判断是否打开蓝牙
     *
     * @return
     */
    public boolean isOpen() {
        if (bluetoothAdapter.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }
}
