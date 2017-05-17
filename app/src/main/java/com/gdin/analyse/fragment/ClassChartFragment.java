package com.gdin.analyse.fragment;


import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.chart.LineChartCardView;
import com.gdin.analyse.chart.PieChartCardView;

import java.util.ArrayList;

public class ClassChartFragment extends BaseFragment{

    PieChartCardView pieCard;
    LineChartCardView lineCard;
    private int type;

    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.class_result_chart_layout,null);
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity)getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("班级成绩分析图");
        activity.resetMenuItem(-1);
        activity.showBack(true);
        if (activity.getDetailType() == type)
            return;
        setFirst(true);
        this.type = activity.getDetailType();

    }

    @Override
    public void initData(View view) {
        pieCard = (PieChartCardView) view.findViewById(R.id.pie_card);

        lineCard = (LineChartCardView)view.findViewById(R.id.line_card);

        updateView();
    }

    public static ClassChartFragment newInstance() {

        Bundle args = new Bundle();

        ClassChartFragment fragment = new ClassChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void updateView(){
        pieCard.setData(getRankData(),type);
        pieCard.postInvalidate();
        lineCard.setData(type);
        lineCard.postInvalidate();
    }

    public ArrayList<Integer> getRankData() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(15);
        data.add(21);
        return data;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getArguments().clear();
    }
}
