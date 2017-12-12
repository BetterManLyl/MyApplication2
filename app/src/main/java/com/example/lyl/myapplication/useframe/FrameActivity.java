package com.example.lyl.myapplication.useframe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.TransitionInflater;

import com.example.lyl.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/1.
 * 框架的使用
 */

public class FrameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_user);
        ButterKnife.bind(this);
        setupWindowAnimations();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupWindowAnimations() {

    }

    @OnClick(R.id.btn_dialog)
    public void onViewClicked() {
        startActivity(new Intent(this, DialogActivity.class));
    }

    @OnClick(R.id.brn_animations)
    public void onViewClickedanimations() {
        startActivity(new Intent(this, AnimationsActivity.class));
    }
}
