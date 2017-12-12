package com.example.lyl.myapplication.dispatchevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author lyl
 * @date 2017/12/11.
 */

@SuppressLint("AppCompatCustomView")
public class ButtonTouch extends Button {
    public ButtonTouch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.eTag("lyl1", "ButtonTouch:dispatchTouchEvent---->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.eTag("lyl1", "ButtonTouch:dispatchTouchEvent---->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.eTag("lyl1", "ButtonTouch:dispatchTouchEvent---->ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.eTag("lyl1", "ButtonTouch:dispatchTouchEvent---->ACTION_CANCEL");
                break;
            default:
                break;
        }

        //return false ：事件在本层不再继续进行分发，并交由上层控件的onTouchEvent方法进行消费
        //（如果本层控件已经是Activity，那么事件将被系统消费或处理）。即只执行到 ACTION_DOWN
        //return true ：表示该View内部消化掉了所有事件。
        //如果事件分发返回系统默认的 super.dispatchTouchEvent(ev)，
        // 事件将分发给本层的事件拦截onInterceptTouchEvent 方法进行处理
        //return true;
        //return false
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.eTag("lyl1", "ButtonTouch:onTouchEvent---->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.eTag("lyl1", "ButtonTouch:onTouchEvent---->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.eTag("lyl1", "ButtonTouch:onTouchEvent---->ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.eTag("lyl1", "ButtonTouch:onTouchEvent---->ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
