package com.example.lyl.myapplication.testcountdow;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.event.EventTwo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lyl on 2017/9/5.
 * 倒计时
 */

public class CountDownActivity extends AppCompatActivity {
    private TextView tvtime1, tvtime2, tvtime3;
    private Button btn_event;
    private long time = 0;
    Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cnuntdown);
        tvtime1 = (TextView) findViewById(R.id.tvtime1);
        tvtime2 = (TextView) findViewById(R.id.tvtime2);
        tvtime3 = (TextView) findViewById(R.id.tvtime3);
        btn_event = (Button) findViewById(R.id.btn_event);
        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventTwo());
            }
        });
        handler.postDelayed(runnable, 1000);//1s之后执行runnable方法
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            time--;
            String formatLongToTimeStr = formatLongToTimeStr(time);
            String[] split = formatLongToTimeStr.split("：");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    tvtime1.setText(split[0] );
                }
                if (i == 1) {
                    tvtime2.setText(split[1] );
                }
                if (i == 2) {
                    tvtime3.setText(split[2] );
                }

            }
            if (time > 0) {
                handler.postDelayed(this, 1000);
            }
        }
    };

    public String formatLongToTimeStr(Long l) {
        if (l>0){
            int hour = 0;
            int minute = 0;
            int second = 0;
            second = l.intValue();
            if (second > 60) {
                minute = second / 60;         //取整
                second = second % 60;         //取余
            }

            if (minute > 60) {
                hour = minute / 60;
                minute = minute % 60;
            }
            String strtime = hour + "：" + minute + "：" + second;
            return strtime;

        }else{
            return "00" + "：" + "00" + "：" + "00";
        }
    }
}
