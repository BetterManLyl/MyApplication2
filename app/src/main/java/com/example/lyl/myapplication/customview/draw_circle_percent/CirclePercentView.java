package com.example.lyl.myapplication.customview.draw_circle_percent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * @author lyl
 * @date 2017/12/19.
 * 制作一个饼状图
 * 其实根据我们上面的知识已经能自己制作一个饼状图了。不过制作东西最重要的不是制作结果，而是制作思路。
 * 相信我贴上代码大家一看就立刻明白了，非常简单的东西。不过嘛，咱们还是想了解一下制作思路：先分析饼状图的构成，
 * 非常明显，饼状图就是一个又一个的扇形构成的，每个扇形都有不同的颜色，对应的有名字，数据和百分比。
 * 经以上信息可以得出饼状图的最基本数据应包括：名字 数据值 百分比 对应的角度 颜色。
 */

public class CirclePercentView extends View {

    /**
     * 颜色值
     */
    private int[] colors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    //初始角度
    private float mStartAngle = 0;
    //数据集合
    private ArrayList<Model> mData = new ArrayList<>();
    //宽高
    private int mWidth, mHeight;

    private Paint paint = new Paint();

    public CirclePercentView(Context context) {
        this(context, null);
        init();
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;

    }

    private void init() {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setData(mData);
        if (null == mData) {
            return;
        }
        // 当前起始角度
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);                // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        RectF rect = new RectF(-r, -r, r, r);                     // 饼状图绘制区域

        for (int i = 0; i < mData.size(); i++) {
            Model pie = mData.get(i);
            paint.setColor(pie.color);
            canvas.drawArc(rect, currentStartAngle, pie.angle, true, paint);
            currentStartAngle += pie.angle;
        }

    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }

    // 设置数据
    public void setData(ArrayList<Model> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();   // 刷新
    }

    // 初始化数据
    private void initData(ArrayList<Model> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            Model pie = mData.get(i);

            sumValue += pie.value;       //计算数值和

            int j = i % colors.length;       //设置颜色
            pie.setColor(colors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            Model pie = mData.get(i);

            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;

            Log.i("angle", "" + pie.getAngle());
        }
    }
}
