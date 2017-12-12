package com.example.lyl.myapplication.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lyl.myapplication.R;

/**
 * @author lyl
 * @date 2017/11/7.
 */

public class MyView extends View {

    private Bitmap bg;
    private String name;
    private float text_size;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取属性的三种方式
        //1、用命名空间获取
//        String name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "name");
//        String bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "bg");
//        Log.e("lyl", "name: " + name);
//        Log.e("lyl", "bg: " + bg);
        //2、遍历属性集合
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            Log.e("lyl", attrs.getAttributeName(i) + "------》" + attrs.getAttributeValue(i));
        }
        //3、使用系统工具
        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        for (int i = 0; i < ty.getIndexCount(); i++) {

            int index = ty.getIndex(i);
            switch (index) {
                case R.styleable.MyView_bg:
                    Drawable dbg = ty.getDrawable(index);
                    BitmapDrawable bit = (BitmapDrawable) dbg;
                    bg = bit.getBitmap();
                    break;
                case R.styleable.MyView_name:
                    name = ty.getString(index);
                    break;

                case R.styleable.MyView_text_size:
                    text_size =  ty.getDimension(index,10.0f);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint pa = new Paint();
        pa.setTextSize(text_size);
        canvas.drawText(name + "---", 50, 50, pa);
        canvas.drawBitmap(bg, 50, 50, pa);
    }
}
