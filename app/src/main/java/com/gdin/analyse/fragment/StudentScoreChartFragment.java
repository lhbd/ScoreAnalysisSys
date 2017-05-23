package com.gdin.analyse.fragment;

import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.chart.LineDependColumnCardView;
import com.gdin.analyse.chart.PolygonsView;

public class StudentScoreChartFragment extends BaseFragment {

    private LineDependColumnCardView ldc;
    private PolygonsView polygonsView;

    private float[] subjectsValues;
    private int sChartType=-1;

    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.student_result_chart_layout, null);
    }

    @Override
    public void initData(View view) {
        polygonsView = (PolygonsView)view.findViewById(R.id.poly_card_view);
        ldc = (LineDependColumnCardView)view.findViewById(R.id.ldc_card);

        if (subjectsValues == null)
            return;
        ldc.setSubjectsValues(subjectsValues);
        ldc.postInvalidate();
        updatePolygonsView();

    }
    private void updatePolygonsView(){
        polygonsView.setChValue(getValue(subjectsValues[1]));
        polygonsView.setMathValue(getValue(subjectsValues[2]));
        polygonsView.setEnValue(getValue(subjectsValues[3]));
        polygonsView.postInvalidate();
    }

    private float getValue(float value){
        if (value<10){
            return (value*10/25)<1?1:(value*10/25);
        }else{
            return (value/25)<1?1:(value/25);
        }
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity) getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("成绩分析图");
        activity.resetMenuItem(-1);
        activity.showBack(true);
        if (activity.getsChartType() == sChartType)
            return;
        setFirst(true);
        this.sChartType = activity.getsChartType();
        this.subjectsValues = activity.getSubjectsValues();
    }

    public static StudentScoreChartFragment newInstance() {
        Bundle bundle = new Bundle();
        StudentScoreChartFragment fragment = new StudentScoreChartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
