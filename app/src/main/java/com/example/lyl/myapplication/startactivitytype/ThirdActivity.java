package com.example.lyl.myapplication.startactivitytype;

import android.content.Intent;
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

public class ThirdActivity extends BaseActivity {

    @BindView(R.id.tv_third)
    TextView tvThird;
    @BindView(R.id.btn_start)
    Button btnThird;

    @Override
    public int getLayoutId() {
        return R.layout.third_activity;
    }

    @Override
    public void initView() {
        super.initView();
        LogUtils.eTag("lyl1", this.toString());
        tvThird.setText(this.toString());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.eTag("lyl1", this.toString());
        tvThird.setText(this.toString());
    }

    @OnClick({R.id.tv_third, R.id.btn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startActivity(new Intent(this, StartActivity.class));
                break;
            case R.id.btn_third:
                break;
            default:
                break;
        }
    }
}
