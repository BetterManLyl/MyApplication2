package com.example.lyl.myapplication.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.ButterKnife;

/**
 * @author lyl
 * @date 2017/12/6.
 */

public abstract class BaseActivity extends FragmentActivity implements BaseInterface {
    public Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //配置log总开关 false关 true开
        LogUtils.getConfig().setLogSwitch(true);
        ButterKnife.bind(this);
        getExtraValue();
        initView();
        initData();
        KeyboardUtils.showSoftInput(this);
    }


    public void initView() {

    }

    public void initData() {


    }

    public void getExtraValue() {
        intent = this.getIntent();
    }

    /**
     * Toast
     * ToastUtils 工具类
     *
     * @param message 内容
     */
    public void showToast(String message) {
        if (!getIntentState()) {
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        ToastUtils.showShort(message);
    }

    /**
     * 获取网络状态
     *
     * @return
     */
    public boolean getIntentState() {
        if (NetworkUtils.isAvailableByPing()) {
            return true;
        }
        return false;
    }

    /**
     * 获取布局id
     *
     * @return
     */
    @Override
    public abstract int getLayoutId();


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
