package com.example.lyl.myapplication.testhandler;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.CleanUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.example.lyl.myapplication.R;
import com.wuxiaolong.androidutils.library.ActivityManagerUtil;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lyl on 2017/7/20.
 * <p>
 * Hnadler
 * 简单的消息传输机制
 */

public class TestHandlerActivity extends AppCompatActivity {
    private TextView tv_handler, tv_handler_two, tv_handler_three, tv_thread_handler, tv_thread_timer;
    private static Handler handler;
    private Runnable runnable;
    private ExecutorService executor;
    private Button btn_stop,test_util;
    public ActivityManagerUtil activityManagerUtil;
    private static final String TAG="LYL";private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testhandler);
        ToastUtils.setGravity(Gravity.CENTER,0,0);
        ToastUtils.setBgColor(R.color.bj_color);
        activityManagerUtil = ActivityManagerUtil.getInstance();
        activityManagerUtil.pushOneActivity(this);
        //executor = Executors.newCachedThreadPool();
        tv_handler = (TextView) findViewById(R.id.tv_handler);
        tv_handler_two = (TextView) findViewById(R.id.tv_handler_two);
        tv_handler_three = (TextView) findViewById(R.id.tv_handler_three);
        tv_thread_handler = (TextView) findViewById(R.id.tv_thread_handler);
        tv_thread_timer = (TextView) findViewById(R.id.tv_thread_timer);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        test_util= (Button) findViewById(R.id.test_util);
        image= (ImageView) findViewById(R.id.image);
        test_util.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityManagerUtil.appExit();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler != null && runnable != null) {
                    handler.removeCallbacks(runnable);
                }
            }
        });

        SPUtils.getInstance("lyl").put("key","lyl");

        String bb=SPUtils.getInstance("lyl").getString("key");

        Log.e(TAG, "isAppRoot: "+ AppUtils.isAppRoot());
        Log.e(TAG, "getAppName: "+ AppUtils.getAppName());
        Log.e(TAG, "getAppPath: "+ AppUtils.getAppPath());
        Log.e(TAG, "getAppSignature: "+ AppUtils.getAppSignature());
        Log.e(TAG, "getStatusBarHeight: "+ BarUtils.getStatusBarHeight());
        Drawable d=AppUtils.getAppIcon();
        image.setImageDrawable(d);

        KeyboardUtils.showSoftInput(this);
        Toast.makeText(this, "bb:"+bb, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "aa"+AppUtils.getAppPackageName(), Toast.LENGTH_SHORT).show();
        CleanUtils.cleanExternalCache();
        tv_handler.setText("handler");

        //子线程做UI的更新认为不安全，需要在主线程才能做更新
//        tv_handler.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv_handler.setText("new handler");
//                    }
//                }).start();
//            }
//        });

        //Looper

        /**
         * {@link #onDestroy()}.
         */
        runnable = new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "new handler";
                handler.sendMessage(message);
                /**
                 * 每个1s更新一下tv_handler内容，循环更新
                 */
                handler.postDelayed(this, 1000);
            }
        };
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                if (bundle != null) {
                    Toast.makeText(TestHandlerActivity.this, "Bundle:" + bundle.getString("key"), Toast.LENGTH_SHORT).show();
                }
                if (msg.what == 1) {
                    if (tv_handler.getText().toString().equals("new handler")) {
                        tv_handler.setText("handler");
                    } else {
                        tv_handler.setText(msg.obj.toString());
                    }
                }
            }
        };


        tv_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "new handler";
                handler.sendMessage(message);
            }
        });


        /**
         * 3s后执行tv_handler的UI更新
         */
        tv_handler_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(runnable, 3000);
            }
        });

        /**
         * handler携带bundle传值操作
         */
        tv_handler_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这三个message创建的方式相同
                // Message message=new Message();
                // Message message=Message.obtain();
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "new handler";
                Bundle bundle = new Bundle();
                bundle.putString("key", "new handler");
                message.setData(bundle);
                //这两个发送消的最终处理方式是一样的
                handler.sendMessage(message);
                // handler.sendEmptyMessage(1);
            }
        });

        tv_thread_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new
            }
        });
//        tv_thread_timer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (handler!=null){
//
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//
//                            Looper.prepare();
//                            Toast.makeText(TestHandlerActivity.this, "i"+1, Toast.LENGTH_SHORT).show();
//                            handler.postDelayed(this,1000);
//
//                        }
//                    },1000);
//                    Looper.loop();
//                }
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭线程
        handler.removeCallbacks(runnable);
    }


    class A {
        A() {

        }

        Handler handler = new Handler() {

        };
    }
}
