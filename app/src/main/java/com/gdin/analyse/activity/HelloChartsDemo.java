package com.gdin.analyse.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.gdin.analyse.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class HelloChartsDemo extends BaseAppCompatActivity {


    @BindView(R.id.pie)
    PieChartView pieView;
    private PieChartData mPieChartData;                 //饼状图数据


    @Override
    protected int getLayoutId() {
        return R.layout.chart_demo;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("饼状图");
        init();
    }

    private void init() {
        initData();
        initListener();
    }


    private void initListener() {
        pieView.setOnValueTouchListener(new ValueTouchListener());
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
        mPieChartData.setHasLabels(true);       //内部有标签
        mPieChartData.setHasLabelsOnlyForSelected(false);    //true为仅点击时显示标签
        mPieChartData.setHasLabelsOutside(false);   //外部有标签
        mPieChartData.setHasCenterCircle(false);    //空心圆环

//        mPieChartData.setCenterText1("Hello");             //单行文本内容
//        mPieChartData.setSlicesSpacing(18);                 //分离间距为18
//
//        //是否显示双行文本
//        if (isHasCenterDoubleText) {
//            mPieChartData.setCenterText2("World");             //文本内容
//            mPieChartData.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
//                    (int) getResources().getDimension(R.dimen.pie_chart_double_text_size)));
//        }
        pieView.setPieChartData(mPieChartData);         //设置控件
    }

    /**
     * 改变数据时的动画
     */
    private void changePiesAnimate() {
        //随机设置值
        for (SliceValue value : mPieChartData.getValues()) {
            value.setTarget((float) Math.random() * 30 + 15);
        }
        pieView.startDataAnimation();         //设置动画
    }


    /**
     * 每部分点击监听
     */
    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(HelloChartsDemo.this, "当前选中块约占: " + (int) value.getValue() + " %", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {

        }
    }

}
