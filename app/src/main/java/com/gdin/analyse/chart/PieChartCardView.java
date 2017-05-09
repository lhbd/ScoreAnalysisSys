package com.gdin.analyse.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public  class PieChartCardView extends BaseFrameLayout {

    PieChartView pieView;
    //    PieChartView pieView;
    private PieChartData mPieChartData;                 //饼状图数据



    public PieChartCardView(Context context) {
        super(context);
    }

    public PieChartCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        View view = View.inflate(getContext(), R.layout.pie_cardview_layout, null);
        addView(view);
        initText(view);
        pieView = (PieChartView) view.findViewById(R.id.pie);

    }

    @Override
    protected void initListener() {
        pieView.setOnValueTouchListener(new ValueTouchListener());
    }

    private void initText(View view){
        TextView top10Point = (TextView) view.findViewById(R.id.top10_point);
        TextView top50Point = (TextView) view.findViewById(R.id.top50_point);
        TextView otherPoint = (TextView) view.findViewById(R.id.other_point);
        top10Point.setTextColor(ChartUtils.COLORS[0]);
        top50Point.setTextColor(ChartUtils.COLORS[2]);
        otherPoint.setTextColor(ChartUtils.COLORS[4]);
    }

    public void setData(List<Integer> data) {
        if (data == null || data.size() < 1)
            return;
        /*===== 随机设置每块的颜色和数据 =====*/
        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            SliceValue sliceValue = new SliceValue((float) data.get(i), ChartUtils.COLORS[i*2]);
            values.add(sliceValue);
        }

        /*===== 设置相关属性 类似Line Chart =====*/
        mPieChartData = new PieChartData(values);
        mPieChartData.setHasLabels(true);       //内部有标签
        mPieChartData.setHasLabelsOnlyForSelected(false);    //true为仅点击时显示标签
        mPieChartData.setHasLabelsOutside(false);   //外部有标签
        mPieChartData.setHasCenterCircle(false);    //空心圆环
        pieView.setPieChartData(mPieChartData);         //设置控件
    }

    /**
     * 每部分点击监听
     */
    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            requestDisallowInterceptTouchEvent(false);
            ((ClassResultActivity)getContext()).addStudentRollFragment();
        }

        @Override
        public void onValueDeselected() {

        }
    }


}
