package com.gdin.analyse.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.charts.PieChartFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.SliceValue;

public class HelloChartsDemo extends BaseAppCompatActivity {


    @BindView(R.id.pie_view)
    PieChartFrameLayout pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation(BaseAppCompatActivity.FADE);
        getToolbarTitle().setText("饼状图");
        init();
    }

    private void init() {
        pieView.addOnValueSelectedListener(new PieChartFrameLayout.OnValueSelected() {
            @Override
            public void onValueSelected(SliceValue value) {
                Toast.makeText(HelloChartsDemo.this, "当前选中块约占: " + (int) value.getValue() + " %", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.chart_demo;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }
}
