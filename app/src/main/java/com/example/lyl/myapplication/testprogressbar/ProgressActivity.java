package com.example.lyl.myapplication.testprogressbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lyl on 2017/9/14.
 * 测试progressbar的使用
 */

public class ProgressActivity extends BaseActivity {

    private ProgressBar progressBar1, progressBar2, progressBar3;
    private SeekBar seekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_progress_activity);


        progressBar1 = (ProgressBar) findViewById(R.id.progress1);
        progressBar2 = (ProgressBar) findViewById(R.id.progress2);
        progressBar3 = (ProgressBar) findViewById(R.id.progress3);

        seekBar = (SeekBar) findViewById(R.id.seekbar);

        initProgress();

        //开始任务
        initTask();
    }

    int i = 0;

    private void initTask() {
        progressBar1.setProgress(0);
        final Timer timer = new Timer();
        TimerTask timrTask = new TimerTask() {
            @Override
            public void run() {
                if (i > 100) {
                    timer.cancel();
                    return;
                }
                //setProgress();该方法已经处理了让progressbar在主线程中进行
                progressBar1.setProgress(i);
                seekBar.setProgress(i);
                seekBar.setSecondaryProgress(i+10);
                progressBar1.setSecondaryProgress(i+10);
                Log.e(TAG, "run: " + Thread.currentThread());
                ++i;
            }
        };
        // 参数：
        // 10，延时10ms后执行。
        // 100，每隔100ms执行1次task。
        timer.schedule(timrTask, 10, 100);
    }

    private void initProgress() {

        progressBar1.setMax(80);
        progressBar2.setMax(60);
        progressBar3.setMax(50);
        seekBar.setMax(100);
        seekBar.setProgress(20);

        //监听seekbar状态的改变
        seekBar.setOnSeekBarChangeListener(new SeekBarStateChange());
        progressBar1.setProgress(60);
        progressBar2.setProgress(61);
        progressBar3.setProgress(61);

        int progressPosition = progressBar1.getProgress();
        Log.e(TAG, "progressPosition: " + progressPosition);
    }


    class SeekBarStateChange implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {//手指滑动改变的时候出发
            Log.e(TAG, "onProgressChanged: " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {//手指按压的时候出发
            Log.e(TAG, "onStartTrackingTouch: ");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {//手指抬起的时候出发
            Log.e(TAG, "onStopTrackingTouch: ");
        }
    }
}
