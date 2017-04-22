package com.gdin.analyse.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.gdin.analyse.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * @author lecho
 * @revision xiarui 2016.09.07
 * @description 饼状图 Pie Chart 的使用
 */
public class PieChartFrameLayout extends FrameLayout {


    PieChartView mPieChartFrameLayout;
    /*========= 数据相关 =========*/
    private PieChartData mPieChartData;                 //饼状图数据

    private OnValueSelected listener;
    public void addOnValueSelectedListener(OnValueSelected listener){
        this.listener = listener;
    }
    public interface OnValueSelected {
        void onValueSelected(SliceValue value);
    }


    public PieChartFrameLayout(Context context) {
        super(context);
        init();
    }

    public PieChartFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieChartFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        View view = inflate(getContext(),R.layout.pie_chart_layout,this);
        this.mPieChartFrameLayout = (PieChartView)view.findViewById(R.id.pie);
        initData();
        initListener();
        addView(view);
    }


    private void initListener() {
        mPieChartFrameLayout.setOnValueTouchListener(new ValueTouchListener());
    }

    /**
     * 设置相关数据
     */
    private void initData() {

        int numValues = 6;                //把一张饼切成6块

        /*===== 随机设置每块的颜色和数据 =====*/
        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            values.add(sliceValue);
        }

        /*===== 设置相关属性 类似Line Chart =====*/
        mPieChartData = new PieChartData(values);
        mPieChartData.setHasLabels(false);       //内部有标签
        mPieChartData.setHasLabelsOnlyForSelected(false);    //true为仅点击时显示标签
        mPieChartData.setHasLabelsOutside(false);   //外部有标签
        mPieChartData.setHasCenterCircle(false);    //空心圆环

        mPieChartData.setCenterText1("Hello");             //单行文本内容
        mPieChartData.setSlicesSpacing(18);                 //分离间距为18
//
//        //是否显示双行文本
//        if (isHasCenterDoubleText) {
//            mPieChartData.setCenterText2("World");             //文本内容
//            mPieChartData.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
//                    (int) getResources().getDimension(R.dimen.pie_chart_double_text_size)));
//        }
        mPieChartFrameLayout.setPieChartData(mPieChartData);         //设置控件
    }

    /**
     * 改变数据时的动画
     */
    private void changePiesAnimate() {
        //随机设置值
        for (SliceValue value : mPieChartData.getValues()) {
            value.setTarget((float) Math.random() * 30 + 15);
        }
        mPieChartFrameLayout.startDataAnimation();         //设置动画
    }


    /**
     * 每部分点击监听
     */
    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
//            Toast.makeText(PieChartActivity.this, "当前选中块约占: " + (int) value.getValue() + " %", Toast.LENGTH_SHORT).show();
            if (listener!=null){
                listener.onValueSelected(value);
            }
        }

        @Override
        public void onValueDeselected() {
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
