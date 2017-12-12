package com.example.lyl.myapplication.testbofang;


import android.Manifest;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.utils.MPermissionUtils;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by lyl on 2017/9/13.
 */

public class RecordActivity extends BaseActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private Button play_pause, reset;
    private SeekBar seekbar;
    private boolean ifplay = false;
    private MediaPlayer player = null;
    private String musicName = "1.mp3";
    private boolean iffirst = false;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isChanging = false;//互斥变量，防止定时器与SeekBar拖动时进度冲突
    private ProgressBar progressBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_activity);
        player = new MediaPlayer();
        findViews();// 各组件
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void findViews() {
        play_pause = (Button) findViewById(R.id.play_pause);
        reset = (Button) findViewById(R.id.reset);
        play_pause.setOnClickListener(new MyClick());
        reset.setOnClickListener(new MyClick());

        seekbar = (SeekBar) findViewById(R.id.seekbar);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        seekbar.setOnSeekBarChangeListener(new MySeekbar());
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e(TAG, "onCompletion: ");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e(TAG, "onError: ");
        Toast.makeText(this, "播放出错了", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e(TAG, "onPrepared: ");
    }

    class MyClick implements View.OnClickListener {
        public void onClick(View v) {
            final File file = new File(getVoiceDir(),
                    musicName);
            // 判断有没有要播放的文件
            if (file.exists()) {
                switch (v.getId()) {
                    case R.id.play_pause:

                        MPermissionUtils.requestPermissionsResult(RecordActivity.this, 100, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new MPermissionUtils.OnPermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                if (player != null && !ifplay) {
                                    play_pause.setText("暂停");
                                    if (!iffirst) {
                                        player.reset();
                                        try {
                                            //player.setDataSource(file.getAbsolutePath());
                                            player.setDataSource("http://www.runachina.net/weChatServer/upload/voiceFile/20170925104151043.mp3");
                                            player.prepare();// 准备

                                        } catch (IllegalArgumentException e) {
                                            e.printStackTrace();
                                            Log.e(TAG, "IllegalArgumentException: " );
                                            showToast("播放失败1");
                                        } catch (IllegalStateException e) {
                                            e.printStackTrace();
                                            Log.e(TAG, "IllegalStateException: " );
                                            showToast("播放失败2");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            Log.e(TAG, "IOException: " );
                                            showToast("播放失败3");
                                        }
                                        seekbar.setMax(player.getDuration());//设置进度条,获取文件的总时长
                                        progressBar.setMax(player.getDuration());
                                        //----------定时器记录播放进度---------//
                                        mTimer = new Timer();
                                        mTimerTask = new TimerTask() {
                                            @Override
                                            public void run() {
                                                if (isChanging == true) {
                                                    return;
                                                }
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if (player != null) {
                                                            seekbar.setProgress(player.getCurrentPosition());
                                                            progressBar.setProgress(player.getCurrentPosition());
                                                        }
                                                    }
                                                });
                                            }
                                        };
                                        mTimer.schedule(mTimerTask, 0, 10);
                                        iffirst = true;
                                    }
                                    player.start();// 开始
                                    ifplay = true;
                                } else if (ifplay) {
                                    play_pause.setText("继续");
                                    player.pause();
                                    ifplay = false;
                                }

                            }

                            @Override
                            public void onPermissionDenied() {
                                //弹出提示框
                                MPermissionUtils.showTipsDialog(RecordActivity.this);
                            }
                        });


                        break;
                    case R.id.reset:
                        if (ifplay) {
                            player.seekTo(0);
                        } else {
                            player.reset();
                            try {
                                //player.setDataSource(file.getAbsolutePath());
                                player.setDataSource("http://www.runachina.net/weChatServer/upload/voiceFile/20170925104151042.mp3");
                                player.prepare();// 准备
                                player.start();// 开始
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        }
    }

    //进度条处理
    class MySeekbar implements SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            isChanging = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            player.seekTo(seekbar.getProgress());
            isChanging = false;
        }

    }


    //来电处理
    protected void onDestroy() {

        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (player != null) {
            player = null;
        }
        super.onDestroy();
    }

    protected void onPause() {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
            }
        }
        super.onPause();
    }

    //请求权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onResume() {
        if (player != null) {
            if (!player.isPlaying()) {
                player.start();
            }
        }
        super.onResume();
    }

    /**
     * @return 声音文件的存储目录
     */
    public static String getVoiceDir() {
        return Environment.getExternalStorageDirectory() + "/runaHM/voice/";
    }
}
