package com.gdin.analyse.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.chart.LineChartCardView;
import com.gdin.analyse.chart.PieChartCardView;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.StudentRollRequestEntity;
import com.gdin.analyse.entity.StudentScoreResultEntity;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.ArrayList;
import java.util.List;

public class ClassChartFragment extends BaseFragment{

    private final static int SUM_SCORE = 0;
    private final static int CH_SCORE = 1;
    private final static int MATH_SCORE = 2;
    private final static int EN_SCORE = 3;

    private PieChartCardView pieCard;
    private LineChartCardView lineCard;
    private int type;
    private SharedPreferences sp;
    private List<StudentScoreResultEntity> allScoreDatas = new ArrayList<>();
    private float[] pointValues;


    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.class_result_chart_layout,null);
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity)getActivity();
        if (activity == null)
            return;
        activity.resetMenuItem(-1);
        activity.showBack(true);
        if (activity.getDetailType() == type)
            return;
        setFirst(true);
        this.type = activity.getDetailType();
        updateView();
    }

    @Override
    public void initData(View view) {
        if (sp == null){
            sp = ((ClassResultActivity)getActivity()).getSp();
        }

        pieCard = (PieChartCardView) view.findViewById(R.id.pie_card);

        lineCard = (LineChartCardView)view.findViewById(R.id.line_card);

        if (allScoreDatas == null || allScoreDatas.size()==0){
            getAllStudentScoreData();
        }
    }

    private void getAllStudentScoreData() {
        HttpMethods.getInstance().getStudentRoll(new cSubscriber<HttpResult<List<StudentScoreResultEntity>>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<List<StudentScoreResultEntity>> result, int i) {
                allScoreDatas.addAll(result.getData());
                updateView();
            }
        }, new StudentRollRequestEntity(sp.getInt("loginSchoolId",0),CustomApplication.getExamName(),sp.getInt("loginGradeId",0),
                sp.getInt("loginClassId",0)));
    }

    private void updateView(){
        if (allScoreDatas == null || allScoreDatas.size()==0)
            return;
        if (pointValues == null){
            pointValues = new float[allScoreDatas.size()];
        }

        switch (type){
            case SUM_SCORE:
                lineCard.getLineLabel().setText("总分得分详情");
                for (int i=0;i<allScoreDatas.size();i++){
                    pointValues[i] = allScoreDatas.get(i).getSumScore();
                }
                break;
            case CH_SCORE:
                lineCard.getLineLabel().setText("语文得分详情");
                for (int i=0;i<allScoreDatas.size();i++){
                    pointValues[i] = allScoreDatas.get(i).getChScore();
                }
                break;
            case MATH_SCORE:
                lineCard.getLineLabel().setText("数学得分详情");
                for (int i=0;i<allScoreDatas.size();i++){
                    pointValues[i] = allScoreDatas.get(i).getMathScore();
                }
                break;
            case EN_SCORE:
                lineCard.getLineLabel().setText("英语得分详情");
                for (int i=0;i<allScoreDatas.size();i++){
                    pointValues[i] = allScoreDatas.get(i).getEnScore();
                }
                break;
        }
        pieCard.setData(getRankData(),type);
        pieCard.postInvalidate();
        lineCard.setData(pointValues);
        lineCard.postInvalidate();
    }
    public ArrayList<Integer> getRankData() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(15);
        data.add(21);
        return data;
    }


    public static ClassChartFragment newInstance() {

        Bundle args = new Bundle();

        ClassChartFragment fragment = new ClassChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getArguments().clear();
    }
}
