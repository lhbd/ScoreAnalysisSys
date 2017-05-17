package com.gdin.analyse.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.fragment.StudentRollFragment;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public  class PieChartCardView extends BaseFrameLayout {

    private final static int SUM_SCORE = 0;
    private final static int CH_SCORE = 1;
    private final static int MATH_SCORE = 2;
    private final static int EN_SCORE = 3;
    PieChartView pieView;
    TextView pieLabel;
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
        pieLabel = (TextView)view.findViewById(R.id.pie_label);
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

    public void setData(List<Integer> data,int type) {
        if (data == null || data.size() < 1)
            return;
        switch (type){
            case SUM_SCORE:
                pieLabel.setText("总分排名");
                break;
            case CH_SCORE:
                pieLabel.setText("语文排名");
                break;
            case MATH_SCORE:
                pieLabel.setText("数学排名");
                break;
            case EN_SCORE:
                pieLabel.setText("英语排名");
                break;
        }
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
        changePiesAnimate();
    }


    private void changePiesAnimate() {
        for (SliceValue value : mPieChartData.getValues()) {
            value.setTarget((float) Math.random() * 30 + 15);
        }
        pieView.startDataAnimation();
    }

    /**
     * 每部分点击监听
     */
    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            requestDisallowInterceptTouchEvent(false);
            ((ClassResultActivity)getContext()).addFragment(StudentRollFragment.newInstance(),2);
        }

        @Override
        public void onValueDeselected() {

        }
    }


}
