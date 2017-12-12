package com.example.lyl.myapplication.startactivitytype;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/6.
 */

public class StartActivity extends BaseActivity {
    @BindView(R.id.tv_activity)
    TextView tvActivity;
    @BindView(R.id.btn_standard_activity)
    Button btnActivity;
    @BindView(R.id.btn_singletop_activity)
    Button btnSingletopActivity;
    @BindView(R.id.btn_singletask_activity)
    Button btnSingletaskActivity;
    @BindView(R.id.btn_singleinstance_activity)
    Button btnSingleinstanceActivity;
    @BindView(R.id.btn_click)
    Button btnClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            LogUtils.eTag("lyl1", "savedInstanceState");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.eTag("lyl1", "outState");
    }

    @Override
    public int getLayoutId() {
        return R.layout.start_activity;
    }

    @Override
    public void initView() {
        super.initView();
        LogUtils.eTag("lyl1", this.toString());
        LogUtils.eTag("lyl1", this.getTaskId());
        tvActivity.setText(this.toString());
        /**
         * 2、再执行setOnClickListener监听事件
         */
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.eTag("test", "cansal button --->onclick");
            }
        });
        /**
         * 1、先执行setOnTouchListener监听事件
         */
        btnClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    LogUtils.eTag("test", "cansal button ---> cancel");
                    btnClick.setBackgroundResource(R.color.blue);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    LogUtils.eTag("test", "cansal button ---> down");
                    btnClick.setBackgroundResource(R.color.color12db62);
                }

                /**
                 * 如果返回 true。则 setOnTouchListener 消费了该事件，不再继续往下传递事件，
                 * setOnClickListener 监听将不在执行
                 */
                // return true;
                return false;

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogUtils.eTag("lyl1", this.toString());
        LogUtils.eTag("lyl1", this.getTaskId());
        super.onNewIntent(intent);
    }

    @OnClick({R.id.tv_activity, R.id.btn_standard_activity, R.id.btn_singletop_activity,
            R.id.btn_singletask_activity, R.id.btn_singleinstance_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_activity:
                break;
            case R.id.btn_standard_activity:
                startActivity(new Intent(this, SecondActivity.class));

                break;
            case R.id.btn_singletop_activity:
                startActivity(new Intent(this, SecondActivity.class));

                break;
            case R.id.btn_singletask_activity:
                startActivity(new Intent(this, SecondActivity.class));

                break;
            case R.id.btn_singleinstance_activity:
                startActivity(new Intent(this, StartActivity.class));

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("lyl1", "onTouchEvent");
        return super.onTouchEvent(event);
    }

}
