package com.gdin.analyse.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.chart.LineDependColumnCardView;

public class ClassChartDetailFragment extends BaseFragment{

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
        if (sp == null){
            sp = ((ClassResultActivity)getActivity()).getSp();
        }
        LineDependColumnCardView lcc = (LineDependColumnCardView)view.findViewById(R.id.lcc_card);
        lcc.setSubjectsValues(getArguments().getFloatArray("subjectValues"));
        lcc.postInvalidate();
    }

    public static ClassChartDetailFragment newInstance(float[] subjectValues) {

        Bundle bundle = new Bundle();
        bundle.putFloatArray("subjectValues",subjectValues);
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
