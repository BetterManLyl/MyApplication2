package com.example.lyl.myapplication.Gesture;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/14.
 * 手势密码
 */

public class GestureActivity extends BaseActivity {
    @BindView(R.id.btn_normal)
    Button btnNormal;
    @BindView(R.id.btn_l_style)
    Button btnLStyle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gesture;
    }


    @OnClick({R.id.btn_normal, R.id.btn_l_style})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                startActivity(new Intent(this, NormalActivity.class));
                break;
            case R.id.btn_l_style:
                startActivity(new Intent(this, LStyleActivity.class));
                break;
            default:
                break;
        }
    }
}
