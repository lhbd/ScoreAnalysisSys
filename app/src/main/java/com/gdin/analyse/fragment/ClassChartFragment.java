package com.gdin.analyse.fragment;


import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.chart.PieChartCardView;

import java.util.ArrayList;

public class ClassChartFragment extends BaseFragment{

    PieChartCardView pieCard;

    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.class_result_chart_layout,null);
    }

    @Override
    public void initData(View view) {
        pieCard = (PieChartCardView) view.findViewById(R.id.pie_card);
        pieCard.setData(getArguments().getIntegerArrayList("data"));
        pieCard.postInvalidate();
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity)getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("班级成绩分析图");
        activity.showItem(false);
    }

    public static ClassChartFragment newInstance(ArrayList<Integer> data) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("data",data);
        ClassChartFragment fragment = new ClassChartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
