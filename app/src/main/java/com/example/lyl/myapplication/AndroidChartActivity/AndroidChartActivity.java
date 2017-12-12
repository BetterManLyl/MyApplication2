package com.example.lyl.myapplication.AndroidChartActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.bean.Liebiao;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lyl
 * @date 2017/11/22.
 */

@SuppressWarnings("ALL")
public class AndroidChartActivity extends AppCompatActivity {
    private LineChart mLineChart;
    //X轴
    XAxis xAxis;
    YAxis yAxis;
    private LimitLine limitLine;

    private Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_chart);
        initView();
        initData();
    }

    private void initView() {
        mLineChart = (LineChart) findViewById(R.id.lineChart);//显示边界
        button = (Button) findViewById(R.id.more_chart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AndroidChartActivity.this, MoreLinechartActivity.class));
            }
        });
        //显示边界
        mLineChart.setDrawBorders(false);

    }

    /**
     * 设置数据
     */
    private void initData() {
        setAnimation();
        setLineChat();
        setxAxis();
        setYAyis();
        setLengend();
        setDescription();
        setMarkerView();

        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            entries.add(new Entry(i, 20.2f + i));
        }
        List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            entries2.add(new Entry(i, 30.2f + i));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "温度");
        LineDataSet lineDataSet2 = new LineDataSet(entries2, "流量");
        LineData data = new LineData(lineDataSet);

        LineData data2 = new LineData(lineDataSet2);
        //设置曲线值的圆点是实心还是空心
        lineDataSet2.setDrawCircleHole(false);
        lineDataSet2.setCircleColor(Color.RED);
        //设置字体大小
        lineDataSet2.setValueTextSize(8f);
        //线模式为圆滑曲线（默认折线）
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        mLineChart.setData(data);
        mLineChart.setData(data2);
    }

    /**
     * 设置动画效果
     */
    public void setAnimation() {
        mLineChart.animateX(1000, Easing.EasingOption.Linear);
        mLineChart.animateY(1000, Easing.EasingOption.Linear);
    }


    private List<String> mList = new ArrayList<>();

    /**
     * 设置图表属性
     */
    public void setLineChat() {
        //打开或关闭对图表所有轴的的缩放。
        mLineChart.setScaleEnabled(false);
        //打开或关闭对图表的拖动。
        mLineChart.setDragEnabled(false);

        //设置为true时允许高亮显示拖动结束的对象在缩放到最下时。默认：true
        mLineChart.setHighlightPerDragEnabled(true);


    }

    /**
     * 设置限制线
     */
    public void setLimitLine() {
        limitLine = new LimitLine(60, "高限制线");
        limitLine.setLineWidth(2f);
        limitLine.setTextSize(20f);
        limitLine.setTextColor(Color.RED);  //颜色
        limitLine.setLineColor(Color.BLUE);
    }

    /**
     * 设置底部说明  文字颜色  大小  位置
     */
    public void setLengend() {
        Legend legend = mLineChart.getLegend();
        legend.setTextSize(20f);
        legend.setTextColor(Color.BLUE);
        //设置标签是否换行（当多条标签时 需要换行显示\）
        legend.setWordWrapEnabled(true);
        //隐藏Lengend  true 显示  false 不显示
        legend.setEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
    }

    /**
     * 设置X轴属性
     */
    public void setxAxis() {

        for (int i = 0; i < 30; i++) {
            mList.add("i" + i);
        }

        //获取X轴对象
        xAxis = mLineChart.getXAxis();
        //设置X轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        /**
         * //设置X轴有多少个点
         //第二个参数表示是否平均分配 如果为true则按比例分为12个点、如果为false则适配X刻度的值来分配点，可能没有12个点。
         */
        xAxis.setLabelCount(20, true);

        //设置底部轴线的颜色
        xAxis.setTextColor(Color.RED);

        //设置折线的颜色
        xAxis.setGridColor(Color.BLUE);
        /**
         * 设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
         */
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(30f);

        //设置x轴是否显示网格线
        xAxis.setDrawGridLines(false);
        //设置轴是否被绘制。默认绘制,false不会被绘制。
        xAxis.setEnabled(true);
        //置为true打开绘制轴的标签。底部x轴的值
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(true);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });
    }

    /**
     * 描述
     */
    public void setDescription() {

        Description description = new Description();
        description.setTextColor(Color.RED);
        //设置描述内容
        description.setText("X轴描述");
        mLineChart.setDescription(description);
    }

    /**
     * 设置显示markview
     */
    public void setMarkerView() {
        MyMarkerView markerView = new MyMarkerView(this);
        mLineChart.setMarkerView(markerView);
    }

    /**
     * 设置
     */
    public void setYAyis() {
        /**
         * 获取y轴线   两条
         */
        YAxis leftYAxis = mLineChart.getAxisLeft();
        //设置y轴数值颜色
        leftYAxis.setTextColor(Color.GREEN);
        leftYAxis.setGridColor(Color.LTGRAY);
        YAxis rightYAxis = mLineChart.getAxisRight();
        //设置右侧y轴线不显示
        rightYAxis.setEnabled(false);
        /**
         * 设置最大值和最小值
         */
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(100f);
        rightYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMaximum(100f);
        setLimitLine();
        leftYAxis.addLimitLine(limitLine); //Y轴添加限制线
    }


    /**
     * 自定义MarkerView
     */
    public class MyMarkerView extends MarkerView {

        private TextView tvContent;
        private DecimalFormat format = new DecimalFormat("##0.0");

        public MyMarkerView(Context context) {
            super(context, R.layout.layout_markerview);
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            tvContent.setText(format.format(e.getY()));
            super.refreshContent(e, highlight);
        }

        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
        }
    }
}
