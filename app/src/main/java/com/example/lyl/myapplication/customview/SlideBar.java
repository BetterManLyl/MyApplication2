package com.example.lyl.myapplication.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.lyl.myapplication.R;

/**
 * @author lyl
 * @date 2017/11/13.
 */

public class SlideBar extends View {

    public static String[] INDEX_STRING = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private Paint paint;
    private int itemWidth;
    private int itemHeight;

    public SlideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight();
        int singleHeight = itemHeight / INDEX_STRING.length;// 获取每一个字母的高度
        for (int i = 0; i < INDEX_STRING.length; i++) {
            float xPos = itemWidth / 2 - paint.measureText(INDEX_STRING[i]) / 2;
            float yPos = singleHeight * i + singleHeight / 2;
            paint.setColor(Color.WHITE);
            paint.setTextSize(40);
            canvas.drawText(INDEX_STRING[i], xPos, yPos, paint);

            paint.reset();// 重置画笔
        }
    }


}
