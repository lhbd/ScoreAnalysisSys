package com.gdin.analyse.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentScoreDetailFragment extends BaseFragment {


    @BindView(R.id.s_exam_name_value)
    TextView sExamNameValue;
    @BindView(R.id.grade_class_value)
    TextView gradeClassValue;
    @BindView(R.id.s_whole_score_value)
    TextView sWholeScoreValue;
    @BindView(R.id.s_ch_score_value)
    TextView sChScoreValue;
    @BindView(R.id.s_math_score_value)
    TextView sMathScoreValue;
    @BindView(R.id.s_en_score_value)
    TextView sEnScoreValue;
    @BindView(R.id.s_whole_g_rank_value)
    TextView sWholeGRankValue;
    @BindView(R.id.s_ch_g_rank_value)
    TextView sChGRankValue;
    @BindView(R.id.s_math_g_rank_value)
    TextView sMathGRankValue;
    @BindView(R.id.s_en_g_rank_value)
    TextView sEnGRankValue;
    @BindView(R.id.s_whole_c_rank_value)
    TextView sWholeCRankValue;
    @BindView(R.id.s_ch_c_rank_value)
    TextView sChCRankValue;
    @BindView(R.id.s_math_c_rank_value)
    TextView sMathCRankValue;
    @BindView(R.id.s_en_c_rank_value)
    TextView sEnCRankValue;

    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.s_score_layout, null);
    }

    @Override
    public void initData(View view) {
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity) getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("成绩一览");
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

    public static StudentScoreDetailFragment newInstance() {
        Bundle bundle = new Bundle();
        StudentScoreDetailFragment fragment = new StudentScoreDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.s_score_detail_label, R.id.s_g_rank_detail_label, R.id.s_c_rank_detail_label})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.s_score_detail_label:
                ((ClassResultActivity)getContext()).addStudentScoreChartFragment();
                break;
            case R.id.s_g_rank_detail_label:
                ((ClassResultActivity)getContext()).addStudentScoreChartFragment();
                break;
            case R.id.s_c_rank_detail_label:
                ((ClassResultActivity)getContext()).addStudentScoreChartFragment();
                break;
            case R.id.s_select_exam:
                getExamData();
                break;
        }
    }
}
