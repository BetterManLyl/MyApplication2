package com.example.lyl.myapplication.testbofang;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;


/**
 * Created by lyl on 2017/9/13.
 */

public class RecordActivity2 extends BaseActivity implements View.OnClickListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener{
    private TextView statusView;
    private TextView bufferView;
    private Button playButton;
    private Button pauseButton;
    private MediaPlayer mPlayer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recorder_layout);

        init();
    }

    private void init(){
        statusView=(TextView)findViewById(R.id.statusview);
        bufferView=(TextView)findViewById(R.id.bufferview);
        playButton=(Button)findViewById(R.id.playButton);
        pauseButton=(Button)findViewById(R.id.pauseButton);

        playButton.setEnabled(false);
        pauseButton.setEnabled(false);
        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        mPlayer=new MediaPlayer();

        mPlayer.setOnErrorListener(this);
        mPlayer.setOnBufferingUpdateListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnPreparedListener(this);

        statusView.setText("mediaplayer created");

        try {
            //调用setDataSource方法，传入音频文件的http位置，此时处于Initialized状态
            mPlayer.setDataSource("http://www.runachina.net/weChatServer/upload/voiceFile/20170925104151049.mp3");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        statusView.setText("setDataSource done");
        statusView.setText("calling prepareAsync");
        /**
         * 调用prepareAsync方法，试MediaPlayer对象进入Preparing状态，让内部播放引擎继续未完成的准备工作。
         */
        mPlayer.prepareAsync();
    }

    /**
     * 当prepareAsync方法完成时，将调用onPrepared（）方法，表明音频准备播放。
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO Auto-generated method stub
        statusView.setText("onPrepared called");
        playButton.setEnabled(true);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v==playButton){
            //if(mPlayer.isPlaying()){
            mPlayer.start();
            statusView.setText("start called");
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
            //}
        }
        else if(v==pauseButton){
            mPlayer.pause();
            statusView.setText("pause called");
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
        }



    }

    /**
     * 当MediaPlayer完成播放音频文件时，将调用onCompletion方法。
     * 此时设置“播放”按钮可点击，“暂停”按钮不能点击
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO Auto-generated method stub
        statusView.setText("onCompletion called");
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);
    }

    //当MediaPlayer正在缓冲的时候，将调用该Activity的onBufferingUpdate方法。
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        // TODO Auto-generated method stub
        bufferView.setText(""+percent+"%");

    }

    /**
     * 如果MediaPlayer出现错误，将调用onError方法。
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "比方戳", Toast.LENGTH_SHORT).show();
        return true;
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mPlayer.stop();
    }

    /**
     * 当按手机上的返回按键的时候，会自动调用系统的onKeyDown方法，
     * 而onKeyDown方法右会自动调用onDestroy()方法
     * 销毁该Activity,此时如果onDestroy()方法不重写，那么正在播放
     * 的音乐不会停止，所以这时候要重写onDestroy()方法，
     * 在该方法中，加入mediaplayer.stop()方法，表示按返回键的时候，
     * 会调用mediaPlayer对象的stop方法，
     * 从而停止音乐的播放
     *
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.mp3_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
