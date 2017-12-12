package com.example.lyl.myapplication.testblemac;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.UUID;

import static com.example.lyl.myapplication.testmenu.BlueToothControl.isConnected;

/**
 * Created by lyl on 2017/8/11.
 * Service服务类
 */

public class MyService extends Service {
    private static final String TAG = "lyl";
    private BluetoothGatt bluetoothGatt;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothGattService blser;
    private BluetoothGattCharacteristic blstic;
    //设置扫描时间
    private static int STOP_SCAN = 10000;
    //是否正在扫描
    public static boolean isScan = true;

    private MyBinder myBinder = new MyBinder();

    // onBind()（通过bind方式启动一个service回调的方法）。
    //当其他组件调用bindService()方法请求绑定Service时，该方法被回调
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    //onStartCommand()（通过start方式启动一个service时回调的方法）、
    //当其他组件调用startService()方法请求启动Service时，该方法被回调。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //扫描设备回调
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            if (device.getName() != null) {
                if (device.getName().contains("LoRa")) {
                    //停止扫描
                    bluetoothAdapter.stopLeScan(leScanCallback);
                    bluetoothGatt = device.connectGatt(MyService.this, true, bluetoothGattCallback);
                }
            }
        }
    };


    //连接回调
    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothGatt.STATE_CONNECTED) {
                isConnected = true;
                //查询服务
                bluetoothGatt.discoverServices();
                stateListner.state(true);
                stateListner.scanState(false);
                boolean conn=bluetoothGatt.connect();

                Log.e(TAG, "onConnectionStateChange:连接成功 ："+conn);
            } else {
                isConnected = false;
                stateListner.state(false);
                stateListner.scanState(true);
                Log.e(TAG, "onConnectionStateChange:连接失败 ");
            }
            super.onConnectionStateChange(gatt, status, newState);
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.e(TAG, "onServicesDiscovered: ");

            blser=bluetoothGatt.getService(UUID.fromString(""));
            blstic=blser.getCharacteristic(UUID.fromString(""));
            if (blstic!=null){

                bluetoothGatt.setCharacteristicNotification(blstic,true);
            }
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.e(TAG, "onCharacteristicRead: "+characteristic.getValue());
            bluetoothGatt.readCharacteristic(blstic);
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.e(TAG, "onCharacteristicWrite: ");

            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.e(TAG, "onCharacteristicChanged: ");
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.e(TAG, "onDescriptorRead: ");
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.e(TAG, "onDescriptorWrite: ");
            super.onDescriptorWrite(gatt, descriptor, status);
        }
    };


    public void connect(boolean enable) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (enable) {
            isScan = false;
            stateListner.scanState(true);
            bluetoothAdapter.startLeScan(leScanCallback);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isScan = true;
                    Log.e(TAG, "connect: 搜索完成");
                    bluetoothAdapter.stopLeScan(leScanCallback);
                    stateListner.scanState(false);
                }
            }, STOP_SCAN);
        } else {
            Log.e(TAG, "connect: 正在搜索");
            isScan = false;
            bluetoothAdapter.stopLeScan(leScanCallback);
        }
    }

    public boolean isConnected() {
        if (bluetoothGatt.connect()) {
            Log.e(TAG, "isConnected: 连接");
            return true;
        }
        Log.e(TAG, "isConnected: 断开");
        return false;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        if (bluetoothGatt != null) {
            bluetoothGatt.close();

        }
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public MyService getMyService() {
            return MyService.this;
        }
    }


    private StateListner stateListner;

    public void setStateListner(StateListner stateListner) {
        this.stateListner = stateListner;
    }

    public interface StateListner {
        void state(boolean listen);

        void scanState(boolean scanState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
