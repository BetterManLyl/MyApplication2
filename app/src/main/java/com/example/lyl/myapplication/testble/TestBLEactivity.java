package com.example.lyl.myapplication.testble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lyl on 2017/8/9.
 * 测试低功耗蓝牙
 * BLE分为三部分：Service，Characteristic，Descriptor。
 * 这三部分都是使用UUID来作为唯一标识符加以区分。
 * 一个BLE终端可以包含多个Service，一个Service可以包含多个Characteristic，
 * 而一个Characteristic包含一个value和多个Descriptor，一个Descriptor只包含一个value。
 * UUID格式为：0000ffe1-0000-1000-8000-00805f9b34fb
 * <p>
 * *************************************************************************************
 * 参考博客：
 * http://www.jianshu.com/p/2268cfedc051
 * BLE开发过程
 * 1、判断手机是否支持蓝牙，是否打开蓝牙，是否支持BLE
 * 2、扫描周边BLE设备
 * a、
 * <p>
 * 3、建立连接
 * <p>
 * 理解性的博客
 * http://www.cnblogs.com/dongweiq/p/5772857.html
 */

public class TestBLEactivity extends BaseActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning = true;
    private Handler mHandler;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;
    private List<String> list = new ArrayList<>();
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private String SERVICE_UUID="0000ffe0-0000-1000-8000-00805f9b34fb";
    private String CHARACTER_UUID ="0000ffe1-0000-1000-8000-00805f9b34fb";
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testble);
        listview = (ListView) findViewById(R.id.lv);
        button= (Button) findViewById(R.id.send);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list);
        listview.setAdapter(adapter);
        //获取蓝牙适配器
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        isSupportBLE();
        mHandler = new Handler();
        scanLeDevice(mScanning);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showToast("扫描结束");
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            if (mLeScanCallback == null) {
                Log.e("lyl", "scanLeDevice: 空异常");
                return;
            }
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }

    }


    private BluetoothGatt bluetoothGatt;
    private List<BluetoothGattService> bluetoothGattServices = new ArrayList<>();
    private List<BluetoothGattCharacteristic> bluetoothGattCharacteristics = new ArrayList<>();
    public BluetoothGattCharacteristic bluetoothGattCharacteristic;
    /**
     * 查询回调
     * onLeScan的参数
     * device表示搜索到的ble设备；
     * rssi表示信号强度,是负的，数值越大代表信号强度越大
     * scanRecord表示远程设备广告记录的内容（蓝牙名称）
     */
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi,
                                     byte[] scanRecord) {
                    list.add(device.getAddress());
                    //连接mac地址为:mac地址
                    if (device.getName().contains("LoRa")) {

                        Log.e("lyl", "onLeScan: 扫描到了设备");
//                    //扫描到设备之后就可以连接了  主要用到BluetoothDevice.connectGatt();  返回一个连接的BluetoothGatt
                        bluetoothGatt = device.connectGatt(TestBLEactivity.this, true, bluetoothGattCallback);


                        //判断根据uuid获取到的bluetoothGattCharacteristic具有什么属性
                        int charaProp = bluetoothGattCharacteristic.getProperties();
                        if ((charaProp &  BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            // Log.e("nihao","gattCharacteristic的UUID为:"+gattCharacteristic.getUuid());
                            // Log.e("nihao","gattCharacteristic的属性为:  可读");
                        }
                        if ((charaProp & BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
                            // Log.e("nihao","gattCharacteristic的UUID为:"+gattCharacteristic.getUuid());
                            // Log.e("nihao","gattCharacteristic的属性为:  可写");
                        }
                        if ((charaProp & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                            // Log.e("nihao","gattCharacteristic的UUID为:"+gattCharacteristic.getUuid()+gattCharacteristic);
                            // Log.e("nihao","gattCharacteristic的属性为:  具备通知属性");
                        }

                    } else {
                        return;
                    }
                    //adapter.notifyDataSetChanged();


//                    //拿到连接后的bluetoothGatt 可以获取其下面的服务列表
//                    List<BluetoothGattService> serviceList = bluetoothGatt.getServices();
                    //serviceList.get(0).getCharacteristic(UUID.fromString(""));
                }
            };


    //读操作  读取的结果回调在 #link onCharacteristicRead()
    //只需将相应的特征值传入即可得到该特征值下的数据，
    public void readDatas() {
        bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }

    //写操作 我们可以通过向characteristic写入指令（发送指令）以此来达到控制BLE终端设备的目的：
    //接收到返回数据的前提是我们设置了该特征具有Notification功能，
    //所以完整的写操作代码应该是这样的（注意设置特征Notification的代码要放在最前）：
    public void writeDatas() {
        if (bluetoothGattCharacteristic != null) {
            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
            //将指令放置进特征中
            bluetoothGattCharacteristic.setValue(new byte[]{0x11, (byte) 0xa1});
            //设置回复形式
            bluetoothGattCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
            //写数据
            bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        } else {
            return;
        }
    }

    /**
     * 连接设备的回调
     */
    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        //连接状态改变回调
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                //连接成功后，发送发现服务请求
                Log.e("AAAAAAAA", "启动服务发现:" + bluetoothGatt.discoverServices());

            }
            super.onConnectionStateChange(gatt, status, newState);
        }

        //发现服务回调。就是在我们连接成功之后bluetoothGatt.discoverServices()的回调
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            //查询服务成功后
            if (status == BluetoothGatt.GATT_SUCCESS) {
                bluetoothGattServices = bluetoothGatt.getServices();
                //根据硬件工程师提供的UUID来获取我们想要的BluetoothGattService
                BluetoothGattService bluetoothGattService = bluetoothGatt.getService(UUID.fromString(SERVICE_UUID));
                //根据硬件工程师提供的UUID来获取我们想要的BluetoothGattCharacteristic
                bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(UUID.fromString(CHARACTER_UUID));

//                for (int i = 0; i < bluetoothGattServices.size(); i++) {
//                    //获取该服务的uuid
//                    bluetoothGattServices.get(i).getUuid();
//                    //获取该服务（i）下面的 bluetoothGattCharacteristics集合
//                    bluetoothGattCharacteristics = bluetoothGattServices.get(i).getCharacteristics();
//                    for (int j = 0; j < bluetoothGattCharacteristics.size(); j++) {
//                        //获取属性
//                        int charaProp = bluetoothGattCharacteristics.get(j).getProperties();
//                        //获取该bluetoothGattCharacteristics（i）的UUID
//                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
//                            // Log.e("nihao","gattCharacteristic的UUID为:"+gattCharacteristic.getUuid());
//                            // Log.e("nihao","gattCharacteristic的属性为:  可读");
//                        }
//                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
//                            // Log.e("nihao","gattCharacteristic的UUID为:"+gattCharacteristic.getUuid());
//                            // Log.e("nihao","gattCharacteristic的属性为:  可写");
//                        }
//                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
//                            // Log.e("nihao","gattCharacteristic的UUID为:"+gattCharacteristic.getUuid()+gattCharacteristic);
//                            // Log.e("nihao","gattCharacteristic的属性为:  具备通知属性");
//                        }
//
//                    }
//                }
            } else {
                Log.e("AAAAA", "onservicesdiscovered收到: " + status);
            }
            super.onServicesDiscovered(gatt, status);
        }

        //读操作的回调
        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e("lyl", "onCharacteristicRead: 读取成功" + characteristic.getValue());
            }
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        //写操作的回调
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        //数据返回的回调（此处接收BLE设备返回数据）
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.e("AAAAAAAA", String.valueOf(characteristic.getValue()));
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
        }
    };

    public void write(){
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic,true);
        //将指令放置进来
        bluetoothGattCharacteristic.setValue(new byte[] {0x55, (byte) 0xAA,0x03,0x02,0x03,0x01,0x08, (byte) 0xAA,0x55});
        //设置回复形式
        bluetoothGattCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
        //开始写数据
        bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);

    }    /**
     * 判断安卓设备蓝牙是否已经打开
     *
     * @return 返回蓝牙是否已经开启
     */
    private boolean isBLEEnabled() {
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled();
    }

    /**
     * 判断手机是否支持低功耗蓝牙
     *
     * @return
     */
    public void isSupportBLE() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            showToast("支持低功耗蓝牙");
        } else {
            showToast("不支持低功耗蓝牙");
        }
    }
}
