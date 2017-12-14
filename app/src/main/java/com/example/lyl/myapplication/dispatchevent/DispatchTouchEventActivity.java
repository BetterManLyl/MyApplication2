package com.example.lyl.myapplication.dispatchevent;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;
import com.example.lyl.myapplication.fanxing.ListConnection;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/11.
 */

public class DispatchTouchEventActivity extends BaseActivity {
    @BindView(R.id.btn_dispatchTouchEvent)
    Button btnDispatchTouchEvent;
    @BindView(R.id.btn_onInterceptTouchEvent)
    Button btnOnInterceptTouchEvent;
    @BindView(R.id.btn_onTouchEvent)
    Button btnOnTouchEvent;
    @BindView(R.id.ll_root_view)
    LinearLayout llRootView;

    @Override
    public int getLayoutId() {
        return R.layout.dispatch_activity;
    }


    @Override
    public void initView() {
        ListConnection<String> listConnection=new ListConnection<>();
        listConnection.say("syso");

        super.initView();
        llRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.eTag("lyl1","llRootView:onTouch");
                return true;
            }
        });

        btnDispatchTouchEvent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.eTag("lyl1","btnDispatchTouchEvent:onTouch");
                return false;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.eTag("lyl1","dispatchTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.eTag("lyl1","dispatchTouchEvent:ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.eTag("lyl1","dispatchTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.eTag("lyl1","dispatchTouchEvent:ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.eTag("lyl1","onTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.eTag("lyl1","onTouchEvent:ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.eTag("lyl1","onTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.eTag("lyl1","onTouchEvent:ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    @OnClick({R.id.btn_dispatchTouchEvent, R.id.btn_onInterceptTouchEvent, R.id.btn_onTouchEvent, R.id.ll_root_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dispatchTouchEvent:
                break;
            case R.id.btn_onInterceptTouchEvent:
                break;
            case R.id.btn_onTouchEvent:
                break;
            case R.id.ll_root_view:
                break;
            default:
                break;
        }
    }
}
