package com.gdin.analyse.chart;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gdin.analyse.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
/**
 * @author lecho
 * @revision xiarui 2016.09.07
 * @description 线性图 Line Chart 的使用 （折线图、曲线图）
 */
public class LineChartCardView extends BaseFrameLayout {

    /*=========== 控件相关 ==========*/
    private LineChartView mLineChartView;               //线性图表控件
    private TextView lineLabel;

    /*=========== 数据相关 ==========*/
    private LineChartData mLineData;                    //图表数据
    private int numberOfLines = 1;                      //图上折线/曲线的显示条数
    private int maxNumberOfLines = 4;                   //图上折线/曲线的最多条数
    private int numberOfPoints = 12;                    //图上的节点数

    /*=========== 状态相关 ==========*/
    private boolean isHasAxes = true;                   //是否显示坐标轴
    private boolean isHasAxesNames = true;              //是否显示坐标轴名称
    private boolean isHasLines = true;                  //是否显示折线/曲线
    private boolean isHasPoints = true;                 //是否显示线上的节点
    private boolean isFilled = false;                   //是否填充线下方区域
    private boolean isHasPointsLabels = false;          //是否显示节点上的标签信息
    private boolean isCubic = false;                    //是否是立体的
    private boolean isPointsHasSelected = false;        //设置节点点击后效果(消失/显示标签)

    /*=========== 其他相关 ==========*/
    private ValueShape pointsShape = ValueShape.CIRCLE; //点的形状(圆/方/菱形)
    float[] pointValues; //将线上的点放在一个数组中

    public LineChartCardView(Context context) {
        super(context);
    }

    public LineChartCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChartCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initView()
    {
        View view = View.inflate(getContext(), R.layout.line_cardview_layout, null);
        addView(view);
        mLineChartView = (LineChartView) view.findViewById(R.id.line);
        lineLabel = (TextView)view.findViewById(R.id.line_label);
        /**
         * 禁用视图重新计算 主要用于图表在变化时动态更改，不是重新计算
         * 类似于ListView中数据变化时，只需notifyDataSetChanged()，而不用重新setAdapter()
         */
        mLineChartView.setViewportCalculationEnabled(false);
    }

    public void setData(float[] pointValues) {
        this.pointValues = pointValues;
//        setPointsValues();          //设置每条线的节点值
        setLinesDatas();            //设置每条线的一些属性
        resetViewport();            //计算并绘图
    }

    @Override
    public void initListener() {
        //节点点击事件监听
        mLineChartView.setOnValueTouchListener(new ValueTouchListener());
    }

    /**
     * 设置线的相关数据
     */
    private void setLinesDatas() {
        if (pointValues == null || pointValues.length == 0)
            return;
        List<Line> lines = new ArrayList<>();
        //循环将每条线都设置成对应的属性
        for (int i = 0; i < numberOfLines; ++i) {
            //节点的值
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j <  pointValues.length; ++j) {
                values.add(new PointValue(j, pointValues[j]));
            }

            /*========== 设置线的一些属性 ==========*/
            Line line = new Line(values);               //根据值来创建一条线
            line.setColor(ChartUtils.COLORS[3]);        //设置线的颜色
            line.setShape(pointsShape);                 //设置点的形状
            line.setHasLines(isHasLines);               //设置是否显示线
            line.setHasPoints(isHasPoints);             //设置是否显示节点
            line.setCubic(isCubic);                     //设置线是否立体或其他效果
            line.setFilled(isFilled);                   //设置是否填充线下方区域
            line.setHasLabels(isHasPointsLabels);       //设置是否显示节点标签
            //设置节点点击的效果
            line.setHasLabelsOnlyForSelected(isPointsHasSelected);
            //节点与线有不同颜色
            line.setPointColor(ChartUtils.COLORS[3]);
            lines.add(line);
        }

        mLineData = new LineChartData(lines);                      //将所有的线加入线数据类中
        mLineData.setBaseValue(Float.NEGATIVE_INFINITY);           //设置基准数(大概是数据范围)
        /* 其他的一些属性方法 可自行查看效果
         * mLineData.setValueLabelBackgroundAuto(true);            //设置数据背景是否跟随节点颜色
         * mLineData.setValueLabelBackgroundColor(Color.BLUE);     //设置数据背景颜色
         * mLineData.setValueLabelBackgroundEnabled(true);         //设置是否有数据背景
         * mLineData.setValueLabelsTextColor(Color.RED);           //设置数据文字颜色
         * mLineData.setValueLabelTextSize(15);                    //设置数据文字大小
         * mLineData.setValueLabelTypeface(Typeface.MONOSPACE);    //设置数据文字样式
        */

        //如果显示坐标轴
        if (isHasAxes) {
            Axis axisX = new Axis();                    //X轴
            Axis axisY = new Axis().setHasLines(true);  //Y轴
            axisX.setTextColor(Color.GRAY);             //X轴灰色
            axisY.setTextColor(Color.GRAY);             //Y轴灰色
            //setLineColor()：此方法是设置图表的网格线颜色 并不是轴本身颜色
            //如果显示名称
            if (isHasAxesNames) {
                axisX.setName("Axis X");                //设置名称
                axisY.setName("Axis Y");
            }
            mLineData.setAxisXBottom(axisX);            //设置X轴位置 下方
            mLineData.setAxisYLeft(axisY);              //设置Y轴位置 左边
        } else {
            mLineData.setAxisXBottom(null);
            mLineData.setAxisYLeft(null);
        }

        mLineChartView.setLineChartData(mLineData);    //设置图表控件
        changeLinesAnimate();
//        dynamicDataDisplay();
    }

    /**
     * 重点方法，计算绘制图表
     */
    private void resetViewport() {
        //创建一个图标视图 大小为控件的最大大小
        final Viewport v = new Viewport(mLineChartView.getMaximumViewport());
        v.left = 0;                             //坐标原点在左下
        v.bottom = 0;
        v.top = 100;                            //最高点为100
        v.right = numberOfPoints - 1;           //右边为点 坐标从0开始 点号从1 需要 -1
        mLineChartView.setMaximumViewport(v);   //给最大的视图设置 相当于原图
        mLineChartView.setCurrentViewport(v);   //给当前的视图设置 相当于当前展示的图
    }

    /**
     * 线条改变时的动画
     */
    private void changeLinesAnimate() {
        //增强for循环改变线条数据
        for (Line line : mLineData.getLines()) {
            for (PointValue value : line.getValues()) {
                value.setTarget(value.getX(), (float) Math.random() * 100);         //更改X坐标
                //value.setTarget(value.getY(), (float) Math.random() * 100);       //或者更改Y坐标
            }
        }
        mLineChartView.startDataAnimation(); //开始动画
    }

    public TextView getLineLabel() {
        return lineLabel;
    }

    /**
     * 节点触摸监听
     */
    private class ValueTouchListener implements LineChartOnValueSelectListener {
        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getContext(), "选中第 " + (value.getX() + 1) + " 个节点", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {

        }
    }

    /**
     * 线条改变动画监听
     */
    private class ChangeLinesAnimListener implements ChartAnimationListener {

        private Viewport v;

        public ChangeLinesAnimListener(Viewport v) {
            this.v = v;
        }

        @Override
        public void onAnimationStarted() {
        }

        @Override
        public void onAnimationFinished() {
            mLineChartView.setMaximumViewport(v);                   //设置最大视图
            mLineChartView.setViewportAnimationListener(null);      //取消监听
        }
    }
}
