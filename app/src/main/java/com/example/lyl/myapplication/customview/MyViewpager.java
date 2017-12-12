package com.example.lyl.myapplication.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * @author lyl
 * @date 2017/11/8.
 * 自定义viewpager
 */

public class MyViewpager extends ViewGroup {

    private static String TAG = "lyl";
    /**
     * 手势识别
     */
    private GestureDetector ge;

    private Context context;

    private Scroller mScroller;


    /**
     * 实现两个参数的构造方法
     *
     * @param context 上下文
     * @param attrs   属性
     */
    public MyViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        mScroller = new Scroller(context);
        ge = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                Log.e(TAG, "onLongPress: ");
                super.onLongPress(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //scrollBy：相对滑动，相对我们当前的控件多少距离，就滑动多少距离
                //distanceX是我们手滑动的距离，即我们的手相对控件滑动了多少，所以X轴滑动这个距离，Y轴滑动0
                Log.e(TAG, "onScroll: ");
                scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.e(TAG, "onDoubleTap: ");
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.e(TAG, "onDoubleTapEvent: ");
                return true;
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 必须重写onLayout()方法
     *
     * @param changed
     * @param l       left
     * @param t       top
     * @param r       right
     * @param b       bottom
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.e(TAG, "l=" + l);
        Log.e(TAG, "t=" + t);
        Log.e(TAG, "r=" + r);
        Log.e(TAG, "b=" + b);
        for (int i = 0; i < getChildCount(); i++) {
            View view = this.getChildAt(i);
            //为每个view进行布局
            view.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将事件传给GestureDetector
        ge.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                int scrollx = getScrollX();
                //屏幕宽度的一半 +  滑动的距离  /  屏幕的宽度
                int pos = (scrollx + getWidth() / 2) / getWidth();
                //滑到最后一张的时候，不能出边界
                if (pos >= getChildCount()) {
                    pos = getChildCount() - 1;
                    Toast.makeText(context, "滑动到最后一张了", Toast.LENGTH_SHORT).show();
                }
                //绝对滑动，直接滑到指定的x值  不能实现平滑的滑动效果
                //  scrollTo(pos * getWidth(), 0);
                mScroller.startScroll(scrollx, 0, pos * getWidth() - scrollx, 0, Math.abs(pos * getWidth()));
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                break;

            default:
                break;
        }
        return true;
    }


    /**
     * 如果当前方法返回true 则触发onTouchEvent方法
     * 返回false 则不拦截事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }
}
