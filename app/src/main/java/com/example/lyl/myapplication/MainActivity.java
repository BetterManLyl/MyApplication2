
package com.example.lyl.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lyl.myapplication.AndroidChartActivity.AndroidChartActivity;
import com.example.lyl.myapplication.bmob.BmobMainActivity;
import com.example.lyl.myapplication.customview.CustomView;
import com.example.lyl.myapplication.dispatchevent.DispatchTouchEventActivity;
import com.example.lyl.myapplication.event.EventOne;
import com.example.lyl.myapplication.event.EventTwo;
import com.example.lyl.myapplication.layoutinflate.InflateActivity;
import com.example.lyl.myapplication.okhttp.OkHttpActivity;
import com.example.lyl.myapplication.retrofit.FilePostActivity;
import com.example.lyl.myapplication.rxandroid.RxAndroidActivity;
import com.example.lyl.myapplication.rxjava.RxJavaActivity;
import com.example.lyl.myapplication.startactivitytype.StartActivity;
import com.example.lyl.myapplication.tablayout.TabTypeActivity;
import com.example.lyl.myapplication.testbaseactivity.SubClass1;
import com.example.lyl.myapplication.testble.TestBLEactivity;
import com.example.lyl.myapplication.testblemac.BLEmacConnectedActivity;
import com.example.lyl.myapplication.testbofang.RecordActivity;
import com.example.lyl.myapplication.testcountdow.CountDownActivity;
import com.example.lyl.myapplication.testdialog.DialogActivity;
import com.example.lyl.myapplication.testexpandlistview.TestExpandlistview;
import com.example.lyl.myapplication.testexpandlistview.TestExpandlistview2;
import com.example.lyl.myapplication.testhandler.TestHandlerActivity;
import com.example.lyl.myapplication.testpermissionrequest.PermissionRequestActivity;
import com.example.lyl.myapplication.testprogressbar.ProgressActivity;
import com.example.lyl.myapplication.testrecyclerandgridview.RecyclerViewActivity;
import com.example.lyl.myapplication.text_database.DataBaseActivity;
import com.example.lyl.myapplication.useframe.FrameActivity;
import com.wuxiaolong.androidutils.library.ActivityManagerUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * 首页
 */
public class MainActivity extends Activity implements View.OnClickListener {


    private Button btn_socket, btn_handler, btn_dialog, socket_client, menu,
            netListener, blue_tooth, test_ble, test_mac, test_database, test_base,
            test_countdown, test_expandlist, test_recycler, seek_bar, progress_btn,
            expendlist2, custom_view, btn_permission, btn_rxjava, rxandroid, androidChart,
            okhttp, btn_retrofit, btn_frame,recyclerview,layout_inflate,btn_startactivity_way,
            btn_tablayout,btn_touchevent,btn_bmob;


