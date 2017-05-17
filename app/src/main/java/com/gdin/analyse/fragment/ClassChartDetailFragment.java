package com.gdin.analyse.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.chart.LineDependColumnChartCardView;

import java.util.ArrayList;

public class ClassChartDetailFragment extends BaseFragment{

    LineDependColumnChartCardView lcc;
    private int type;
    SharedPreferences sp;

    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.class_result_detail_chart_layout,null);
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity)getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("班级成绩分析图");
        activity.resetMenuItem(1);
        activity.showBack(false);
        if (activity.getDetailType() == type)
            return;
        setFirst(true);
        this.type = activity.getDetailType();

    }

    @Override
    public void initData(View view) {
        sp = ((ClassResultActivity)getActivity()).getSp();
        lcc = (LineDependColumnChartCardView)view.findViewById(R.id.lcc_card);
        lcc.setSubjectsValues(getArguments().getIntegerArrayList("subjectValues"));
        lcc.postInvalidate();
    }

    public static ClassChartDetailFragment newInstance(ArrayList<Integer> subjectValues) {

        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("subjectValues",subjectValues);
        ClassChartDetailFragment fragment = new ClassChartDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getArguments().clear();
    }
}
