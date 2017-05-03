package com.gdin.analyse.fragment;


import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.fragment.charts.PieChartCardView;

import java.util.ArrayList;

public class ClassChartFragment extends BaseFragment{

    PieChartCardView pieCard;

    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.chart_demo,null);
    }

    @Override
    public void initData(View view) {
        pieCard = (PieChartCardView) view.findViewById(R.id.pie_card);
        pieCard.setData(getArguments().getIntegerArrayList("data"));
        pieCard.postInvalidate();
    }

    public static ClassChartFragment newInstance(ArrayList<Integer> data) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("data",data);
        ClassChartFragment fragment = new ClassChartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