    private byte[] bbb = new byte[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //将该activity加入管理
        ActivityManagerUtil.getInstance().pushOneActivity(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        btn_socket = (Button) findViewById(R.id.btn_socket);
        btn_handler = (Button) findViewById(R.id.btn_handler);
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        socket_client = (Button) findViewById(R.id.socket_client);
        menu = (Button) findViewById(R.id.menu);
        netListener = (Button) findViewById(R.id.netListener);
        blue_tooth = (Button) findViewById(R.id.test_bluetooth);
        test_ble = (Button) findViewById(R.id.test_bluetoothble);
        test_mac = (Button) findViewById(R.id.test_mac);
        test_database = (Button) findViewById(R.id.test_databae);
        test_base = (Button) findViewById(R.id.test_base);
        test_countdown = (Button) findViewById(R.id.test_countdown);
        test_expandlist = (Button) findViewById(R.id.test_expandlist);
        test_recycler = (Button) findViewById(R.id.test_recycler);
        seek_bar = (Button) findViewById(R.id.test_record);
        progress_btn = (Button) findViewById(R.id.test_progress);
        expendlist2 = (Button) findViewById(R.id.test_expandlist2);
        custom_view = (Button) findViewById(R.id.custom_view);
        btn_permission = (Button) findViewById(R.id.btn_permission);
        btn_rxjava = (Button) findViewById(R.id.btn_rxjava);
        rxandroid = (Button) findViewById(R.id.btn_rxandroid);
        androidChart = (Button) findViewById(R.id.chart);
        okhttp = (Button) findViewById(R.id.okhttp);
        btn_retrofit = (Button) findViewById(R.id.btn_retrofit);
        btn_frame = (Button) findViewById(R.id.btn_frame);
        recyclerview= (Button) findViewById(R.id.recyclerview);
        layout_inflate= (Button) findViewById(R.id.layout_inflate);
        btn_startactivity_way= (Button) findViewById(R.id.btn_startactivity_way);
        btn_tablayout= (Button) findViewById(R.id.btn_tablayout);
        btn_touchevent= (Button) findViewById(R.id.btn_touchevent);
        btn_bmob= (Button) findViewById(R.id.btn_bmob);
        btn_bmob.setOnClickListener(this);
        btn_touchevent.setOnClickListener(this);
        btn_tablayout.setOnClickListener(this);
        btn_startactivity_way.setOnClickListener(this);
        layout_inflate.setOnClickListener(this);
        recyclerview.setOnClickListener(this);
        btn_frame.setOnClickListener(this);
        btn_retrofit.setOnClickListener(this);
        okhttp.setOnClickListener(this);
        androidChart.setOnClickListener(this);
        rxandroid.setOnClickListener(this);
        btn_rxjava.setOnClickListener(this);
        progress_btn.setOnClickListener(this);
        seek_bar.setOnClickListener(this);
        test_countdown.setOnClickListener(this);
        test_database.setOnClickListener(this);
        test_mac.setOnClickListener(this);
        netListener.setOnClickListener(this);
        test_ble.setOnClickListener(this);
        btn_socket.setOnClickListener(this);
        btn_handler.setOnClickListener(this);
        btn_dialog.setOnClickListener(this);
        socket_client.setOnClickListener(this);
        menu.setOnClickListener(this);
        blue_tooth.setOnClickListener(this);
        test_base.setOnClickListener(this);
        test_expandlist.setOnClickListener(this);
        test_recycler.setOnClickListener(this);
        btn_permission.setOnClickListener(this);
        expendlist2.setOnClickListener(this);
        custom_view.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_socket:
                startActivity(new Intent(this, com.example.lyl.myapplication.testsocket.MainActivity.class));
                break;
            case R.id.btn_handler:
                startActivity(new Intent(this, TestHandlerActivity.class));
                break;
            case R.id.btn_dialog:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.socket_client:
                startActivity(new Intent(this, com.example.lyl.myapplication.socketclient.MainActivity.class));
                break;
            case R.id.menu:
                startActivity(new Intent(this, com.example.lyl.myapplication.testmenu.TestMenuActivity.class));
                break;
            case R.id.netListener:
                startActivity(new Intent(this, com.example.lyl.myapplication.testnetworkstatelistener.TestNetWork.class));
                break;
            case R.id.test_bluetooth:
                startActivity(new Intent(this, com.example.lyl.myapplication.testbluesocket.BlueToothActivity.class));
                break;
            case R.id.test_bluetoothble:
                startActivity(new Intent(this, TestBLEactivity.class));
                break;
            case R.id.test_mac:
                startActivity(new Intent(this, BLEmacConnectedActivity.class));
                break;
            case R.id.test_databae:
                startActivity(new Intent(this, DataBaseActivity.class));
                break;
            case R.id.test_base:
                startActivity(new Intent(this, SubClass1.class));
                break;
            case R.id.test_countdown:
                startActivity(new Intent(this, CountDownActivity.class));
                break;
            case R.id.test_expandlist:
                startActivity(new Intent(this, TestExpandlistview.class));
                break;
            case R.id.test_recycler:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.test_record:
                startActivity(new Intent(this, RecordActivity.class));
                break;
            case R.id.test_progress:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case R.id.test_expandlist2:
                startActivity(new Intent(this, TestExpandlistview2.class));
                break;
            case R.id.custom_view:
                startActivity(new Intent(this, CustomView.class));
                break;
            case R.id.btn_permission:
                startActivity(new Intent(this, PermissionRequestActivity.class));
                break;
            case R.id.btn_rxjava:
                startActivity(new Intent(this, RxJavaActivity.class));
                break;
            case R.id.btn_rxandroid:
                startActivity(new Intent(this, RxAndroidActivity.class));
                break;
            case R.id.chart:
                startActivity(new Intent(this, AndroidChartActivity.class));
                break;
            case R.id.okhttp:
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
            case R.id.btn_retrofit:
                startActivity(new Intent(this, FilePostActivity.class));
                break;
            case R.id.btn_frame:
                startActivity(new Intent(this, FrameActivity.class));
                break;
            case R.id.recyclerview:
                startActivity(new Intent(this, com.example.lyl.myapplication.recyclerview.RecyclerViewActivity.class));
                break;
            case R.id.layout_inflate:
                startActivity(new Intent(this, InflateActivity.class));
                break;
            case R.id.btn_startactivity_way:
                startActivity(new Intent(this, StartActivity.class));
                break;
            case R.id.btn_tablayout:
                startActivity(new Intent(this, TabTypeActivity.class));
                break;
            case R.id.btn_touchevent:
                startActivity(new Intent(this, DispatchTouchEventActivity.class));
                break;
            case R.id.btn_bmob:
                startActivity(new Intent(this, BmobMainActivity.class));
                break;
            default:
                break;
        }
    }

    @Subscribe
    public void onEvent(EventOne eventOne) {
        Toast.makeText(this, "eventOne", Toast.LENGTH_SHORT).show();

    }

    @Subscribe
    public void onEvent(EventTwo eventOne) {
        Toast.makeText(this, "eventTwo", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
