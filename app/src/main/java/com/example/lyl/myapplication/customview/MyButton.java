package com.example.lyl.myapplication.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.example.lyl.myapplication.R;

/**
 * @author lyl
 * @date 2017/12/19.
 */

public class MyButton extends View {
    private Rect mBound;
    private Paint paint;
    private String texts = "123";

    private String name;
    private int textsize;
    private int color;


    public MyButton(Context context, AttributeSet attrs) {
        //需要使用this调用
        this(context, attrs, 0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        for (int i = 0; i < ty.getIndexCount(); i++) {
            int attr = ty.getIndex(i);
            switch (attr) {
                case R.styleable.MyView_name:
                    name = ty.getString(i);
                    break;
                case R.styleable.MyView_text_size:
                    textsize = ty.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyView_bg:
                    color = ty.getColor(attr, Color.BLUE);
                    break;
                default:
                    break;
            }
        }
        ty.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initPaint();

        initCanvas(canvas);

        drawRect(canvas);


        drawRoundRect(canvas);

    }

    /**
     * 绘制圆角矩形
     * @param canvas
     */
    private void drawRoundRect(Canvas canvas) {
        RectF re=new RectF(dp2px(150),dp2px(50),dp2px(250),dp2px(100));
        canvas.drawRoundRect(re,30,30,paint);
    }

    /**
     * 绘制画笔
     */
    public void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        paint.setStrokeWidth(10f);         //设置画笔宽度为10px
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(textsize);
        paint.setColor(color);
        mBound = new Rect();
        paint.getTextBounds(name, 0, name.length(), mBound);
    }

    /**
     * 绘制画布
     *
     * @param canvas
     */
    public void initCanvas(Canvas canvas) {
        //绘制颜色是填充整个画布，常用于绘制底色。
        canvas.drawColor(Color.WHITE);
        //绘制文本
        canvas.drawText(name, getWidth(), getHeight() / 4, paint);
        paint.setColor(Color.BLACK);
        //画圆点
        canvas.drawCircle(20, 20, 5, paint);
        //字体的粗细设置
        paint.setStrokeWidth(2f);
        //画圆的集合
        canvas.drawLine(30, 20, 100, 100, paint);
    }


    /**
     * 绘制矩形
     * @param canvas 画布
     */
    public void drawRect(Canvas canvas){
// 第二种
//        Rect rect = new Rect(100,100,800,400);
//        canvas.drawRect(rect,paint);
        Rect re=new Rect(dp2px(50),dp2px(50),dp2px(100),dp2px(100));
        canvas.drawRect(re,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);

    }

    /**
     * 单位转换
     * @param value
     * @return
     */
    public int dp2px(float value){
        return ConvertUtils.dp2px(value);
    }
}
