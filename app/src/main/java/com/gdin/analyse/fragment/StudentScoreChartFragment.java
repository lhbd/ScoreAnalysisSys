package com.gdin.analyse.fragment;

import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.chart.LineChartCardView;
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.List;

public class StudentScoreChartFragment extends BaseFragment {

    LineChartCardView lineChartCardView;

    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.student_result_chart_layout, null);
    }

    @Override
    public void initData(View view) {
        lineChartCardView = (LineChartCardView)view.findViewById(R.id.line_card);
        lineChartCardView.setData();
        lineChartCardView.postInvalidate();
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity) getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("成绩分析图");
        activity.showItem(false);
    }

    private void getExamData() {
        HttpMethods.getInstance().getExam(new cSubscriber<HttpResult<List<ExamDataEntity>>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<List<ExamDataEntity>> result, int i) {
            }
        }, new ExamDataEntity(CustomApplication.getSchoolId(), CustomApplication.getGradeId(), CustomApplication.getClassId()));
    }

    public static StudentScoreChartFragment newInstance() {
        Bundle bundle = new Bundle();
        StudentScoreChartFragment fragment = new StudentScoreChartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
