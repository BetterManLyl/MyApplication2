package com.example.lyl.myapplication.startactivitytype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/6.
 */

public class SecondActivity extends BaseActivity {
    @BindView(R.id.btn_second)
    Button btnSecond;
    @BindView(R.id.tv_stirng)
    TextView textView;
    @BindView(R.id.btn_current_activity)
    Button btnCurrentActivity;
    @BindView(R.id.btn_third)
    Button btnThird;

    @Override
    public int getLayoutId() {
        return R.layout.second_activity;
    }

    @Override
    public void initView() {
        super.initView();
        LogUtils.eTag("lyl1", this.toString());
        LogUtils.eTag("lyl1", this.getTaskId());
        textView.setText(this.toString());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogUtils.eTag("lyl1", this.toString());
        LogUtils.eTag("lyl1", this.getTaskId());
        super.onNewIntent(intent);
    }

    @OnClick({R.id.tv_stirng, R.id.btn_second, R.id.btn_current_activity, R.id.btn_third})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_stirng:
                break;
            case R.id.btn_second:
                startActivity(new Intent(this, StartActivity.class));
                break;
            case R.id.btn_current_activity:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.btn_third:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
            default:
                break;
        }
    }
}
